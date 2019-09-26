package com.demo.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("file")
public class FileUploadController {
	private static Logger log = LoggerFactory.getLogger(FileUploadController.class);
	@RequestMapping(value = "/upload", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public String fileUpload(@RequestParam("file[]") List<MultipartFile> files) throws IOException {
		log.info("get file process...");
		files.stream().forEach(file -> {
			if (!file.isEmpty()) {
			File convertFile = new File("C:/demo/files/" + file.getOriginalFilename());
				try {
					convertFile.createNewFile();
					FileOutputStream fout = new FileOutputStream(convertFile);
					fout.write(file.getBytes());
					fout.close();
				} catch (IOException e) {
//					e.printStackTrace();
					log.error(e.getMessage(), e);
				}
			}
		});
//		return "File is upload successfully";
		return "{\"success\":\"true\",\"content\":{\"url\":\"http://10.8.110.51/{IFDPortal}/login.aspx?transaction=00637003198657938426e91caee0-2c99-499e-93c6-062e4829ee50\"}}";
		
//		{
//		    "success": true,
//		    "code": "",
//		    "message": "",
//		    "stack_trace": "",
//		"request_time": "2019/08/02 17:26:27.552", 
//		"response_time": "2019/08/02 17:26:39.225",
//		"content": {
//		"url": "http://10.8.110.51/{IFDPortal}/login.aspx?transaction=00637003198657938426e91caee0-2c99-499e-93c6-062e4829ee50",
//		        "transaction_id": ""
//		    }
//		}

	}
	
	@RequestMapping(value = "/download", method = RequestMethod.GET) 
	public ResponseEntity<Object> downloadFile(@RequestParam("fileName") String fileName) throws IOException, InterruptedException {
		Thread.sleep(2000);
		String filename = "C:/demo/files/" + fileName;
		File file = new File(filename);
		InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
		HttpHeaders headers = new HttpHeaders();

		headers.add("Content-Disposition", String.format("attachment; filename=\"%s\"", file.getName()));
		headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
		headers.add("Pragma", "no-cache");
		headers.add("Expires", "0");
		
		ResponseEntity<Object> responseEntity = ResponseEntity.ok().headers(headers).contentLength(file.length())
				.contentType(MediaType.parseMediaType("application/txt")).body(resource);
		return responseEntity;
	}
	
//	@RequestMapping(value = "/download", method = RequestMethod.GET) 
//	public void downloadFile(@RequestParam("fileName") String fileName, HttpServletResponse response) throws IOException {
//		String filename = "C:/demo/files/" + fileName;
//		File file = new File(filename);
//		InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
//
//		
//		// 設定response的ContentType
//		response.setContentType("application/x-msdownload");   
//		// 控制使用者儲存檔案時的檔案名稱
//		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
//		// 設置response header的Content-Length，在response被提交到OutputStream之前必須調用這個方法
//		response.setHeader("Content-Length", String.valueOf(file.length()));
//		
//		FileCopyUtils.copy(new FileInputStream(file), response.getOutputStream());
//	}
}

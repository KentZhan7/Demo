import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

import javax.activation.MimetypesFileTypeMap;

import org.w3c.tidy.Tidy;

import freemarker.cache.FileTemplateLoader;
import freemarker.cache.TemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class TestFreeMarker {
	
	public static Template getTemplate(String templateFilePath, String templateFileName) {
		try {
			Configuration cfg = new Configuration();
			cfg.setClassicCompatible(true);
			TemplateLoader templateLoader = new FileTemplateLoader(new File(templateFilePath));
			cfg.setTemplateLoader(templateLoader);
			return cfg.getTemplate(templateFileName);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void main(String[] args) throws IOException {
		
		
//		File file = new File("C:\\eclipse-neon\\eclipse\\tempCertificate.pdf");
//	    String mimeType = URLConnection.guessContentTypeFromName(file.getName());
//		System.out.println(mimeType);
		
		Writer out = null;
		Map<String, Object> replaceData = new HashMap<String, Object>();
		replaceData.put("name", "李OO");
		replaceData.put("officeName", "OO科技");
		replaceData.put("desc", "1234567890");
		replaceData.put("endDate", "");
		replaceData.put("certificateNo", "");
		try {
			out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("C:\\Users\\tp1231\\Desktop\\獎狀\\證書Final_20190726\\out.doc"), "UTF-8"));
			Template temp = getTemplate("C:\\Users\\tp1231\\Desktop\\獎狀\\證書Final_20190726", "withClass.xml");
			temp.setEncoding("UTF-8");
			temp.process(replaceData, out);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TemplateException e) {
			e.printStackTrace();
		} finally {
			try {
				if (out != null)
					out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}

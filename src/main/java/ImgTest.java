import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;

public class ImgTest {
//	static Logger logger = LoggerFactory.getLogger(ImgUtil.class);
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		// 待編碼的網址
	    String url = "腎臟內科-Harrison's principles of internal medicine";

	    try {
	      // 進行 URL 百分比編碼
	      String encodedURL = URLEncoder.encode(url, "UTF-8");

	      // 輸出結果
	      System.out.println(encodedURL);

	    } catch (UnsupportedEncodingException e) {
	      // 例外處理 ...
	    }
		
		
//		compressPictureByQality(new File("C:\\Users\\tp1231\\Desktop\\son1_1.jpg"), (float) 0.5);
	}
	
	public static File compressPictureByQality(File file, float qality) throws IOException {
        BufferedImage src = null;
        FileOutputStream out = null;
        ImageWriter imgWrier;
        ImageWriteParam imgWriteParams;
        System.out.println("開始設定壓縮圖片引數");
        // 指定寫圖片的方式為 jpg
        imgWrier = ImageIO.getImageWritersByFormatName("jpg").next();
        imgWriteParams = new javax.imageio.plugins.jpeg.JPEGImageWriteParam(null);
        // 要使用壓縮，必須指定壓縮方式為MODE_EXPLICIT
        imgWriteParams.setCompressionMode(imgWriteParams.MODE_EXPLICIT);
        // 這裡指定壓縮的程度，引數qality是取值0~1範圍內，
        imgWriteParams.setCompressionQuality(qality);
        imgWriteParams.setProgressiveMode(imgWriteParams.MODE_DISABLED);
        ColorModel colorModel =ImageIO.read(file).getColorModel();// ColorModel.getRGBdefault();
        // 指定壓縮時使用的色彩模式
//        imgWriteParams.setDestinationType(new javax.imageio.ImageTypeSpecifier(
//                colorModel, colorModel.createCompatibleSampleModel(16, 16)));
        imgWriteParams.setDestinationType(new javax.imageio.ImageTypeSpecifier(
                colorModel, colorModel.createCompatibleSampleModel(16, 16)));

        System.out.println("結束設定壓縮圖片引數");
        if (!file.exists()) {
        	System.out.println("Not Found Img File,檔案不存在");
            throw new FileNotFoundException("Not Found Img File,檔案不存在");
        } else {
            System.out.println("圖片轉換前大小"+file.length()+"位元組");
            src = ImageIO.read(file);
            out = new FileOutputStream(file);

            imgWrier.reset();
            // 必須先指定 out值，才能呼叫write方法, ImageOutputStream可以通過任何
            // OutputStream構造
            imgWrier.setOutput(ImageIO.createImageOutputStream(out));
            // 呼叫write方法，就可以向輸入流寫圖片
            imgWrier.write(null, new IIOImage(src, null, null), imgWriteParams);
            out.flush();
            out.close();
            System.out.println("圖片轉換後大小"+file.length()+"位元組");
            return file;
        }
    }
}

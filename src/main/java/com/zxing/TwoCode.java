package com.zxing;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.Binarizer;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.EncodeHintType;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.Result;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;

public class TwoCode {
	
	public static void main(String[] args) {
		encode();
	}
	
	public static void encode(){
		try {
			String content ="https://wsk.qq.com/hybrid/qr/index.shtml?p=DrBOgoQnl2ueydVOJ2MaIvSD7HS0pizB" ;
			MultiFormatWriter writer = new MultiFormatWriter();
			Map hints = new HashMap();
			hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
			BitMatrix bitMatrix = writer.encode(content, BarcodeFormat.QR_CODE, 400, 400, hints);
			File file1 = new File("D:/", "app.jpg");
			MatrixToImageWriter.writeToFile(bitMatrix, "jpg", file1);
			BufferedImage bufferImage = MatrixToImageWriter.toBufferedImage(bitMatrix);
			System.out.println(bufferImage);
		} catch (Exception e2) {
			e2.printStackTrace();
		}
	}
	
	public static void decode(){
		 try {
		             MultiFormatReader formatReader = new MultiFormatReader();
		 String filePath = "D:/test.jpg";
		 File file = new File(filePath);
		 BufferedImage image = ImageIO.read(file);;
		 LuminanceSource source = new BufferedImageLuminanceSource(image);
		 Binarizer  binarizer = new HybridBinarizer(source);
		 BinaryBitmap binaryBitmap = new BinaryBitmap(binarizer);
		 Map hints = new HashMap();
		 hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
		 Result result = formatReader.decode(binaryBitmap,hints);
		 
		             System.out.println("result = "+ result.toString());
		 System.out.println("resultFormat = "+ result.getBarcodeFormat());
		 System.out.println("resultText = "+ result.getText());
		             
		} catch (Exception e) {
		 e.printStackTrace();
		}
	}
	
	
}

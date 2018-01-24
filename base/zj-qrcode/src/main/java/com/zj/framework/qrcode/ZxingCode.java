package com.zj.framework.qrcode;

import com.google.zxing.*;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.zj.framework.qrcode.zxing.BufferedImageLuminanceSource;
import com.zj.framework.qrcode.zxing.MatrixToImageWriter;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * 用google zxing生成和读取二维码
 * @author ouyang
 *
 */
public class ZxingCode {
	public static final String SEPARATOR = String.valueOf((char) 29);

	public static String readQRCode(String path, String fileName) {
		try {
			MultiFormatReader reader = new MultiFormatReader();
			File file = new File(path,fileName+".jpg");
			BufferedImage bufferedImage = ImageIO.read(file);
			LuminanceSource luminanceSource = new BufferedImageLuminanceSource(bufferedImage);
			Binarizer binarizer = new HybridBinarizer(luminanceSource);
			BinaryBitmap binaryBitmap = new BinaryBitmap(binarizer);
			Map hints = new HashMap<EncodeHintType,String>();
            hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
            
			Result result = reader.decode(binaryBitmap,hints);
			
			return result.getText();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void saveQRCode(String contents, String path, String fileName) {
		try {
			MultiFormatWriter writer = new MultiFormatWriter();
			Map<EncodeHintType, String> hints = new HashMap<EncodeHintType, String>();
			hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
			BitMatrix bitMatrix =  writer.encode(contents, BarcodeFormat.QR_CODE, 400, 400, hints);
			File file = new File(path,fileName+".jpg");
			MatrixToImageWriter.writeToFile(bitMatrix, "jpg", file);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

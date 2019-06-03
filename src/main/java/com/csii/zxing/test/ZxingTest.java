package com.csii.zxing.test;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import javax.imageio.ImageIO;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.Binarizer;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.EncodeHintType;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;

// import org.junit.Test;
public class ZxingTest {
	@SuppressWarnings("deprecation")
	// @Test
	public void testEncoder() {
		// 二维码内容
		String text = "http://blog.csdn.net/rongbo_j";
		int width = 200; // 二维码图片宽度
		int height = 200; // 高度
		String format = "gif"; // 图片格式

		Hashtable<EncodeHintType, String> hints = new Hashtable<EncodeHintType, String>();
		hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
		BitMatrix bitMatrix = null;
		try {
			// 编码
			bitMatrix = new MultiFormatWriter().encode(text, BarcodeFormat.QR_CODE, width, height, hints);
		} catch (WriterException e1) {
			e1.printStackTrace();
		}
		File outputFile = new File("src/1.gif");
		try {
			// 输出二维码图片
			MatrixToImageWriter.writeToFile(bitMatrix, format, outputFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// @Test
	public void testDecoder() throws NotFoundException {
		// 二维码图片路径
		String path = this.getClass().getClassLoader().getResource("1.gif").getPath();
		File imageFile = new File(path);
		BufferedImage image = null;
		try {
			image = ImageIO.read(imageFile);
			LuminanceSource source = new BufferedImageLuminanceSource(image);
			Binarizer binarizer = new HybridBinarizer(source);
			BinaryBitmap binaryBitmap = new BinaryBitmap(binarizer);
			Map<DecodeHintType, Object> hints = new HashMap<DecodeHintType, Object>();
			hints.put(DecodeHintType.CHARACTER_SET, "UTF-8");
			// 解码获取二维码中信息
			Result result = new MultiFormatReader().decode(binaryBitmap, hints);
			System.out.println(result.getText());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
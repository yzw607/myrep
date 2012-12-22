package com.wmp.util;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;

import javax.imageio.ImageIO;

public class ImageUtil {
	public static byte[] toByteArray(File imageFile) throws Exception {
		BufferedImage img = ImageIO.read(imageFile);
		ByteArrayOutputStream buf = new ByteArrayOutputStream(
				(int) imageFile.length());
		try {
			ImageIO.write(img, "jpg", buf);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return buf.toByteArray();
	}

	public ByteArrayInputStream getStream(String path) throws Exception {
		File file = new File(path);
		byte[] b = toByteArray(file);
		return new ByteArrayInputStream(b);
	}
}

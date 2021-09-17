package dev.noire.NoireEngine.gfx;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageLoader {

	public static BufferedImage loadImage(String path) {
		try {
			return ImageIO.read(ImageLoader.class.getResource(path));
		} catch (IOException e) {
			
			System.out.println("ImageLoader.loadImage catch clause triggered\n potential problem with loading graphics");
			
		}
		return null;
	}
	
}

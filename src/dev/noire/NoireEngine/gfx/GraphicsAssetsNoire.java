package dev.noire.NoireEngine.gfx;

import java.awt.image.BufferedImage;

public class GraphicsAssetsNoire {

	//BUFFERED IMAGE ARRAYS:
	
	
	//BUFFERED IMAGES:
	//32x32
	public static BufferedImage logoPurpleSmall;
	public static BufferedImage logoGreenSmall;
	public static BufferedImage logoBlueSmall;
	public static BufferedImage logoYellowSmall;
	public static BufferedImage logoGreySmall;
	public static BufferedImage logoOpaqueSmall;
	//64x64
	public static BufferedImage logoOpaqueLarge;
	public static BufferedImage logoGreyLarge;
	public static BufferedImage logoYellowLarge;
	public static BufferedImage logoBlueLarge;
	public static BufferedImage logoGreenLarge;
	public static BufferedImage logoPurpleLarge;
	
	
	//SIZE OF IMAGES ON SPRITE SHEET:
	private static final int width = 32, height = 32;
	
	
	public static void init() {
		SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/textures/logo.png"));
		
		//ASSETS:
		//32x32
		logoPurpleSmall = sheet.crop(0, 0, width, height);
		logoGreenSmall = sheet.crop(width, 0, width, height);
		logoBlueSmall = sheet.crop(width*2, 0, width, height);
		logoYellowSmall = sheet.crop(width*3, 0, width, height);
		logoGreySmall = sheet.crop(width*4, 0, width, height);
		logoOpaqueSmall = sheet.crop(width*5, 0, width, height);
		//64x64
		logoOpaqueLarge = sheet.crop(0, height, width*2, height*2);
		logoGreyLarge = sheet.crop(width*2, height, width*2, height*2);
		logoYellowLarge = sheet.crop(width*4, height, width*2, height*2);
		logoBlueLarge = sheet.crop(width*6, height, width*2, height*2);
		logoGreenLarge = sheet.crop(0, height*3, width*2, height*2);
		logoPurpleLarge = sheet.crop(width*2, height*3, width*2, height*2);
	}
}

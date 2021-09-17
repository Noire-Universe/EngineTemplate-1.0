package dev.noire.NoireEngine.utils;

import java.awt.Font;

import dev.noire.NoireEngine.Handler;

public class FontManager {

	//Fonts:
	private Font defaultSmall;
	private Font defaultMedium;
	private Font defaultLarge;
	
	public FontManager(Handler handler) {
		
		//custom fonts:
		
		
		//default fonts:
		defaultSmall = new Font("Comic Sans MS", Font.PLAIN, (int)handler.getWidht()/64);
		defaultMedium = new Font("Comic Sans MS", Font.PLAIN, (int)handler.getWidht()/32);
		defaultLarge = new Font("Comic Sans MS", Font.PLAIN, (int)handler.getWidht()/16);
	}
	
	
	//GETTERS & SETTERS:
	public Font getSmallFont() {return defaultSmall;}
	public Font getMediumFont() {return defaultMedium;}
	public Font getLargeFont() {return defaultLarge;}
}

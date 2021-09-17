package dev.noire.NoireEngine.states;

import java.awt.Color;
import java.awt.Graphics;

import dev.noire.NoireEngine.Handler;
import dev.noire.NoireEngine.gfx.GraphicsAssetsNoire;

public class PauseState extends State {

	private int red;
	private int green;
	private int blue;
	private boolean colourFadeUp;
	
	public PauseState(Handler handler) {
		super(handler);
		
		red = 0;
		green = 0;
		blue = 0;
		colourFadeUp = true;
	}
	
	public void update() {
		
		if(colourFadeUp)
			fadeUp();
		else
			fadeDown();
		
	}
	
	public void render(Graphics g) {
		
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, handler.getWidht(), handler.getHeight());
		g.setColor(new Color(red, green, blue));
		g.fillRect((handler.getWidht()/2-(64/2)), (handler.getHeight()/2)-((64/2)), 64, 64);
		g.drawImage(GraphicsAssetsNoire.logoOpaqueLarge, (handler.getWidht()/2-(64/2)), (handler.getHeight()/2)-((64/2)), 64, 64, null);
		
		
	}
	
	private void fadeDown() {
		red--;
		if(red <= 0) {
			red = 0;
			green--;
			if(green <=0 ) {
				green = 0;
				blue--;
				if(blue <=0) {
					blue = 0;
					colourFadeUp = true;
				}
			}
		}
	}
	
	private void fadeUp() {
		red++;
		if(red >= 255) {
			red = 255;
			green++;
			if(green >= 255) {
				green = 255;
				blue++;
				if(blue >= 255) {
					blue = 255;
					colourFadeUp = false;
				}
			}
		}
		
	}
	
}

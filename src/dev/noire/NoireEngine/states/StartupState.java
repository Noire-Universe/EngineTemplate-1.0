package dev.noire.NoireEngine.states;

import java.awt.Color;
import java.awt.Graphics;

import dev.noire.NoireEngine.Handler;
import dev.noire.NoireEngine.gfx.GraphicsAssetsNoire;

public class StartupState extends State {
	
	//colour fade:
	private int red, green, blue;
	private boolean colourFadeUp;
	
	//screen timer:
	private int count;
	private long timer, lastTime;
	private final int DISPLAY_DURATION_IN_SECONDS = 2;
	

	public StartupState(Handler handler) {
		super(handler);
		
		//timer:
		count = 0;
		timer = 0;
		lastTime = System.currentTimeMillis();
		
		
		//colour fade: <-creating random colour on start up
		red = (int)(Math.random()*255);
		green = (int)(Math.random()*255);
		blue = (int)(Math.random()*255);
		colourFadeUp = true;
	}
	
	public void update() {
		
		timer += System.currentTimeMillis()-lastTime;
		lastTime = System.currentTimeMillis();
		if(timer >= (DISPLAY_DURATION_IN_SECONDS * 1000)){ //convert seconds to milliseconds
			timer = 0;
			count++;
			if(count==DISPLAY_DURATION_IN_SECONDS) { //switch state after allocated time has passed
				State.setState(handler.getMenuState());
			}
			
		}
		
		//fade the background colour
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

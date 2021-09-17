package dev.noire.NoireEngine;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import dev.noire.NoireEngine.audio.AudioManager;
import dev.noire.NoireEngine.display.Display;
import dev.noire.NoireEngine.files.FileManager;
import dev.noire.NoireEngine.gfx.GraphicsAssetsNoire;
import dev.noire.NoireEngine.input.KeyManager;
import dev.noire.NoireEngine.input.MouseManager;
import dev.noire.NoireEngine.states.GameOverState;
import dev.noire.NoireEngine.states.GameState;
import dev.noire.NoireEngine.states.MenuState;
import dev.noire.NoireEngine.states.PauseState;
import dev.noire.NoireEngine.states.StartupState;
import dev.noire.NoireEngine.states.State;
import dev.noire.NoireEngine.utils.FontManager;

public class Engine implements Runnable {

	//CONSTANTS:
	/****************************
	 *      LOOP CONTROL:       *
	 ****************************/
	private static final int NANO_SECONDS = 1000000000;
	private static final int FPS = 60;
	
	//DECLARE:
	/****************************
	 *        DISPLAY:          *
	 ****************************/
	private Display display;
	
	private String title;
	private int width, height;
	
	private Thread thread;
	private boolean running = false;
	
	private Graphics g;
	private BufferStrategy bs;
	
	//handler
	private Handler handler;
	
	//states
	private State menuState;
	private State gameState;
	private State pauseState;
	private State gameOverState;
	private State startupState;
	
	//input
	private KeyManager keyManager;
	private MouseManager mouseManager;
	
	//audio
	private AudioManager audioManager;
	
	//timers
	
	//fonts
	private FontManager fontManager;
	
	//files
	private FileManager fileManager;
	
	//camera
	
	//->worlds won't be loaded by the engine directly
	
	//->entities won't be loaded by the engine directly
	
	//benchmark
	
	//CONSTRUCTOR
	public Engine(String title, int width, int height) {
		this.title = title;
		this.width = width;
		this.height = height;	
		
		GraphicsAssetsNoire.init();
		
		keyManager = new KeyManager();
		mouseManager = new MouseManager();
		fileManager = new FileManager();
		audioManager = new AudioManager();
		
	}
	
	private void init() {
		display = new Display(title, width, height);
		display.getFrame().addKeyListener(keyManager);
		display.getFrame().addMouseListener(mouseManager);
		display.getFrame().addMouseMotionListener(mouseManager);
		display.getCanvas().addMouseListener(mouseManager);
		display.getCanvas().addMouseMotionListener(mouseManager);
		
		//initialise without Handler dependency:
		
		
		
		//initialise with Handler dependency:
		handler = new Handler(this);
		
		fontManager = new FontManager(handler);
		
		menuState = new MenuState(handler);
		gameState = new GameState(handler);
		pauseState = new PauseState(handler);
		gameOverState = new GameOverState(handler);
		startupState = new StartupState(handler);
		
		//setting the initial state:
		State.setState(startupState);
	}
	
	//GAME LOOP:
	public void update() {
		keyManager.update();
		
		//updating currentState:
		if(State.getState() != null)
			State.getState().update();
		
	}
	
	public void render() {
		bs = display.getCanvas().getBufferStrategy();
		if(bs==null) {
			display.getCanvas().createBufferStrategy(3);
			return;
		}
		g = bs.getDrawGraphics();
		g.clearRect(0, 0, width, height);
		//start drawing...
		
			//drawing currentState:
			if(State.getState() != null)
				State.getState().render(g);
		
		//...end drawing.
		bs.show();
		g.dispose();
	}
	
	public void run() {
		init();
		
		//temporary loop final implementation will follow soon!
		
		int ticks = 0;
		double timer = 0;
		
		double framesPerTick = NANO_SECONDS/FPS;
		double delta = 0;
		
		long now;
		long lastTime = System.nanoTime();
		
		while(running) {
			now = System.nanoTime();
			delta += (now-lastTime)/framesPerTick;
			timer += now-lastTime;
			lastTime = now;
			
			if(delta >= 1) {
				update();
				render();
				ticks++;
				delta--;
			}
			
			if(timer >= NANO_SECONDS) {
				System.out.println("Frames & Ticks: " + ticks);
				ticks = 0;
				timer = 0;
			}
			
		}
		
		stop();
	}
	
	//GETTERS & SETTERS:
	public int getWidth() {return width;}
	public int getHeight() { return height;}
	
	public KeyManager getKeyManager() {return keyManager;}
	public MouseManager getMouseManager() {return mouseManager;}
	public FileManager getFileManager() {return fileManager;}
	public AudioManager getAudioManager() {return audioManager;}
	public FontManager getFontManager() {return fontManager;}
	
	//STATES:
	public State getMenuState() {return menuState;}
	public State getGameState() {return gameState;}
	public State getPauseState() {return pauseState;}
	public State getGameOverState() {return gameOverState;}
	
	//ON/OFF-SWITCH:
	public synchronized void start() {
		if(running)
			return;
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	
	public synchronized void stop() {
		if(!running)
			return;
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}

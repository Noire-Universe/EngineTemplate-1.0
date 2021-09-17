package dev.noire.NoireEngine;

import dev.noire.NoireEngine.audio.AudioManager;
import dev.noire.NoireEngine.files.FileManager;
import dev.noire.NoireEngine.input.KeyManager;
import dev.noire.NoireEngine.input.MouseManager;
import dev.noire.NoireEngine.states.State;
import dev.noire.NoireEngine.utils.FontManager;
import dev.noire.NoireEngine.worlds.World;

public class Handler {

	private Engine engine;
	private World world;
	
	public Handler(Engine engine) {
		this.engine = engine;
		
	}
	
	//Feature related GETTERS & SETTERS:
	
	//input
	public KeyManager getKeyManager() {return engine.getKeyManager();}
	public MouseManager getMouseManager() {return engine.getMouseManager();}
	
	//file manager
	public FileManager getFileManager() {return engine.getFileManager();}
	
	//font manager
	public FontManager getFontManager() {return engine.getFontManager();}
	
	//audio manager
	public AudioManager getAudioManager() {return engine.getAudioManager();}
	
	//camera
	
	//..add as needed..
	
	//display
	public int getWidht() {return engine.getWidth();}
	public int getHeight() {return engine.getHeight();}

	//states
	public State getMenuState() {return engine.getMenuState();}
	public State getGameState() {return engine.getGameState();}
	public State getGameOverState() {return engine.getGameOverState();}
	public State getPauseState() {return engine.getPauseState();}
	
	//BASIC GETTERS AND SETTERS:
	public Engine getEngine() {
		return engine;
	}

	public void setEngine(Engine engine) {
		this.engine = engine;
	}

	public World getWorld() {
		return world;
	}

	public void setWorld(World world) {
		this.world = world;
	}
}

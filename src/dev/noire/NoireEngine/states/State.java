package dev.noire.NoireEngine.states;

import java.awt.Graphics;

import dev.noire.NoireEngine.Handler;

public abstract class State {

	/***********************************************
	 *              STATE MANAGER:                 *
	 ***********************************************/
	
	private static State currentState = null;
	public static State getState() {return currentState;}
	public static void setState(State state) {currentState = state;}
	
	
	/***********************************************
	 *             ABSTRACT CLASS:                 *
	 ***********************************************/
	
	protected Handler handler;
	
	public State(Handler handler) {
		this.handler = handler;
		
	}
	
	public abstract void update();
	public abstract void render(Graphics g);
	
	
}

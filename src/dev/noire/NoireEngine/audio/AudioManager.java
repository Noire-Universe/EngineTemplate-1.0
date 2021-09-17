package dev.noire.NoireEngine.audio;

public class AudioManager {
	//fields
	private boolean isMuted; //needed for providing a mute option of the program
	private float musicVolume; //music master volume control
	private float soundVolume; //sound master volume control
	
	//music objects:...
	//sound objects:...
		
	public AudioManager() {
		isMuted = false;
		musicVolume = 0; //0 is a reference to the default volume provided by the file
		soundVolume = 0; //0 is a reference to the default volume provided by the file
		
		//area for individual volume control of each sound and music file:
		//load files....
	}
	
	public void update() {
		//probably not going be needed but keeping it here for consistency
		//NOTE!!!!! METHOD NOT YET CALLED BY ENGINE.JAVA!!!!!
	}
	
	//methods to play defined sound and music objects
	//methods to stop playing music objects
	
	//DEALING WITH MUTING SOUND AND MUSIC:
	public void mute() { //mute!
		if (!isMuted) {
			isMuted = true;
			//trigger stop functions of all music objects!
		}
	}

	public void unMute() { //un-mute!
		if (isMuted) isMuted = false;
	}

	//getters & setters for mute boolean
	public boolean isMuted() { return isMuted;}
	public void setMuted(boolean isMuted) { this.isMuted = isMuted;}
	
	//GETTERS AND SETTERS:
	public float getMusicVolume() {return musicVolume;}
	public float getSoundVolume() { return soundVolume;}
	//...to be extended...
}

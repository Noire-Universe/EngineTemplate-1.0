package dev.noire.NoireEngine.audio;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class AudioObject {

	//fields:
	private File file;
	private Clip clip;
	private FloatControl gainControl;
	
	private boolean isLooping; //music loop gate
	
	public AudioObject(String location, boolean isLooping) {
		this.isLooping = isLooping; //decision for looping done at constructor level
		file = new File(location); //define the location of the file to be played
		
		try { //trying to load the audio file
			
			AudioInputStream inputStream = AudioSystem.getAudioInputStream(file); //streaming the file into the program
			clip = AudioSystem.getClip(); //creating an audio clip
			clip.open(inputStream); //opening the audio clip as a stream
			gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN); //setting up volume control of the file
			
		}catch(UnsupportedAudioFileException e) { //error handling...
			e.printStackTrace();
			System.out.println("unsupported audio file"); //<-- debug!
		}
		catch(IOException e) {
			e.printStackTrace();
			System.out.println("IOException");  //<-- debug!
		}
		catch(LineUnavailableException e) {
			e.printStackTrace();
			System.out.println("LineUnavailable");  //<-- debug!
		}
		
	}
	
	//PLAYING THE AUDIO FILE (in separate thread):
		//comments will need to be added after testing (if this works!)
	public void play() {
		Runnable jumpPlayer = new Runnable() {
			@Override
			public void run() {
				try {
					clip.setMicrosecondPosition(0);
					gainControl.setValue(gainControl.getValue());
					clip.start();
					if(isLooping)
						clip.loop(Clip.LOOP_CONTINUOUSLY);
				}catch(Exception e) {
					e.printStackTrace();
				}
			
				}
			};
			new Thread(jumpPlayer).start();
	}
	
	//STOP THE AUDIO FILE:
	public void stop() { clip.stop(); }
	
	//VOLUME CONTROL:
	public void increaseVolume(float modifier) { //make it louder
		if(modifier + gainControl.getValue() < gainControl.getMaximum())
			gainControl.setValue(gainControl.getValue()+modifier);
	}
	
	public void decreaseVolume(float modifier) { //make it quieter
		if(gainControl.getValue() - modifier > gainControl.getMinimum())
			gainControl.setValue(gainControl.getValue()-modifier);
	}
	
	public void setValue(float newValue) { //set specific value
		if(gainControl.getValue()-newValue > gainControl.getMinimum() && gainControl.getValue()+newValue < gainControl.getMaximum()) {
			gainControl.setValue(gainControl.getValue()+newValue);
		}
	}
	//getters for current, min and max value of the file
	public float getCurrentValue() { return gainControl.getValue();}
	public float getMaxValue() { return gainControl.getMaximum();}
	public float getMinValue() { return gainControl.getMinimum();}
	
}

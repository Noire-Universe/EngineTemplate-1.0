package dev.noire.NoireEngine.files;

import java.io.File;

public class FileManager {

	private String fileFolder; //whole file path
	private String specificFolder = "NoireEngine"; //program specific sub folder
	private File directory;
	
	public FileManager() {
		DealWithDirectory(); //creates a directory if it does not already exist
		
		
		System.out.println(fileFolder); //redundant line just to check for response (need to be removed on finalisation)
		
	}
	
	private void DealWithDirectory() {
		fileFolder = System.getenv("APPDATA") + "\\" + specificFolder; //create full path
		directory = new File(fileFolder); //set full path
		if(!directory.exists()) //check if full path exists
			directory.mkdir(); //generate full path
	}
	
	public void WriteToProgramData() {
		//override/create file
	}
	
	public void WriteToGameData() {
		//override/create file
	}
	
	//public dataType getProgramData(){}
	//public dataType getGameData(){}
	
}

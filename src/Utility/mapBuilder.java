package Utility;

import java.io.File;

public class mapBuilder {

	private int numofmaps = 25;
	
	public mapBuilder() {
		// TODO Auto-generated constructor stub
		/*
		create10CitiesMaps();
		create25CitiesMaps();
		create50CitiesMaps();
		create100CitiesMaps();
		*/
	}
	
	public void create10CitiesMaps(){
		File dir = new File("Maps/Ten");
		for(int i = 0; i < numofmaps; i++){
			File file = new File(dir,"Map"+i+".txt");
		}
	}
	public void create25CitiesMaps(){
		File dir = new File("Maps/TwentyFive");
		for(int i = 0; i < numofmaps; i++){
			File file = new File(dir,"Map"+i+".txt");
		}
	}
	public void create50CitiesMaps(){
		File dir = new File("Maps/Fifty");
		for(int i = 0; i < numofmaps; i++){
			File file = new File(dir,"Map"+i+".txt");
		}
	}
	public void create100CitiesMaps(){
		File dir = new File("Maps/Hundred");
		for(int i = 0; i < numofmaps; i++){
			File file = new File(dir,"Map"+i+".txt");
		}
	}
}

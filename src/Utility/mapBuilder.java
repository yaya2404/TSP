package Utility;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class MapBuilder {

	private int numofmaps = 25;
	
	public MapBuilder() {
		// TODO Auto-generated constructor stub
		/*
		create10CitiesMaps();
		create25CitiesMaps();
		create50CitiesMaps();
		create100CitiesMaps();
		*/
	}
	
	public void create10CitiesMaps() throws IOException{
		File dir = new File(".");
		for(int i = 0; i < numofmaps; i++){
			File file = new File(dir,"Maps/Ten/Map"+(i+1)+".txt");
			if(!file.exists()){
				file.createNewFile();
			}
			FileWriter writer = new FileWriter(file);
			Map map = new Map(10);
			writer.write(map.toString());
			writer.close();
		}
	}
	public void create25CitiesMaps() throws IOException{
		File dir = new File(".");
		for(int i = 0; i < numofmaps; i++){
			File file = new File(dir,"Maps/TwentyFive/Map"+(i+1)+".txt");
			if(!file.exists()){
				file.createNewFile();
			}
			FileWriter writer = new FileWriter(file);
			Map map = new Map(25);
			writer.write(map.toString());
			writer.close();
		}
	}
	public void create50CitiesMaps() throws IOException{
		File dir = new File(".");
		for(int i = 0; i < numofmaps; i++){
			File file = new File(dir,"Maps/Fifty/Map"+(i+1)+".txt");
			if(!file.exists()){
				file.createNewFile();
			}
			FileWriter writer = new FileWriter(file);
			Map map = new Map(50);
			writer.write(map.toString());
			writer.close();
		}
	}
	public void create100CitiesMaps() throws IOException{
		File dir = new File(".");
		for(int i = 0; i < numofmaps; i++){
			File file = new File(dir,"Maps/Hundred/Map"+(i+1)+".txt");
			if(!file.exists()){
				file.createNewFile();
			}
			FileWriter writer = new FileWriter(file);
			Map map = new Map(100);
			writer.write(map.toString());
			writer.close();
		}
	}
}

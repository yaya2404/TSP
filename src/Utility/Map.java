package Utility;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Map {

	Node[] listOfCities;
	int cities;
	
	/**
	 * Used to random generate the location of the cities on the map
	 * 
	 * 
	 * @param cities	number of cities the map contains
	 */
	public Map(int cities) {
		// TODO Auto-generated constructor stub
		listOfCities = new Node[cities];
		this.cities = cities;
		ArrayList<Integer> duplicateX = new ArrayList<Integer>();
		ArrayList<Integer> duplicateY = new ArrayList<Integer>();
		Random random = new Random();
		int i = 0;
		int x = -1;
		int y = -1;
		
		do{
			x = random.nextInt(101);
			y = random.nextInt(101);
			if(!duplicateX.contains(x) || !duplicateY.contains(y)){
				listOfCities[i] = new Node(String.valueOf(i), x, y);
				i++;
			}
		}while(i < cities);
	}
	
	
	/**
	 * 
	 * Used read city locations directly from file
	 * 
	 * @param file	contains information regarding name and coordinates of every city on file
	 * @throws FileNotFoundException 
	 */
	public Map(File file){
		FileReader freader;
		BufferedReader breader;
		try {
			freader = new FileReader(file);
			breader = new BufferedReader(freader);
			cities = Integer.parseInt(breader.readLine().replaceAll("<","").replaceAll(">", ""));
			String[] input;
			String line;
			listOfCities = new Node[cities];
			for(int i = 0; i < cities; i++){
				line = breader.readLine();
				line = line.replaceAll("<", "").replaceAll(">", "");
				input = line.split(" ");
				listOfCities[i] = new Node(input[0], 
						Integer.valueOf(input[1]),
						Integer.valueOf(input[2]));
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch(IOException i){
			i.printStackTrace();
		}
		
		
	}
	
	public String toString(){
		StringBuilder out = new StringBuilder();
		out.append("<"+listOfCities.length+">\n");
		
		for(int i = 0; i < listOfCities.length; i++){
			out.append("<City"+(i+1)+"> <"+listOfCities[i].getX()+"> <"+listOfCities[i].getY()+">");
			if(i != listOfCities.length -1){
				out.append("\n");
			}
		}
		
		return out.toString();
	}
	public int getNumOfCities(){
		return this.cities;
	}
	public Node[] getListOfCities(){
		return this.listOfCities;
	}
}

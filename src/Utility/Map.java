package Utility;

import java.util.ArrayList;
import java.util.Random;

public class Map {

	Node[] listOfCities;
	
	
	public Map(int cities) {
		// TODO Auto-generated constructor stub
		listOfCities = new Node[cities];
		
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
	public String toString(){
		StringBuilder out = new StringBuilder();
		out.append("<"+listOfCities.length+">\n");
		
		for(int i = 0; i < listOfCities.length; i++){
			out.append("<City "+(i+1)+"> <"+listOfCities[i].getX()+"> <"+listOfCities[i].getY()+">\n");
		}
		
		return out.toString();
	}
}

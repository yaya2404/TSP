package Test;

import java.io.File;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import Algorithm.AStarSearch;
import Utility.Map;



public class Astartesting {

	public static void main(String[] args) {
		File folder = new File("Maps");
		File[] folders = folder.listFiles();
		
		int totalproblems = 0;
		int problems = 0;
		double runtime = 0;
		long endtime = 0;
		int fileindex = 0;
		int nodes = 0;
		double cost = 0;
		
		//per folder
		for(int x = 0; x < folders.length; x++){
			File[] files = folders[x].listFiles();
			System.out.println("Challenge Set: " + folders[x].getName() + " Cities ");
			endtime = System.currentTimeMillis() + 600000; //time minutes
			while(System.currentTimeMillis() < endtime){
				if(fileindex == files.length){
					break;
				}
				Map map = new Map(files[fileindex]);
				AStarSearch a = new AStarSearch(map);
				a.findPath();
				runtime += a.getTime();
				nodes += a.getNumOfExpandedNodes();
				cost += a.getPathCost();
				fileindex++;
			}
			System.out.println("A) Number of problems solved: " + fileindex);
			System.out.println("B) Average solution time for challenge: " + runtime/fileindex + "ms");
			System.out.println("C) Average number of nodes generated: " + nodes/fileindex);
			System.out.println("D) Average solution quality " + cost/fileindex);
			runtime = 0;
			fileindex = 0;
			nodes = 0;
			cost = 0;
		}
	}

}

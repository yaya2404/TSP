package Test;

import java.io.File;

import Algorithm.AStarSearch;
import Algorithm.SimulatedAnnealing;
import Utility.Map;

public class SAtesting {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
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
				SimulatedAnnealing a = new SimulatedAnnealing(10000, 0.000003, map);
				a.findSolution();
				runtime += a.getTime();
				nodes += a.getNodesGenerated();
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

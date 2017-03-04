package Algorithm;

import java.util.HashSet;
import java.util.PriorityQueue;

import Utility.Map;
import Utility.Node;


public class AStarSearch {

	private Map map;
	PriorityQueue<Node> open;
	HashSet<Node> visited; 
	HashSet<Node> unvisited;
	private long time;
	private double memory;
	
	public AStarSearch(Map map) {
		// TODO Auto-generated constructor stub
		this.map = map;
		unvisited = new HashSet<Node>(map.getNumOfCities());
		visited = new HashSet<Node>(map.getNumOfCities());
	}
	public boolean findPath(){
		Node current = null;
		Node start = map.getListOfCities()[0];
		setUpList();
		
		
		
		long starttime = System.nanoTime();
		double startMemory = (Runtime.getRuntime().totalMemory() -  Runtime.getRuntime().freeMemory())/ 1024d; 
		double endMemory = 0;
		
		
		while(!open.isEmpty()){
			current = open.remove();
			visited.add(current);
			unvisited.remove(current);
			
			for(Node s: unvisited){
				if(!visited.contains(s)){
					s.setG(current.getG() + current.calculateDistance(s));
					//update f
					
					s.setParent(current);
					
					if(open.contains(s)){
						open.remove(s);
					}
					open.add(s);
				}
			}
			
		}
		//reached last city need to compute final distance by connecting to start point.
		
		start.setG(current.getG() + current.calculateDistance(start));
		start.setParent(current);
		
		time = (System.nanoTime() - starttime)/1000000;
		endMemory = (Runtime.getRuntime().totalMemory() -  Runtime.getRuntime().freeMemory())/ 1024d; //KB output
		memory = endMemory - startMemory;
		if(memory < 0){
			memory = 0;
		}
	
		return true;
	}
	
	private void setUpList(){
		open = new PriorityQueue<Node>(map.getNumOfCities(), new Node.NodeComparator());
		Node start = this.map.getListOfCities()[0];
		Node[] list = map.getListOfCities();
		start.setG(0.0);
		
		for(Node s: list){
			unvisited.add(s);
		}
		//updates f here
		
		open.add(start);
	}
}

package Algorithm;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.PriorityQueue;

import Utility.Map;
import Utility.Node;
import edu.princeton.cs.algs4.Edge;
import edu.princeton.cs.algs4.EdgeWeightedGraph;
import edu.princeton.cs.algs4.KruskalMST;



public class AStarSearch {

	private Map map;
	PriorityQueue<Node> open;
	HashSet<Node> visited; 
	HashSet<Node> unvisited;
	private long time;
	private double memory;
	private double startHeur;
	private int nodesgen;
	
	
	public AStarSearch(Map map) {
		// TODO Auto-generated constructor stub
		this.map = map;
		unvisited = new HashSet<Node>(map.getNumOfCities());
		visited = new HashSet<Node>(map.getNumOfCities());
	}
	public boolean findPath(){
		Node current = null;
		Node[] cities = map.getListOfCities();
		Node start = cities[0];
		setUpList();
		
		
		
		long starttime = System.nanoTime();
		double startMemory = (Runtime.getRuntime().totalMemory() -  Runtime.getRuntime().freeMemory())/ 1024d; 
		double endMemory = 0;
		double h = 0;
		
		while(!open.isEmpty()){
			current = open.remove();
			visited.add(current);
			unvisited.remove(current);
			for(Node s: unvisited){
				if(!visited.contains(s)){
					s.setG(current.getG() + current.calculateDistance(s));
					
					
					//set up MST for heuristic
					EdgeWeightedGraph graph = new EdgeWeightedGraph(cities.length);
					for(int i = 0; i < cities.length; i++){
						for(int j = i; j <  cities.length; j++){
							if(i != j){
								if(unvisited.contains(cities[i]) && unvisited.contains(cities[j])){
									if(!s.equals(cities[i]) && !s.equals(cities[j])){
										graph.addEdge(new Edge(cities[i].getCityNum()-1, cities[j].getCityNum()-1, cities[i].getEdgeCost(cities[j].getCityNum()-1)));
									}
								}
							}
						}
					}
					
					KruskalMST mst = new KruskalMST(graph);
					
					h = mst.weight();
					
					Iterator<Edge> itr = mst.edges().iterator();
					
					//list of vertices that are in the tree
					ArrayList<Integer> list = new ArrayList<Integer>();
					while(itr.hasNext()){
						Edge e = itr.next();
						if(!list.contains(e.either())){
							list.add(e.either());
						}
						if(!list.contains(e.other(e.either()))){
							list.add(e.other(e.either()));
						}
					}
					if(list.size() == 0){
						Iterator<Node> it = unvisited.iterator();
						while(it.hasNext()){
							list.add(it.next().getCityNum()-1);
						}
					} 
					//Searches for shortest edge from start to tree
					int shortest = list.get(0);
					for(int i = 0; i < list.size(); i++){
						if(cities[0].getEdgeCost(shortest) > cities[0].getEdgeCost(list.get(i))){
							shortest = i;
						}
					}
					
					h += cities[0].getEdgeCost(shortest);
					
					//Searches for shortest edge from neighbor to tree
					shortest = list.get(0);
					for(int i = 0; i < list.size(); i++){
						if(s.getEdgeCost(shortest) > s.getEdgeCost(list.get(i))){
							shortest = i;
						}
					}
					h+=s.getEdgeCost(shortest);
					s.setH(h);
					s.updateF();
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
		//printPath();
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
		
		EdgeWeightedGraph test = new EdgeWeightedGraph(list.length);
		for(int i = 0; i < map.getNumOfCities(); i ++){
			for(int j = i; j < map.getNumOfCities(); j++){
				if(i != j){
					test.addEdge(new Edge(list[i].getCityNum()-1,list[j].getCityNum()-1, list[i].getEdgeCost(j)));
				}
			}
		}
		KruskalMST testing = new KruskalMST(test);
		startHeur = testing.weight() + start.findShortestEdge();
		start.setH(startHeur);
		
		start.updateF();
		
		open.add(start);
	}
	public void printPath(){
		Node start = map.getListOfCities()[0];
		Node iter = start.getParent();
		
		System.out.println(start + "g: " + start.getG() + " h: " + 0 + " f: " + start.getG());
		
		while(iter != start){
			System.out.println(iter + "g: " +iter.getG() + " h: " + iter.getH() + " f: " + iter.getF());
			iter = iter.getParent();
		}
		System.out.println(start + "g: 0" + " h: " + startHeur + " f: " + 0);
	}
	public long getTime(){
		return this.time;
	}
	public int getNumOfExpandedNodes(){
		return this.visited.size();
	}
	public double getPathCost(){
		Node start = map.getListOfCities()[0];
		return start.getG();
	}
}

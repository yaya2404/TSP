package Utility;

import java.util.ArrayList;
import java.util.Comparator;


public class Node {
	
	private String city;
	private int num;
	private int x;
	private int y;
	private boolean visited;
	private double g;
	private double h;
	private double f;
	private Node parent;
	private double[] edgecost;
	
	public Node(String city,int x,int y) {
		// TODO Auto-generated constructor stub
		this.city = city;
		this.x = x;
		this.y = y;
		this.visited = false;
		this.g = Double.POSITIVE_INFINITY;
		this.parent = null;
		this.num = Integer.valueOf(city.substring(4));
		this.h = 0;
		this.f = this.g;
	}
	
	public String getCity(){
		return this.city;
	}
	public int getCityNum(){
		return this.num;
	}
	public int getX(){
		return this.x;
	}
	public int getY(){
		return this.y;
	}
	public boolean hasVisited(){
		return this.visited;
	}
	public void setVisited(boolean value){
		this.visited = value;
	}
	public double calculateDistance(Node goal){
		return Math.sqrt(Math.pow((this.x-goal.x),2)+Math.pow((this.y-goal.y),2));
	}
	public void setG(Double value){
		this.g = value;
	}
	public double getG(){
		return this.g;
	}
	public void setH(Double value){
		this.h = value;
	}
	public double getH(){
		return this.h;
	}
	public double getF(){
		return this.f;
	}
	public void updateF(){
		f = g + h;
	}
	public void setParent(Node node){
		this.parent = node;
	}
	public Node getParent(){
		return this.parent;
	}
	public static class NodeComparator implements Comparator<Node>{

		public int compare(Node x, Node y){
			if(x.f != y.f) 
				return (x.f < y.f) ? -1 : 1;
			
			else if(x.g != y.g) 
				return (x.g > y.g) ? -1 : 1; //favors the larger g-values
			
			else
				return (x.h < y.h) ? -1 : 1;
				
		}
		
	}
	public String toString(){
		return this.city + ": " + "(" + this.x + "," + this.y + ")";
	}

	public void initEdgeCostList(int cities) {
		this.edgecost = new double[cities];
	}
	
	public void setEdgeCost(int index, double value){
		this.edgecost[index] = value;
	}
	
	public double getEdgeCost(int index){
		return this.edgecost[index];
	}
	
	public double findShortestEdge(){
		
		int shortest = edgecost.length - this.num;	//avoid picking the edge returning to itself because that edge = 0
		
		for(int i = 0; i < edgecost.length; i++){
			if(i != this.num-1){
				if(edgecost[shortest] > edgecost[i]){
					shortest = i;
				}
			}
		}
		return edgecost[shortest];
	}
}

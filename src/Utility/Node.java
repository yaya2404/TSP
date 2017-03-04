package Utility;

import java.util.Comparator;


public class Node {
	
	private String city;
	private int x;
	private int y;
	private boolean visited;
	private double g;
	private Node parent;
	
	
	public Node(String city,int x,int y) {
		// TODO Auto-generated constructor stub
		this.city = city;
		this.x = x;
		this.y = y;
		this.visited = false;
		this.g = Double.POSITIVE_INFINITY;
		this.parent = null;
	}
	
	public String getCity(){
		return this.city;
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
	public void setParent(Node node){
		this.parent = node;
	}
	public static class NodeComparator implements Comparator<Node>{

		@Override
		public int compare(Node o1, Node o2) {
			if(o1.g != o2.g){
				return (o1.g > o2.g) ? 1 : -1;
			}else{
				//makes the comparator deterministic
				return -1;
			}
		}
		
	}
	public String toString(){
		return this.city + ": " + "(" + this.x + "," + this.y + ")";
	}
	
}

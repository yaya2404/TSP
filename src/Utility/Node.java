package Utility;

public class Node {
	
	private String city;
	private int x;
	private int y;
	private boolean visited;
	
	public Node(String city,int x,int y) {
		// TODO Auto-generated constructor stub
		this.city = city;
		this.x = x;
		this.y = y;
		this.visited = false;
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
}

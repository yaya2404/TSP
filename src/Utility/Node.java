package Utility;

public class Node {
	
	private String city;
	private int x;
	private int y;
	
	
	public Node(String city,int x,int y) {
		// TODO Auto-generated constructor stub
		this.city = city;
		this.x = x;
		this.y = y;
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
}

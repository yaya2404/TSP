package Utility;

import java.util.ArrayList;
import java.util.Collections;

public class Tour {
	
	private ArrayList<Node> tour = new ArrayList<Node>();
	private double distance = 0;
	private Node[] list;
	
	public Tour(Map map) {
		// TODO Auto-generated constructor stub
		list = map.getListOfCities();
		for(Node s: list){
			tour.add(s);
		}
	}
	public Tour(ArrayList<Node> tour){
        this.tour = (ArrayList<Node>) tour.clone();
    }
    public ArrayList<Node> getTour(){
        return tour;
    }

    public void generateIndividual() {
        for (int cityIndex = 0; cityIndex < list.length; cityIndex++) {
          setCity(cityIndex, list[cityIndex]);
        }
        Collections.shuffle(tour);
    }

    public Node getCity(int tourPosition) {
        return tour.get(tourPosition);
    }

    public void setCity(int tourPosition, Node city) {
        tour.set(tourPosition, city);
        distance = 0;
    }
    
    public double getDistance(){
        if (distance == 0) {
            int tourDistance = 0;
            for (int cityIndex=0; cityIndex < tourSize(); cityIndex++) {
                Node fromCity = getCity(cityIndex);
                Node destinationCity;
                
                if(cityIndex+1 < tourSize()){
                    destinationCity = getCity(cityIndex+1);
                }else{
                    destinationCity = getCity(0);
                }
                tourDistance += fromCity.calculateDistance(destinationCity);
            }
            distance = tourDistance;
        }
        return distance;
    }
    public int tourSize() {
        return tour.size();
    }
    
    @Override
    public String toString() {
        String geneString = "|";
        for (int i = 0; i < tourSize(); i++) {
            geneString += getCity(i)+"|";
        }
        return geneString;
    }
}

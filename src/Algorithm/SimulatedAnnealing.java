package Algorithm;

import java.util.ArrayList;

import Utility.Map;
import Utility.Node;
import Utility.Tour;
import javafx.scene.chart.XYChart;

public class SimulatedAnnealing {
	
	private double temp;
	private double cooling;
	private Map map;
	private double pathcost;
	private long time;
	private int nodesgen;
	private ArrayList<Double> values;
	XYChart.Series<Number, Number> series = new XYChart.Series<Number,Number>();
	
	public SimulatedAnnealing(double startingTemperature, double coolingRate, Map map) {
		// TODO Auto-generated constructor stub
		this.temp = startingTemperature;
		this.cooling = coolingRate;
		this.map = map;
		this.values = new ArrayList<Double>();
	}
    public static double acceptanceProbability(double energy, double newEnergy, double temperature) {
        if (newEnergy < energy) {
            return 1.0;
        }
        return Math.exp((energy - newEnergy) / temperature);
    }
	public boolean findSolution(){
		Tour currentSolution = new Tour(map);
        currentSolution.generateIndividual();
        nodesgen += currentSolution.tourSize();
        long count = 0;
        
        Tour best = new Tour(currentSolution.getTour());
        long starttime = System.nanoTime();
        while (temp > 1) {
        	
            Tour newSolution = new Tour(currentSolution.getTour());
            nodesgen += newSolution.tourSize();
            
            int tourPos1 = (int) (newSolution.tourSize() * Math.random());
            int tourPos2 = (int) (newSolution.tourSize() * Math.random());
            Node citySwap1 = newSolution.getCity(tourPos1);
            Node citySwap2 = newSolution.getCity(tourPos2);

            newSolution.setCity(tourPos2, citySwap1);
            newSolution.setCity(tourPos1, citySwap2);
            
            double currentEnergy = currentSolution.getDistance();
            double neighbourEnergy = newSolution.getDistance();

            if (acceptanceProbability(currentEnergy, neighbourEnergy, temp) > Math.random()) {
                currentSolution = new Tour(newSolution.getTour());
            }

            if (currentSolution.getDistance() < best.getDistance()) {
                best = new Tour(currentSolution.getTour());
            }
            //values.add(newSolution.getDistance());
            series.getData().add(new XYChart.Data<Number,Number>(count, newSolution.getDistance()));
            count++;
            // Cool system
            temp *= 1-cooling;
        }
        pathcost = best.getDistance();
        time = (System.nanoTime() - starttime)/1000000;
        //System.out.println("Final solution distance: " + best.getDistance());
        //System.out.println("Tour: " + best);
		return true;
	}
	public long getTime(){
		return this.time;
	}
	public double getPathCost(){
		return this.pathcost;
	}
	public int getNodesGenerated(){
		return this.nodesgen;
	}
	public ArrayList<Double> getValues(){
		return this.values;
	}
	public XYChart.Series<Number, Number> getSeries(){
		return this.series;
	}
}

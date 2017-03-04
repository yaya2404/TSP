package Algorithm;

import Utility.Map;
import Utility.Node;
import Utility.Tour;

public class SimulatedAnnealing {
	
	private double temp;
	private int iter;
	private double cooling;
	private Map map;

	public SimulatedAnnealing(double startingTemperature, int iterations, double coolingRate, Map map) {
		// TODO Auto-generated constructor stub
		this.temp = startingTemperature;
		this.iter = iterations;
		this.cooling = coolingRate;
		this.map = map;
	}
    public static double acceptanceProbability(int energy, int newEnergy, double temperature) {
        // If the new solution is better, accept it
        if (newEnergy < energy) {
            return 1.0;
        }
        // If the new solution is worse, calculate an acceptance probability
        return Math.exp((energy - newEnergy) / temperature);
    }
	public boolean findSolution(){
		Tour currentSolution = new Tour(map);
        currentSolution.generateIndividual();
        
        System.out.println("Initial solution distance: " + currentSolution.getDistance());

        
        Tour best = new Tour(currentSolution.getTour());
        
        while (temp > 1) {
        	
            Tour newSolution = new Tour(currentSolution.getTour());

            
            int tourPos1 = (int) (newSolution.tourSize() * Math.random());
            int tourPos2 = (int) (newSolution.tourSize() * Math.random());
            Node citySwap1 = newSolution.getCity(tourPos1);
            Node citySwap2 = newSolution.getCity(tourPos2);

            newSolution.setCity(tourPos2, citySwap1);
            newSolution.setCity(tourPos1, citySwap2);
            
            int currentEnergy = currentSolution.getDistance();
            int neighbourEnergy = newSolution.getDistance();

            if (acceptanceProbability(currentEnergy, neighbourEnergy, temp) > Math.random()) {
                currentSolution = new Tour(newSolution.getTour());
            }

            if (currentSolution.getDistance() < best.getDistance()) {
                best = new Tour(currentSolution.getTour());
            }
            
            // Cool system
            temp *= 1-cooling;
        }

        System.out.println("Final solution distance: " + best.getDistance());
        System.out.println("Tour: " + best);
		return false;
	}
}

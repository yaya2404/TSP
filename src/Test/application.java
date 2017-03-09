package Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import Algorithm.AStarSearch;
import Algorithm.SimulatedAnnealing;
import Utility.Map;
import Utility.Node;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import edu.princeton.cs.algs4.*;

public class application extends Application {

	@Override
	public void start(Stage primaryStage) {
		File f = new File(".");
		f = new File(f.getAbsolutePath() + "/Maps");
		FileChooser fc = new FileChooser();
		fc.setInitialDirectory(f);
		File file = fc.showOpenDialog(primaryStage);
		Map map = new Map(file);
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String input = null;
		System.out.println("1. A* Search\n2. Simulated Annealing");
		try {
			input = reader.readLine();
			if(input.equalsIgnoreCase("1")){
				AStarSearch test = new AStarSearch(map);
				test.findPath();
				test.printPath();
			}else if(input.equalsIgnoreCase("2")){
				
				int temperature = 0;
				double coolingrate = 0;
				ArrayList<Double> values;
				System.out.println("Type in a temperature and cooling rate in the form: x,y");
				String[] inp = reader.readLine().split(",");
				temperature = Integer.valueOf(inp[0]);
				coolingrate = Double.valueOf(inp[1]);
				SimulatedAnnealing test2 = new SimulatedAnnealing(temperature,coolingrate,map);
				test2.findSolution();
				values = test2.getValues();
				final NumberAxis xAxis = new NumberAxis();
		        final NumberAxis yAxis = new NumberAxis();
		        xAxis.setLabel("Solution");
		        yAxis.setLabel("Path Cost");
		        final LineChart<Number,Number> lineChart = new LineChart<Number,Number>(xAxis,yAxis);
		        lineChart.setTitle("Simulated Annealing");
		        //XYChart.Series<Number, Number> series = new XYChart.Series<Number,Number>();
		        XYChart.Series<Number, Number> series = test2.getSeries();
		        series.setName("Cost of Path");
		        /*
		        for(int i = 0; i < values.size(); i++){
		        	series.getData().add(new XYChart.Data<Number,Number>(i, values.get(i)));
		        }
		        */
		        lineChart.getData().add(series);
		        Scene scene  = new Scene(lineChart,1280, 960);
		        primaryStage.setScene(scene);
		        primaryStage.show();
			}else{
				System.out.println("Error");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/*
		
		test.findPath();
		*/
		//
		//test2.findSolution();
		
	}

	public static void main(String[] args) {
		launch(args);
	}
}

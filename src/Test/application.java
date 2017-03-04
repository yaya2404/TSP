package Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

import Algorithm.AStarSearch;
import Algorithm.SimulatedAnnealing;
import Utility.Map;
import Utility.Node;
import javafx.application.Application;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class application extends Application {

	@Override
	public void start(Stage primaryStage) {
		File f = new File(".");
		f = new File(f.getAbsolutePath() + "/Maps");
		FileChooser fc = new FileChooser();
		fc.setInitialDirectory(f);
		File file = fc.showOpenDialog(primaryStage);
		Map map = new Map(file);
		System.out.println("Select an algorithm:");
		System.out.println("1: AStar\n2:SimulatedAnnealing");
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String in = null;
		try {
			in = reader.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(in.equals("1")){
			AStarSearch test = new AStarSearch(map);
			test.findPath();
		}else{
			SimulatedAnnealing test = new SimulatedAnnealing(1000,1,0.003,map);
			test.findSolution();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}

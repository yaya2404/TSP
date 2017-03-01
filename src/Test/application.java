package Test;

import java.io.File;

import Utility.Map;
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
		System.out.println(map);
	}

	public static void main(String[] args) {
		launch(args);
	}
}

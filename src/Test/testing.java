package Test;

import java.io.IOException;

import Utility.Map;
import Utility.mapBuilder;

public class testing {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		mapBuilder test = new mapBuilder();
		try {
			test.create10CitiesMaps();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

package _2048;

import java.util.ArrayList;


public class Console{
    
	
	//for testpurposes
	public static void printValues(ArrayList<Integer> values, int rows, int columns){
		System.out.println(" ----- Waarden -------");
		int counter = 0;
		for (int i = 0; i < rows; i++){
			for (int j = 0; j< columns; j++){	
				System.out.print(values.get(counter) + " - ");
				counter++;
			}
			System.out.println();
		}
		System.out.println(" --- Einde Waarden ----");
			
	}
	public static void printValues(String string, ArrayList<Integer> values, int rows, int columns){
		System.out.println(" ----- Waarden ( "+string+"  )-------");
		int counter = 0;
		for (int i = 0; i < rows; i++){
			for (int j = 0; j< columns; j++){	
				System.out.print(values.get(counter) + " - ");
				counter++;
			}
			System.out.println();
		}
		System.out.println(" --- Einde Waarden ----");
			
	}
}
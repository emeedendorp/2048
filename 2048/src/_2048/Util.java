package _2048;

import java.util.ArrayList;


public class Util{
    
	public static ArrayList<ArrayList<Integer>> splitArray ( ArrayList<Integer> values, int parts){
		ArrayList<ArrayList<Integer>> returnList = new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> row = new ArrayList<Integer>();
		int columns = values.size() / parts;
		int counter = 0;
		for (int i=0 ; i < parts; i++){
			for (int j = 0; j < columns; j ++){
				row.add(values.get(counter));
				counter++;
			}
			returnList.add(row);
			row = new ArrayList<Integer>();
		}
		return returnList;
    }

	public static  ArrayList<ArrayList<Integer>> transpose(ArrayList<ArrayList<Integer>> list){
		ArrayList<ArrayList<Integer>> bufferList = new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> row = new ArrayList<Integer>();
		// initialize arrays and write first value;
		row = list.get(0);
		for (int j = 0; j < row.size(); j++) {
			ArrayList<Integer> bufferRow = new ArrayList<Integer>();
			bufferRow.add(row.get(j));
			bufferList.add(bufferRow);
		}
		// write next values
		for (int i = 1; i < list.size(); i++) {
			row = list.get(i);
			for (int j = 0; j < row.size(); j++) {
				ArrayList<Integer> bufferRow = bufferList.get(j);
				bufferRow.add(row.get(j));
				bufferList.set(j, bufferRow);
			}
		}
		return bufferList;
		
	}
	
	public static ArrayList<Integer> mergeArray(ArrayList<ArrayList<Integer>> list){
		ArrayList<Integer> returnList = new ArrayList<Integer>();
		for (int i=0 ; i< list.size(); i++){
			ArrayList<Integer> row = list.get(i);
			for (int j = 0 ; j < row.size(); j++){
				returnList.add(row.get(j));
			}
		}
		return returnList;
	}

}
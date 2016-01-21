package _2048;

import java.util.ArrayList;


public class Sort{
    
	public static ArrayList<Integer> right ( ArrayList<Integer> row){
    	ArrayList<Integer> newRow = new ArrayList<Integer>();
    	// get zeros
    	for (int i = 0; i < row.size(); i ++){
    		if (row.get(i)==0){
    			newRow.add(0);
    		}
    	}
    	// get all nonzero values
    	for (int i = 0; i < row.size(); i ++){
    		if (row.get(i)!=0){
    			newRow.add(row.get(i));
    		}
    	}
    	newRow = sumRight(newRow);
    	return newRow;
 
    }
	private static ArrayList<Integer> sumRight(ArrayList<Integer> row){
		ArrayList<Integer> newRow = new ArrayList<Integer>();
			int size = row.size()-1;
			int value1 = row.get(size);
			int value2 = row.get(size-1);
			// --- saves values in reverse order
			//value1
			if (value1 == value2){
				newRow.add(value1*2);
				value1 = 0;	
			}
			else{
				newRow.add(value1);			
				value1 = value2;
			}
			//value2 until before last
			for (int i = size-2; i >= 0; i--){
				value2 = row.get(i);
				if (value1 == value2){
					newRow.add(value1*2);
					value1= 0;
				}
				else {
					newRow.add(value1);
					value1 = value2;
				}
			}
			//last value
			newRow.add(value1);
			// we have the row in reverse order from what we want, not separated zeros yet
			// separate zeros and add it last (still reversed row)
			ArrayList<Integer> bufferRow = new ArrayList<Integer>();
			//zeros
			for (int j=0;j<newRow.size();j++){
				if (newRow.get(j)==0){
					bufferRow.add(0);
				}
			}
			//nonzeros
			for (int j=newRow.size()-1;j>=0;j--){
				if (newRow.get(j)!=0){
					bufferRow.add(newRow.get(j));
				}
			}
			newRow= bufferRow;
		return newRow;
	}
	
	

	public static ArrayList<Integer> left( ArrayList<Integer> row){
		ArrayList<Integer> newRow = new ArrayList<Integer>();
		// get all nonzero values
		for (int i = 0; i < row.size(); i ++){
			if (row.get(i)!=0){
				newRow.add(row.get(i));
			}
		}
		//get the zeros
		for (int i = 0; i < row.size(); i ++){
			if (row.get(i)==0){
				newRow.add(0);
			}
		}
		// sum equal values
		// fill empty spot with zero
		row = sumValue(newRow);
		//reset array
		newRow = new ArrayList<Integer>();
		// get all values
		// get all nonzero numbers again
		for (int i = 0; i < row.size(); i ++){
			if (row.get(i)!=0){
				newRow.add(row.get(i));
			}
		}
		//get all zeros
		for (int i = 0; i < row.size(); i ++){
			if (row.get(i)==0){
				newRow.add(0);
			}
		}
		return newRow;
	}
	

	

	private static ArrayList<Integer> sumValue(ArrayList<Integer> row){
		ArrayList<Integer> newRow = new ArrayList<Integer>();
			int value1 = row.get(0);
			int value2 = row.get(1);
			//value1
			if (value1 == value2){
				newRow.add(value1*2);
				value1 = 0;	
			}
			else{
				newRow.add(value1);			
				value1 = value2;
			}
			//value2 until before last	
			for (int i = 2; i < row.size(); i++){
				value2 = row.get(i);
				//if previous value is equal, double current value
				if (value1 == value2){
					newRow.add(value1*2);
					value1= 0;
				}
				//else keep value the same 
				else {
					newRow.add(value1);
					value1 = value2;
				}
			}
			//last value
			newRow.add(value1);
		return newRow;
	}


}
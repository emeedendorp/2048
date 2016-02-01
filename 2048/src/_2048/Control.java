package _2048;

import java.util.ArrayList;

import javax.swing.JPanel;

public class Control extends JPanel {
	private static final long serialVersionUID = 1L;

	Settings settings;
	ArrayList<Integer> values;
	int rows, columns, score;
	boolean started = false;

	public Control() {
		score = 0;
		settings = new Settings();
		settings.applySettingsToView(this);
		values = new ArrayList<Integer>(rows * columns);
		// add array with value 0
		for (int i = 0; i < rows * columns; i++) {
			values.add(0);
		}
		// add a value of 2 to a random spot
		int startvalue1 = (int) (Math.random() * rows * rows);
		values.set(startvalue1, 2);
		// add another value of 2 to a random spot, different from previous
		int startvalue2 = (int) (Math.random() * rows * columns);
		while (startvalue1 == startvalue2) {
			startvalue2 = (int) (Math.random() * rows * columns);
		}
		values.set(startvalue2, 2);
	}

	private void setScore() {
		int score = 0;
		for (int i = 0; i < values.size(); i++) {
			score += values.get(i);
		}
		this.score = score;
	}

	public ArrayList<Integer> swipe(int direction) {
		GameModes mode = new GameModes(1);
		boolean addNewValue = checkForNewNumber(direction, values);
		if (checkFull()) {
			System.out.print(values + " -> ");
			System.out.println("No more moves.");
		}
		ArrayList<ArrayList<Integer>> rowList = makeRows();
		if (direction < 2) {
			rowList = makeRows();
		} else {
			rowList = makeVertRows();
		}
		ArrayList<ArrayList<Integer>> returnList = new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> row = new ArrayList<Integer>();
		for (int i = 0; i < rowList.size(); i++) {
			row = rowList.get(i);
			if (direction == 0) {
				row = Sort.left(row);
			} else if (direction == 1) {
				row = Sort.right(row);
			} else if (direction == 2) {
				row = Sort.left(row);
			} else if (direction == 3) {
				row = Sort.right(row);
			}
			returnList.add(row);
		}
		if (direction > 1) {
			returnList = transpose(returnList);
		}
		values = convertToList(returnList);
		// check if anything changes -
		if (addNewValue) {
			addNewValue();
		}
		setScore();
		repaint();
		return values;
	}

	private boolean checkForNewNumber(int direction, ArrayList<Integer> values) {
		boolean status = false;
		int counter = 0;
		ArrayList<Integer> row = new ArrayList<Integer>();
		//check if right or down has any effect
		if ((direction == 1) || (direction == 3)){
			if (direction == 3){
				values = Util.mergeArray(Util.transpose(Util.splitArray(values, rows)));
			}
			for (int i = 0; i < rows; i++) {
				row = new ArrayList<Integer>();
				counter = counter + columns-1;
				for (int j = columns-1 ; j >= 0 ; j--){
					row.add(values.get(counter));
					counter--;				
					if (checkForMoves(row)){
						status = true;
					}	
				}
				counter= 0;
			}
		}
		// check if arrow up or left has any effect
		if ((direction == 0) || (direction == 2)) {
			if (direction == 2){
				values = Util.mergeArray(Util.transpose(Util.splitArray(values, rows)));
			}
			for (int i = 0; i < rows; i++) {
				row = new ArrayList<Integer>();
				
				for (int j = 0 ; j < columns ; j++){
					row.add(values.get(counter));
					counter++;
				}
				
				if (checkForMoves(row)){;
					status = true;
				}	

				
			}
		}		
		
		
		//TODO check if swipe up has any effect
		if (direction == 2) {
			/**ArrayList<ArrayList<Integer>> arrayToTranspose = new ArrayList<ArrayList<Integer>>();
			ArrayList<Integer> t_row = new ArrayList<Integer>();
			int t_counter = 0;
			for (int i=0 ; i < rows; i++){
				for (int j = 0; j > columns; j ++){
					t_row.add(values.get(t_counter));
					t_counter++;
				}
				arrayToTranspose.add(t_row);
				t_row = new ArrayList<Integer>();
			}
			arrayToTranspose = transpose(arrayToTranspose);
			**/
		}	
		return status;
	}
	
	private boolean checkForMoves(ArrayList<Integer> values){
			boolean status = false;
			boolean zerostatus = false;
			int lastvalue = values.get(0);
			if (values.get(0)==0){
				zerostatus = true;
			}
			for (int i = 1; i < values.size(); i++){
				if ((zerostatus)&&(values.get(i)!=0)){
					status = true;
				}
				if ((lastvalue == values.get(i))&&(lastvalue!=0)){
					status = true;
				}
				if (values.get(i)!=0){
					lastvalue = values.get(i);
				}
			}
		// status true -> there are moves left
		// status false -> no moves left
		return status;	
	}

	protected boolean checkFull() {
		boolean full = true;
		// check for adjacent duplicates horizontally
		int counter = 0;
		int value1 = 0;
		int value2 = 0;
		// check if there are any zeros left --> return false;
		for (int i = 0; i < values.size(); i++) {
			if (values.get(i) == 0) {
				return false;
			}
		}

		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns - 1; j++) {
				value1 = values.get(counter);
				counter++;
				value2 = values.get(counter);
				if (value1 == value2) {
					full = false;
				}

			}
			counter++;
		}

		// check for adjacent duplicates vertically
		int pos1 = 0;
		int pos2 = columns;
		for (int i = 0; i < values.size() - columns; i++) {
			value1 = values.get(pos1);
			value2 = values.get(pos2);
			if (value1 == value2) {
				full = false;
			}
			pos1++;
			pos2++;
		}
		return full;
	}

	private ArrayList<ArrayList<Integer>> transpose(
			ArrayList<ArrayList<Integer>> list) {
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
				ArrayList<Integer> bufferRij = bufferList.get(j);
				bufferRij.add(row.get(j));
				bufferList.set(j, bufferRij);
			}
		}
		return bufferList;
	}

	private ArrayList<Integer> convertToList(ArrayList<ArrayList<Integer>> list) {
		ArrayList<Integer> rij = new ArrayList<Integer>();
		ArrayList<Integer> newList = new ArrayList<Integer>();
		for (int i = 0; i < list.size(); i++) {
			rij = list.get(i);
			for (int j = 0; j < rij.size(); j++) {
				newList.add(rij.get(j));
			}
		}
		return newList;
	}

	protected ArrayList<ArrayList<Integer>> makeRows() {
		ArrayList<ArrayList<Integer>> rowList = new ArrayList<ArrayList<Integer>>();
		int counter = 0;
		for (int i = 0; i < rows; i++) {
			ArrayList<Integer> rij = new ArrayList<Integer>();
			for (int j = 0; j < columns; j++) {
				rij.add(values.get(counter));
				counter++;
			}
			rowList.add(rij);
		}
		return rowList;
	}

	private ArrayList<ArrayList<Integer>> makeVertRows() {
		ArrayList<ArrayList<Integer>> rowList = new ArrayList<ArrayList<Integer>>();
		// even amount of arrays with amount of columns and fill them with 1
		// value
		int count = 0;
		for (int j = 0; j < columns; j++) {
			ArrayList<Integer> row = new ArrayList<Integer>();
			row.add(values.get(count));
			rowList.add(row);
			count++;
		}

		// for the remainder of rows, get the corresponding array and fill it
		// with 1 value
		for (int i = 1; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				ArrayList<Integer> row = rowList.get(j);
				row.add(values.get(count));
				count++;
				rowList.set(j, row);
			}
		}
		return rowList;
	}

	private void addNewValue() {
		// check if array values is full
		boolean full = true;
		for (int i = 0; i < values.size(); i++) {
			if (values.get(i) == 0) {
				full = false;
			}
		}
		while (!full) {
			int newPos = (int) (Math.random() * values.size());
			if (values.get(newPos) == 0) {
				values.set(newPos, 2);
				full = true;
			}
		}
	}

	int getRows() {
		return rows;
	}

	void setRows(int rows) {
		this.rows = rows;
	}

	int getColumns() {
		return columns;
	}

	void setColumns(int columns) {
		this.columns = columns;
	}
	public int getScore(){
		return score;
	}
}
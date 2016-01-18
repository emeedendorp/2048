package _2048;

import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.JPanel;

public class View extends JPanel
{
	private static final long serialVersionUID = 1L;
	
	Settings settings;
	ArrayList<Integer> values;
	int rows,columns, score;
  
	  public View() {

		  settings = new Settings();
		  settings.applySettingsToView(this);
		  values = new ArrayList<Integer>(rows*columns);
		  //add array with value 0
		  for (int i = 0; i <rows * columns; i++) {
			  values.add(0);
	    }
		  //add a value of 2 to a random spot
	    int startvalue1 = (int)(Math.random() * rows * rows);
	    values.set(startvalue1, 2);
	    	//add another value of 2 to a random spot, different from previous
	    int startvalue2 = (int)(Math.random() * rows * columns);
	    while (startvalue1 == startvalue2) {
	      startvalue2 = (int)(Math.random() * rows * columns);
	    }
	    values.set(startvalue2, 2);
	  }
  
  public void paintComponent(Graphics g)
  {
    super.paintComponent(g);  
    paintBoard(g);
  }
  

  public void paintBoard(Graphics g)
  { 
	Tile tile = new Tile();
	settings.applySettingstoTile(tile);
	for (int i=0; i < values.size(); i++){
		//check for new row
		if ((i%columns==0)&&(i!=0)){
			int back = (columns) * tile.getWidth();
			tile.setX(tile.getX()-back);
			tile.setY(tile.getY()+tile.getHeight());
		}
		tile.setValue(values.get(i));
		tile.draw(g);
		int x = tile.getX()+tile.getWidth();
		tile.setX(x);
	}
	score = getScore();
  }

  private int getScore(){
		int score=0;
		for (int i = 0; i < values.size();i++){
			score+=values.get(i);
		}
		return score;
  }
	/** oude setup
	//voor de gradients (niet uitgetest of dit echt nodig is)  
	Graphics2D g2 = (Graphics2D) g;
	g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
	RenderingHints.VALUE_ANTIALIAS_ON);
    int aantalhokjes = rijen * kolommen;
    int startx = settings.x;
    int starty = settings.y;
    Font font1 = new Addfont().createFont();
    g.setFont(font1);
    settings.fm = g.getFontMetrics();
    for (int i = 0; i < aantalhokjes; i++)
    {
     g2.setColor(Color.gray);
     
     
    
     g2.drawRect(startx, starty, settings.breedte / kolommen, settings.hoogte / rijen);
     g2.fillRect(startx, starty, (settings.breedte / kolommen)-2, (settings.hoogte / rijen)-2); 
      if (((Integer)waarden.get(i)).intValue() != 0)
      {
        String word = waarden.get(i)+"";
        
        int woordlengte = settings.fm.stringWidth(word);
        int regelhoogte = settings.fm.getHeight();
        int startpositiex = startx + (settings.breedte / kolommen - woordlengte) / 2;
        int startpositiey = starty + (settings.hoogte / rijen + regelhoogte) / 2;
        g2.setPaint(settings.fontcolor);
        g2.drawString(word, startpositiex, startpositiey);
      }
      startx += settings.breedte / kolommen;
      if (i % kolommen == kolommen - 1)
      {
        startx = settings.x;
        starty += settings.hoogte / rijen;
      }
      }
      **/
    

// **** NEW SETUP ******
  public void swipe(int direction){
		if (checkFull()){
			System.out.print(values+" -> ");
			System.out.println("No more moves.");
		}
	  	ArrayList<ArrayList<Integer>> rowList = makeRows();
		if (direction < 2){
			rowList = makeRows();
		}
		else{
			rowList = makeVertRows();
		}
		ArrayList<ArrayList<Integer>> returnList = new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> row = new ArrayList<Integer>();
		for (int i = 0; i < rowList.size();i++){
			row = rowList.get(i);
			if (direction == 0){
			row = sortLeft(row);
			}
			else if (direction == 1){
			row = sortRight(row);	
			}
			else if (direction == 2){	
			row = sortLeft(row);
			}
			else if (direction == 3){
			row = sortRight(row);
			}
			returnList.add(row);
		}
		if (direction > 1){
			returnList = transpose(returnList);
		}
		values = convertToList(returnList);
		//check if enything changes - 
		if (checkForNewNumber(direction)){
		addNewValue();
		}
		repaint();		
	}
  
  private boolean checkForNewNumber(int richting) {
		// TODO Auto-generated method stub
	  int counter = 0;
	  boolean status = true;
	  boolean addNumber = true;
	  boolean fullRows = true;
	  int currentValue = -1;
	  if (richting == 0){
		  //check if swipe left has any effect
		  for (int i = 0; i < rows; i++){
			  int value = values.get(counter);
			  currentValue = -1;
			  for (int j = 0; j < columns; j++){
				  if (value == 0) {
					  status = false;
				  }
				  if ((value !=0) && (status == false)){
					  System.out.println("status set to false(2)");
					  return false;
				  }
				  if ((value == currentValue)&&(value != 0)){	
					  System.out.println(value + " " + currentValue);
					  System.out.println("status set to false(3)");
					  return false;
				  }
				  if ((j == columns-1) && value ==0){
					  fullRows = false;
					  
				  }
				  currentValue = values.get(counter);
				  System.out.println(currentValue);
				  counter++;			  
			  }
		  }
		  if (fullRows){
			  addNumber = false;
			  System.out.println("status set to false(4)");
		  }
	  }
	  if (richting == 1){
		  //check if swipe right has any effect
	  }
	  if (richting == 2){
		  //check if swipe up has any effect
	  }
	  if (richting == 3){
		  //check if swipe down has any effect
	  }
	  return addNumber;		
	}

protected boolean checkFull(){
	  boolean full = true;
	  //check for adjacent duplicates horizontally
	  int counter = 0;
	  int value1 = 0;
	  int value2 = 0;
	  //check if there are any zeros left --> return false;
	  for (int i =0; i < values.size(); i++){
		  if (values.get(i)==0){
			  return false;
		  }
	  }
	  
	  
	  for (int i =0; i < rows; i++){
		  for (int j = 0 ; j < columns-1; j++){
			  value1= values.get(counter);
			  counter++;
			  value2= values.get(counter);
			  if (value1 == value2){
				  full= false;
			  } 
			  
		  }
		counter++;
	  }
	  
	  //check for adjacent duplicates vertically
	  int pos1 = 0;
	  int pos2= columns;
	  for (int i=0; i < values.size()-columns;i++){
		  value1 = values.get(pos1);
		  value2 = values.get(pos2);
		  if (value1==value2){
			  full=false;
		  }
		  pos1++;
		  pos2++;
	  }
	return full;	
  }

  private ArrayList<ArrayList<Integer>> transpose(ArrayList<ArrayList<Integer>>  list){
	  ArrayList<ArrayList<Integer>>  bufferList = new ArrayList<ArrayList<Integer>>();
	  ArrayList<Integer> row = new ArrayList<Integer>();
	  	  //initialize arrays and write first value;
		  row = list.get(0);
		  for (int j = 0; j<row.size(); j++){
			  ArrayList<Integer> bufferRow = new ArrayList<Integer>();
			  bufferRow.add(row.get(j));
			  bufferList.add(bufferRow);
		  }
		  // write next values
		 for (int i = 1; i< list.size();i++){ 
			 row = list.get(i);
			  for (int j = 0; j<row.size(); j++){
				  ArrayList<Integer> bufferRij = bufferList.get(j);
				  bufferRij.add(row.get(j));
				  bufferList.set(j, bufferRij);
			  }
	  }
	return bufferList;  
  }
  
  private ArrayList<Integer> convertToList(ArrayList<ArrayList<Integer>> list){
	  	ArrayList<Integer> rij = new ArrayList<Integer>();
	  	ArrayList<Integer> newList = new ArrayList<Integer>();
	  	for (int i = 0; i< list.size();i++){
	  		rij = list.get(i);
	  		for (int j=0; j < rij.size(); j++){
	  			newList.add(rij.get(j));
	  		}
	  	}  	
	  	return newList;
  }
  
protected ArrayList<ArrayList<Integer>> makeRows(){
		  ArrayList<ArrayList<Integer>> rowList = new ArrayList<ArrayList<Integer>>();
		  int counter = 0;
		  for (int i = 0; i< rows; i++){
			  ArrayList<Integer> rij = new ArrayList<Integer>();
			  for (int j = 0; j < columns; j++){
				  rij.add(values.get(counter));
				  counter++;
			  }
			  rowList.add(rij);
		  }
		  return rowList;
	}
private ArrayList<ArrayList<Integer>> makeVertRows(){
	  ArrayList<ArrayList<Integer>> rowList = new ArrayList<ArrayList<Integer>>();
	  	//even amount of arrays with amount of columns and fill them with 1 value
	  	int count = 0;
	  	for (int j = 0; j< columns; j++){
		  ArrayList<Integer> row = new ArrayList<Integer>();
		  row.add(values.get(count));
		  rowList.add(row);
		  count++;
		}  
	  	
	  	//for the remainder of rows, get the corresponding array and fill it with 1 value
	  for (int i = 1; i < rows; i++){
	  		for (int j = 0; j< columns; j++){
	  		ArrayList<Integer>row = rowList.get(j);
			  row.add(values.get(count));
			  count++;
			  rowList.set(j, row);
	  		}
		  }
	  return rowList;
}

private ArrayList<Integer> sortLeft( ArrayList<Integer> row){
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

protected ArrayList<Integer> sortRight( ArrayList<Integer> row){
	ArrayList<Integer> newRow = new ArrayList<Integer>();
	row = sumRight(row);
	// get zeros
	for (int i = 0; i < row.size(); i ++){
		if (row.get(i)==0){
			newRow.add(0);
		}
	}
	// get all values
	for (int i = 0; i < row.size(); i ++){
		if (row.get(i)!=0){
			newRow.add(row.get(i));
		}
	}
	return newRow;
}

private void addNewValue(){
	//check if array values is full
	boolean full = true;
	for (int i = 0; i < values.size(); i++){
		if (values.get(i)== 0){
			full = false;
		}
	}
	while (!full){
		int newPos = (int)(Math.random()*values.size());
		if (values.get(newPos)==0){
			values.set(newPos, 2);
			full = true;
		}
	}
}
private ArrayList<Integer> sumValue(ArrayList<Integer> row){
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
private ArrayList<Integer> sumRight(ArrayList<Integer> row){
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
}
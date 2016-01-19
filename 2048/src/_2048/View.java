package _2048;

import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;

public class View extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	Settings settings;
	ArrayList<Integer> values;
	int columns, rows, score;
	
	public View(Control control) {
		this.settings = control.settings;
		this.values = control.values;
		this.columns = control.columns;
		this.rows = control.rows;	
		this.score = control.score;
		// TODO Auto-generated constructor stub
		
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
		//TODO fix score and control.getScore
		//score = control.getScore();
	  }


}

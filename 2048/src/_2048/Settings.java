package _2048;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.GradientPaint;

public class Settings {
	  int x,y,width,height,rows, columns,newx, newy, iterations;
	  Color background,foreground,fontcolor;
	  FontMetrics fm;
	  GradientPaint redtowhite, bluetogreen,test1,test2;
	  Colorscheme colorscheme;
	  float color1,color2, color3;

	public Settings(){
		  
		//settings for tile
		color1 = 0.9f;
		color2 = 0.6f;
		color3 = 0.3f;
		iterations = 9;
		colorscheme = new Colorscheme(color1, color2, color3, iterations);
		x = 10;
		y = 10;
		width = 70;
		height = 70;
		foreground = Color.BLACK;
		fontcolor = Color.white;
		  
		// settings for view  
		rows = 4;
		columns = 4;
		
		  
		// --- settings not used
		// add new gradient
		// fill RoundRectangle2D.Double
		redtowhite = new GradientPaint(0, 40, Color.gray, 0,100, Color.white, true);
		test1 = new GradientPaint(0, 0, Color.gray, 0,50, Color.white, true);
		test2 = new GradientPaint(0, 150, Color.gray, 0,50, Color.white, true);
		bluetogreen = new GradientPaint(x, y, Color.blue, 200, y, Color.green);
	}
	void applySettingstoTile(Tile tile){
		tile.setX(x);
		tile.setY(y);
		tile.setWidth(width);
		tile.setHeight(height);
		tile.setFontcolor(fontcolor);
		tile.setBackground(background);	
		tile.setColorscheme(colorscheme);
	}
	void applySettingsToView(View view){
		view.setRows(rows);
		view.setColumns(columns);
	}
}
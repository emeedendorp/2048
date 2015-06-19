package _2048;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.GradientPaint;

public class Settings {
	  int x,y,breedte,hoogte,rijen, kolommen,newx, newy, iterations;
	  Color background,foreground,fontcolor;
	  FontMetrics fm;
	  GradientPaint redtowhite, bluetogreen,test1,test2;
	  Colorscheme colorscheme;
	  float color1,color2, color3;

	public Settings(){
		  
		//settings voor de tile
		color1 = 0.9f;
		color2 = 0.6f;
		color3 = 0.3f;
		iterations = 9;
		colorscheme = new Colorscheme(color1, color2, color3, iterations);
		x = 10;
		y = 10;
		breedte = 70;
		hoogte = 70;
		foreground = Color.BLACK;
		fontcolor = Color.white;
		  
		// settings voor view  
		rijen = 4;
		kolommen = 4;
		
		  
		// --- niet gebruikte settings
		//aanmaken nieuwe gradient
		// fill RoundRectangle2D.Double
		redtowhite = new GradientPaint(0, 40, Color.gray, 0,100, Color.white, true);
		test1 = new GradientPaint(0, 0, Color.gray, 0,50, Color.white, true);
		test2 = new GradientPaint(0, 150, Color.gray, 0,50, Color.white, true);
		bluetogreen = new GradientPaint(x, y, Color.blue, 200, y, Color.green);
	}
	void applySettingstoTile(Tile tile){
		tile.setX(x);
		tile.setY(y);
		tile.setBreedte(breedte);
		tile.setHoogte(hoogte);
		tile.setFontcolor(fontcolor);
		tile.setBackground(background);	
		tile.setColorscheme(colorscheme);
	}
	void applySettingsToView(View view){
		view.setRijen(rijen);
		view.setKolommen(kolommen);
	}
}
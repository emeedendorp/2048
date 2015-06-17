package _2048;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.GradientPaint;
import java.util.ArrayList;

public class Settings {
	  int x,y,breedte,hoogte,rijen, kolommen,newx, newy;
	  Color background,foreground,fontcolor;
	  FontMetrics fm;
	  GradientPaint redtowhite, bluetogreen,test1,test2;
	  Colorscheme colorscheme;

	public Settings(){
		  
		//settings voor de tile
		colorscheme = new Colorscheme();
		x = 60;
		y = 60;
		breedte = 400;
		hoogte = 400;
		foreground = Color.BLACK;
		fontcolor = Color.yellow;
		  
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
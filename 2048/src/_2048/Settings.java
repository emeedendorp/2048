package _2048;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.GradientPaint;
import java.util.ArrayList;

public class Settings {
	  int x,y,breedte,hoogte,rijen, kolommen,newx, newy;
	  Color background,foreground,fontcolor;
	  ArrayList<Integer> waarden;
	  FontMetrics fm;
	  GradientPaint redtowhite, bluetogreen,test1,test2;

	public Settings(){
		  x = 40;
		  y = 40;
		  breedte = 400;
		  hoogte = 400;
		  rijen = 4;
		  kolommen = 4;
		  foreground = Color.BLACK;
		  fontcolor = Color.yellow;
		  waarden = new ArrayList<Integer>(rijen * kolommen);
			//aanmaken nieuwe gradient
		    // fill RoundRectangle2D.Double
		    redtowhite = new GradientPaint(0, 40, Color.gray, 0,100,
		        Color.white, true);
		    test1 = new GradientPaint(0, 0, Color.gray, 0,50,
			        Color.white, true);
		    test2 = new GradientPaint(0, 150, Color.gray, 0,50,
			        Color.white, true);
		    bluetogreen = new GradientPaint(x, y, Color.blue, 200, y,
			        Color.green);
	}
}
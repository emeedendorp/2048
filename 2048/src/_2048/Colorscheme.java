package _2048;

import java.awt.Color;
import java.util.HashMap;

public class Colorscheme {
	HashMap<Integer, Color> hash = new HashMap<>();

	public Colorscheme(){
	hash.put(0, Color.yellow);	
	hash.put(2, Color.white);
	hash.put(4, Color.gray);
	hash.put(8, Color.darkGray);
	hash.put(16, Color.black);
	hash.put(32, Color.pink);
	hash.put(64, Color.orange);
	hash.put(128, Color.RED);
	hash.put(256, Color.blue);
	}
	
	Color getColor(int value){
		Color color;
		if (hash.get(value) !=null){
			color = hash.get(value);
		}
		else{
			color = Color.GREEN;
		}
		return color ;
		
	}
	void setColor(int value, Color color){
		hash.put(value, color);
	}
	
	public Colorscheme(int hue){
	hash.put(0, Color.yellow);	
	hash.put(2, Color.white);
	hash.put(4, Color.gray);
	hash.put(8, Color.darkGray);
	hash.put(16, Color.black);
	hash.put(32, Color.pink);
	hash.put(64, Color.orange);
	hash.put(128, Color.RED);
	hash.put(256, Color.blue);
	}	
}

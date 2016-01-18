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
	public Colorscheme(float hue1, float hue2, float hue3, int iterations){
		makeMono(hue1, hue2, hue3, iterations);
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

	
	private void makeMono(float hue, float hue2, float hue3, int iterations){
		float saturationIncrease = 0.9f / iterations ;
		int breakpoint = iterations;
		if (hue2 == 0){
			hue2 = hue;
			hue3 = 0; //third value ignored when no second value present
		}
		else{
			saturationIncrease = saturationIncrease /2;
			breakpoint = iterations /2;
		}
		if (hue3 == 0){
			hue3 = hue;		
		}
		else{
			saturationIncrease = saturationIncrease / 1.5f;
			breakpoint = iterations/3;
		}
		
		
		float saturation = 0.1f; //saturation
		float brightness = 0.8f; //brightness
		Color myRGBColor = Color.getHSBColor(hue, saturation, brightness);
		hash.put(0, myRGBColor);
		for (int i = 0;i < iterations; i++){
			int index = (int) Math.pow(2, (i+1));
			saturation += saturationIncrease;
			myRGBColor = Color.getHSBColor(hue, saturation, brightness);
			hash.put(index, myRGBColor);
			if(i==breakpoint){
				breakpoint +=breakpoint;
				hue = hue2;
				hue2 = hue3;
			}	
		}
	
	}
	

}

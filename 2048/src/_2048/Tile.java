package _2048;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;



public class Tile {
	int x,y, height, width, value;
	Color background, fontcolor;
	Font font;
	FontMetrics fm;
	String valueToDisplay;
	Colorscheme colorscheme;


	public Tile(){
		font = new Addfont().createFont();
		x = 10;
		y = 10;
		height = 50;
		width = 50;
		background = Color.gray;
		fontcolor = Color.yellow;
		value = 122;	
		colorscheme = new Colorscheme();
	}
	
	void draw(Graphics g){
	    g.setFont(font);
	    fm = g.getFontMetrics();
	    if (value!=0){
	    	valueToDisplay = Integer.toString(value);
	    	}
	    else{
	    	valueToDisplay = "";
	    }
	    Color currentColor = colorscheme.getColor(value);
        int wordLength = fm.stringWidth(valueToDisplay);
        int stringHeight = fm.getHeight();
        g.setColor(currentColor);
        g.fillRect(x, y, width, height);
        g.setColor(fontcolor);
        int startx = x + (width - wordLength) / 2;
        int starty = y + (height + stringHeight) / 2;
        g.drawString(valueToDisplay, startx, starty);
	}

	int getX() {
		return x;
	}

	void setX(int x) {
		this.x = x;
	}

	int getY() {
		return y;
	}

	void setY(int y) {
		this.y = y;
	}

	int getHeight() {
		return height;
	}

	void setHeight(int hoogte) {
		this.height = hoogte;
	}

	int getWidth() {
		return width;
	}

	void setWidth(int breedte) {
		this.width = breedte;
	}

	Color getBackground() {
		return background;
	}

	void setBackground(Color background) {
		this.background = background;
	}

	Color getFontcolor() {
		return fontcolor;
	}

	void setFontcolor(Color fontcolor) {
		this.fontcolor = fontcolor;
	}

	Font getFont() {
		return font;
	}

	void setFont(Font font) {
		this.font = font;
	}

	int getValue() {
		return value;
	}

	void setValue(int value) {
		this.value = value;
	}

	FontMetrics getFm() {
		return fm;
	}

	void setFm(FontMetrics fm) {
		this.fm = fm;
	}

	Colorscheme getColorscheme() {
		return colorscheme;
	}

	void setColorscheme(Colorscheme colorscheme) {
		this.colorscheme = colorscheme;
	}
}

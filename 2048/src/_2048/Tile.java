package _2048;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;



public class Tile {
	int x,y, hoogte, breedte, value;
	Color background, fontcolor;
	Font font;
	FontMetrics fm;
	String valueToDisplay;
	Colorscheme colorscheme;


	public Tile(){
		font = new Addfont().createFont();
		x = 10;
		y = 10;
		hoogte = 50;
		breedte = 50;
		background = Color.gray;
		fontcolor = Color.yellow;
		value = 122;	
		colorscheme = new Colorscheme();
	}
	
	void teken(Graphics g){
	    g.setFont(font);
	    fm = g.getFontMetrics();
	    valueToDisplay = Integer.toString(value);
	    Color currentColor = colorscheme.getColor(value);
        int woordlengte = fm.stringWidth(valueToDisplay);
        int regelhoogte = fm.getHeight();
        g.setColor(currentColor);
        g.fillRect(x, y, breedte, hoogte);
        g.setColor(fontcolor);
        int startx = x + (breedte - woordlengte) / 2;
        int starty = y + (hoogte + regelhoogte) / 2;
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

	int getHoogte() {
		return hoogte;
	}

	void setHoogte(int hoogte) {
		this.hoogte = hoogte;
	}

	int getBreedte() {
		return breedte;
	}

	void setBreedte(int breedte) {
		this.breedte = breedte;
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

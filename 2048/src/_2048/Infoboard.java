package _2048;

import java.awt.Graphics;
import javax.swing.JButton;
import javax.swing.JPanel;


public class Infoboard extends JPanel{
	private static final long serialVersionUID = 1L;
	JButton test = new JButton("Score"+ "");
	Control control;
	public Infoboard(Control control){
		add(test);
		this.control = control;
	}
	public void paintComponent(Graphics g){
		g.drawString("Score: " + control.score, 225, 225);
	}
}

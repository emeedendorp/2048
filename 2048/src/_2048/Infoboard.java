package _2048;

import java.awt.Graphics;
import javax.swing.JButton;
import javax.swing.JPanel;


public class Infoboard extends JPanel{
	private static final long serialVersionUID = 1L;
	JButton start = new JButton("New Game");
	Control control;
	
	
	public Infoboard(Control control){
		start.addActionListener(new StartButtonHandler(control));
		add(start);

	    start.setFocusable(false); 
		this.control = control;
	}
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.drawString("Score: " + control.getScore(), 10, 50);
	}
}

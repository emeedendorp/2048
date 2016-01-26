package _2048;

import java.awt.Graphics;
import javax.swing.JButton;
import javax.swing.JPanel;


public class Infoboard extends JPanel{
	private static final long serialVersionUID = 1L;
	String buttontext;
	JButton start = new JButton(buttontext);
	Control control;
	
	public Infoboard(Control control){
		start.addActionListener(new StartButtonHandler(control));
		buttontext = "Game starten";
		start.setText(buttontext);
		add(start);
		this.control = control;
	}
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		if(control.started){
		start.setText(buttontext);
		}
		g.drawString("Score: " + control.getScore(), 10, 50);
		
	}
}

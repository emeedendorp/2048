package _2048;

import java.awt.Graphics;
import javax.swing.JButton;
import javax.swing.JPanel;


public class Infoboard extends JPanel{
	private static final long serialVersionUID = 1L;
	JButton test = new JButton("test");
	Control view;
	public Infoboard(Control view){
		add(test);
		this.view = view;
	}
	public void paintComponent(Graphics g){
		g.drawString("Score: " + view.score, 25, 25);
	}
}

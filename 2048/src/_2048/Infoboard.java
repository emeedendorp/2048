package _2048;

import java.awt.Graphics;
import javax.swing.JButton;
import javax.swing.JPanel;


public class Infoboard extends JPanel{
	private static final long serialVersionUID = 1L;
	String buttontext, tenmovestext;
	JButton start = new JButton(buttontext);
	JButton tenmoves = new JButton(tenmovestext);
	Control control;
	GameModes gamemode; 
	
	public Infoboard(Control control){
		start.addActionListener(new StartButtonHandler(control));	
		gamemode = new GameModes(control.settings.gamemode);
		buttontext = "New Game";
		start.setText(buttontext);
		add(start);
		this.control = control;
	}
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		if(control.started){
		start.setEnabled(false);
		}
		g.drawString("Goal: " + gamemode.getText(), 0, 70);
		g.drawString("Score: " + control.getScore(), 10, 50);
		
	}
	
}

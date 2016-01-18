package _2048;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JButton;

public class EnterAction extends AbstractAction{

	private static final long serialVersionUID = 1L;
	JButton testButton;
	
	public EnterAction(JButton testButton){
		this.testButton = testButton;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		System.out.println( "The Enter key has been pressed in Enteraction." );
	}

}

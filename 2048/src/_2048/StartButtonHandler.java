package _2048;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartButtonHandler implements ActionListener {

	Control control;
	
	public StartButtonHandler(Control control){
		this.control = control;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("kh gets started as " + control.started);
		control.started = true;
		System.out.println("kh set started as " + control.started);
		// TODO Auto-generated method stub

	}

}

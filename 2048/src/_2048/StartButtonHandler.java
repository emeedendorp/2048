package _2048;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartButtonHandler implements ActionListener {

	Control control;
	Infoboard infoboard;
	
	public StartButtonHandler(Control control){
		this.control = control;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		control.started = true;	}

}

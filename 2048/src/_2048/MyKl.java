package _2048;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MyKl
  implements KeyListener
{
  Control control;
  View view;
  
  public MyKl(View view, Control control)
  {
    this.control = control;
    this.view = view;
  }
  
  public void keyPressed(KeyEvent e)
  {
	  int code = e.getKeyCode();
	  if (code ==37){//left arrow
		  view.values = control.swipe(0);
	  }
	  if (code ==39){//right arrow
		  view.values = control.swipe(1);
	  }
	  if (code ==38){//up arrow
		  view.values = control.swipe(2);
	  }
	  if (code ==40){//down arrow
		  view.values = control.swipe(3);
	  }
	  view.repaint();
  }
  
  public void keyReleased(KeyEvent e) {}
  
  public void keyTyped(KeyEvent e) {}
}

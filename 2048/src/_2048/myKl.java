package _2048;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class myKl
  implements KeyListener
{
  View view;
  
  public myKl(View view)
  {
    this.view = view;
  }
  
  public void keyPressed(KeyEvent e)
  {
	  int code = e.getKeyCode();
	  if (code ==37){//left arrow
	  this.view.swipe(0);
	  }
	  if (code ==39){//right arrow
	  this.view.swipe(1);
	  }
	  if (code ==38){//up arrow
		  this.view.swipe(2);
	  }
	  if (code ==40){//down arrow
		  this.view.swipe(3);
	  }
  }
  
  public void keyReleased(KeyEvent e) {}
  
  public void keyTyped(KeyEvent e) {}
}

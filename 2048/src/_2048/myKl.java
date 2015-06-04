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
	  if (code ==37){
	  this.view.swipe(0);
	  }
	  if (code ==39){
	  this.view.swipe(1);
	  }
	  if (code ==40){
		  this.view.swipe(2);
	  }
  }
  
  public void keyReleased(KeyEvent e) {}
  
  public void keyTyped(KeyEvent e) {}
}

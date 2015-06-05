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
	  if (code ==37){//pijl naar links
	  this.view.swipe(0);
	  }
	  if (code ==39){//pijl naar rechts
	  this.view.swipe(1);
	  }
	  if (code ==38){//pijl naar boven
		  this.view.swipe(2);
	  }
  }
  
  public void keyReleased(KeyEvent e) {}
  
  public void keyTyped(KeyEvent e) {}
}

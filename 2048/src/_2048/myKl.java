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
    this.view.links();
  }
  
  public void keyReleased(KeyEvent e) {}
  
  public void keyTyped(KeyEvent e) {}
}

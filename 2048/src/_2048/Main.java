package _2048;

import java.awt.Font;
import javax.swing.JFrame;

public class Main
  extends JFrame
{
  private static final long serialVersionUID = 1L;
  public static Font font = null;
  
  public static void main(String[] args)
  {
    JFrame frame = new Main();
    frame.setSize(500, 500);
    frame.setDefaultCloseOperation(3);
    frame.setTitle("2048");
    frame.setContentPane(new Hoofdpaneel());
    frame.setVisible(true);
  }
}
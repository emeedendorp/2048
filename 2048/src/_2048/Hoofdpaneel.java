package _2048;


import java.awt.BorderLayout;
import javax.swing.JPanel;

public class Hoofdpaneel
  extends JPanel
{
  private static final long serialVersionUID = 1L;
  
  public Hoofdpaneel()
  {
    setLayout(new BorderLayout());
    Control view = new Control();  
    Infoboard infoboard = new Infoboard(view);
    //add(infoboard, BorderLayout.NORTH);
    add(view, "Center");
    add(infoboard, "East");
    view.setFocusable(true);       
    view.addKeyListener(new myKl(view));
    //view.addActionListener(new MyKb());
    //view.requestFocus();
  }
}

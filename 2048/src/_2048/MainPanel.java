package _2048;


import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JPanel;

public class MainPanel
  extends JPanel
{
  private static final long serialVersionUID = 1L;
  
  public MainPanel()
  {
    setLayout(new BorderLayout());
    Control control = new Control();  
    View view = new View(control);
    view.setPreferredSize(new Dimension(300,300));
    Infoboard infoboard = new Infoboard(control);
    infoboard.setPreferredSize(new Dimension(200,300));
    add(view, "Center");
    add(infoboard, "East");
    view.setFocusable(true);       
    view.add(new ActionListener(view,control,infoboard));
  }
  


	
}

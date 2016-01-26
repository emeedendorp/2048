package _2048;


import java.awt.BorderLayout;

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
    Infoboard infoboard = new Infoboard(control);
    add(view, "Center");
    add(infoboard, "East");
    view.setFocusable(true);       
    view.add(new ActionListener(view,control,infoboard));
  }
  


	
}

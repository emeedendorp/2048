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
    View view = new View();   
    Infoboard infoboard = new Infoboard(view);
    add(infoboard, BorderLayout.NORTH);
    add(view, BorderLayout.CENTER);
    view.setFocusable(true);   
    view.addKeyListener(new myKl(view));
    view.requestFocus();
  }
}

package _2048;


import java.awt.BorderLayout;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

public class Hoofdpaneel
  extends JPanel
  {
  private static final long serialVersionUID = 1L;
  private static final int IFW = JComponent.WHEN_IN_FOCUSED_WINDOW;
  private static final String MOVE_UP = "move up";
  private static final String MOVE_DOWN = "move down";
  private static final String MOVE_LEFT = "move left";
  private static final String MOVE_RIGHT = "move right";  
  
  public Hoofdpaneel()
  {
    setLayout(new BorderLayout());
    View view = new View();   
    Infoboard infoboard = new Infoboard(view);
    //add(infoboard, BorderLayout.NORTH);
    
    view.setFocusable(true);   
    view.addKeyListener(new myKl(view));

    view.getInputMap(IFW).put(KeyStroke.getKeyStroke("UP"), MOVE_UP);
    view.getInputMap(IFW).put(KeyStroke.getKeyStroke("DOWN"), MOVE_DOWN);
    view.getInputMap(IFW).put(KeyStroke.getKeyStroke("LEFT"), MOVE_LEFT);
    view.getInputMap(IFW).put(KeyStroke.getKeyStroke("RIGHT"), MOVE_RIGHT);    
   	view.getActionMap().put(MOVE_UP, new MoveAction(1));
    view.getActionMap().put(MOVE_DOWN, new MoveAction(1));
    add(view, BorderLayout.CENTER);
    
  }
}

package _2048;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.*;


@SuppressWarnings("serial")
public class ActionListener extends JPanel {

	Control control;
	View view;
	Infoboard infoboard;
	
   public ActionListener(View view, Control control, Infoboard infoboard) {
      ActionMap actionMap = getActionMap();
      int condition = JComponent.WHEN_IN_FOCUSED_WINDOW;
      InputMap inputMap = getInputMap(condition);
      this.control = control;
      this.view = view;
      this.infoboard = infoboard;
      for (Direction direction : Direction.values()) {
          inputMap.put(direction.getKeyStroke(), direction.getText());
          actionMap.put(direction.getText(), new MyArrowBinding(direction.getText()));
       }
   }

   private class MyArrowBinding extends AbstractAction {
      public MyArrowBinding(String text) {
        putValue(ACTION_COMMAND_KEY, text);
        System.out.println("text: "+ text);
      }

      @Override
      public void actionPerformed(ActionEvent e) {
         String actionCommand = e.getActionCommand();
 		if (!control.started){
			return;
		}
         if (actionCommand == "Left"){
        	 view.values = control.swipe(0);
         }
         if (actionCommand == "Right"){
        	 view.values = control.swipe(1);
         }
         if (actionCommand == "Up"){
        	 view.values = control.swipe(2);
         }
         if (actionCommand == "Down"){
        	 view.values = control.swipe(3);
         }
 		view.repaint();
 		infoboard.repaint();
      }
   }
}
enum Direction {
	   UP("Up", KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0)),
	   DOWN("Down", KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0)),
	   LEFT("Left", KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0)),
	   RIGHT("Right", KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0));

	Direction(String text, KeyStroke keyStroke) {
		this.text = text;
		this.keyStroke = keyStroke;
 }
 private String text;
 private KeyStroke keyStroke;

 public String getText() {
    return text;
 }

 public KeyStroke getKeyStroke() {
    return keyStroke;
 }

 @Override
 public String toString() {
    return text;
 }
}
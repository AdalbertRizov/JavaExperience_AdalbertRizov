package View;

import java.awt.event.KeyEvent;
import javax.swing.JTextField;
public class NameTextField extends JTextField {
	
	final static String badchars 
    = "`~!@#$%^&*()[]_+=\\|\"':;?/>< ";

	public void processKeyEvent(KeyEvent ev) {

     char c = ev.getKeyChar();

     if((Character.isDigit(c) && !ev.isAltDown()) 
        || badchars.indexOf(c) > -1) {
         ev.consume();
         return;
     }
     if(c == '-' && getDocument().getLength() > 0){
     	ev.consume();
     }else {
     	super.processKeyEvent(ev);
     }
	}//processKeyEvent
}

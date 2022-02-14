package View;

import java.awt.event.KeyEvent;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
public class PriceTextField extends JTextField {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static String badchars 
       = "`~!@#$%^&*()[]_+=\\|\"':;,?/>< ";
	
	public  String getBadChars(){
		return badchars;
	}//getBadChars
	
	public  void setBadChars(String bChars){
		badchars=bChars;
	}//setBadChars
	
    public void processKeyEvent(KeyEvent ev) {

        char c = ev.getKeyChar();
        if((Character.isLetter(c) && !ev.isAltDown()) 
           || badchars.indexOf(c) > -1) {
            ev.consume();
            return;
        }
        String tStr=this.getText();
        if(tStr.indexOf('.')>-1){
        	badchars=badchars+".";
        }
        
        
   
       //JOptionPane.showMessageDialog(null,Byte.toString(comma));
         if(c == '-' && getDocument().getLength() > 0){
        	ev.consume();
        }else {
        	super.processKeyEvent(ev);
        }
        /*if(c=='\n'&&getDocument().getLength()==0){
        	ev.consume();
        }else{
        	super.processKeyEvent(ev);
        }*/

    }
}


package views;

import java.awt.Color;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

public class Utilities {
	private Color white,grey,green,blue;
public Utilities() {
	 white= new Color(255, 255, 255);
	 grey=new Color(192, 192, 192);
	 green=new Color(60, 179, 113);
	 blue=new Color(0, 0, 255);
}
public Color getWhite() {
	return white;
}
public void setWhite(Color white) {
	this.white = white;
}
public Color getGrey() {
	return grey;
}
public void setGrey(Color grey) {
	this.grey = grey;
}
public Color getGreen() {
	return green;
}
public void setGreen(Color green) {
	this.green = green;
}
public Color getBlue() {
	return blue;
}
public void setBlue(Color blue) {
	this.blue = blue;
}

public static void showMessage(String txt, boolean error) {
	if(error) {
		JOptionPane optionPane = new JOptionPane(txt, JOptionPane.ERROR_MESSAGE);    
		JDialog dialog = optionPane.createDialog("Error");
		dialog.setAlwaysOnTop(true);
		dialog.setVisible(true);
	}else {
		JOptionPane.showMessageDialog(null,txt);
	}
}

public static boolean askUserMessage(String txt) {
	if (JOptionPane.showConfirmDialog(null, txt, "WARNING",
	        JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
	    return true;
	} else {
	    return false;
	}
}

}

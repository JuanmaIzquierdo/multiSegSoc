package seguridadSocial;

import java.awt.Color;

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

}

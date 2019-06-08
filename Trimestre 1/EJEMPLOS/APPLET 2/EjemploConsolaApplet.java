//APPLET
import java.awt.*;
import java.applet.Applet;

public class EjemploConsolaApplet extends Applet {
	public void paint(Graphics g){
		g.setColor(Color.RED);
		g.drawString("Hola mundo Applet",100,100);
	}
}
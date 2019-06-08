//APPLETS

import java.awt.*;
import java.applet.Applet;

public class EjemploConsolaApplet extends Applet{
	public void init(){//inicializa componentes
		setLayout(new FlowLayout());
		add(new Label ("Hola mundo Applet"));
	}
}
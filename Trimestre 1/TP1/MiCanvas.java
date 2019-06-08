import java.awt.*;
import java.awt.geom.*;

//import java.lang.Math.*;
public class MiCanvas extends Canvas{
	Matriz m;
	private static boolean esta = false;

	public MiCanvas(){
		setBounds(0,0,650,200);
		esta = false;
	}

	public MiCanvas(Matriz m){
		this.m=m;
		setBounds(20,350,650,200);
		esta = true;
	}

	public void paint (Graphics g){
		if(esta) {
			int f,c;
			int x,y;
			int num,contador;
			int ranini,ranfin,sum=0;
			double angulo, pi,angulo1=0;
			Graphics2D g2=(Graphics2D) g;

			f=m.obtenerFila();
			c=m.obtenerColumna();
			ranini=m.obtenerIni();
			ranfin=m.obtenerEnd();

			for (x=0;x<f;x++){
				for(y=0;y<c;y++){
					num=m.obtenerElemento(x,y);
					g.setColor(new Color ((num*15+30)%256,(num*120+80)%256,((num*7+290)%256)));
					g.fillRect(y*(250/c)+100,x*(250/f)+15,250/c,300/f);
				}
			}
			contador=ranini;
			do{
				sum=0;
				for (x=0;x<f;x++){
					for(y=0;y<c;y++){
						if(contador==m.obtenerElemento(x,y))
							sum++;
					}
				}
				if(sum!=0){
					angulo=(double)(sum*360)/(f*c);
					g.setColor(new Color ((contador*15+30)%256,(contador*120+80)%256,((contador*7+290)%256)));
					g2.fill(new Arc2D.Double(600,10,270,270,angulo1, angulo,Arc2D.PIE));

					angulo1+=angulo;
				}
				contador++;
			}while (contador<=ranfin);


		}

	}
}
//CLASE MICANVAST
import java.awt.*;
import java.awt.Graphics;
import java.awt.geom.*;

public class MiCanvasT extends java.awt.Canvas{
	Matriz aux;
	int []v;
	private static boolean esta=false;

	public MiCanvasT(){
		setBounds(50,50,500,500);
		esta=false;
	}
	public MiCanvasT(Matriz m){
		this.aux=m;
		setBounds(50,50,200,300);
		esta=true;
	}
	public void paint(Graphics g){
		int num,cant=0;
		double ang1=0,ang2=0;
		int f,c;
		int red,green,blue;
		Graphics2D g2=(Graphics2D)g;
		if(esta){
			v=new int[(aux.obtenerEnd()-aux.obtenerIni())+1];
			for(num=0;num<=(aux.obtenerEnd()-aux.obtenerIni());num++){
				for(c=0;c<aux.obtenerColumna();c++){
					for(f=0;f<aux.obtenerFila();f++){
						if(aux.obtenerElemento(f,c)==(num+aux.obtenerIni())){
							cant++;
						}
					}
				}
				v[num]=cant;
				cant=0;
			}
			for(num=0;num<=(aux.obtenerEnd()-aux.obtenerIni());num++){
				ang1=ang2;
				ang2=((v[num]*360)/100)+ang1;
				red=((num*10)%256);
				green=((num*10)+50)%256;
				blue=((num+100)%256);
				g2.setColor(new Color(red,green,blue));
				g2.fill(new Arc2D.Double(500,500,200,200,(int)ang1,(int)(ang2-ang1+1),2));
			}
		}
	}
}
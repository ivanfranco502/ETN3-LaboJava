/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package calculadora2;

/**
 *
 * @author 32a08
 */
public class Micanvas extends java.awt.Canvas {
    String operacion;
    double grado3;
    double grado2;
    double grado1;
    double independiente;
    double y;
    public Micanvas(){}
    public Micanvas(String s,double tres,double dos,double uno, double ind){
	setBounds(0,0,650,200);
        operacion=s;
        grado3=tres;
        grado2=dos;
        grado1=uno;
        independiente=ind;
    }
    public void paint (java.awt.Graphics g){
        double x=(double)-44;
        g.setColor(java.awt.Color.BLACK);
        g.drawLine(10,180,450,180);
        g.drawLine(245,10,245,350);
        if(operacion.compareTo("seno")==0){
            do{
                g.setColor(java.awt.Color.RED);
                g.drawLine((int)(x*10+26),(int)(java.lang.Math.sin(x)*100+180),(int)(x*10+26),(int)(java.lang.Math.sin(x)*100+180));
                //System.out.println(String.valueOf(java.lang.Math.sin(x)).toString());
                x+=0.001;
            }while(x<44);
        }
        if(operacion.compareTo("coseno")==0){
            do{
                g.setColor(java.awt.Color.RED);
                g.drawLine((int)(x*10+26),(int)(java.lang.Math.cos(x)*100+180),(int)(x*10+26),(int)(java.lang.Math.cos(x)*100+180));
                //System.out.println(String.valueOf(java.lang.Math.sin(x)).toString());
                x+=0.001;
            }while(x<44);
        }
        if(operacion.compareTo("tangente")==0){
            do{
                g.setColor(java.awt.Color.RED);
                g.drawLine((int)(x*10+26),(int)(-java.lang.Math.tan(x)*100+180),(int)(x*10+26),(int)(-java.lang.Math.tan(x)*100+180));
                //System.out.println(String.valueOf(java.lang.Math.sin(x)).toString());
                x+=0.001;
            }while(x<44);
        }
        if(operacion.compareTo("polinomica")==0){
            x=-44;
            y=0;
            do{
                g.setColor(java.awt.Color.RED);
                y=-(java.lang.Math.pow((double)x,(double)3)*grado3);
                y=y-(java.lang.Math.pow((double)x,(double)2)*grado2);
                y=y-(java.lang.Math.pow((double)x,(double)1)*grado1);
                y=y-independiente;
                g.drawLine((int)(x*10+246),(int)(y*10+180),(int)(x*10+246),(int)(y*10+180));
                //((java.lang.Math.pow((double)x,(double)3)*grado3)+(java.lang.Math.pow((double)x,(double)2)*grado2)+(java.lang.Math.pow((double)x,(double)1)*grado1)+independiente);
                x+=0.001;
            }while(x<44);
        }
 
    }
}

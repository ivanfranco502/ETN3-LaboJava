//CLASE ANIMAL
import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import javax.accessibility.*;

class Animal extends Thread{
	private String nombre;
	private int prioridad;
	private JLayeredPane pic;
	private JLabel foto;
	private ImageIcon icon;
	int vue;
	ImageIcon po=new ImageIcon("imagenes/podio.gif");
	ImageIcon li=new ImageIcon("imagenes/liebre.gif");
	ImageIcon ch=new ImageIcon("imagenes/chita.gif");
	ImageIcon to=new ImageIcon("imagenes/tortuga.gif");
	JLabel liebre=new JLabel(li);
	JLabel chita=new JLabel(ch);
	JLabel tortuga=new JLabel(to);
	JLabel podio;
	static String pod;
	char c;

	public Animal(String n, int p, JLayeredPane jl,int a){
		nombre= n;
		prioridad= p;
		pic=new JLayeredPane();
		pic=jl;
		vue=a;
		podio=new JLabel(po);

		setPriority(prioridad);
	}
	public void run(){
		double num,x=(double)0,y=(double)0;
		int cont;
		JLabel finalap=new JLabel();
		pod="";

		for(int vuelta=0;vuelta<vue;vuelta++){
			if(vuelta==vue-1 && vue>1){
				finalap.setText("Final Lap");
				finalap.setBounds((int)350,(int)210,500,200);
				pic.add(finalap,JLayeredPane.DRAG_LAYER);
				pic.moveToFront(foto);
			}
			for(cont=0;cont<2000;cont++){
		//		System.out.print(nombre.charAt(0)+" ");

				try{
					Thread.sleep(5);
				}catch(InterruptedException ie){}

			System.out.println("");


			num=Math.PI*2/(double)2000*(double)cont;

			switch(nombre.charAt(0)){
				case 'L':
					x=370 *Math.cos(num);
					y=245 *Math.sin(num);
					icon=new ImageIcon("imagenes/liebre.gif");
					break;
				case 'T':
					x=300 *Math.cos(num);
					y=200 *Math.sin(num);
					icon=new ImageIcon("imagenes/tortuga.gif");
					break;
				case 'C':
					x=200*Math.cos(num);
					y=140 *Math.sin(num);
					icon=new ImageIcon("imagenes/chita.gif");
					break;
			}

			if(cont==0 && vuelta==0){
				foto=new JLabel(icon);
				pic.add(foto,JLayeredPane.POPUP_LAYER);
				pic.moveToFront(foto);
			}
			foto.setBounds((int)x+390,(int)y+210,200,200);

			yield();
			}
		}
		//System.out.print("Llego" + nombre);
		pod=pod+nombre.charAt(0);
		finalap.setText("");
		try{
			Thread.sleep(500);
		}catch(InterruptedException ie){}
		foto.setVisible(false);

		podio.setBounds(220,120,500,500);
		pic.add(podio,JLayeredPane.PALETTE_LAYER);

		for(int a=0;a<pod.length();a++){
			c=pod.charAt(a);
			switch (a){
				case 0:
					switch (c){
						case 'C':
								chita.setBounds(370,180,200,200);
								pic.add(chita,JLayeredPane.POPUP_LAYER);
							break;
						case 'L':
								liebre.setBounds(370,180,200,200);
								pic.add(liebre,JLayeredPane.POPUP_LAYER);
							break;
						case 'T':
								tortuga.setBounds(370,180,200,200);
								pic.add(tortuga,JLayeredPane.POPUP_LAYER);
							break;
					}
					break;
				case 1:
					switch (c){
						case 'C':
								chita.setBounds(260,210,200,200);
								pic.add(chita,JLayeredPane.POPUP_LAYER);
							break;
						case 'L':
								liebre.setBounds(260,210,200,200);
								pic.add(liebre,JLayeredPane.POPUP_LAYER);
							break;
						case 'T':
								tortuga.setBounds(260,210,200,200);
								pic.add(tortuga,JLayeredPane.POPUP_LAYER);
							break;
					}
					break;
				case 2:
					switch (c){
						case 'C':
								chita.setBounds(480,240,200,200);
								pic.add(chita,JLayeredPane.POPUP_LAYER);
							break;
						case 'L':
								liebre.setBounds(480,240,200,200);
								pic.add(liebre,JLayeredPane.POPUP_LAYER);
							break;
						case 'T':
								tortuga.setBounds(480,240,200,200);
								pic.add(tortuga,JLayeredPane.POPUP_LAYER);
							break;
					}
					break;
			}
		}
		try{
			Thread.sleep(3000);
		}catch(InterruptedException ie){}
		podio.setVisible(false);
		chita.setVisible(false);
		tortuga.setVisible(false);
		liebre.setVisible(false);
	}


}
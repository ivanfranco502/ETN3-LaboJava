//clase carreraanimal
import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import javax.accessibility.*;

class CarreraAnimal extends JFrame implements ActionListener{
	JLayeredPane capa;
	ImageIcon pi=new ImageIcon("imagenes/pista.gif");
	Animal a,b,c;
	JLabel pista;

	JComboBox vueltas;
	String []opc={"1 vuelta","2 vuelta","3 vuelta","4 vuelta","5 vuelta"};
	JButton empezar;

	public CarreraAnimal(){
		capa = new JLayeredPane();
		pista=new JLabel(pi);

		vueltas=new JComboBox(opc);
		empezar=new JButton("Correr!");

		setLayout(new java.awt.BorderLayout());

		pista.setBounds(10,0,900,700);
		capa.add(pista,JLayeredPane.DEFAULT_LAYER);

		add(capa,java.awt.BorderLayout.CENTER);
		add(vueltas,java.awt.BorderLayout.NORTH);
		add(empezar,java.awt.BorderLayout.EAST);

		vueltas.setSelectedIndex(0);

		empezar.addActionListener(this);
		vueltas.addActionListener(this);

		setSize(1020,740);
		setVisible(true);
	}
	public void actionPerformed(ActionEvent ae){
		if(ae.getSource()==empezar){
			if(vueltas.getSelectedItem()=="1 vuelta"){
				a=new Animal("Tortuga",(int)Math.floor((Math.random()*10)+1),capa,1);
				b=new Animal("Chita",(int)Math.floor((Math.random()*10)+1),capa,1);
				c=new Animal("Liebre",(int)Math.floor((Math.random()*10)+1),capa,1);
			}else{
				if(vueltas.getSelectedItem()=="2 vuelta"){
					a=new Animal("Tortuga",(int)Math.floor((Math.random()*10)+1),capa,2);
					b=new Animal("Chita",(int)Math.floor((Math.random()*10)+1),capa,2);
					c=new Animal("Liebre",(int)Math.floor((Math.random()*10)+1),capa,2);
				}else{
					if(vueltas.getSelectedItem()=="3 vuelta"){
						a=new Animal("Tortuga",(int)Math.floor((Math.random()*10)+1),capa,3);
						b=new Animal("Chita",(int)Math.floor((Math.random()*10)+1),capa,3);
						c=new Animal("Liebre",(int)Math.floor((Math.random()*10)+1),capa,3);
					}else{
						if(vueltas.getSelectedItem()=="4 vuelta"){
							a=new Animal("Tortuga",(int)Math.floor((Math.random()*10)+1),capa,4);
							b=new Animal("Chita",(int)Math.floor((Math.random()*10)+1),capa,4);
							c=new Animal("Liebre",(int)Math.floor((Math.random()*10)+1),capa,4);
						}else{
							if(vueltas.getSelectedItem()=="5 vuelta"){
								a=new Animal("Tortuga",(int)Math.floor((Math.random()*10)+1),capa,5);
								b=new Animal("Chita",(int)Math.floor((Math.random()*10)+1),capa,5);
								c=new Animal("Liebre",(int)Math.floor((Math.random()*10)+1),capa,5);
							}
						}
					}
				}
			}
			a.start();
			b.start();
			c.start();
		}
	}
	public static void main(String []s){
		CarreraAnimal ca=new CarreraAnimal();
		ca.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
}
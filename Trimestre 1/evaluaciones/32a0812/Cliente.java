//Clase Cliente
/*Hacer una clase capáz de almacenar en un objeto de la clase Vector, los datos
de los alumnos de un curso. Esa clase tiene la capacidad de aceptar acciones
desde una clase Cliente, que será la encargada de hacer ALTAS de los datos de
los alumnos, devolver la lista de los alumnos y también devolver el boletín
individual de un alumno indicado.*/

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import java.io.*;
import java.net.*;
import java.util.Vector;

public class Cliente extends JFrame implements ActionListener{
	Alumno al;
	public static void main(String []s){
		JTextField dni,apellido,nombre,curso,division,materia;
		JLabel a,b,c,d,e;
		JTextArea resultado;
		JButton enviar;
		List alumn;

		a=new JLabel("DNI");
		b=new JLabel("APELLIDO");
		c=new JLabel("NOMBRE");
		d=new JLabel("CURSO");
		e=new JLabel("DIVISION");
		f=new JLabel("MATERIA");

		dni=new JTextField();
		apellido=new JTextField();
		nombre=new JTextField();
		curso=new JTextField();
		division=new JTextField();

		alum=new List();
		resultado=new JTextArea();
		enviar=new JButton("Cargar");

		setLayout(new java.awt.FlowLayout());

		add(a);
		add(dni);
		add(b);
		add(apellido);
		add(c);
		add(nombre);
		add(d);
		add(curso);
		add(e);
		add(division);
		add(f);
		add(materia);
		add(enviar);
		add(resultado);
	}
	public void ActionPerformed(ActionEvent ae){
		if(ae.getSource()=enviar){
			al=new Alumno();
			al=new Alumno(dni.getText(),apellido.getText(),nombre.getText(),curso.getText(),division.getText(),materia.getText());
			list.addElement(apellido.getText() +" "+ nombre.getText()+" "+curso.getText()+" "+division.getText());
			EnviarDatos();
		}

	}
	public void EnviarDatos(){
		try{
			Socket s=new Socket("localHost",1234);
			ObjectOutputStream oos= new ObjectOutputStream(s.getOut());
			oos.writeObject(al);
			oos.close();
			s.close();
		}catch(IOException ioe){
			ioe.printStackTrace();
		}
	}
	setVisible(true);
}
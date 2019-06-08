//CONSOLA GRAFICA

import javax.swing.*;

public class EjemploConsolaGrafica extends javax.swing.JFrame{
	public EjemploConsolaGrafica(){
		setLayout(new java.awt.FlowLayout());
		add(new JLabel("Hola Mundo Componentes"));
		setSize(300,300);
		setVisible(true);
	}
	public static void main (String[] s){
		EjemploConsolaGrafica ecg=new EjemploConsolaGrafica();
		ecg.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
}
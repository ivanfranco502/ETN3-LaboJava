import javax.swing.*;
import java.awt.event.*;
import java.util.Vector;
import java.awt.*;
import java.io.*;
import java.awt.Component.*;

public class TrabajoMatriz extends JFrame implements ActionListener{
	JComboBox modo;
	String[] strmodo ={"Default","Custom"};
	JTextArea resultado;
	List lista;
	JPanel pcentro;
	JPanel resultados;
	MiCanvas mc;
	JComboBox ordenado;
	String[] strordenado={"SELECCIONE","Por Fila","Por Columna","Entero"};
	JTextField fila;
	JTextField columna;
	JTextField inicio;
	JTextField fin;
	JPanel pcustom;
	JPanel grafico;
	JPanel grabar;
	JLabel lfila;
	JLabel lcolumna;
	JLabel linicio;
	JLabel lfin;
	JLabel lmatriz;
	JLabel lordenado;
	JLabel vacio;
	JButton nuevo;
	JButton leer;
	JButton guardar;
	Matriz mat,aux;
	int f,c,x,y;
	Vector v=new Vector();

 	public TrabajoMatriz(){
		setLayout(new java.awt.BorderLayout());

		modo=new JComboBox(strmodo);
		modo.setSelectedIndex(0);

		resultado=new JTextArea();

		ordenado=new JComboBox(strordenado);
		ordenado.setSelectedIndex(0);

		fila=new JTextField();
		columna=new JTextField();
		inicio=new JTextField();
		fin=new JTextField();

		lfila=new JLabel();
		lcolumna=new JLabel();
		linicio=new JLabel();
		lfin=new JLabel();
		lmatriz=new JLabel("Modo Matriz");
		lordenado=new JLabel("Ordenar por");
		leer=new JButton("Leer");
		guardar=new JButton("Guardar");
		nuevo=new JButton("Nuevo");
		vacio=new JLabel();

		mc=new MiCanvas();
		lista=new List();

		lfila.setText("FILA:");
		lcolumna.setText("COLUMNA:");
		linicio.setText("RANGO INICIAL:");
		lfin.setText("RANGO FINAL:");

		pcustom= new JPanel();
		pcustom.setLayout(new java.awt.GridLayout(4,4));
		pcustom.add(lfila);
		pcustom.add(lcolumna);
		pcustom.add(linicio);
		pcustom.add(lfin);
		pcustom.add(fila);
		pcustom.add(columna);
		pcustom.add(inicio);
		pcustom.add(fin);
		pcustom.add(nuevo);
		pcustom.add(vacio);
		lfila.setVisible(false);
		lcolumna.setVisible(false);
		linicio.setVisible(false);
		lfin.setVisible(false);
		fila.setVisible(false);
		columna.setVisible(false);
		inicio.setVisible(false);
		fin.setVisible(false);
		pcustom.add(lmatriz);
		pcustom.add(lordenado);
		pcustom.add(leer);
		pcustom.add(guardar);
		pcustom.add(modo);
		pcustom.add(ordenado);

		grafico=new JPanel();
		grafico.setLayout(new java.awt.GridLayout(1,1));
		grafico.add(mc);

		resultados=new JPanel();
		resultados.setLayout(new java.awt.GridLayout(1,2));
		resultados.add(lista);
		resultados.add(resultado);

		pcentro=new JPanel();
		pcentro.setLayout(new java.awt.GridLayout(2,1));
		pcentro.add(resultados);
		pcentro.add(grafico);


		add(pcustom,java.awt.BorderLayout.NORTH);
		add(pcentro,java.awt.BorderLayout.CENTER);

		nuevo.addActionListener(this);
		ordenado.addActionListener(this);
		lista.addActionListener(this);
		leer.addActionListener(this);
		guardar.addActionListener(this);
		modo.addActionListener(this);

		setSize(1024,768);
		setVisible(true);
	}
	public void actionPerformed(ActionEvent ae){
		if (ae.getSource()==leer){
			try{
				File fi = new File("U:\\TP1\\matrices.dat");
				ObjectInputStream ois=new ObjectInputStream(new FileInputStream (fi));
				v.removeAllElements();
				lista.clear();
				v=(Vector)ois.readObject();
				for(f=0;f<v.size();f++){
					aux=(Matriz)(v.elementAt(f));
					lista.add("[ " + String.valueOf(aux.obtenerFila()).toString() + " , " + String.valueOf(aux.obtenerColumna()).toString() + " , " + String.valueOf(aux.obtenerIni()).toString() + " , " + String.valueOf(aux.obtenerEnd()).toString() + " ]");
				}
				ois.close();
			}catch(IOException ioe){
				ioe.printStackTrace();
			}catch(ClassNotFoundException cnfe){
				cnfe.printStackTrace();
			}catch(ClassCastException cce){
				cce.printStackTrace();
			}
		}else{
			if (ae.getSource()==guardar){
				try{
					File fi=new File ("U:\\TP1\\matrices.dat");
					ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fi));
					oos.writeObject(v);
					oos.close();
				}catch(IOException ioe){
					ioe.printStackTrace();
				}
			}
		}
		if(ae.getSource()==modo){
			if(modo.getSelectedItem()=="Custom"){
				lfila.setVisible(true);
				lcolumna.setVisible(true);
				linicio.setVisible(true);
				lfin.setVisible(true);
				fila.setVisible(true);
				columna.setVisible(true);
				inicio.setVisible(true);
				fin.setVisible(true);
			}else{
				lfila.setVisible(false);
				lcolumna.setVisible(false);
				linicio.setVisible(false);
				lfin.setVisible(false);
				fila.setVisible(false);
				columna.setVisible(false);
				inicio.setVisible(false);
				fin.setVisible(false);
			}
		}
		if(ae.getSource()==nuevo){
			if(modo.getSelectedItem()=="Default"){
				mat= new Matriz();
				resultado.setText("");
				v.addElement(mat);
				lista.add("[ " + String.valueOf(mat.obtenerFila()).toString() + " , " + String.valueOf(mat.obtenerColumna()).toString() + " , " + String.valueOf(mat.obtenerIni()).toString() + " , " + String.valueOf(mat.obtenerEnd()).toString() + " ]");
			}else{
				if(isInt(fila.getText())){
					if(isInt(columna.getText())){
						if(isNum(inicio.getText())){
							if(isNum(fin.getText())){
								mat= new Matriz(Integer.parseInt(fila.getText()),Integer.parseInt(columna.getText()),Integer.parseInt(inicio.getText()),Integer.parseInt(fin.getText()));
								resultado.setText("");
								v.addElement(mat);
								lista.add("[ " + String.valueOf(mat.obtenerFila()).toString() + " , " + String.valueOf(mat.obtenerColumna()).toString() + " , " + String.valueOf(mat.obtenerIni()).toString() + " , " + String.valueOf(mat.obtenerEnd()).toString() + " ]");
							}else{
								fin.setText("");
							}
						}else{
							inicio.setText("");
						}
					}else{
						columna.setText("");
					}
				}else{
					fila.setText("");
				}
			}
		}else{
			if(ae.getSource()==ordenado){
				if(ordenado.getSelectedItem()=="Por Fila"){
					resultado.setText("");
					mat.ordenaMatriz(1);
						for(f=0;f<mat.obtenerFila();f++){
							for(c=0;c<mat.obtenerColumna();c++){
								resultado.setText(resultado.getText()+String.valueOf(mat.obtenerElemento(f,c)).toString()+" ");
							}
							resultado.setText(resultado.getText()+"\n");
						}

				}else{
					if(ordenado.getSelectedItem()=="Por Columna"){
						resultado.setText("");
						mat.ordenaMatriz(2);
						for(f=0;f<mat.obtenerFila();f++){
							for(c=0;c<mat.obtenerColumna();c++){
								resultado.setText(resultado.getText()+String.valueOf(mat.obtenerElemento(f,c)).toString()+" ");
							}
							resultado.setText(resultado.getText()+"\n");
						}

					}else{
						if(ordenado.getSelectedItem()=="Entero"){
							resultado.setText("");
							mat.ordenaMatriz(3);
							for(f=0;f<mat.obtenerFila();f++){
								for(c=0;c<mat.obtenerColumna();c++){
									resultado.setText(resultado.getText()+String.valueOf(mat.obtenerElemento(f,c)).toString()+" ");
								}
								resultado.setText(resultado.getText()+"\n");
							}

						}
					}
				}
			}else{
				if(ae.getSource()==lista){
					aux= (Matriz)v.elementAt(lista.getSelectedIndex());
					resultado.setText("");
					for(f=0;f<aux.obtenerFila();f++){
						for(c=0;c<aux.obtenerColumna();c++){
							resultado.setText(resultado.getText()+String.valueOf(aux.obtenerElemento(f,c)).toString()+" ");
						}
					resultado.setText(resultado.getText()+"\n");
					}
					grafico.remove(mc);
					mc= new MiCanvas(aux);
					grafico.add(mc);
					//matrizgrafica.repaint();
					validate();
				}

			}
		}
	}
	private static boolean isInt(String cadena){
		try {
			Integer.parseInt(cadena);
			return true;
		} catch (NumberFormatException nfe){
			return false;
		}
	}
	private static boolean isNum(String cadena){
		try{
			Double.parseDouble(cadena);
			return true;
		}catch(NumberFormatException nfe){
			return false;
		}
	}
	public static void main(String []s){
		TrabajoMatriz tpm=new TrabajoMatriz();
		tpm.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
}
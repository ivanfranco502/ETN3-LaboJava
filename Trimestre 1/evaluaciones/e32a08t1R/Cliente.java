//CLASE CLIENTE
import java.io.*;
import java.net.*;
import java.io.*;
import java.net.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

public class Cliente extends JFrame implements ActionListener{
	List lstarchivos;
	File f= new File("U:\\");
	File aux;
	String [] strfiles;
	String dire;
	int indice;

	public Cliente(){
		lstarchivos=new List();
		setLayout(new java.awt.BorderLayout());

		add(lstarchivos,BorderLayout.CENTER);

		lstarchivos.addActionListener(this);

		cargarArchivos();

		setSize(500,600);
		setVisible(true);

	}

	public void cargarArchivos(){
		strfiles=f.list();

		for(indice=0;indice<9;indice++){
			aux=new File("U:\\"+strfiles[indice]);
			if(aux.isFile()){
				lstarchivos.add(strfiles[indice]);
			}
		}


	}

	public void actionPerformed(ActionEvent ae){
		if(ae.getSource()==lstarchivos){
			dire=lstarchivos.getItem(lstarchivos.getSelectedIndex());
			try{
				Socket ss= new Socket("localHost",1234);
				ObjectOutputStream ois=new ObjectOutputStream(ss.getOutputStream());

				ois.writeObject(dire);

				ois.close();
				ss.close();

			}catch(IOException ioe){
				ioe.printStackTrace();
			}
			System.out.println(dire);
		}
	}

	public static void main(String[]s){
		Cliente cte=new Cliente();
		cte.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
}
//CLASE SERVIDOR
import java.io.*;
import java.net.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

public class Servidor extends JFrame implements ActionListener{
	JButton btnconectar;
	String dire;
	File f;
	public Servidor(){
		btnconectar= new JButton("CONECTAR");
		setLayout(new java.awt.BorderLayout());

		add(btnconectar,BorderLayout.CENTER);

		btnconectar.addActionListener(this);

		setSize(500,500);
		setVisible(true);
	}

	public void actionPerformed (ActionEvent ae){
		if(ae.getSource()==btnconectar){
			try{
				ServerSocket ssk= new ServerSocket(1234);
				Socket st= ssk.accept();
				ObjectInputStream ois=new ObjectInputStream(st.getInputStream());

				dire=(String)ois.readObject();
				f=new File("C:\\temp\\"+dire);

				f.createNewFile();

				ois.close();
				st.close();
				ssk.close();

			}catch(IOException ioe){
				ioe.printStackTrace();
			}catch(ClassNotFoundException cnfe){
				cnfe.printStackTrace();
			}
		}
	}

	public static void main(String[]s){
		Servidor sv=new Servidor();
		sv.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
}
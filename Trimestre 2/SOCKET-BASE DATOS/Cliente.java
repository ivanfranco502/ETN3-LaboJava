//CLASE CLIENTE
import java.io.*;
import java.net.*;
import java.io.*;
import java.net.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import java.sql.*;
import java.util.Vector;

public class Cliente extends JFrame implements ActionListener{
	String drv="sun.jdbc.odbc.JdbcOdbcDriver";
	String dbs="jdbc:odbc:agenda";
	JTable base;
	JButton mostrar;
	JButton cargar;
	JButton actualizar;
	JButton eliminar;
	JPanel basedatos;
	List error;
	JLabel ape;
	JLabel nom;
	JLabel tel;
	JLabel mai;
	JLabel nac;
	JTextField apellido;
	JTextField nombre;
	JTextField telefono;
	JTextField mail;
	JTextField nacimiento;
	JScrollPane scr;
	Vector v;

	public Cliente(){
		base= new JTable(30,5);

		basedatos=new JPanel();
		mostrar=new JButton("Mostrar");
		cargar=new JButton("Cargar");
		actualizar=new JButton("Actualizar");
		eliminar=new JButton("Borrar");
		scr=new JScrollPane(base);
		error=new List();

		ape=new JLabel("Apellido:");
		nom=new JLabel("Nombre:");
		tel=new JLabel("Telefono:");
		mai=new JLabel("e-Mail:");
		nac=new JLabel("Fecha Nacimiento:");

		apellido=new JTextField();
		nombre=new JTextField();
		telefono=new JTextField();
		mail=new JTextField();
		nacimiento=new JTextField();
		v=new Vector();

		basedatos.setLayout(new java.awt.GridLayout(1,1));

		setLayout(null);

		base.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		base.setBounds(0,0,770,300);
		basedatos.setBounds(10,10,770,300);

		ape.setBounds(10,400,80,20);
		apellido.setBounds(10,440,80,20);

		nom.setBounds(110,400,80,20);
		nombre.setBounds(110,440,80,20);

		tel.setBounds(210,400,80,20);
		telefono.setBounds(210,440,80,20);

		mai.setBounds(310,400,80,20);
		mail.setBounds(310,440,80,20);

		nac.setBounds(410,400,120,20);
		nacimiento.setBounds(410,440,80,20);

		mostrar.setBounds(35,500,80,40);
		cargar.setBounds(135,500,80,40);
		actualizar.setBounds(235,500,100,40);
		eliminar.setBounds(355,500,80,40);

		error.setBounds(575,350,210,180);

		basedatos.add(scr);
		add(basedatos);
		add(ape);
		add(apellido);
		add(nom);
		add(nombre);
		add(tel);
		add(telefono);
		add(mai);
		add(mail);
		add(nac);
		add(nacimiento);
		add(mostrar);
		add(cargar);
		add(actualizar);
		add(eliminar);
		add(error);

		mostrar.addActionListener(this);
		cargar.addActionListener(this);
		actualizar.addActionListener(this);
		eliminar.addActionListener(this);

		setSize(800,600);
		setVisible(true);

	}
	public void enviar(){
		try{
			Socket ss= new Socket("localHost",1234);
			ObjectOutputStream ois=new ObjectOutputStream(ss.getOutputStream());

			ois.writeObject(v);

			ois.close();
			ss.close();

		}catch(IOException ioe){
			ioe.printStackTrace();
		}
		System.out.println("ENVIO");
		recibir();
	}
	public void recibir(){
	//	System.out.println("Entro recibir");
		try{
			ServerSocket ssk= new ServerSocket(4321);
	//		System.out.println("Entro ssk");
			Socket st= ssk.accept();
	//		System.out.println("Entro st");
			ObjectInputStream ois=new ObjectInputStream(st.getInputStream());
	//		System.out.println("Entro ois");

			v=(Vector)ois.readObject();
	//		System.out.println("Entro read object");
	//		System.out.println((String)v.elementAt(1));
			ois.close();
			st.close();
			ssk.close();

		}catch(IOException ioe){
			ioe.printStackTrace();
		}catch(ClassNotFoundException cnfe){
			cnfe.printStackTrace();
		}
		printear();
	}
	public void printear(){
		int fil=0,col=0,aux=0,indice=0;
		//System.out.println("Entro printear");
		basedatos.remove(scr);
	//	System.out.println("Entro remove base");
		base=new JTable(30,5);
	//	System.out.println("Entro constructor table");
		scr=new JScrollPane(base);
	//	System.out.println("Entro constructor scroll");
		basedatos.add(scr);
	//	System.out.println("Entro add scroll");
		base.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	//	System.out.println("Entro modo selection");
		validate();
	//	System.out.println("Entro validate");
	//	System.out.println(String.valueOf(v.size()).toString());
		for(indice=0;indice<(v.size());indice++){
			base.setValueAt(v.elementAt(indice),fil,col);
			col++;
			aux++;
			if(aux==5){
				fil++;
				aux=0;
				col=0;
			}
		}
		//System.out.println("Salio while");
	}

	public void actionPerformed(ActionEvent ae){
		int fil=0;
		String nom,ape,tel,correo,nac;
		String q;
		if(ae.getSource()==eliminar){
			if(base.getSelectedRow()>(-1)){
				v.clear();
				v.add((String)"eliminar");
				v.add(base.getValueAt(base.getSelectedRow(),0));
				v.add(base.getValueAt(base.getSelectedRow(),1));
				v.add(base.getValueAt(base.getSelectedRow(),3));

				enviar();
				printear();
			}else{
				error.add("No selecciono campo");
			}

		}
		if(ae.getSource()==actualizar){
			if(base.getSelectedRow()>(-1)){
				if(apellido.getText().compareTo("")==0){
					ape=String.valueOf(base.getValueAt(base.getSelectedRow(),0)).toString();
				}else{
					ape=apellido.getText();
				}
				if(nombre.getText().compareTo("")==0){
					nom=String.valueOf(base.getValueAt(base.getSelectedRow(),1)).toString();
				}else{
					nom=nombre.getText();
				}
				if(telefono.getText().compareTo("")==0){
					tel=String.valueOf(base.getValueAt(base.getSelectedRow(),2)).toString();
				}else{
					tel=telefono.getText();
				}
				if(mail.getText().compareTo("")==0){
					correo=String.valueOf(base.getValueAt(base.getSelectedRow(),3)).toString();
				}else{
					correo=mail.getText();
				}
				if(nacimiento.getText().compareTo("")==0){
					nac=String.valueOf(base.getValueAt(base.getSelectedRow(),4)).toString();
				}else{
					nac=nacimiento.getText();
				}
				v.clear();
				v.add((String)"modificar");
				v.add(ape);
				v.add(nom);
				v.add(tel);
				v.add(correo);
				v.add(nac);
				v.add((String)base.getValueAt(base.getSelectedRow(),0));
				v.add((String)base.getValueAt(base.getSelectedRow(),1));
				v.add((String)base.getValueAt(base.getSelectedRow(),3));

				enviar();
				printear();
			}else{
				error.add("No selecciono campo");
			}

		}
		if(ae.getSource()==mostrar){
			v.clear();
			v.add((String)"mostrar");

			enviar();
			printear();

		}else{
			if(ae.getSource()==cargar){
				if(apellido.getText().compareTo("")==0){
					apellido.setText("Completar");
					apellido.selectAll();
					apellido.requestFocus();
				}else{
					if(nombre.getText().compareTo("")==0){
						nombre.setText("Completar");
						nombre.selectAll();
						nombre.requestFocus();
					}else{
						if(telefono.getText().compareTo("")==0){
							telefono.setText("Completar");
							telefono.selectAll();
							telefono.requestFocus();
						}else{
							if(mail.getText().compareTo("")==0){
								mail.setText("Completar");
								mail.selectAll();
								mail.requestFocus();
							}else{
								if(nacimiento.getText().compareTo("")==0){
									nacimiento.setText("Completar");
									nacimiento.selectAll();
									nacimiento.requestFocus();
								}else{
									v.clear();
									v.add((String)"agregar");
									v.add(apellido.getText());
									v.add(nombre.getText());
									v.add(telefono.getText());
									v.add(mail.getText());
									v.add(nacimiento.getText());

									apellido.setText("");
									nombre.setText("");
									telefono.setText("");
									mail.setText("");
									nacimiento.setText("");
									enviar();
									printear();
								}
							}
						}
					}
				}
			}
		}
	}

	public static void main(String[]s){
		Cliente cte=new Cliente();
		cte.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
}
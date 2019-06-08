//base de datos

import java.sql.*;
import java.io.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.*;
import java.awt.*;

class Agenda extends JFrame implements ActionListener{
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

	public Agenda(){
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

	public void actionPerformed(ActionEvent ae){
		int fil=0;
		String nom,ape,tel,correo,nac;
		String q;
		if(ae.getSource()==eliminar){
			if(base.getSelectedRow()>(-1)){
				try{
					Class.forName(drv);
					Connection cnx=DriverManager.getConnection(dbs);
					Statement stm=cnx.createStatement();

					int res= stm.executeUpdate("DELETE * FROM Contactos WHERE Apellido='"+base.getValueAt(base.getSelectedRow(),0)+"'AND Nombre='"+base.getValueAt(base.getSelectedRow(),1)+"'AND Mail='"+base.getValueAt(base.getSelectedRow(),3)+"'");
					if(res==0){
						int res1= stm.executeUpdate("DELETE * FROM Contactos WHERE Apellido='"+base.getValueAt(base.getSelectedRow(),0)+"'");
						if(res1==0){
							error.add("Error eliminacion");
						}else{
							error.add("Eliminacion OK");
						}
					}else{
						error.add("Eliminacion OK");
					}
					stm.close();
					cnx.close();
				}catch(SQLException sqle){
					sqle.printStackTrace();
				}catch(ClassNotFoundException cnfe){
					cnfe.printStackTrace();
				}
			}else{
				error.add("No selecciono campo");
			}
			mostrar.doClick(1);
		}
		if(ae.getSource()==actualizar){
			if(base.getSelectedRow()>(-1)){
				try{
					Class.forName(drv);
					Connection cnx=DriverManager.getConnection(dbs);
					q = "UPDATE Contactos SET Apellido=? ,Nombre=? ,Telefono=? ,Mail=? , FechaNac=? WHERE Apellido=? AND Nombre=? AND Mail=?";
					//q = "UPDATE Contactos SET Apellido=? ,Nombre=? ,Telefono=? ,Mail=? , FechaNac=? WHERE Apellido='"+base.getValueAt(base.getSelectedRow(),0)+"' AND Nombre='"+base.getValueAt(base.getSelectedRow(),1)+"' AND Mail='"+base.getValueAt(base.getSelectedRow(),3)+"'";
					PreparedStatement pstm=cnx.prepareStatement(q,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);

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
					pstm.setString(1,ape);
					pstm.setString(2,nom);
					pstm.setString(3,tel);
					pstm.setString(4,correo);
					pstm.setString(5,nac);
					pstm.setString(6,(String)base.getValueAt(base.getSelectedRow(),0));
					pstm.setString(7,(String)base.getValueAt(base.getSelectedRow(),1));
					pstm.setString(8,(String)base.getValueAt(base.getSelectedRow(),3));

					int res= pstm.executeUpdate();
					if(res==0){
						int res1= pstm.executeUpdate();
						if(res1==0){
							error.add("Error actualizacion");
						}else{
							error.add("Actualizacion OK");
						}
					}else{
						error.add("Actualizacion OK");
					}
					pstm.close();
					cnx.close();
				}catch(SQLException sqle){
					sqle.printStackTrace();
				}catch(ClassNotFoundException cnfe){
					cnfe.printStackTrace();
				}
			}else{
				error.add("No selecciono campo");
			}
			mostrar.doClick(1);
		}
		if(ae.getSource()==mostrar){
			basedatos.remove(scr);
			base=new JTable(30,5);
			scr=new JScrollPane(base);
			basedatos.add(scr);
			base.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			validate();
			try{
				Class.forName(drv);
				Connection cnx=DriverManager.getConnection(dbs);
				Statement stm=cnx.createStatement();

				ResultSet rst= stm.executeQuery("SELECT * from Contactos");

				while(rst.next()){
					base.setValueAt(rst.getString("Apellido"),fil,0);
					base.setValueAt(rst.getString("Nombre"),fil,1);
					base.setValueAt(rst.getString("Telefono"),fil,2);
					base.setValueAt(rst.getString("Mail"),fil,3);
					base.setValueAt(rst.getString("FechaNac"),fil,4);
					fil++;
				}
				rst.close();
				stm.close();
				cnx.close();
			}catch(SQLException sqle){
				sqle.printStackTrace();
			}catch(ClassNotFoundException cnfe){
				cnfe.printStackTrace();
			}
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
									try{
										Class.forName(drv);
										Connection cnx=DriverManager.getConnection(dbs);
										q="INSERT into Contactos (Apellido, Nombre, Telefono, Mail, FechaNac)values(?,?,?,?,?)";
										PreparedStatement stm=cnx.prepareStatement(q,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);

										stm.setString(1,apellido.getText());
										stm.setString(2,nombre.getText());
										stm.setString(3,telefono.getText());
										stm.setString(4,mail.getText());
										stm.setString(5,nacimiento.getText());

										int res= stm.executeUpdate();

										if(res==0){
											int res1= stm.executeUpdate();
											if(res1==0){
												error.add("Error carga");
											}else{
												error.add("Carga OK");
											}
										}else{
											error.add("Carga OK");
										}
										apellido.setText("");
										nombre.setText("");
										telefono.setText("");
										mail.setText("");
										nacimiento.setText("");

										stm.close();
										cnx.close();
									}catch(SQLException sqle){
										sqle.printStackTrace();
									}catch(ClassNotFoundException cnfe){
										cnfe.printStackTrace();
									}
								}
							}
						}
					}
				}
				mostrar.doClick(1);
			}
		}
	}
	public static void main(String []s){
		Agenda ag=new Agenda();
		ag.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
}
//CLASE SERVIDOR
import java.io.*;
import java.net.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import java.util.Vector;
import java.sql.*;

public class Servidor extends JFrame implements ActionListener{
	JButton btnconectar;
	String sentencia;
	Vector v;
	String drv="sun.jdbc.odbc.JdbcOdbcDriver";
	String dbs="jdbc:odbc:agenda";
	int fil;

	public Servidor(){
		btnconectar= new JButton("CONECTAR");

		v=new Vector();
		setLayout(new java.awt.FlowLayout());

		add(btnconectar);



		btnconectar.addActionListener(this);

		setSize(100,100);
		setVisible(true);
	}
	public void recibir(){
		try{
			ServerSocket ssk= new ServerSocket(1234);
			Socket st= ssk.accept();
			ObjectInputStream ois=new ObjectInputStream(st.getInputStream());

			v=(Vector)ois.readObject();
		//	System.out.println((String)v.elementAt(0));
			ejecutar();
			ois.close();
			st.close();
			ssk.close();
		//	System.out.println((String)v.elementAt(1));
			enviar(v);

		}catch(IOException ioe){
			ioe.printStackTrace();
		}catch(ClassNotFoundException cnfe){
			cnfe.printStackTrace();
		}
	}
	public void ejecutar(){
		sentencia=(String)v.elementAt(0);

		if(sentencia.compareTo("eliminar")==0){

			try{
				Class.forName(drv);
				Connection cnx=DriverManager.getConnection(dbs);
				Statement stm=cnx.createStatement();
				int res= stm.executeUpdate("DELETE * FROM Contactos WHERE Apellido='"+(String)v.elementAt(1)+"'AND Nombre='"+(String)v.elementAt(2)+"'AND Mail='"+(String)v.elementAt(3)+"'");

				if(res==0){
					int res1= stm.executeUpdate("DELETE * FROM Contactos WHERE Apellido='"+(String)v.elementAt(1)+"'AND Nombre='"+(String)v.elementAt(2)+"'AND Mail='"+(String)v.elementAt(3)+"'");
				}
				stm.close();
				cnx.close();
			}catch(SQLException sqle){
				sqle.printStackTrace();
			}catch(ClassNotFoundException cnfe){
				cnfe.printStackTrace();
			}
			v.clear();
			muestra();
			enviar(v);
		}else{
			if(sentencia.compareTo("agregar")==0){
//System.out.println("ENTRO AGREGAR");
				try{
					Class.forName(drv);
				//	System.out.println("driver");
					Connection cnx=DriverManager.getConnection(dbs);
				//	System.out.println("conexion base");
					String q="INSERT into Contactos (Apellido, Nombre, Telefono, Mail, FechaNac)values(?,?,?,?,?)";
				//	System.out.println("string");
					PreparedStatement stm=cnx.prepareStatement(q,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
				//	System.out.println("prepared statement");

					stm.setString(1,(String)v.elementAt(1));
					stm.setString(2,(String)v.elementAt(2));
					stm.setString(3,(String)v.elementAt(3));
					stm.setString(4,(String)v.elementAt(4));
					stm.setString(5,(String)v.elementAt(5));
//System.out.println("ejecuto sentencia SQL");
					int res= stm.executeUpdate();

					if(res==0){
						int res1= stm.executeUpdate();
					}

					stm.close();
					cnx.close();
				//	System.out.println("cierro conexion y statement");
				}catch(SQLException sqle){
					sqle.printStackTrace();
				}catch(ClassNotFoundException cnfe){
					cnfe.printStackTrace();
				}
				v.clear();
				muestra();
				enviar(v);
			}else{
				if(sentencia.compareTo("modificar")==0){

					try{
						Class.forName(drv);
						Connection cnx=DriverManager.getConnection(dbs);
						String q = "UPDATE Contactos SET Apellido=? ,Nombre=? ,Telefono=? ,Mail=? , FechaNac=? WHERE Apellido=? AND Nombre=? AND Mail=?";
						//q = "UPDATE Contactos SET Apellido=? ,Nombre=? ,Telefono=? ,Mail=? , FechaNac=? WHERE Apellido='"+base.getValueAt(base.getSelectedRow(),0)+"' AND Nombre='"+base.getValueAt(base.getSelectedRow(),1)+"' AND Mail='"+base.getValueAt(base.getSelectedRow(),3)+"'";
						PreparedStatement pstm=cnx.prepareStatement(q,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);

						pstm.setString(1,(String)v.elementAt(1));
						pstm.setString(2,(String)v.elementAt(2));
						pstm.setString(3,(String)v.elementAt(3));
						pstm.setString(4,(String)v.elementAt(4));
						pstm.setString(5,(String)v.elementAt(5));
						pstm.setString(6,(String)v.elementAt(6));
						pstm.setString(7,(String)v.elementAt(7));
						pstm.setString(8,(String)v.elementAt(8));

						int res= pstm.executeUpdate();
						if(res==0){
							int res1= pstm.executeUpdate();
						}
						pstm.close();
						cnx.close();
					}catch(SQLException sqle){
						sqle.printStackTrace();
					}catch(ClassNotFoundException cnfe){
						cnfe.printStackTrace();
					}
					v.clear();
					muestra();
					enviar(v);
				}else{
					if(sentencia.compareTo("mostrar")==0){
						muestra();
					}
				}
			}
		}
	}

	public void muestra(){
		try{
			Class.forName(drv);
			Connection cnx=DriverManager.getConnection(dbs);
			Statement stm=cnx.createStatement();

			ResultSet rst= stm.executeQuery("SELECT * from Contactos");

			v.clear();
			while(rst.next()){
				v.add(rst.getString("Apellido"));
				v.add(rst.getString("Nombre"));
				v.add(rst.getString("Telefono"));
				v.add(rst.getString("Mail"));
				v.add(rst.getString("FechaNac"));
			}
			rst.close();
			stm.close();
			cnx.close();
		}catch(SQLException sqle){
			sqle.printStackTrace();
		}catch(ClassNotFoundException cnfe){
			cnfe.printStackTrace();
		}
	}
	public void enviar(Vector v){
		//System.out.println("Entro enviar");
		try{
			Socket ss= new Socket("localHost",4321);
			//System.out.println("Entro ss");
			ObjectOutputStream ois=new ObjectOutputStream(ss.getOutputStream());
		//	System.out.println("Entro ois");
			ois.writeObject(v);

			//System.out.println("escribio");
			ois.close();
			ss.close();
			//System.out.println("cierro socket");
		}catch(IOException ioe){
			//ioe.printStackTrace();
		}
	}
	public void actionPerformed (ActionEvent ae){
		if(ae.getSource()==btnconectar){
			recibir();
		}
	}

	public static void main(String[]s){
		Servidor sv=new Servidor();
		sv.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
}
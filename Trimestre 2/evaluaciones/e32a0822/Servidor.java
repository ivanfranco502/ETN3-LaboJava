/*
-------------------------------------------------------------------------------
Evaluación de Laboratorio III - 2do. Trimestre - (2/2) - Grupo A
-------------------------------------------------------------------------------
Apellido/s, Nombre/s: Franco, Iván

Número de Cuenta:	  32a08
-------------------------------------------------------------------------------
Enunciado:

- Hacer una clase que cumpla la función de ser un servidor de datos de una base
  labo32a22.mdb.

- Una segunda clase será la encargada de actuar de interface de usuario para la
  generación de las operaciones con dicha base.

- Generar una tabla de nombre Activos, donde se incorporan los elementos de la
  tabla Clientes que cumplan con la condición de estar activos.
-------------------------------------------------------------------------------
Nota:

- Se puede utilizar el tutoria y el help.
- Guardar los archivos de la evaluación en una carpeta e32axx22.
- Los listados deben armarse de una selección de datos, que el usuario elige.
-	Se representa en JTable.
- Utilizar PreparedStatement().
- Para crear tablas buscar en el Help o en el Tutorial CREATE TABLE.
-------------------------------------------------------------------------------
*/
import java.awt.*;
import javax.swing.*;
import java.sql.*;
import java.io.*;
import java.net.*;
import java.util.Vector;

class Servidor{
	String drv="sun.jdbc.odbc.JdbcOdbcDriver";
	String dbs="jdbc:odbc:labo32a22";
	Vector vec;
	public Servidor(){
		vec=new Vector();
		recibir();
	}
	public void recibir(){
		try{
			System.out.println("Esperando recibir...");
			ServerSocket ssk=new ServerSocket(1234);
			Socket sok=ssk.accept();
			ObjectInputStream ois= new ObjectInputStream(sok.getInputStream());

			vec=(Vector)ois.readObject();

			ois.close();
			sok.close();
			ssk.close();

			System.out.println("Recepcion OK");

			ejecutar();
			System.out.println("Ejecucion OK");

		}catch(IOException ioe){
		}catch(ClassNotFoundException cnfe){
		}
		enviar();
	}
	public void printear(){
		try{
			Class.forName(drv);
			Connection cnx=DriverManager.getConnection(dbs);
			Statement stm=cnx.createStatement();
			ResultSet rst=stm.executeQuery("SELECT * FROM Clientes WHERE Pagado=SI");

			vec.clear();

			while(rst.next()){
				vec.addElement(rst.getString("RecNo"));
				vec.addElement(rst.getString("Socio"));
				vec.addElement(rst.getString("Apellido"));
				vec.addElement(rst.getString("Nombre"));
				vec.addElement(rst.getString("Calle"));
				vec.addElement(rst.getString("Numero"));
				vec.addElement(rst.getString("Piso"));
				vec.addElement(rst.getString("Departamento"));
				vec.addElement(rst.getString("Localidad"));
				vec.addElement(rst.getString("Telefono"));
				vec.addElement(rst.getString("Celular"));
				vec.addElement(rst.getString("Email"));
				vec.addElement(rst.getString("Nacimiento"));
				vec.addElement(rst.getString("Edad"));
				vec.addElement(rst.getString("Ingreso"));
				vec.addElement(rst.getString("Abono"));
				vec.addElement(rst.getString("Renovacion"));
				vec.addElement(rst.getString("Pagado"));
				vec.addElement(rst.getString("Vencimiento"));
				vec.addElement(rst.getString("Apto"));
				vec.addElement(rst.getString("Deudas"));
				vec.addElement(rst.getString("Observaciones"));
			}

		}catch(SQLException sqle){
		}catch(ClassNotFoundException cnfe){
		}
	}
	public void ejecutar(){
		String sentencia;

		sentencia=(String)vec.elementAt(0);

		if(sentencia.compareTo("mostrar")==0){
			printear();
		}else{
			if(sentencia.compareTo("agregar")==0){
				try{
					String q="INSERT into Clientes (RecNo,Socio,Apellido,Nombre,Calle,Numero,Piso,Departamento,Localidad,Telefono,Celular,Email,Nacimiento,Edad,Ingreso,Abono,Renovacion,Pagado,Vencimiento,Apto,Deudas,Observaciones)values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
					Class.forName(drv);
					Connection cnx=DriverManager.getConnection(dbs);
					PreparedStatement stm=cnx.prepareStatement(q,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);

					for(int x=1;x<23;x++){
						stm.setString(x,(String)vec.elementAt(x));
					}

					int rst=stm.executeUpdate();

					vec.clear();

				}catch(SQLException sqle){
				}catch(ClassNotFoundException cnfe){
				}
				printear();
			}else{
				if(sentencia.compareTo("eliminar")==0){
					try{
						String q="DELETE * FROM Clientes WHERE Apellido=? AND Nombre=? AND Socio=?";
						Class.forName(drv);
						Connection cnx=DriverManager.getConnection(dbs);
						PreparedStatement stm=cnx.prepareStatement(q,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);

						stm.setString(1,(String)vec.elementAt(1));
						stm.setString(2,(String)vec.elementAt(2));
						stm.setString(3,(String)vec.elementAt(3));

						int rst=stm.executeUpdate();

						vec.clear();

					}catch(SQLException sqle){
					}catch(ClassNotFoundException cnfe){
					}
					printear();
				}
			}
		}
	}
	public void enviar(){
		try{
			Socket sok=new Socket("localHost",4321);
			ObjectOutputStream oos=new ObjectOutputStream(sok.getOutputStream());

			oos.writeObject(vec);
			System.out.println("Envio OK");
			oos.close();
			sok.close();

		}catch(IOException ioe){
		}
		recibir();
	}
	public static void main(String []s){
		Servidor sv=new Servidor();
	}
}
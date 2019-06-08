/*-----------------------------------------------------------------------------------------------------
Evaluación de Laboratorio III - 2do. Trimestre - Grupo A
-----------------------------------------------------------------------------------------------------
Apellido/s, Nombre/s: Franco,Iván

Número de Cuenta	: 32a08
-----------------------------------------------------------------------------------------------------
Tema: JDBC

Se necesita una aplicación que permita almacenar un texto que hace referencia a una pregunta, cinco
textos que hacen referencia a posibles respuestas. Estas preguntas deben estar agrupadas por niveles
y asignaturas.

Para poder cargar las preguntas y sus respuestas, se deberá haber un LOGON, donde el Usuario debe
tener categoría PROFESOR, y debe existir en una tabla PROFESORES de la base de datos junto con el
password.

Si se accede a la aplicación con categoría ALUMNO, luego de chequear su existencia en la tabla
ALUMNOS, la aplicación deberá generar un examen de 5 preguntas. Este examen debe permitir solo una
respuesta por pregunta, debe permitir saltar a la siguiente pregunta, marcando a la misma como no
respondida para volver a preguntarla luego de la última pregunta, y también debe permitir finalizar
el examen.

Una vez finalizado el examen, deberá mostrar los resultados del mismo entregando el porcentaje de
aciertos.

Deberá también almacenar en una tabla EXAMENFINAL El CODIGO de Alumno, el CODIGO de EXAMEN, cantidad
de respuestas BUENAS, cantidad de respuestas MALAS y el PORCENTAJE de aciertos obtenido.

También será necesario almacenar un CODIGO de examen y los CODIGOS de las preguntas que forman el
mismo y el número de la respuesta correcta.
-----------------------------------------------------------------------------------------------------
NOTAS:
	- Guardar la evaluación y la base de datos en una carpeta 32axxt2e1-2008.
	- El nombre de la base de datos en el programa debe ser LABO32A
-----------------------------------------------------------------------------------------------------*/
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.io.*;
import javax.swing.*;
import javax.swing.event.*;

class Evaluacion extends JFrame implements ActionListener{

	JLabel cat;
	List categoria;
	JLabel us;
	JTextField usuario;
	JLabel pas;
	JPasswordField password;
	JButton conectar;
	JLabel pregunta;
	String respuesta1;
	String respuesta2;
	String respuesta3;
	String respuesta4;
	String respuesta5;
	List respuestas;
	JButton contestar;
	JButton saltar;
	JButton finalizar;
	JLabel resultado;
	static int cont;
	static String correcta;
	static int porcentaje;
	String drv="sun.jdbc.odbc.JdbcOdbcDriver";
	String dbs="jdbc:odbc:LABO32A";
	String nom;
	String pass;
	Boolean encontro=false;
	public Evaluacion(){
		cat=new JLabel("Categoria");
		categoria=new List();
		us=new JLabel("Usuario:");
		usuario=new JTextField();
		pas=new JLabel("Contraseña:");
		password=new JPasswordField();
		conectar=new JButton("Conectar");
		cont=1;
		correcta="";
		porcentaje=0;
		pregunta=new JLabel();
		respuestas=new List();
		contestar=new JButton("Contestar");
		saltar= new JButton("Saltar");
		finalizar=new JButton("Finalizar");
		resultado=new JLabel("");
		setLayout(null);

		pregunta.setBounds(200,150,300,40);
		respuestas.setBounds(200,200,300,90);
		contestar.setBounds(200,300,100,40);
		saltar.setBounds(300,300,100,40);
		finalizar.setBounds(400,300,100,40);
		resultado.setBounds(300,400,100,40);


		cat.setBounds(10,10,80,40);
		categoria.setBounds(100,10,80,40);
		us.setBounds(200,10,80,40);
		usuario.setBounds(300,10,80,40);
		pas.setBounds(400,10,80,40);
		password.setBounds(500,10,80,40);
		conectar.setBounds(600,10,100,40);

		categoria.add("PROFESOR");
		categoria.add("ALUMNO");

		add(cat);
		add(categoria);
		add(us);
		add(usuario);
		add(pas);
		add(password);
		add(conectar);

		add(pregunta);
		add(respuestas);
		add(contestar);
		add(saltar);
		add(resultado);
		add(finalizar);

		pregunta.setVisible(false);
		respuestas.setVisible(false);
		contestar.setVisible(false);
		saltar.setVisible(false);
		resultado.setVisible(false);
		finalizar.setVisible(false);

		conectar.addActionListener(this);
		contestar.addActionListener(this);
		saltar.addActionListener(this);
		finalizar.addActionListener(this);

		setSize(800,700);
		setVisible(true);
	}
	public void pregunta(){
		pregunta.setText("");
		respuestas.clear();
		if(cont<=5){
			try{
				Class.forName(drv);
				Connection cnx=DriverManager.getConnection(dbs);
				Statement stm= cnx.createStatement();
				nom="SELECT Pregunta,Respuesta1,Respuesta2,Respuesta3,Respuesta4,Respuesta5,Correcta FROM pregunta WHERE Num="+ String.valueOf(cont).toString();
				ResultSet rst=stm.executeQuery(nom);

				System.out.println(String.valueOf(cont).toString());
				while(rst.next()){

					pregunta.setText(rst.getString("Pregunta"));
					respuestas.add(rst.getString("Respuesta1"));
					respuestas.add(rst.getString("Respuesta2"));
					respuestas.add(rst.getString("Respuesta3"));
					respuestas.add(rst.getString("Respuesta4"));
					respuestas.add(rst.getString("Respuesta5"));
					correcta=rst.getString("Correcta");
				}
			}catch(ClassNotFoundException cnfe){
				cnfe.printStackTrace();
			}catch(SQLException sqle){
				sqle.printStackTrace();
			}
		}else{
			contestar.setEnabled(false);
			saltar.setEnabled(false);
		}

	}
	public void actionPerformed(ActionEvent ae){

		if(ae.getSource()==contestar){

			if(correcta.compareTo(respuestas.getSelectedItem())==0){
				porcentaje+=1;
			}
			cont++;
			pregunta();
		}
		if(ae.getSource()==finalizar){
			porcentaje=(int)((porcentaje*100)/5);
			resultado.setText(String.valueOf(porcentaje).toString()+"%");
			porcentaje=0;
			cont=1;
			correcta="";
			pregunta.setVisible(false);
			respuestas.setVisible(false);
			contestar.setVisible(false);
			contestar.setEnabled(true);
			saltar.setVisible(false);
			saltar.setEnabled(true);
			finalizar.setVisible(false);
			/*try{
				Class.forName(drv);
				Connection cnx=DriverManager.getConnection(dbs);
				Statement stm= cnx.createStatement();
				int res=stm.executeUpdate("insert into ");

			}catch(ClassNotFoundException cnfe){
				cnfe.printStackTrace();
			}catch(SQLException sqle){
				sqle.printStackTrace();
			}*/

		}
		if(ae.getSource()==saltar){
			cont++;
			pregunta();
		}
		if(ae.getSource()==conectar){
			resultado.setVisible(false);

			if(categoria.getSelectedItem().compareTo("PROFESOR")==0){
				try{
					Class.forName(drv);
					Connection cnx=DriverManager.getConnection(dbs);
					Statement stm= cnx.createStatement();
					ResultSet rst=stm.executeQuery("SELECT * FROM profesores");

					while(rst.next() || encontro==false){
						nom=rst.getString("Usuario");
						pass=rst.getString("Password");
						if(nom.compareTo(usuario.getText())==0){
							if(pass.compareTo(password.getText())==0){
								encontro=true;
							}
						}
					}
					if(encontro==true){
						resultado.setText("Profesor Logueado");
					}else{
						resultado.setText("No Existe Profesor");
					}
					rst.close();
					stm.close();
					cnx.close();
				}catch(ClassNotFoundException cnfe){
					cnfe.printStackTrace();
				}catch(SQLException sqle){
					sqle.printStackTrace();
				}
			}else{
				if(categoria.getSelectedItem().compareTo("ALUMNO")==0){
					try{
						Class.forName(drv);
						Connection cnx=DriverManager.getConnection(dbs);
						Statement stm= cnx.createStatement();
						ResultSet rst=stm.executeQuery("SELECT * FROM alumno");

						while(rst.next() || encontro==false){
							nom=rst.getString("Usuario");
							pass=rst.getString("Password");
							if(nom.compareTo(usuario.getText())==0){
								if(pass.compareTo(password.getText())==0){
									encontro=true;
								}
							}
						}
						rst.close();
						stm.close();
						cnx.close();
					}catch(ClassNotFoundException cnfe){
						cnfe.printStackTrace();
					}catch(SQLException sqle){
						sqle.printStackTrace();
					}
					if(encontro){
						pregunta.setVisible(true);
						respuestas.setVisible(true);
						contestar.setVisible(true);
						saltar.setVisible(true);
						resultado.setVisible(true);
						finalizar.setVisible(true);
						pregunta();
					}
				}
			}

		}
	}
	public static void main(String[]s){
		Evaluacion eva= new Evaluacion();
		eva.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
}

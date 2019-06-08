/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package server;
import java.io.*;
import java.net.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import java.util.Vector;
import java.sql.*;
/**
 *
 * @author 32a08
 */
public class Main {
    Vector v= new Vector();
    public Main(){
        recibir();
    }
    public void enviar(int fin, String doc){
    
        v.clear();
        v.add(fin);
        v.add(doc);
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
    public void ejecutar(Vector v){
        String drv="sun.jdbc.odbc.JdbcOdbcDriver";
        String dbs="jdbc:odbc:Gestion";
        int cont=0;
        int fin=0;
        String doc=null;
        boolean paso=false;
        if(((String)v.elementAt(0)).compareTo("MEDICO")==0){
            try{
                Class.forName(drv);

                Connection cnx=DriverManager.getConnection(dbs);

                Statement stm= cnx.createStatement();
                ResultSet rs=stm.executeQuery("SELECT Codigo FROM Medicos WHERE Nombre='"+(String)v.elementAt(1)+"' AND Apellido='"+(String)v.elementAt(2)+"' AND Direccion='"+(String)v.elementAt(3)+"' AND CodEspecialidad="+(String)v.elementAt(4)+" AND Dias='"+(String)v.elementAt(5)+"' AND HoraComienzo='"+(String)v.elementAt(6)+"' AND HoraFin='"+(String)v.elementAt(7)+"'");

                try{
                    while(rs.next()){
                        cont=Integer.parseInt((String)rs.getString("Codigo"));
                        paso=true;
                        System.out.println("DATO:"+(String)rs.getString("Codigo"));
                    }
                    if(cont==0 && !paso){
                        int rst=stm.executeUpdate("INSERT into Medicos (Nombre,Apellido,Direccion,CodEspecialidad,Dias,HoraComienzo,HoraFin,Telefono,Documento) values('"+(String)v.elementAt(1)+"','"+(String)v.elementAt(2)+"','"+(String)v.elementAt(3)+"',"+(String)v.elementAt(4)+",'"+(String)v.elementAt(5)+"','"+(String)v.elementAt(6)+"','"+(String)v.elementAt(7)+"','"+(String)v.elementAt(8)+"','"+(String)v.elementAt(9)+"')");
                        ResultSet rs1=stm.executeQuery("SELECT Codigo FROM Medicos WHERE Nombre='"+(String)v.elementAt(1)+"' AND Apellido='"+(String)v.elementAt(2)+"' AND Direccion='"+(String)v.elementAt(3)+"' AND CodEspecialidad="+(String)v.elementAt(4)+" AND Dias='"+(String)v.elementAt(5)+"' AND HoraComienzo='"+(String)v.elementAt(6)+"' AND HoraFin='"+(String)v.elementAt(7)+"'");
                        fin=1;
                        doc=(String)rs1.getString("Codigo");
                        paso=false;
                        rs1.close();
                    }else{
                        fin=2;
                    }
                }catch(SQLException sqle){
                    /*int rst=stm.executeUpdate("INSERT into Medicos (Nombre,Apellido,Direccion,CodEspecialidad,Dias,HoraComienzo,HoraFin,Telefono,Documento) values('"+(String)v.elementAt(1)+"','"+(String)v.elementAt(2)+"','"+(String)v.elementAt(3)+"',"+(String)v.elementAt(4)+",'"+(String)v.elementAt(5)+"','"+(String)v.elementAt(6)+"','"+(String)v.elementAt(7)+"','"+(String)v.elementAt(8)+"','"+(String)v.elementAt(9)+"')");
                    ResultSet rs1=stm.executeQuery("SELECT Codigo FROM Medicos WHERE Nombre='"+(String)v.elementAt(1)+"' AND Apellido='"+(String)v.elementAt(2)+"' AND Direccion='"+(String)v.elementAt(3)+"' AND CodEspecialidad="+(String)v.elementAt(4)+" AND Dias='"+(String)v.elementAt(5)+"' AND HoraComienzo='"+(String)v.elementAt(6)+"' AND HoraFin='"+(String)v.elementAt(7)+"'");
                    rs1.next();
                    fin=1;
                    doc=(String)rs1.getString("Codigo");
                    rs1.close();
                   */  
                }
                rs.close();
                stm.close();
                cnx.close();
            }catch(ClassNotFoundException cnfe){
                    //cnfe.printStackTrace();
            }catch(SQLException sqle){
                    //sqle.printStackTrace();
            }
            enviar(fin,doc);
        }else{
        
        }
    }
    
    public void recibir(){
        try{
                ServerSocket ssk= new ServerSocket(1234);
                Socket st= ssk.accept();
                ObjectInputStream ois=new ObjectInputStream(st.getInputStream());

                v=(Vector)ois.readObject();
        	System.out.println((String)v.elementAt(0));
                ejecutar(v);
                ois.close();
                st.close();
                ssk.close();
        //	System.out.println((String)v.elementAt(1));

        }catch(IOException ioe){
                //ioe.printStackTrace();
        }catch(ClassNotFoundException cnfe){
                //cnfe.printStackTrace();
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Main m=new Main();
    }

}

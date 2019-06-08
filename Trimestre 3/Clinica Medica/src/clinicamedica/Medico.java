/*
 * Medico.java
 *
 * Created on 6 de octubre de 2008, 19:44
 */

package clinicamedica;

import java.sql.*;
import java.io.*;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
/**
 *
 * @author  32a08
 */
public class Medico extends javax.swing.JFrame {
String nom,ape;
int codigomedico;
String drv="sun.jdbc.odbc.JdbcOdbcDriver";
String dbs="jdbc:odbc:Gestion";
JTable tabla;
JScrollPane scrtabla;
JOptionPane jopaviso;
    /** Creates new form Medico */
    public Medico(String codigo,String nombre,String apellido) {
        initComponents();
        tabla=new JTable(30,5);
        scrtabla=new JScrollPane(tabla);
        nom=nombre;
        ape=apellido;
        
        codigomedico=Integer.parseInt(codigo);
        scrtabla.setBounds(10,120,530,290);
        scrtabla.setVisible(true);
        add(scrtabla);
        validate();
        
        cargarDatos();
        cargarTabla();
        
        setSize(600,500);
        setVisible(true);
    }
    public void cargarTabla(){
        int fil=0;
        remove(scrtabla);
        tabla=new JTable(30,5);
        scrtabla=new JScrollPane(tabla);
        scrtabla.setBounds(10,120,530,290);
        scrtabla.setVisible(true);
        add(scrtabla);
        tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        validate();
        try{
                Class.forName(drv);
                Connection cnx=DriverManager.getConnection(dbs);
                Statement stm=cnx.createStatement();

                ResultSet rst= stm.executeQuery("SELECT * from Turnos WHERE CodMedico="+codigomedico+"");

                while(rst.next()){
                        tabla.setValueAt(rst.getString("Dia"),fil,0);
                        tabla.setValueAt(rst.getString("HoraComienzo"),fil,1);
                        tabla.setValueAt(rst.getString("CodEspecialidad"),fil,2);
                        tabla.setValueAt(rst.getString("CodMedico"),fil,3);
                        tabla.setValueAt(rst.getString("CodPaciente"),fil,4);
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
    }
    public void cargarDatos(){
        try{
            Class.forName(drv);

            Connection cnx=DriverManager.getConnection(dbs);

            Statement stm= cnx.createStatement();

            ResultSet rst=stm.executeQuery("SELECT Nombre,Apellido,Direccion,Telefono,Documento FROM Medicos WHERE Nombre='"+nom+"' AND Apellido='"+ape+"' AND Codigo="+codigomedico+"");

                while(rst.next()){
                    nom=rst.getString("Nombre");
                    lblnombre.setText(lblnombre.getText()+"   "+nom);
                    ape=rst.getString("Apellido");
                    lblapellido.setText(lblapellido.getText()+"   "+ape);
                    lbldireccion.setText(lbldireccion.getText()+"   "+rst.getString("Direccion"));
                    lbltelefono.setText(lbltelefono.getText()+"   "+rst.getString("Telefono"));
                    lbldocumento.setText(lbldocumento.getText()+"   "+rst.getString("Documento"));
                }
    
            rst.close();
            stm.close();
            cnx.close();
        }catch(ClassNotFoundException cnfe){
                cnfe.printStackTrace();
        }catch(SQLException sqle){
                sqle.printStackTrace();
        }
    }
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblnombre = new javax.swing.JLabel();
        lblapellido = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        lbldocumento = new javax.swing.JLabel();
        lbltelefono = new javax.swing.JLabel();
        lbldireccion = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btneliminar = new javax.swing.JButton();
        btnhistoria = new javax.swing.JButton();
        btnturno = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("MEDICO");
        setResizable(false);
        getContentPane().setLayout(null);

        lblnombre.setText("Nombre:");
        getContentPane().add(lblnombre);
        lblnombre.setBounds(20, 30, 250, 14);

        lblapellido.setText("Apellido:");
        getContentPane().add(lblapellido);
        lblapellido.setBounds(20, 50, 250, 14);

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("DATOS MEDICO:");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(0, 0, 550, 14);

        lbldocumento.setText("Documento:");
        getContentPane().add(lbldocumento);
        lbldocumento.setBounds(290, 30, 250, 14);

        lbltelefono.setText("Telefono:");
        getContentPane().add(lbltelefono);
        lbltelefono.setBounds(290, 50, 250, 14);

        lbldireccion.setText("Direccion:");
        getContentPane().add(lbldireccion);
        lbldireccion.setBounds(20, 70, 250, 14);

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("TURNOS:");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(0, 100, 550, 14);

        btneliminar.setText("Finalización Turno");
        btneliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btneliminarActionPerformed(evt);
            }
        });
        getContentPane().add(btneliminar);
        btneliminar.setBounds(40, 420, 140, 23);

        btnhistoria.setText("Ver Historia Clinica");
        btnhistoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnhistoriaActionPerformed(evt);
            }
        });
        getContentPane().add(btnhistoria);
        btnhistoria.setBounds(200, 420, 170, 23);

        btnturno.setText("Historial Turnos");
        btnturno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnturnoActionPerformed(evt);
            }
        });
        getContentPane().add(btnturno);
        btnturno.setBounds(390, 420, 150, 23);

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void btnhistoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnhistoriaActionPerformed
// TODO add your handling code here:
    if(tabla.getSelectedRow()>(-1)){
      if(tabla.getValueAt(tabla.getSelectedRow(),4)!=null){
        new HistoriaClinica((String)(tabla.getValueAt(tabla.getSelectedRow(),4)),nom,ape).setVisible(true);
      }else{
      jopaviso.showMessageDialog(null,"No seleccionó ningún paciente para ver su historia clinica","Historia Error",jopaviso.WARNING_MESSAGE);
      }
      
    }else{
        jopaviso.showMessageDialog(null,"No seleccionó ningún paciente para ver su historia clinica","Historia Error",jopaviso.WARNING_MESSAGE);
    }
}//GEN-LAST:event_btnhistoriaActionPerformed
private void guardarturno(){
Date dia;
String datos;
try{
    File f=new File("U:\\Trimestre 3\\Clinica Medica\\historias\\ht.dat");
    ObjectInputStream ois=new ObjectInputStream(new FileInputStream(f));
    datos=(String)ois.readObject();
    ois.close();
    ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream(f));
    oos.writeObject(datos+"\n"+tabla.getValueAt(tabla.getSelectedRow(),0)+"|"+tabla.getValueAt(tabla.getSelectedRow(),1)+"|"+tabla.getValueAt(tabla.getSelectedRow(),2)+"|"+tabla.getValueAt(tabla.getSelectedRow(),3)+"|"+tabla.getValueAt(tabla.getSelectedRow(),4));
    jopaviso.showMessageDialog(null,"Se ha cargado el turno a la base de turnos","Archivo guardado",jopaviso.INFORMATION_MESSAGE);
    oos.close();
}catch(IOException ioe){
    ioe.printStackTrace();
    jopaviso.showMessageDialog(null,"Se ha producido un error imprevisto","Error Archivo",jopaviso.ERROR_MESSAGE);
}catch(ClassNotFoundException cnfe){
    cnfe.printStackTrace();
    jopaviso.showMessageDialog(null,"Se ha producido un error imprevisto","Error Archivo",jopaviso.ERROR_MESSAGE);
}

}
private void btneliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneliminarActionPerformed
// TODO add your handling code here:
    if(tabla.getSelectedRow()>(-1)){
        try{
                Class.forName(drv);
                Connection cnx=DriverManager.getConnection(dbs);
                Statement stm=cnx.createStatement();

                try{
                    int res= stm.executeUpdate("DELETE * FROM Turnos WHERE Dia='"+tabla.getValueAt(tabla.getSelectedRow(),0)+"'AND HoraComienzo='"+tabla.getValueAt(tabla.getSelectedRow(),1)+"' AND CodEspecialidad="+tabla.getValueAt(tabla.getSelectedRow(),2)+" AND CodMedico="+tabla.getValueAt(tabla.getSelectedRow(),3)+" AND CodPaciente='"+tabla.getValueAt(tabla.getSelectedRow(),4)+"'");
                    guardarturno();
                    if(res==0){
                        try{
                            int res1= stm.executeUpdate("DELETE * FROM Contactos WHERE Apellido='"+tabla.getValueAt(tabla.getSelectedRow(),0)+"'");
                            if(res1==0){
                                    jopaviso.showMessageDialog(null,"No se pudo eliminar el turno seleccionado","Eliminacion error",jopaviso.WARNING_MESSAGE);
                            }else{
                                    jopaviso.showMessageDialog(null,"El turno se borró exitosamente","Eliminacion OK",jopaviso.WARNING_MESSAGE);
                                    cargarTabla();
                            }
                        }catch(SQLException sqle){jopaviso.showMessageDialog(null,"No seleccionó ningún turno para borrar","Eliminacion Error",jopaviso.WARNING_MESSAGE);}
                    }else{
                        jopaviso.showMessageDialog(null,"El turno se borró exitosamente","Eliminacion OK",jopaviso.WARNING_MESSAGE);
                        cargarTabla();
                    }
                    stm.close();
                    cnx.close();
                }catch(SQLException sqle){
                jopaviso.showMessageDialog(null,"No seleccionó ningún turno para borrar","Eliminacion Error",jopaviso.WARNING_MESSAGE);
                }

        }catch(SQLException sqle){
                //sqle.printStackTrace();
        }catch(ClassNotFoundException cnfe){
                //cnfe.printStackTrace();
        }
    }else{
            jopaviso.showMessageDialog(null,"No seleccionó ningún turno para borrar","Eliminacion Error",jopaviso.WARNING_MESSAGE);
    }
}//GEN-LAST:event_btneliminarActionPerformed

private void btnturnoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnturnoActionPerformed
    new HistoriaTurnos().setVisible(true);
}//GEN-LAST:event_btnturnoActionPerformed

    /**
    * @param args the command line arguments
    */


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btneliminar;
    private javax.swing.JButton btnhistoria;
    private javax.swing.JButton btnturno;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel lblapellido;
    private javax.swing.JLabel lbldireccion;
    private javax.swing.JLabel lbldocumento;
    private javax.swing.JLabel lblnombre;
    private javax.swing.JLabel lbltelefono;
    // End of variables declaration//GEN-END:variables

}

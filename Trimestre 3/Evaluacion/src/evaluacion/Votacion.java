/*
 * Votacion.java
 *
 * Created on 15 de octubre de 2008, 19:36
 */

package evaluacion;
import java.sql.*;
import java.io.*;
import javax.swing.*;
import javax.swing.JTable;
/**
 *
 * @author  32a08
 */
public class Votacion extends javax.swing.JFrame {
    String drv="sun.jdbc.odbc.JdbcOdbcDriver";
    String dbs="jdbc:odbc:eleccion";
    int prov;
    int partido;
    int [][]matriz=new int[23][7];
    JScrollPane tabla;
    JTable resultado;
    /** Creates new form Votacion */
    public Votacion() {
        initComponents();
        resultado=new JTable(23,3);
        tabla=new JScrollPane(resultado);
        
        tabla.setBounds(10,150,400,200);
        add(tabla);
        setSize(500,500);
        setVisible(true);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnsimular = new javax.swing.JButton();
        totalprovincia = new javax.swing.JButton();
        provpartidos = new javax.swing.JButton();
        paispartidos = new javax.swing.JButton();
        partidogana = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        btnsimular.setText("Simular votacion");
        btnsimular.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsimularActionPerformed(evt);
            }
        });
        getContentPane().add(btnsimular);
        btnsimular.setBounds(110, 10, 130, 23);

        totalprovincia.setText("Votos Totales Provincia");
        totalprovincia.setEnabled(false);
        totalprovincia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                totalprovinciaActionPerformed(evt);
            }
        });
        getContentPane().add(totalprovincia);
        totalprovincia.setBounds(10, 50, 150, 23);

        provpartidos.setText("Votos Partidos Provincia");
        provpartidos.setEnabled(false);
        provpartidos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                provpartidosActionPerformed(evt);
            }
        });
        getContentPane().add(provpartidos);
        provpartidos.setBounds(180, 50, 150, 23);

        paispartidos.setText("Votos Partidos Pais");
        paispartidos.setEnabled(false);
        paispartidos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                paispartidosActionPerformed(evt);
            }
        });
        getContentPane().add(paispartidos);
        paispartidos.setBounds(10, 90, 130, 23);

        partidogana.setText("Voto Partido Provincia Gano");
        partidogana.setEnabled(false);
        partidogana.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                partidoganaActionPerformed(evt);
            }
        });
        getContentPane().add(partidogana);
        partidogana.setBounds(150, 90, 180, 23);

        pack();
    }// </editor-fold>//GEN-END:initComponents
private void cargarDatos(){
    int []electores=new int[23];
    for(int fi=0;fi<23;fi++){
        for(int cont=0;cont<7;cont++){
            electores[fi]=electores[fi]+matriz[fi][cont];
        }
    }
    try{    
        Class.forName(drv);
        Connection cnx=DriverManager.getConnection(dbs);
        Statement stm= cnx.createStatement();
        for(int fi=0;fi<23;fi++){
            int rst= stm.executeUpdate("Update Provincias set Electores='"+electores[fi]+"' WHERE Codigo='"+matriz[fi][0]+"'");
        }
        for(int f=0;f<23;f++){
            for(int c=0;c<7;c++){
                int rst2= stm.executeUpdate("INSERT into Eleccion (codProvincia,codPartido,Votos) values ('"+f+"','"+c+"','"+matriz[f][c]+"')");
            }
        }
        stm.close();
        cnx.close();
    }catch(ClassNotFoundException cnfe){
        cnfe.printStackTrace();
    }catch(SQLException sqle){
        sqle.printStackTrace();
    }
}
private void btnsimularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsimularActionPerformed
    for(int x=0;x<17000000;x++){
        prov=(int)Math.floor((Math.random()*22)+1);
        partido=(int)Math.floor((Math.random()*6)+1);
        matriz[prov][partido]++;
    }
    cargarDatos();
    paispartidos.setEnabled(true);
    partidogana.setEnabled(true);
    provpartidos.setEnabled(true);
    totalprovincia.setEnabled(true);
}//GEN-LAST:event_btnsimularActionPerformed

private void totalprovinciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_totalprovinciaActionPerformed
    int fil=0;
    remove(tabla);
    resultado=new JTable(23,3);
    tabla=new JScrollPane(resultado);
    add(tabla);
    
    try{    
        Class.forName(drv);
        Connection cnx=DriverManager.getConnection(dbs);
        Statement stm= cnx.createStatement();
        ResultSet rst= stm.executeQuery("SELECT * FROM Provincias");
        
        while(rst.next()){
            resultado.setValueAt(rst.getString("Codigo"),fil,0);
            resultado.setValueAt(rst.getString("Nombre"),fil,1);
            resultado.setValueAt(rst.getString("Electores"),fil,2);

            fil++;
        }
        rst.close();
        stm.close();
        cnx.close();
    }catch(ClassNotFoundException cnfe){
        cnfe.printStackTrace();
    }catch(SQLException sqle){
        sqle.printStackTrace();
    }
}//GEN-LAST:event_totalprovinciaActionPerformed

private void provpartidosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_provpartidosActionPerformed
int fil=0;
    remove(tabla);
    resultado=new JTable(23,3);
    tabla=new JScrollPane(resultado);
    add(tabla);
    
    try{    
        Class.forName(drv);
        Connection cnx=DriverManager.getConnection(dbs);
        Statement stm= cnx.createStatement();
        ResultSet rst= stm.executeQuery("SELECT * FROM Eleccion");
        
        while(rst.next()){
            resultado.setValueAt(rst.getString("codProvincia"),fil,0);
            resultado.setValueAt(rst.getString("codPartido"),fil,1);
            resultado.setValueAt(rst.getString("Votos"),fil,2);

            fil++;
        }
        rst.close();
        stm.close();
        cnx.close();
    }catch(ClassNotFoundException cnfe){
        cnfe.printStackTrace();
    }catch(SQLException sqle){
        sqle.printStackTrace();
    }
}//GEN-LAST:event_provpartidosActionPerformed

private void paispartidosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_paispartidosActionPerformed
    int fil=0;
    int []partido=new int[7];
    int numero,dato;
    String auxiliar;
    
    remove(tabla);
    resultado=new JTable(7,2);
    tabla=new JScrollPane(resultado);
    add(tabla);
    
    try{    
        Class.forName(drv);
        Connection cnx=DriverManager.getConnection(dbs);
        Statement stm= cnx.createStatement();
        ResultSet rst= stm.executeQuery("SELECT codPartido,Votos FROM Eleccion");
        
        while(rst.next()){
            auxiliar=rst.getString("codPartido");
            numero=Integer.parseInt(auxiliar);
            auxiliar=rst.getString("Votos");
            dato=Integer.parseInt(auxiliar);
            partido[numero]+=dato;
        }
        for(fil=0;fil<7;fil++){
            resultado.setValueAt(fil+1,fil,0);
            resultado.setValueAt(partido[fil],fil,1);
        }
        
        rst.close();
        stm.close();
        cnx.close();
    }catch(ClassNotFoundException cnfe){
        cnfe.printStackTrace();
    }catch(SQLException sqle){
        sqle.printStackTrace();
    }
}//GEN-LAST:event_paispartidosActionPerformed

private void partidoganaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_partidoganaActionPerformed
int fil=0;
    int []partido=new int[7];
    int numero,dato,aux;
    String auxiliar;
    
    remove(tabla);
    resultado=new JTable(23,2);
    tabla=new JScrollPane(resultado);
    add(tabla);
    
    for(numero=0;numero<23;numero++){
        try{    
            Class.forName(drv);
            Connection cnx=DriverManager.getConnection(dbs);
            Statement stm= cnx.createStatement();
            ResultSet rst= stm.executeQuery("SELECT * FROM Eleccion WHERE codProvincia='"+numero+"'");

            while(rst.next()){
                resultado.setValueAt(rst.getString("codProvincia"),fil,0);
                auxiliar=rst.getString("codPartido");
                dato=Integer.parseInt(auxiliar);
                auxiliar=rst.getString("Votos");
                aux=Integer.parseInt(auxiliar);
                partido[dato]=aux;
                fil++;
            }
            buscarMayor(partido,numero);
            
            rst.close();
            stm.close();
            cnx.close();
        }catch(ClassNotFoundException cnfe){
            cnfe.printStackTrace();
        }catch(SQLException sqle){
            sqle.printStackTrace();
        }
    }
}//GEN-LAST:event_partidoganaActionPerformed
public void buscarMayor(int []vector,int fil){
int x,y,aux;
    for(x=0;x<7;x++){
       for(y=0;y<7;y++){
        if(vector[x]<vector[y]){
            aux=vector[y];
            vector[y]=vector[x];
            vector[x]=vector[y];
        }
       }
    }
    resultado.setValueAt(vector[0],fil,1);
}
    /**
    * @param args the command line arguments
    */


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnsimular;
    private javax.swing.JButton paispartidos;
    private javax.swing.JButton partidogana;
    private javax.swing.JButton provpartidos;
    private javax.swing.JButton totalprovincia;
    // End of variables declaration//GEN-END:variables

}

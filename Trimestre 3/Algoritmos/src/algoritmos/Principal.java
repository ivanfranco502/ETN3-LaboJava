/*
 * Principal.java
 *
 * Created on 15 de octubre de 2008, 15:30
 */

package algoritmos;

import java.util.Vector;
import java.awt.event.KeyEvent;
/**
 *
 * @author  32a08
 */
public class Principal extends javax.swing.JFrame {
    Vector datos;
    int numero;
    Vector cantidad,valores;
    /** Creates new form Principal */
    public Principal() {
        initComponents();
        datos=new Vector();
        cantidad=new Vector();
        valores=new Vector();
        
        setSize(400,200);
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

        jLabel1 = new javax.swing.JLabel();
        btncargar = new javax.swing.JButton();
        txtdato = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        btntabla = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        jLabel1.setText("Ingrese el valor numérico:");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(20, 10, 150, 20);

        btncargar.setText("Cargar Dato");
        btncargar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncargarActionPerformed(evt);
            }
        });
        getContentPane().add(btncargar);
        btncargar.setBounds(110, 50, 110, 23);

        txtdato.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtdatoKeyPressed(evt);
            }
        });
        getContentPane().add(txtdato);
        txtdato.setBounds(170, 10, 150, 19);
        getContentPane().add(jSeparator1);
        jSeparator1.setBounds(10, 80, 310, 10);

        btntabla.setText("Mostrar Tabla");
        btntabla.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btntablaActionPerformed(evt);
            }
        });
        getContentPane().add(btntabla);
        btntabla.setBounds(10, 100, 120, 23);

        pack();
    }// </editor-fold>//GEN-END:initComponents
private boolean estaDato(int numero){
    int contador=0;
    boolean esta=false;
    String n;
    int aux;
    do{

        if(valores.size()>0){
            if(numero==valores.elementAt(contador)){
                esta=true;
                n=String.valueOf(cantidad.elementAt(contador)).toString();
                aux=Integer.parseInt(n);
                aux=aux+1;
                cantidad.setElementAt(aux, contador);
                System.out.println(String.valueOf(cantidad.elementAt(contador)).toString());
            }
            contador++;
        }else{
            contador=valores.size();
        }
    }while(contador<valores.size() && !esta);
    return(esta);
}
private void ordenarVectores(){
    int x,y;
    String aux,aux2,cant1,cant2;
    
    int num1,num2;
    
    for(x=0;x<valores.size();x++){
        for(y=0;y<valores.size();y++){
            aux=(String)valores.elementAt(x);
            aux2=(String)valores.elementAt(y);
            num1=Integer.parseInt(aux);
            num2=Integer.parseInt(aux2);
            if(num1>num2){
                valores.size();
            }
        }
    }
}
private void contarDatos(int numero){
    if(!estaDato(numero)){
        valores.add(numero);
        cantidad.add(1);
        System.out.println(String.valueOf(cantidad.elementAt(0)).toString());
    }
    
}
private void cargarDato(String valor){
    try{
        numero=Integer.parseInt(valor);
        datos.add(numero);
        contarDatos(numero);
        txtdato.setText("");
    }catch(NumberFormatException nfe){
        txtdato.setText("");
    }
}
private void btncargarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncargarActionPerformed
   cargarDato(txtdato.getText());
}//GEN-LAST:event_btncargarActionPerformed

private void txtdatoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtdatoKeyPressed
    if(evt.getKeyCode()==KeyEvent.VK_ENTER){
        cargarDato(txtdato.getText());
    }
}//GEN-LAST:event_txtdatoKeyPressed

private void btntablaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btntablaActionPerformed
    if(valores.size()>1){
        ordenarVectores();
    }
    new Tabla(valores, cantidad).setVisible(true);
}//GEN-LAST:event_btntablaActionPerformed

    /**
    * @param args the command line arguments
    */


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btncargar;
    private javax.swing.JButton btntabla;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField txtdato;
    // End of variables declaration//GEN-END:variables

}

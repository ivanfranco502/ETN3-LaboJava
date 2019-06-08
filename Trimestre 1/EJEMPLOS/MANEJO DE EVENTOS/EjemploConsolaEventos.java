//manejo de eventos
import javax.swing.*;
import java.awt.event.*;

public class EjemploConsolaEventos extends JFrame implements ActionListener{
	JButton aceptar;
	JLabel cartel;
	JTextField ingreso;
	public EjemploConsolaEventos(){
		aceptar=new JButton("Aceptar");
		ingreso=new JTextField();
		cartel=new JLabel();

		setLayout(new java.awt.GridLayout(3,1));
		add(ingreso);
		add(aceptar);
		add(cartel);
		aceptar.addActionListener(this);
		setSize(400,400);
		setVisible(true);
	}
	public void actionPerformed(ActionEvent ae){
		if(ae.getSource()==aceptar){
			cartel.setText(ingreso.getText());
			cartel.setFont(new java.awt.Font("Comic Sans CS",java.awt.Font.BOLD | java.awt.Font.ITALIC,24));

		}
	}
	public static void main(String []s){
		EjemploConsolaEventos ece= new EjemploConsolaEventos();
		ece.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
}
import java.awt.*;
import javax.swing.*;
import java.sql.*;
import java.io.*;
import java.net.*;
import java.awt.event.*;
import java.util.Vector;
import javax.swing.event.*;

class Cliente extends JFrame implements ActionListener{
	JTable base;
	JScrollPane scp;
	JTextField recno;
	JTextField socio;
	JTextField apellido;
	JTextField nombre;
	JTextField calle;
	JTextField numero;
	JTextField piso;
	JTextField departamento;
	JTextField localidad;
	JTextField telefono;
	JTextField celular;
	JTextField email;
	JTextField nacimiento;
	JTextField edad;
	JTextField ingreso;
	JTextField abono;
	JTextField renovacion;
	JTextField pagado;
	JTextField vencimiento;
	JTextField apto;
	JTextField deudas;
	JTextField observaciones;
	JButton agregar;
	JButton mostrar;
	JButton eliminar;
	Vector v=new Vector();
	public Cliente(){
		base=new JTable(1530,22);
		scp=new JScrollPane(base);

		recno=new JTextField("RecNo");
		socio=new JTextField("Socio");
		apellido=new JTextField("Apellido");
		nombre=new JTextField("Nombre");
		calle=new JTextField("Calle");
		numero=new JTextField("Numero");
		piso=new JTextField("Piso");
		departamento=new JTextField("Departamento");
		localidad=new JTextField("Localidad");
		telefono=new JTextField("Telefono");
		celular=new JTextField("Celular");
		email=new JTextField("Email");
		nacimiento=new JTextField("Nacimiento");
		edad=new JTextField("Edad");
		ingreso=new JTextField("Ingreso");
		abono=new JTextField("Abono");
		renovacion=new JTextField("Renovacion");
		pagado=new JTextField("Pagado");
		vencimiento=new JTextField("Vencimiento");
		apto=new JTextField("Apto");
		deudas=new JTextField("Deudas");
		observaciones=new JTextField("Observaciones");

		agregar=new JButton("Agregar");
		mostrar=new JButton("Mostrar");
		eliminar=new JButton("Eliminar");

		setLayout(new java.awt.FlowLayout());

		add(recno);
		add(socio);
		add(apellido);
		add(nombre);
		add(calle);
		add(numero);
		add(piso);
		add(departamento);
		add(localidad);
		add(telefono);
		add(celular);
		add(email);
		add(nacimiento);
		add(edad);
		add(ingreso);
		add(abono);
		add(renovacion);
		add(pagado);
		add(vencimiento);
		add(apto);
		add(deudas);
		add(observaciones);
		add(agregar);
		add(mostrar);
		add(eliminar);
		add(scp);

		agregar.addActionListener(this);
		mostrar.addActionListener(this);
		eliminar.addActionListener(this);

		setSize(1000,700);
		setVisible(true);

	}
	public void actionPerformed(ActionEvent ae){
		if(ae.getSource()==agregar){
			v.addElement("agregar");
			v.addElement(recno.getText());
			v.addElement(socio.getText());
			v.addElement(apellido.getText());
			v.addElement(nombre.getText());
			v.addElement(calle.getText());
			v.addElement(numero.getText());
			v.addElement(piso.getText());
			v.addElement(departamento.getText());
			v.addElement(localidad.getText());
			v.addElement(telefono.getText());
			v.addElement(celular.getText());
			v.addElement(email.getText());
			v.addElement(nacimiento.getText());
			v.addElement(edad.getText());
			v.addElement(ingreso.getText());
			v.addElement(abono.getText());
			v.addElement(renovacion.getText());
			v.addElement(pagado.getText());
			v.addElement(vencimiento.getText());
			v.addElement(apto.getText());
			v.addElement(deudas.getText());
			v.addElement(observaciones.getText());
			enviar();
		}else{
			if(ae.getSource()==mostrar){
				v.addElement("mostrar");
				enviar();
			}else{
				if(ae.getSource()==eliminar){
					v.addElement("eliminar");
					v.addElement(base.getValueAt(base.getSelectedRow(),2));
					v.addElement(base.getValueAt(base.getSelectedRow(),3));
					v.addElement(base.getValueAt(base.getSelectedRow(),1));
					enviar();
				}
			}
		}
	}
	public void printear(){
		int c,f;
		int indice;
		remove(scp);
		base=new JTable(1530,22);
		validate();
		add(scp);
		f=0;
		c=0;
		for(indice=0;indice<v.size();indice++){
			base.setValueAt((String)v.elementAt(indice),f,c);
			c++;
			if(c==21){
				c=0;
				f++;
			}
		}
		v.clear();
	}
	public void recibir(){
		try{
			System.out.println("Esperando recibir...");
			ServerSocket ssk=new ServerSocket(4321);
			Socket sok=ssk.accept();
			ObjectInputStream ois= new ObjectInputStream(sok.getInputStream());

			v=(Vector)ois.readObject();

			ois.close();
			sok.close();
			ssk.close();


		}catch(IOException ioe){
		}catch(ClassNotFoundException cnfe){
		}
		System.out.println("Recibo OK");
		printear();
	}
	public void enviar(){
		try{
			Socket sok=new Socket("localHost",1234);
			ObjectOutputStream oos=new ObjectOutputStream(sok.getOutputStream());

			oos.writeObject(v);

			oos.close();
			sok.close();

		}catch(IOException ioe){
		}
		recibir();
	}
	public static void main(String []s){
		Cliente c=new Cliente();
		c.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
}
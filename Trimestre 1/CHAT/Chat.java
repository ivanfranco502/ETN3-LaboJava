//Visual
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.lang.String;
import java.awt.*;

public class Chat extends JFrame implements ActionListener,KeyListener{
	static JButton enviar;
	static JTextArea recibo;
	static JTextField ingreso;
	static List lista;
	static JScrollPane panel;

	public Chat(){
		enviar=new JButton("Enviar");
		ingreso=new JTextField();
		recibo=new JTextArea();
		lista=new List();
		panel=new JScrollPane(recibo);

		setLayout(null);

		panel.setBounds(20,20,350,350);
		lista.setBounds(400,20,200,350);
		ingreso.setBounds(20,400,370,50);
		enviar.setBounds(400,400,200,50);

		add(ingreso);
		add(panel);
		add(lista);
		add(enviar);

		lista.add("YO");//0
		lista.add("BERTA");//1
		lista.add("LAUCHA");//2
		lista.add("GASTON");//3
		lista.add("TOMAS");//4
		lista.add("EMILSE");//5
		lista.add("YANINA");//6
		lista.add("HERNAN");//7
		lista.add("EGON");//8
		lista.add("DIEGO");//9

		lista.select(0);

		enviar.addActionListener(this);
		ingreso.addKeyListener(this);

		setSize(650,500);
		setVisible(true);
	}
	public void keyTyped(KeyEvent e){}
	public void keyReleased(KeyEvent e){}
	public void keyPressed(KeyEvent e){
		if(e.getKeyCode()==KeyEvent.VK_ENTER){
			if(lista.getSelectedIndex()==0){
				funcenviar("localHost");
			}else{
				if(lista.getSelectedIndex()==9){
					funcenviar("10.10.10.212");
				}else{
					if(lista.getSelectedIndex()==7){
						funcenviar("10.10.10.210");
					}else{
						if(lista.getSelectedIndex()==1){
							funcenviar("10.10.10.202");
						}else{
							if(lista.getSelectedIndex()==2){
								funcenviar("10.10.10.205");
							}else{
								if(lista.getSelectedIndex()==3){
									funcenviar("10.10.10.206");
								}else{
									if(lista.getSelectedIndex()==4){
										funcenviar("10.10.10.207");
									}else{
										if(lista.getSelectedIndex()==5){
											funcenviar("10.10.10.208");
										}else{
											if(lista.getSelectedIndex()==6){
												funcenviar("10.10.10.209");
											}else{
												if(lista.getSelectedIndex()==8){
													funcenviar("10.10.10.211");
												}else{
													funcenviar("localHost");
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
			ingreso.requestFocus();
		}
	}
	public void actionPerformed(ActionEvent ae){
			if(ae.getSource()==enviar){
				if(lista.getSelectedIndex()==0){
					funcenviar("localHost");
				}else{
					if(lista.getSelectedIndex()==1){
						funcenviar("10.10.10.212");
					}else{
						if(lista.getSelectedIndex()==2){
							funcenviar("10.10.10.210");
						}else{
							if(lista.getSelectedIndex()==3){
								funcenviar("10.10.10.202");
							}else{
								funcenviar("localHost");
							}
						}
					}
				}
			ingreso.requestFocus();
			}
	}
	public void funcenviar(String ip){
		try{
			Socket so=new Socket(ip,12345);
			ObjectOutputStream oos= new ObjectOutputStream(so.getOutputStream());
			try{
				oos.writeObject(ingreso.getText());
				recibo.setText(recibo.getText()+"YO: "+ingreso.getText()+"\n");
			}catch(ConnectException ce){
				recibo.setText(recibo.getText()+"ERROR DE ENVIO DESTINATARIO NO ESTA PREPARADO PARA RECIBIR");
			}
			oos.close();
			so.close();
		}catch(IOException ioe){
			ioe.printStackTrace();
		}
		ingreso.setText("");
	}
	public static class funcrecibir implements Runnable{
		public void run(){
			do{
				try{
					ServerSocket srvSocket=new ServerSocket(12345);
					Socket sk=srvSocket.accept();
					ObjectInputStream ois=new ObjectInputStream(sk.getInputStream());
					String a=(String) ois.readObject();
					recibo.setText(recibo.getText()+lista.getItem(lista.getSelectedIndex())+": "+a+"\n");
					ois.close();
					sk.close();
					srvSocket.close();
				}catch(IOException ioe){
					ioe.printStackTrace();
				}catch(ClassNotFoundException cnfe){
					cnfe.printStackTrace();
				}
			}while(true);
		}

	}
	public static void main(String []s){
		Chat ece= new Chat();
		Thread t= new Thread(new funcrecibir());
		t.start();
		ece.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
}
//Cliente

import java.io.*;
import java.net.*;

public class Cliente{
	public static void main(String []s){
		try{
			Socket so=new Socket("10.10.10.212",12345);
			ObjectOutputStream oos= new ObjectOutputStream(so.getOutputStream());
			oos.writeObject("hola soy el fantasma escritor");
			oos.close();
			so.close();
		}catch(IOException ioe){
			ioe.printStackTrace();
		}
	}
}
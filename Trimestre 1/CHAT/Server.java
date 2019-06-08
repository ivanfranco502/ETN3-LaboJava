//Server
import java.io.*;
import java.net.*;

public class Server{
	public static void main(String []s){
		try{
			ServerSocket srvSocket=new ServerSocket(12345);
			Socket sk=srvSocket.accept();
			ObjectInputStream ois=new ObjectInputStream(sk.getInputStream());
			String a=(String) ois.readObject();
			System.out.println(a);
			ois.close();
			sk.close();
			srvSocket.close();
		}catch(IOException ioe){
			ioe.printStackTrace();
		}catch(ClassNotFoundException cnfe){
			cnfe.printStackTrace();
		}
	}
}
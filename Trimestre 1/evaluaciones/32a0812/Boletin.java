//Clase Boletin

public class Boletin{
	private String materia;
	private int notat1;
	private int notat2;
	private int notat3;

	public Boletin(){
		materia="Laboratorio";
		notat1=3;
		notat2=5;
		notat3=9;
	}

	public Boletin(String a,int b,int c,int d){
		materia=a;
		notat1=b;
		notat2=c;
		notat3=d;
	}
}
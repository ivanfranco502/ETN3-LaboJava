//Clase Alumno

public class Alumno{

	private int dni;
	private String apellido;
	private String nombre;
	private int curso;
	private int division;
	private Boletin notas;

	public Alumno(){
		int indice;

		dni=34684048;
		apellido="Franco";
		nombre="Ivan";
		curso=6;
		division=2;
		notas=new Boletin();

	}
	public Alumno(int a,String b, String c, int d, int e, String mat){
		int indice;

		dni=a;
		apellido=b;
		nombre=c;
		curso=d;
		division=e;

		notas=new Boletin(mat,GenerarNota(),GenerarNota(),GenerarNota());

	}
	public int GenerarNota(){
		int nota;

		nota=(int)Math.floor(Math.random()*9)+1;

		return(nota);
	}
}
//Clase Matriz
import java.io.*;

class Matriz implements Serializable{

	private int fila;
	private int columna;
	private int [][]mat;
	private int inicio;
	private int end;

	private void cargaMatriz(int ini,int fin){
		int indicef;
		int indicec;

		for(indicef=0;indicef<fila;indicef++){
			for(indicec=0;indicec<columna;indicec++){
				mat[indicef][indicec]=(int)Math.floor((Math.random()*(fin-ini+1))+ini);
			}
		}
	}
	public Matriz(){
		fila=5;
		columna=5;
		mat=new int[fila][columna];
		inicio=0;
		end=1;
		cargaMatriz(inicio,end);
	}
	public Matriz(int f,int c,int ini,int fin){
		fila=f;
		columna=c;
		mat=new int[fila][columna];
		inicio=ini;
		end=fin;
		cargaMatriz(inicio,end);
	}

	public void ordenaMatriz(int forma){
		int f;
		int fi;
		int c;
		int co;
		int aux;

		switch(forma){
			case 1:
			//POR FILA
				for(f=0;f<fila;f++){
					for(c=0;c<columna;c++){
						for(co=c+1;co<columna;co++){
							if((mat[f][c])>(mat[f][co])){
								aux=mat[f][c];
								mat[f][c]=mat[f][co];
								mat[f][co]=aux;
							}
						}
					}
				}
				break;
			case 2:
			//POR COLUMNA
				for(c=0;c<columna;c++){
					for(f=0;f<fila;f++){
						for(fi=f+1;fi<fila;fi++){
							if(mat[f][c]>mat[fi][c]){
								aux=mat[f][c];
								mat[f][c]=mat[fi][c];
								mat[fi][c]=aux;
							}
						}
					}
				}
				break;
			case 3:
			//ENTERA
				for(f=0;f<fila;f++){
					for(fi=0;fi<fila;fi++){
						for(c=0;c<columna;c++){
							for(co=0;co<columna;co++){
								if(mat[f][c]<mat[fi][co]){
									aux=mat[f][c];
									mat[f][c]=mat[fi][co];
									mat[fi][co]=aux;
								}
							}
						}
					}
				}
				break;
		}
	}
	public void mostrarMatriz(){
		int f;
		int c;

		for(f=0;f<fila;f++){
			for(c=0;c<columna;c++){
				System.out.print(mat[f][c]);
			}
			System.out.println("");
		}
	}
	public int obtenerIni(){
		return(inicio);
	}
	public int obtenerEnd(){
		return(end);
	}
	public int obtenerFila(){
		return(fila);
	}
	public int obtenerColumna(){
		return(columna);
	}
	public int obtenerElemento(int f,int c){
		return(mat[f][c]);
	}
	public static void main(String []s){
		Matriz mat= new Matriz();
		mat.mostrarMatriz();
		System.out.println("ELEMENTO 1-1:"+ mat.obtenerElemento(1,1));
		System.out.println("CANT COL:"+ mat.obtenerColumna());
		System.out.println("CANT FIL:"+ mat.obtenerFila());
		mat.ordenaMatriz(1);
		System.out.println("ORDENA FILA");
		mat.mostrarMatriz();
		mat.ordenaMatriz(2);
		System.out.println("ORDENA COLUMNA");
		mat.mostrarMatriz();
		mat.ordenaMatriz(3);
		System.out.println("ORDENA COMPLETA");
	    mat.mostrarMatriz();

	}
}



//Programa general
/*----------------------------------------------------------------------------
Evaluaci�n de Laboratorio III - 1er. Trimestre - (2/2)
------------------------------------------------------------------------------
Nombre: Ivan
Apellido: Franco
Curso: 6to
Division: 2da
------------------------------------------------------------------------------
Enunciado:

Hacer una clase Alumno que contenga como propiedades:

- DNI
- Apellido
- Nombre
- Curso
- Divisi�n
- Notas * 5

Donde Notas es un objeto de la clase Bolet�n, compuesto por:

- Materia
- Nota T1
- Nota T2
- Nota T3

Hacer una clase cap�z de almacenar en un objeto de la clase Vector, los datos
de los alumnos de un curso. Esa clase tiene la capacidad de aceptar acciones
desde una clase Cliente, que ser� la encargada de hacer ALTAS de los datos de
los alumnos, devolver la lista de los alumnos y tambi�n devolver el bolet�n
individual de un alumno indicado.
------------------------------------------------------------------------------
NOTAS:
	- Guardar los archivos fuentes en una carpeta 32axx12, donde se deben reem-
	  plazar las xx por el n�mero de su cuenta.
	- Las notas de cada alumno las genera al azar.
	- El bolet�n se deber� generar con las notas de 5 materias (A-B-C-D-E)
	- La clase Cliente deber� mostrar inicialmente la lista de los alumnos vacia.
	- La consulta de las notas de los alumnos se har� seleccionando al alumno
	  desde la lista.
----------------------------------------------------------------------------*/
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import java.io.*;
import java.net.*;
import java.util.Vector;

public class InterfazAlumno extends JFrame{
	Vector v=new Vector();
	Alumno al;

	private static void main(String []s){
		List alumnos=new List();

		setLayout(new FlowLayout());
		add(alumnos);

		alumnos.setText(v);
	}
	public void RecibirDatos(){
		ServerSocket sv=new ServerSocket(1234);
		ObjectInputStream ois= new ObjectInputStream();
	}
}
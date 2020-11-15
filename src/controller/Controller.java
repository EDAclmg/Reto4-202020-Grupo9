package controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import estructuras_de_datos.ArregloDinamico;
import estructuras_de_datos.DiGraph;
import estructuras_de_datos.Edge;
import estructuras_de_datos.Vertex;
import modeloMundo.Axon;
import modeloMundo.Cittadella;
import modeloMundo.Neuronio;
import view.View;

public class Controller 
{
	/**
	 * Vista de programa.
	 */
	private View view;

	/**
	 * Modelo del mundo.
	 */
	private Cittadella modelo;

	/**
	 * Crear la vista y el modelo del proyecto
	 * @param capacidad tamaNo inicial del arreglo
	 */
	public Controller ()
	{
		view = new View( );
		modelo = new Cittadella( );

	}

	/**
	 * Programa.
	 */
	public void run( ) 
	{
		Scanner lector = new Scanner(System.in);
		boolean fin = false;
		view.printFileSelection( );
		int file = lector.nextInt( );
		if(file != 6) 
			modelo.cargarDatos(selectFile(file));

		else 
		{
			view.elegirArchivos( );
			String[] archivos = lector.next( ).split(",");
			for(int i = 0; i < archivos.length; i++)
			{
				int num = (int) Integer.parseInt(archivos[i]);
				if(num < 5 && num > 0)
					modelo.cargarDatos(selectFile(num));
				else
				{
					view.printMessage("Un numero no esta dentro del rango 1-4.");
					view.printMessage("El programa morira por ello.");
					fin = true;
					lector.close( );
				}
			}
		}

		while( !fin )
		{
			view.printMenu( );
			int option = lector.nextInt( );
			DiGraph<Integer, Neuronio, Axon> grafo = modelo.getGraph( );
			switch(option)
			{
			case 1:

				view.printMessage("El numero de viajes leidos es: " + modelo.getResults( ));
				view.printMessage("El numero de estaciones es: " + grafo.vertices( ).size( ) );
				view.printMessage("El numero total de arcos es: " + grafo.edges( ).size( ));
				ArregloDinamico<Edge<Integer, Neuronio, Axon>> arcos = modelo.darExtremos( );
				Edge<Integer, Neuronio, Axon> menor = arcos.getElement(1);
				Edge<Integer, Neuronio, Axon> mayor = arcos.getElement(2);
				view.printMessage("El arco con el peso minimo va de " + menor.getInfo( ).darInicio( ) + " a la estacion " + menor.getInfo( ).darFin( ) + ". Contando con un peso de " + menor.weight( ) );
				view.printMessage("El arco con el peso maximo va de " + mayor.getInfo( ).darInicio( ) + " a la estacion " + mayor.getInfo( ).darFin( ) + ". Contando con un peso de " + mayor.weight( ));
				break;
			case 2:
				view.printMessage("Ingrese el id de la estacion a buscar");
				int estacion = lector.nextInt( );
				Vertex<Integer, Neuronio, Axon> vertice = grafo.getVertex(estacion);
				if(vertice != null)
				{
					view.printMessage("El grado de entrada es: " + vertice.indegree( ));
					view.printMessage("El grado de salida es: " + vertice.outdegree( ));
				}
				else
					view.printMessage("No se encontro la estacion");
			case 3:
				view.printMessage("Gracias por utilizar el programa. Chao");
				fin = true;
				break;
			default:
				break;
			}
		}
	}

	/**
	 * Seleccion de archivo.
	 * @param file Archivo a cargar.
	 * @return String del archivo a cargar.
	 */
	public String selectFile(int file )
	{
		String resp = "";
		switch(file)
		{
		case 1:
			resp = "./data/201801-1-citibike-tripdata.csv";
			break;
		case 2:
			resp = "./data/201801-2-citibike-tripdata.csv";
			break;
		case 3:
			resp = "./data/201801-3-citibike-tripdata.csv";
			break;
		case 4:
			resp = "./data/201801-4-citibike-tripdata.csv";
			break;
		case 5:
			resp = "./data/201801-1-citibike-tripdata.csv";
			modelo.cargarDatos(selectFile(2));
			modelo.cargarDatos(selectFile(3));
			modelo.cargarDatos(selectFile(4));
			break;
		default:
			resp = "./data/Chachacha.csv";
			break;
		}
		return resp;
	}
}
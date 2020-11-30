package controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import estructuras_de_datos.ArregloDinamico;
import estructuras_de_datos.DiGraph;
import estructuras_de_datos.Edge;
import estructuras_de_datos.Lista;
import estructuras_de_datos.ListaEncadenada;
import estructuras_de_datos.Vertex;
import modeloMundo.Ruta;
import modeloMundo.Viaje;
import modeloMundo.Central;
import modeloMundo.Estacion;
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
	private Central modelo;

	/**
	 * Crear la vista y el modelo del proyecto
	 * @param capacidad tamaNo inicial del arreglo
	 */
	public Controller ()
	{
		view = new View( );
		modelo = new Central( );

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
			DiGraph<Integer, Estacion, Ruta> grafo = modelo.getGraph( );
			String edad;
			ArregloDinamico<Vertex<Integer, Estacion, Ruta>> estaciones;
			Lista<Vertex<Integer, Estacion, Ruta>> ruta;

			switch(option)
			{
			case 1:

				view.printMessage("El numero de viajes leidos es: " + modelo.getResults( ));
				view.printMessage("El numero de estaciones es: " + grafo.vertices( ).size( ) );
				view.printMessage("El numero total de arcos es: " + grafo.edges( ).size( ));
				ArregloDinamico<Edge<Integer, Estacion, Ruta>> arcos = modelo.darExtremos( );
				Edge<Integer, Estacion, Ruta> menor = arcos.getElement(1);
				Edge<Integer, Estacion, Ruta> mayor = arcos.getElement(2);
				view.printMessage("El arco con el peso minimo va de " + menor.getInfo( ).darInicio( ) + " a la estacion " + menor.getInfo( ).darFin( ) + ". Contando con un peso de " + menor.weight( ) );
				view.printMessage("El arco con el peso maximo va de " + mayor.getInfo( ).darInicio( ) + " a la estacion " + mayor.getInfo( ).darFin( ) + ". Contando con un peso de " + mayor.weight( ));
				break;
			case 2:
				view.printMessage("Ingrese el id de la estacion a buscar");
				int estacion = lector.nextInt( );
				Vertex<Integer, Estacion, Ruta> vertice = grafo.getVertex(estacion);
				if(vertice != null)
				{
					view.printMessage("El grado de entrada es: " + vertice.indegree( ));
					view.printMessage("El grado de salida es: " + vertice.outdegree( ));
				}
				else
					view.printMessage("No se encontro la estacion");
				break;
			case 3:
				view.printMessage("Ingrese el id de la primera estacion");
				int est1 = lector.nextInt( );
				view.printMessage("Ingrese el id de la segunda estacion");
				int est2 = lector.nextInt( );
				view.printMessage("El numero de componentes fuertemente conectados es: " + grafo.kosarajuSCC( ));
				String bool = grafo.getVertex(est1).getIdCC( ) == grafo.getVertex(est2).getIdCC( ) ? "si" : "no";
				view.printMessage("Las estaciones " + bool + " pertenecen al mismo cluster.");
				break;
			case 4:
				view.printMessage("Las estaciones a las que mas bicicletas llegan son: ");
				ArregloDinamico<Vertex<Integer, Estacion, Ruta>> mayoresEntrada = modelo.darMayorIn( );
				for( int i = 1; i <= 3; i++ )	
					view.printMessage("Estacion " + mayoresEntrada.getElement(i).getInfo( ).darNombre( ) + " con un grado de entrada de " + mayoresEntrada.getElement(i).indegree( ));
				view.printMessage(" ");

				view.printMessage("Las estaciones de las que mas bicicletas salen son: ");
				ArregloDinamico<Vertex<Integer, Estacion, Ruta>> mayoresSalida = modelo.darMayorOut( );
				for( int i = 1; i <= 3; i++ )	
					view.printMessage("Estacion " + mayoresSalida.getElement(i).getInfo( ).darNombre( ) + " con un grado de salida de " + mayoresSalida.getElement(i).outdegree( ));
				view.printMessage(" ");

				view.printMessage("Las estaciones menos utilizadas son : ");
				ArregloDinamico<Vertex<Integer, Estacion, Ruta>> menores = modelo.darMenorGradoCombinado( );
				for( int i = 1; i <= 3; i++ )	
					view.printMessage("Estacion " + menores.getElement(i).getInfo( ).darNombre( ) + " con un grado combinado de " + (menores.getElement(i).outdegree( ) + menores.getElement(i).indegree( )));
				break;
			case 5:
				view.printMessage("Ingrese su rango de edad separado por -, o con un +. Un ejemplo del formato es:  0-10, 11-20, 21-30, 31-40, 41-50, 51-60, 60+");
				edad = lector.next( );
				estaciones = null;
				if(edad.contains("+"))
				{
					String[] resp = edad.split("\\+");
					estaciones = modelo.recomendarRuta(Integer.parseInt(resp[0]), 200);
				}
				else
				{
					String[] valores = edad.split("-");
					estaciones = modelo.recomendarRuta(Integer.parseInt(valores[0]), Integer.parseInt(valores[1]));
				}
				view.printMessage("La estacion de salida es: " + estaciones.getElement(1).getInfo( ).darNombre( ));
				view.printMessage("La estacion de entrada es: " + estaciones.getElement(2).getInfo( ).darNombre( ));

				ruta = grafo.Dijkstra(estaciones.getElement(1).getId( ), estaciones.getElement(2).getId( ));	
				for(int i = 0; i < ruta.size( ); i++)
					view.printMessage("La " + (i + 1) + " estacion es " + ruta.getElement(i).getInfo( ).darNombre( ));
				break;
			case 6:
				view.printMessage("Ingrese la latitud del punto de salida.");
				String latInicial = lector.next( );

				view.printMessage("Ingrese la longitud del punto de salida.");
				String lonInicial = lector.next( );

				view.printMessage("Ingrese la latitud del punto de llegada.");
				String latFinal = lector.next( );

				view.printMessage("Ingrese la longitud del punto de llegada.");
				String lonFinal = lector.next( );

				Vertex<Integer, Estacion, Ruta> estacionInicial = modelo.darEstacionMasCercana( ((Double.parseDouble(latInicial)*Math.PI)/180), ((Double.parseDouble(lonInicial)*Math.PI)/180) );
				Vertex<Integer, Estacion, Ruta> estacionFinal = modelo.darEstacionMasCercana( ((Double.parseDouble(latFinal)*Math.PI)/180), ((Double.parseDouble(lonFinal)*Math.PI)/180) );

				view.printMessage("La estacion mas cercana de salida es: " + estacionInicial.getInfo( ).darNombre( ));
				view.printMessage("La estacion mas cercana de llegada es: " + estacionFinal.getInfo( ).darNombre( ));
				ruta = grafo.Dijkstra(estacionInicial.getId( ), estacionFinal.getId( ));
				view.printMessage("El tiempo promedio es: " + estacionFinal.darCosto( ));
				for(int i = 0; i < ruta.size( ); i++)
					view.printMessage("La " + (i + 1) + " estacion de la ruta es " + ruta.getElement(i).getInfo( ).darNombre( ));


				break;
			case 7:
				view.printMessage("Ingrese su rango de edad separado por -, o con un +. Un ejemplo del formato es:  0-10, 11-20, 21-30, 31-40, 41-50, 51-60, 60+");
				edad = lector.next( );
				estaciones = null;
				if(edad.contains("+"))
				{
					String[] resp = edad.split("\\+");
					estaciones = modelo.estacionesAdyacentes(Integer.parseInt(resp[0]), 200);
				}
				else
				{
					String[] valores = edad.split("-");

					estaciones = modelo.estacionesAdyacentes(Integer.parseInt(valores[0]), Integer.parseInt(valores[1]));
				}

				view.printMessage("El par estaciones adyacentes es: " + estaciones.getElement(1).getInfo( ).darNombre( ) + " y " +  estaciones.getElement(2).getInfo( ).darNombre( ));
				if(estaciones.size( ) > 2 && !estaciones.getElement(2).equals(estaciones.getElement(3)))
				{
					for(int i = 3; i < estaciones.size( ); i++)
						view.printMessage("Otro par de estaciones adyacentes es: " + estaciones.getElement(i).getInfo( ).darNombre( ) + " y " +  estaciones.getElement(i+1).getInfo( ).darNombre( ));
				}
				break;

			case 8:
				view.printMessage("Ingrese el identificador de la bicicleta");
				int id = lector.nextInt( );

				view.printMessage("Ingrese la fecha a buscar en el siguiente formato: DD-MM-AAAA. Donde A es el anio, M es el mes y D es el dia");
				SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
				String fecha = lector.next( );
				Date fechaFormateada = null;
				try 
				{
					fechaFormateada = formato.parse(fecha);
				} 
				catch (ParseException e) 
				{
					view.printMessage("Se presento un error en el formato de la fecha.");
					view.printMessage("");
				}
				ArregloDinamico<Viaje> viajes = modelo.reporteBicicleta(id, fechaFormateada);
				for(int i = 1; i <= viajes.size( ); i++)
				{
					view.printMessage("La bicicleta paso por " + viajes.getElement(i).darArco( ).getSource( ).getInfo( ).darNombre( ));
				}
				modelo.organizarViajes(viajes);
				modelo.calcularInformacionViajes(viajes);
				view.printMessage("El tiempo de uso de la bicicleta fue de " + modelo.darTiempoDeUso( ) + " segundos.");
				view.printMessage("El tiempo estacionada de la bicicleta fue de " + modelo.darTiempoEstacionada( ) + " segundos.");
				break;
			case 9:
				view.printMessage("Gracias por utilizar el programa. Chao");
				fin = true;
				break;
			case 10:
				view.printMessage("Ingrese el centro");
				int centro = lector.nextInt( );
				ArregloDinamico<ArregloDinamico<Vertex<Integer, Estacion, Ruta>>> ciclos = modelo.rutasCirulares(centro, 0, 10000);
				for(int i = 1; i<= ciclos.size( ); i++)
					if(ciclos.getElement(i) != null && ciclos.getElement(i).getElement(i) != null )
					{
						String resp = "";
						for(int j = 1; j<= ciclos.getElement(i).size( ); j++) 
						{
							resp += (" La estacion " + j + " es " + ciclos.getElement(i).getElement(j).getInfo( ).darNombre( ));
						}
						view.printMessage(resp);
					}
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
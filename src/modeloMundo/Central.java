package modeloMundo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import estructuras_de_datos.ArregloDinamico;
import estructuras_de_datos.DiGraph;
import estructuras_de_datos.Edge;
import estructuras_de_datos.Lista;
import estructuras_de_datos.Vertex;

public class Central 
{

	/**
	 *  Constate que representa el radio de la tierra en KM.
	 */
	private final static Double RADIO_TERRESTRE = 3958.8;

	/**
	 *  Representa el total de viajes leidos.
	 */
	private int total;

	/**
	 * Grafo
	 */
	private DiGraph<Integer, Estacion, Ruta> grafoDirigido;

	/**
	 * Representa el tiempo de uso de la utima bicicleta consultada.
	 */
	private double tiempoDeUso;

	/**
	 * Representa el tiempo estacionada de la utima bicicleta consultada.
	 */
	private double tiempoEstacionada;
	/**
	 * Metodo constructor de la clase.
	 */
	public Central( )
	{
		total = 0;
		grafoDirigido = new DiGraph<Integer, Estacion, Ruta>( );
		tiempoDeUso = -1.0;
		tiempoEstacionada = -1.0;
	}

	/**
	 * Retorna el numero de viajes cargados.
	 * @return Numero de viajes cargados.
	 */
	public int getResults( ) 
	{
		return total;
	}

	/**
	 * Retorna el grafo.
	 * @return Grafo.
	 */
	public DiGraph<Integer, Estacion, Ruta> getGraph( )
	{
		return grafoDirigido;
	}

	/**
	 * Retorna los arcos con menor y mayor peso.
	 * @return Arcos con menor y mayor peso.
	 */
	public ArregloDinamico<Edge<Integer, Estacion, Ruta>> darExtremos() 
	{
		Lista<Edge<Integer, Estacion, Ruta>> arcos = grafoDirigido.edges( );
		ArregloDinamico<Edge<Integer, Estacion, Ruta>> retorno = new ArregloDinamico<Edge<Integer, Estacion, Ruta>>(2);
		retorno.addLast(arcos.getElement(1));
		retorno.addLast(arcos.getElement(1));
		for(int i = 2; i <= arcos.size( ); i++)
		{
			Edge<Integer, Estacion, Ruta> actual = arcos.getElement(i);
			if( actual.weight( ) < retorno.getElement(1).weight( ))
				retorno.changeInfo(1, actual);
			if( actual.weight( ) > retorno.getElement(2).weight( ))
				retorno.changeInfo(2, actual);
		}
		return retorno;
	}

	/**
	 * Retorna las estaciones con mayor y menor flujo.
	 * @return Estaciones con mayor y menor.
	 */
	public ArregloDinamico<Vertex<Integer, Estacion, Ruta>> darMayorIn( ) 
	{
		ArregloDinamico<Vertex<Integer, Estacion, Ruta>> estaciones = new ArregloDinamico<Vertex<Integer, Estacion, Ruta>>(3);
		Lista<Vertex<Integer, Estacion, Ruta>> vertices = grafoDirigido.vertices( );

		for(int i = 1; i <= 3; i++)
			estaciones.addAtPos( vertices.firstElement( ), i);

		for(int i = 2; i <= vertices.size( ); i++)
		{
			Vertex<Integer, Estacion, Ruta> actual = vertices.getElement(i);

			if(actual.indegree( ) > estaciones.getElement(1).indegree( ))
				estaciones.changeInfo(1, actual);

			else if(actual.indegree( ) > estaciones.getElement(2).indegree( ))
				estaciones.changeInfo(2, actual);

			else if(actual.indegree( ) > estaciones.getElement(3).indegree( ))
				estaciones.changeInfo(3, actual);
		}
		return estaciones;
	}

	/**
	 * Retorna las estaciones con mayor y menor flujo.
	 * @return Estaciones con mayor y menor.
	 */
	public ArregloDinamico<Vertex<Integer, Estacion, Ruta>> darMayorOut( ) 
	{
		ArregloDinamico<Vertex<Integer, Estacion, Ruta>> estaciones = new ArregloDinamico<Vertex<Integer, Estacion, Ruta>>(3);
		Lista<Vertex<Integer, Estacion, Ruta>> vertices = grafoDirigido.vertices( );

		for(int i = 1; i <= 3; i++)
			estaciones.addAtPos( vertices.firstElement( ), i);

		for(int i = 2; i <= vertices.size( ); i++)
		{
			Vertex<Integer, Estacion, Ruta> actual = vertices.getElement(i);

			if(actual.outdegree( ) > estaciones.getElement(1).outdegree( ))
				estaciones.changeInfo(1, actual);

			else if(actual.outdegree( ) > estaciones.getElement(2).outdegree( ))
				estaciones.changeInfo(2, actual);

			else if(actual.outdegree( ) > estaciones.getElement(3).outdegree( ))
				estaciones.changeInfo(3, actual);
		}
		return estaciones;
	}

	/**
	 * Retorna las estaciones con mayor y menor flujo.
	 * @return Estaciones con mayor y menor.
	 */
	public ArregloDinamico<Vertex<Integer, Estacion, Ruta>> darMenorGradoCombinado( ) 
	{
		ArregloDinamico<Vertex<Integer, Estacion, Ruta>> estaciones = new ArregloDinamico<Vertex<Integer, Estacion, Ruta>>(3);
		Lista<Vertex<Integer, Estacion, Ruta>> vertices = grafoDirigido.vertices( );

		for(int i = 1; i <= 3; i++)
			estaciones.addAtPos( vertices.firstElement( ), i);

		for(int i = 2; i <= vertices.size( ); i++)
		{
			Vertex<Integer, Estacion, Ruta> actual = vertices.getElement(i);
			int total = actual.outdegree( ) + actual.indegree( );

			if(total < estaciones.getElement(1).outdegree( ) + estaciones.getElement(1).indegree( ))
				estaciones.changeInfo(1, actual);

			else if(total < estaciones.getElement(2).outdegree( ) + estaciones.getElement(2).indegree( ))
				estaciones.changeInfo(2, actual);

			else if(total < estaciones.getElement(3).outdegree( ) + estaciones.getElement(3).indegree( ))
				estaciones.changeInfo(3, actual);
		}
		return estaciones;
	}

	/**
	 * Muestra las estaciones con mayor entrada y salida, respectivamente de personas en el rango de edad estipulado.
	 * @param min Edad minima.
	 * @param max Edad maxima.
	 * @return 
	 */
	public ArregloDinamico<Vertex<Integer, Estacion, Ruta>> recomendarRuta(int min, int max) 
	{
		Vertex<Integer, Estacion, Ruta> salida = null;
		Vertex<Integer, Estacion, Ruta> entrada = null;
		ArregloDinamico<Vertex<Integer, Estacion, Ruta>> estaciones = new ArregloDinamico<Vertex<Integer, Estacion, Ruta>>(2);
		int mayorSalida = 0;
		int mayorEntrada = 0;
		Lista<Vertex<Integer, Estacion, Ruta>> vertices = grafoDirigido.vertices( );
		for(int i = 1; i <= vertices.size( ); i++)
		{
			Vertex<Integer, Estacion, Ruta> act = vertices.getElement(i);
			int actSalida = personasPorEdadSalida(act, min, max);
			if( actSalida > mayorSalida )
			{
				mayorSalida = actSalida;
				salida = act;
			}

			int actEntrada = personasPorEdadEntrada(act, min, max);
			if( actEntrada > mayorSalida )
			{
				mayorEntrada = actEntrada;
				entrada = act;
			}
		}
		estaciones.addLast(salida);
		estaciones.addLast(entrada);
		return estaciones;
	}

	/**
	 * Cuenta el numero de personas dentro del rango de edad que salen de la estacion.
	 * @param entrada Estacion a contar.
	 * @param max Edad maxima.
	 * @param min Edad minima.
	 * @return Entradas por edad.
	 */
	private int personasPorEdadSalida(Vertex<Integer, Estacion, Ruta> salida, int min, int max) 
	{
		if(salida == null)
			return 0;
		Lista<Edge<Integer, Estacion, Ruta>> arcos = salida.edges( );
		int contador = 0;
		for(int i = 1; i <= arcos.size( ); i++)
		{
			ArregloDinamico<Viaje> viajes = arcos.getElement(i).getInfo( ).darViajes( );
			for(int j = 1; j <= viajes.size( ); j++)
			{
				int edad = viajes.getElement(j).darEdad( );
				if( min <= edad && edad <= max) contador ++;
			}
		}
		return contador;
	}

	/**
	 * Cuenta el numero de personas dentro del rango de edad que entran a la estacion.
	 * @param entrada Estacion a contar.
	 * @param max Edad maxima.
	 * @param min Edad minima.
	 * @return Entradas por edad.
	 */
	private int personasPorEdadEntrada(Vertex<Integer, Estacion, Ruta> entrada, int min, int max) 
	{
		if(entrada == null)
			return 0;
		int contador = 0;
		Lista<Vertex<Integer, Estacion, Ruta>> vertices = grafoDirigido.vertices( );
		for(int i = 1; i <= vertices.size( ); i++)
		{
			Edge<Integer, Estacion, Ruta> arco = vertices.getElement(i).getEdge(entrada.getId( ));
			if(arco != null)
			{
				ArregloDinamico<Viaje> viajes = arco.getInfo( ).darViajes( );
				for(int j = 1; j <= viajes.size( ); j++)
				{
					int edad = viajes.getElement(j).darEdad( );
					if( min <= edad && edad <= max) contador ++;
				}
			}
		}
		return contador;
	}

	/**
	 * Encuentra el par de estaciones adyacentes que mas utilizan las personas en un rango de edad.
	 * @param min Edad minima.
	 * @param max Edad maxima.
	 * @return Retorna el par de estaciones mas usadas de tipo customer en un rango de edad.
	 */
	public ArregloDinamico<Vertex<Integer, Estacion, Ruta>> estacionesAdyacentes(int min, int max) 
	{
		Lista<Vertex<Integer, Estacion, Ruta>> vertices = grafoDirigido.vertices( );
		ArregloDinamico<Vertex<Integer, Estacion, Ruta>> estaciones = new ArregloDinamico<Vertex<Integer, Estacion, Ruta>>(2);

		Vertex<Integer, Estacion, Ruta> est1 = null;
		Vertex<Integer, Estacion, Ruta> est2 = null;
		int mayor = 0;

		for(int i = 1; i <= vertices.size( ); i ++)
		{
			Vertex<Integer, Estacion, Ruta> actual = vertices.getElement(i);
			Lista<Vertex<Integer, Estacion, Ruta>> adyacentes = actual.vertices( );
			int tot1 = customersEntrada(actual, min, max) + customersSalida(actual, min, max);

			for(int j = 1; j <= adyacentes.size( ); j++)
			{
				int tot2 = customersEntrada(adyacentes.getElement(j), min, max) + customersSalida(adyacentes.getElement(j), min, max);

				if( (tot1 + tot2) > mayor)
				{
					est1 = actual; 
					est2 = adyacentes.getElement(j);
					mayor = tot1 + tot2;
					estaciones = new ArregloDinamico<Vertex<Integer, Estacion, Ruta>>(2);
				}
				else if( mayor > 0 && (tot1 + tot2) == mayor)
				{
					estaciones.addLast(actual);
					estaciones.addLast(adyacentes.getElement(j));
				}
			}
		}
		estaciones.addFirst(est2);
		estaciones.addFirst(est1);
		return estaciones;
	}

	/**
	 * Cuenta el numero de personas dentro del rango de edad que salen de la estacion.
	 * @param entrada Estacion a contar.
	 * @param max Edad maxima.
	 * @param min Edad minima.
	 * @return Entradas por edad.
	 */
	private int customersSalida(Vertex<Integer, Estacion, Ruta> salida, int min, int max) 
	{
		int contador = 0;
		if(salida != null)
		{
			Lista<Edge<Integer, Estacion, Ruta>> arcos = salida.edges( );

			for(int i = 1; i <= arcos.size( ); i++)
			{
				ArregloDinamico<Viaje> viajes = arcos.getElement(i).getInfo( ).darViajes( );
				for(int j = 1; j <= viajes.size( ); j++)
				{
					int edad = viajes.getElement(j).darEdad( );
					String sub = viajes.getElement(j).darSubscripcion( );
					if(sub.contains("Customer"))
						if( min <= edad && edad <= max ) 
							contador++;
				}
			}
		}
		return contador;
	}

	/**
	 * Cuenta el numero de personas dentro del rango de edad que entran a la estacion.
	 * @param entrada Estacion a contar.
	 * @param max Edad maxima.
	 * @param min Edad minima.
	 * @return Entradas por edad.
	 */
	private int customersEntrada(Vertex<Integer, Estacion, Ruta> entrada, int min, int max) 
	{

		int contador = 0;
		if(entrada != null)
		{
			Lista<Vertex<Integer, Estacion, Ruta>> vertices = grafoDirigido.vertices( );
			for(int i = 1; i <= vertices.size( ); i++)
			{
				Edge<Integer, Estacion, Ruta> arco = vertices.getElement(i).getEdge(entrada.getId( ));
				if(arco != null)
				{
					ArregloDinamico<Viaje> viajes = arco.getInfo( ).darViajes( );
					for(int j = 1; j <= viajes.size( ); j++)
					{
						int edad = viajes.getElement(j).darEdad( );
						if( min <= edad && edad <= max && viajes.getElement(j).darSubscripcion( ).contains("Customer")) contador ++;
					}
				}
			}
		}
		return contador;
	}

	/**
	 * Retorna la estacion mas cercana a las coordenadas por parametro.
	 * @param lat Latitud de la posicion.
	 * @param lon Longitud de la posicion.
	 * @return Estacion mas cercana a las coordenadas.
	 */
	public Vertex<Integer, Estacion, Ruta> darEstacionMasCercana(double lat, double lon)
	{
		Lista<Vertex<Integer, Estacion, Ruta>> vertices = grafoDirigido.vertices( );
		Vertex<Integer, Estacion, Ruta> estacion = vertices.getElement(1);
		Double distanciaAct = distanciaHarvesiana(lat, lon, ((estacion.getInfo( ).darLat( )*Math.PI)/180) , ((estacion.getInfo( ).darLong( )*Math.PI)/180));
		for(int i = 2; i <= vertices.size( ); i++)
		{
			Vertex<Integer, Estacion, Ruta> actual = vertices.getElement(i);
			Double distAct = distanciaHarvesiana(lat, lon, ((actual.getInfo( ).darLat( )*Math.PI)/180) , ((actual.getInfo( ).darLong( )*Math.PI)/180));
			if( distAct < distanciaAct)
			{
				distanciaAct = distAct;
				estacion = actual;
			}
		}

		return estacion;
	}


	@SuppressWarnings("deprecation")
	public ArregloDinamico<Viaje> reporteBicicleta(int id, Date fechaFormateada) 
	{
		Lista<Vertex<Integer, Estacion, Ruta>> vertices = grafoDirigido.vertices( );
		ArregloDinamico<Viaje> retorno = new ArregloDinamico<Viaje>(10);
		for(int i = 1; i <= vertices.size( ); i++)
		{
			Vertex<Integer,Estacion,Ruta> verticeActual = vertices.getElement(i);
			Lista<Edge<Integer, Estacion, Ruta>> arcos = verticeActual.edges( );
			for(int j = 1; j <= arcos.size( ); j++)
			{
				Edge<Integer, Estacion, Ruta> arcoActual = arcos.getElement(j);
				Ruta ruta = arcoActual.getInfo( );
				ArregloDinamico<Viaje> viajes = ruta.darViajes( );
				for(int k = 1; k <= viajes.size( ); k++)
				{
					Viaje viajeActual = viajes.getElement(k);
					if(viajeActual.darId( ) == id && fechaFormateada.getDate( ) == viajeActual.darStartTime( ).getDate( ) && fechaFormateada.getMonth( ) == viajeActual.darStartTime( ).getMonth( ))
					{
						viajeActual.asignarSalida(arcoActual);
						retorno.addLast(viajeActual);
					}
				}
			}
		}
		return retorno;
	}

	/**
	 * Da el tiempo de uso de la ultima bicicleta consultada.
	 * @return Tiempo de uso.
	 */
	public double darTiempoDeUso( ) 
	{
		return tiempoDeUso;
	}

	/**
	 * Da el tiempo estacionada de la ultima bicicleta consultada.
	 * @return Tiempo estacionada.
	 */
	public double darTiempoEstacionada( ) 
	{
		return tiempoEstacionada;
	}

	/**
	 * Hace el calculo de la distancia mediante el metodo haversiano.
	 * <pre> lat y lon estan en radianes.
	 * @param lat Latitud del punto a considerar.
	 * @param lon Longitud del punto a considerar.
	 * @return Distancia del punto central y el punto dado por param.
	 */
	public Double distanciaHarvesiana(Double lat1, Double lon1, Double lat2, Double lon2)
	{
		Double t1 = Math.pow(Math.sin((lat1 - lat2)/2),2);
		Double t2 = Math.cos(lat2)*Math.cos(lat1) * Math.pow(Math.sin((lon1 - lon2)/2),2);
		Double harvesiana = Math.sqrt(t1 + t2);
		Double distancia = (2 * RADIO_TERRESTRE) * Math.asin(harvesiana);
		return distancia;
	}

	/**
	 * Organiza el arreglo de viajes.
	 * @param viajes Viajes a organizar
	 */
	public void organizarViajes(ArregloDinamico<Viaje> viajes)
	{
		Comparable[] compViajes = new Comparable[viajes.size( )];
		for(int i = 1; i <= viajes.size( ); i++)
			compViajes[i-1] = viajes.getElement(i);
		Shellsort(compViajes);
		for(int i = 1; i <= viajes.size( ); i++)
			viajes.changeInfo(i, (Viaje) compViajes[i-1]);
	}
	
	/**
	 * Organiza un arreglo por ShellSort
	 * @param array Arreglo a sortear 
	 */
	public void Shellsort(Comparable[] array) 
	{

		int N = array.length;
		int h = 1;
		while (h < N / 3)
			h = 3 * h + 1;


		while (h >= 1) { 
			for (int i = h; i < N; i++)
			{ 
				for (int j = i; j >= h && (array[j].compareTo(array[j - h]) < 0); j -= h) 
				{
					Comparable temp = array[j];
					array[j] = array[j - h];
					array[j - h] = temp;
				}
			}
			h = h / 3;
		}
	}
	
	public void calcularInformacionViajes(ArregloDinamico<Viaje> viajes) 
	{
		double tempQuieta = 0;
		double tempMov = 0;
		for(int i = viajes.size( ); i > 1; i--)
		{
			long diferencia = viajes.getElement(i).darStartTime( ).getTime( ) - viajes.getElement(i-1).darStopTime( ).getTime( );
			tempQuieta += diferencia;
			tempMov += viajes.getElement(i).getTime( );
		}
		tempMov += viajes.getElement(1).getTime( );
		tempMov = Math.abs(tempMov);
		tiempoDeUso = tempMov;
		tempQuieta /= 1000;
		tiempoEstacionada = tempQuieta;
		
	}
	
	public ArregloDinamico<ArregloDinamico<Vertex<Integer, Estacion, Ruta>>> rutasCirulares(int id, int min, int max)
	{
		grafoDirigido.kosarajuSCC( );
		Vertex<Integer, Estacion, Ruta> inicial = grafoDirigido.getVertex(id);
		ArregloDinamico<ArregloDinamico<Vertex<Integer, Estacion, Ruta>>> logrados = new ArregloDinamico<>(5);		
		Lista<Vertex<Integer, Estacion, Ruta>> adyacentes = inicial.vertices( );

		for(int i = 1; i <= adyacentes.size( ); i++)
		{
			ArregloDinamico<Vertex<Integer, Estacion, Ruta>> rutaActual = new ArregloDinamico<>(10);
			grafoDirigido.dfsRestringido( adyacentes.getElement(i), inicial, rutaActual, logrados);
		}
		//		ArregloDinamico<Double> filtrados = new ArregloDinamico<>(5);
		//
		//		for(int i = 1; i <= tiempos.size( ); i++)
		//		{
		//			Double act =  tiempos.getElement(i);
		//		filtrados.addLast(act);
		//		}
		return logrados;
	}
	/**
	 * Carga los datos.
	 * @param file Archivo a cargar.
	 */
	public void cargarDatos(String file)
	{
		BufferedReader bufferLectura = null;

		try
		{
			bufferLectura = new BufferedReader(new FileReader(file));

			String linea = bufferLectura.readLine( );
			linea = bufferLectura.readLine( );

			while (linea!= null)
			{
				String[] datos = linea.split(",");
				Integer idInit = Integer.parseInt(datos[3]);
				Integer idFinal = Integer.parseInt(datos[7]);

				if(!grafoDirigido.containsVertex(idInit))
				{
					Estacion verticeInit = new Estacion(idInit, datos[4], Double.parseDouble(datos[5]), Double.parseDouble(datos[6]));
					grafoDirigido.insertVertex(idInit, verticeInit);
				}
				if(!grafoDirigido.containsVertex(idFinal))
				{
					Estacion verticeFinal = new Estacion(idFinal, datos[8], Double.parseDouble(datos[9]), Double.parseDouble(datos[10]));
					grafoDirigido.insertVertex(idFinal, verticeFinal);
				}

				Edge<Integer,Estacion,Ruta> arco = grafoDirigido.getEdge(idInit, idFinal);
				SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date startTime = null;
				Date stopTime = null;
				try 
				{
					startTime = formato.parse(datos[1].substring(1, 20));
					stopTime = formato.parse(datos[2].substring(1, 20));
				} 
				catch (ParseException e) 
				{
					System.out.println("Fallo en las fechas.");
					e.printStackTrace( );
				}
				Viaje nuevo = new Viaje(Double.parseDouble(datos[0]), startTime, stopTime, Integer.parseInt(datos[11]), datos[12], Integer.parseInt(datos[13]), Integer.parseInt(datos[14]));
			
				if( !idInit.equals(idFinal) )
				{
					if(arco == null )
					{
						Ruta actual = new Ruta(Integer.parseInt(datos[0]), Integer.parseInt(datos[3]), datos[4], Double.parseDouble(datos[5]), Double.parseDouble(datos[6]), Integer.parseInt(datos[7]), datos[8], Double.parseDouble(datos[9]), Double.parseDouble(datos[10]));
						actual.viajeInicial(nuevo);
						grafoDirigido.addEdge(idInit, idFinal, actual.getAverage( ), actual);	
					}

					else
					{
						Ruta temp = arco.getInfo( );
						temp.agregarViaje(nuevo);
						arco.setWeight(temp.getAverage( ));
					}
				}
				total++;
				linea = bufferLectura.readLine( );
			}
			bufferLectura.close();

		}
		catch(IOException e)
		{
			e.printStackTrace();
		} 
	}
}

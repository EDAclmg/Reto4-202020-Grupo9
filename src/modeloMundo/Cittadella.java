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

public class Cittadella 
{
	/**
	 *  Representa el total de viajes leidos.
	 */
	private int total;

	/**
	 * Grafo
	 */
	private DiGraph<Integer, Neuronio, Axon> grafoDirigido;
	
	/**
	 * Metodo constructor de la clase.
	 */
	public Cittadella( )
	{
		total = 0;
		grafoDirigido = new DiGraph<Integer, Neuronio, Axon>( );
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
	public DiGraph<Integer, Neuronio, Axon> getGraph( )
	{
		return grafoDirigido;
	}
	
	/**
	 * Retorna los arcos con menor y mayor peso.
	 * @return Arcos con menor y mayor peso.
	 */
	public ArregloDinamico<Edge<Integer, Neuronio, Axon>> darExtremos() 
	{
		Lista<Edge<Integer, Neuronio, Axon>> arcos = grafoDirigido.edges( );
		ArregloDinamico<Edge<Integer, Neuronio, Axon>> retorno = new ArregloDinamico<Edge<Integer, Neuronio, Axon>>(2);
		retorno.addLast(arcos.getElement(1));
		retorno.addLast(arcos.getElement(1));
		for(int i = 2; i <= arcos.size( ); i++)
		{
			Edge<Integer, Neuronio, Axon> actual = arcos.getElement(i);
			if( actual.weight( ) < retorno.getElement(1).weight( ))
				retorno.changeInfo(1, actual);
			if( actual.weight( ) > retorno.getElement(2).weight( ))
				retorno.changeInfo(2, actual);
		}
		return retorno;
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
					Neuronio verticeInit = new Neuronio(idInit, datos[4]);
					grafoDirigido.insertVertex(idInit, verticeInit);
				}
				if(!grafoDirigido.containsVertex(idFinal))
				{
					Neuronio verticeFinal = new Neuronio(idFinal, datos[8]);
					grafoDirigido.insertVertex(idFinal, verticeFinal);
				}
				
				Edge<Integer,Neuronio,Axon> arco = grafoDirigido.getEdge(idInit, idFinal);
				Impulso nuevo = new Impulso(Double.parseDouble(datos[0]), datos[1], datos[2], Integer.parseInt(datos[11]), datos[12], Integer.parseInt(datos[13]), Integer.parseInt(datos[14]));
				
				if(arco == null)
				{
					Axon actual = new Axon(Integer.parseInt(datos[0]), Integer.parseInt(datos[3]), datos[4], Double.parseDouble(datos[5]), Double.parseDouble(datos[6]), Integer.parseInt(datos[7]), datos[8], Double.parseDouble(datos[9]), Double.parseDouble(datos[10]));
					actual.impusloInicial(nuevo);
					grafoDirigido.addEdge(idInit, idFinal, actual.getAverage( ), actual);	
				}
				else
				{
					Axon temp = arco.getInfo( );
					temp.agregarImpulso(nuevo);
					arco.setWeight(temp.getAverage( ));
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

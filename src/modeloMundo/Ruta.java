package modeloMundo;

import java.util.Date;

import estructuras_de_datos.ArregloDinamico;

public class Axon implements Comparable<Axon>
{
	/**
	 * Duracion promedio del viaje.
	 */
	private double tripAverage;

	/**
	 * Id de la estacion inicial.
	 */
	private int startStationId;

	/**
	 * Nombre de la estacion inicial.
	 */
	private String startStationName;

	/**
	 * Latitud de la estacion inicial.
	 */
	private double startStationLatitude;

	/**
	 * Longitud de la estacion inicial.
	 */
	private double startStationLongitude;

	/**
	 * Id de la estacion final.
	 */
	private int endStationId;

	/**
	 * Nombre de la estacion final.
	 */
	private String endStationName;

	/**
	 * Latitud de la estacion final.
	 */
	private double endStationLatitude;

	/**
	 * Longitud de la estacion final.
	 */
	private double endStationLongitude;
	
	/**
	 *  Arreglo de impulsos.
	 */
	private ArregloDinamico<Impulso> impulsos;
	/**
	 * Metodo constructor de la clase.
	 */
	public Axon (double pTripAverage, int pStartStationId, String pStartStationName, double pStartStationLat, double pStartStationLong, int pEndStationId, String pEndStationName, double pEndStationLat, double pEndStationLong)
	{
		tripAverage = pTripAverage;
		startStationId = pStartStationId;
		startStationName = pStartStationName;
		startStationLatitude = pStartStationLat;
		startStationLongitude = pStartStationLong;
		endStationId = pEndStationId;
		endStationName = pEndStationName;
		endStationLatitude = pEndStationLat;
		endStationLongitude = pEndStationLong;
		impulsos = new ArregloDinamico<Impulso>(5);
	}

	/**
	 * Agrega un impulso.
	 * @param nuevo Nuevo impulso
	 */
	public void agregarImpulso(Impulso nuevo) 
	{
		addToAverage(nuevo.getTime( ));
		impulsos.addLast(nuevo);
	}
	
	/**
	 * Agrega un impulso.
	 * @param nuevo Nuevo impulso
	 */
	public void impusloInicial(Impulso nuevo) 
	{
		impulsos.addLast(nuevo);
	}
	
	/**
	 * Retorna el promedio de tiempo de los viajes.
	 * @return Tiempo promedio de los viajes.
	 */
	public double getAverage( )
	{
		return tripAverage;
	}
	
	/**
	 * Agrega al promedio.
	 * @param nuevo Nuevo dato a promediar.
	 */
	public void addToAverage(double nuevo)
	{
		tripAverage = (( tripAverage * impulsos.size( ) + nuevo ) / (impulsos.size( ) + 1 ));
	}
	
	/**
	 * Retorna la estacion de inicio.
	 * @return Retorna la estacion de inicio.
	 */
	public String darInicio( ) 
	{
		return startStationName;
	}
	
	/**
	 * Retorna la estacion de fin.
	 * @return Retorna la estacion de fin
	 */
	public String darFin( ) 
	{
		return endStationName;
	}
	
	/**
	 * Metodo inutil
	 */
	public int compareTo(Axon o) 
	{
		return 0;
	}

	
}

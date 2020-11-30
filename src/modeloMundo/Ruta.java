package modeloMundo;

import java.util.Date;

import estructuras_de_datos.ArregloDinamico;

public class Ruta implements Comparable<Ruta>
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
	 *  Arreglo de viajes.
	 */
	private ArregloDinamico<Viaje> viajes;
	
	/**
	 * Metodo constructor de la clase.
	 */
	public Ruta (double pTripAverage, int pStartStationId, String pStartStationName, double pStartStationLat, double pStartStationLong, int pEndStationId, String pEndStationName, double pEndStationLat, double pEndStationLong)
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
		viajes = new ArregloDinamico<Viaje>(5);
	}

	/**
	 * Agrega un viaje.
	 * @param nuevo Nuevo viaje
	 */
	public void agregarViaje(Viaje nuevo) 
	{
		addToAverage(nuevo.getTime( ));
		viajes.addLast(nuevo);
	}
	
	/**
	 * Agrega un impulso.
	 * @param nuevo Nuevo impulso
	 */
	public void viajeInicial(Viaje nuevo) 
	{
		viajes.addLast(nuevo);
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
		tripAverage = (( tripAverage * viajes.size( ) + nuevo ) / (viajes.size( ) + 1 ));
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
	 * Retorna los viajes.
	 * @return Viajes.
	 */
	public ArregloDinamico<Viaje> darViajes( )
	{
		return viajes;
	}
	/**
	 * Metodo inutil
	 */
	public int compareTo(Ruta o) 
	{
		return 0;
	}

	
}

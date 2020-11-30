package modeloMundo;

import java.util.Date;

import estructuras_de_datos.Edge;

public class Viaje implements Comparable<Viaje>
{

	/**
	 * Muestra la duracion del viaje.
	 */
	private double tripDuration;
	
	/**
	 * Tiempo inicial.
	 */
	private Date startTime;

	/**
	 * Tiempo final.
	 */
	private Date stopTime;

	/**
	 * Id de la bicicleta.
	 */
	private int bikeId;
	
	/**
	 * Tipo de usuario.
	 */
	private String usertype;
	
	/**
	 * Fecha de nacimiento del ciclista.
	 */
	private int birthYear;
	
	/**
	 * Genero de ciclista.
	 */
	private int gender;
	
	/**
	 * Genero de ciclista.
	 */
	private Edge<Integer, Estacion, Ruta> arco;
	
	/**
	 * Metodo constructor de la clase.
	 */
	public Viaje ( double pTripDuration, Date pStartTime, Date pStopTime, int pBikeId, String pUserType, int pBirthYear, int pGender)
	{
		tripDuration = pTripDuration;
		startTime = pStartTime;
		stopTime = pStopTime;
		bikeId = pBikeId;
		usertype = pUserType;
		birthYear = pBirthYear;
		gender = pGender;
		arco = null;
	}

	/**
	 * Retorna el tiempo del impulso.
	 * @return Tiempo del impulso. 
	 */
	public double getTime() 
	{
		return tripDuration;
	}
	
	/**
	 * Da la edad.
	 * @return Retorna la edad de la persona que realizó el viaje.
	 */
	public int darEdad( )
	{
		return 2020 - birthYear;
	}
	
	/**
	 * Da el tipo de suscripcion de la persona.
	 * @return Tipo de subscripcion.
	 */
	public String darSubscripcion( )
	{
		return usertype;
	}
	
	/**
	 * Da el id de la bicicleta.
	 * @return Id de la bicicleta.
	 */
	public int darId( )
	{
		return bikeId;
	}
	
	/**
	 * Da el tiempo de salida.
	 * @return Tiempo de salida
	 */
	public Date darStartTime( )
	{
		return startTime;
	}
	
	/**
	 * Da el tiempo de llegada.
	 * @return Tiempo de llegada
	 */
	public Date darStopTime( )
	{
		return stopTime;
	}
	/**
	 * Metodo inutil
	 */
	public int compareTo(Viaje o) 
	{
		return 0;
	}

	public void asignarSalida(Edge<Integer, Estacion, Ruta> arcoActual) 
	{
		arco = arcoActual;
	}
	
	public Edge<Integer, Estacion, Ruta> darArco( )
	{
		return arco;
	}

	
}

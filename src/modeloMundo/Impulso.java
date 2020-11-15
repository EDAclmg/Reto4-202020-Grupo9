package modeloMundo;

import java.util.Date;

public class Impulso implements Comparable<Impulso>
{

	/**
	 * Muestra la duracion del viaje.
	 */
	private double tripDuration;
	
	/**
	 * Tiempo inicial.
	 */
	private String startTime;

	/**
	 * Tiempo final.
	 */
	private String stopTime;

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
	 * Metodo constructor de la clase.
	 */
	public Impulso ( double pTripDuration, String pStartTime, String pStopTime,int pBikeId, String pUserType, int pBirthYear, int pGender)
	{
		tripDuration = pTripDuration;
		startTime = pStartTime;
		stopTime = pStopTime;
		bikeId = pBikeId;
		usertype = pUserType;
		birthYear = pBirthYear;
		gender = pGender;
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
	 * Metodo inutil
	 */
	public int compareTo(Impulso o) 
	{
		return 0;
	}

	
}

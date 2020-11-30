package modeloMundo;

import estructuras_de_datos.ArregloDinamico;

public class Estacion implements Comparable<Estacion>
{
	/**
	 * Representa el id de la estacion. 
	 */
	private int id;
	
	/**
	 * Representa el nombre de la estacion.
	 */
	private String nombre; 
	
	/**
	 * Representa la latitud de la estacion.
	 */
	private Double latitud;
	
	/**
	 * Representa la longitud de la estacion.
	 */
	private Double longitud;
	
	/**
	 * Representa un neuronio o vertice.
	 * @param pNombre Nombre del neuronio.
	 * @param pId Id unico del neuronio.
	 * @param pAxInit Ruta inicial agregar.
	 */
	public Estacion( int pId , String pNombre, Double pLat, Double pLon)
	{
		id = pId;
		nombre = pNombre;
		latitud = pLat;
		longitud = pLon;
	}

	/**
	 * Da el nombre
	 * @return Nombre del vertice.
	 */
	public String darNombre( )
	{
		return nombre;
	}
	
	/**
	 * Da la latitud.
	 * @return Latitud.
	 */
	public Double darLat( )
	{
		return latitud;
	}
	
	/**
	 * Da la longitud.
	 * @return Longitud.
	 */
	public Double darLong( )
	{
		return longitud;
	}
	/**
	 * Metodo inutil.
	 */
	public int compareTo(Estacion arg0) 
	{
		return 0;
	}
}

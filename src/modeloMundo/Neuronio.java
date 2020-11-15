package modeloMundo;

import estructuras_de_datos.ArregloDinamico;

public class Neuronio 
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
	 * Representa un neuronio o vertice.
	 * @param pNombre Nombre del neuronio.
	 * @param pId Id unico del neuronio.
	 * @param pAxInit Axon inicial agregar.
	 */
	public Neuronio( int pId , String pNombre)
	{
		id = pId;
		nombre = pNombre;
	}
}

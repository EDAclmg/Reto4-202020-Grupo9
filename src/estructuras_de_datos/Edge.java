package estructuras_de_datos;

public class Edge <K extends Comparable<K>,V, C> implements Comparable<Edge<K,V, C>>
{

	/**
	 * Vertice de origen del arco
	 */
	private Vertex<K,V,C> source;
	
	/**
	 * Vertice de destino del arco
	 */
	private Vertex<K,V,C> dest;
	
	/**
	 *  Peso del arco
	 */
	private double weight;
	
	/**
	 * Representa la informacion del arco.
	 */
	private C info;
	
	/**
	 * Metodo constructor del arco.
	 * @param pSource Vertice de origen.
	 * @param pDest Vertice de destino.
	 * @param pWeight Peso del arco.
	 */
	public Edge(Vertex<K,V,C> pSource, Vertex<K,V,C> pDest, double pWeight, C pInfo)
	{
		source = pSource;
		dest = pDest;
		weight = pWeight;
		info = pInfo;
	}
	
	/**
	 * Devuelve el vertice origen
	 * @return Vertice origen
	 */
	public Vertex<K,V,C> getSource( )
	{
		return source;
	}
	
	/**
	 * Devuelve el vertice destino
	 * @return Vertice destino
	 */
	public Vertex<K,V,C> getDest( )
	{
		return dest;
	}
	
	/**
	 * Devuelve el peso del arco
	 * @return Peso del arco.
	 */
	public double weight( )
	{
		return weight;
	}
	
	/**
	 * Devuelve la informacion del arco
	 * @return Informacion del arco.
	 */
	public C getInfo( )
	{
		return info;
	}
	
	/**
	 * Modifica el peso del arco.
	 * @param pWeight Nuevo peso del arco.
	 */
	public void setWeight(double pWeight)
	{
		weight = pWeight;
	}
	
	/**
	 * Metodo inutil.
	 */

	@Override
	public int compareTo(Edge<K, V, C> o) {
		// TODO Auto-generated method stub
		return 0;
	}

}

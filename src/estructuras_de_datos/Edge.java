package estructuras_de_datos;

public class Edge <K extends Comparable<K>,V, C> implements Comparable<Edge<K,V, C>>, Cloneable
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
	
	private int count;
	
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
	 * Crea un clone del arco y lo invierte.
	 * @return Clone invertido.
	 */
	public Edge<K,V,C> reverseClone( )
	{
		Edge<K, V, C> clone = null;
		try 
		{
			clone = (Edge<K, V, C>) this.clone( );
		}
		catch (CloneNotSupportedException e) 
		{
			e.printStackTrace();
		}
		clone.reverse( );
		return clone;
	}
	
	/**
	 * Invierte el arco.
	 */
	public void reverse( )
	{
		Vertex<K,V,C> temp = source; 
		source = dest;
		dest = temp;
	}
	/**
	 * Metodo inutil.
	 */
	@Override
	public int compareTo(Edge<K, V, C> o) 
	{
		return (int) (weight - o.weight);
	}

}

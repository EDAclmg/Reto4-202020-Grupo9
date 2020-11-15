package estructuras_de_datos;

public class Vertex< K extends Comparable<K>, V, C> implements Comparable<Vertex<K,V,C>>
{

	/**
	 * Id del vertice.
	 */
	private K id;

	/**
	 * Valor del vertice.
	 */
	private V value;

	/**
	 * Marca del vertice.
	 */
	private boolean marca;

	/**
	 * Representa la lista de arcos del vertice.
	 */
	private Lista<Edge<K,V, C>> adjacentes;

	/**
	 * Representa el numero de arcos entrantes.
	 */
	private int indegree;
	/**
	 * 
	 * @param id
	 * @param value
	 */
	public Vertex(K pId, V pValue)
	{
		id = pId;
		value = pValue;
		marca = false;
		adjacentes = new ListaEncadenada<Edge<K,V,C>>( );
		indegree = 0;
	}

	/**
	 * Devuelve el identificador del vértice.
	 * @return Identificador del vértice.
	 */
	public K getId( )
	{
		return id;
	}

	/**
	 * Devuelve el valor asociado al vértice
	 * @return Valor asociado al vértice
	 */
	public V getInfo( )
	{
		return value;
	}

	/**
	 * Verifica si el nodo esta marcado
	 * @return True si lo esta, false de lo contrario.
	 */
	public boolean getMark( )
	{
		return marca;
	}

	/**
	 * Agrega un arco adyacente al vértice
	 * @param edge Arco a agregar
	 */
	public void addEdge( Edge<K,V,C> edge)
	{
		Edge<K,V,C> arco = getEdge( edge.getDest( ).getId( ));
		if( arco == null)
			adjacentes.addLast(edge);
		else
			arco.setWeight(edge.weight( ));
	}
	
	/**
	 *  Arega un arco de llegada al vertice.
	 */
	public void receiveEdge( ) 
	{
		indegree++;
	}

	/**
	 * Marca el vertice.
	 */
	public void mark( )
	{
		marca = true;
	}

	/**
	 * Desmarca el vertice.
	 */
	public void unmark( )
	{
		marca = false;
	}

	/**
	 * Cuenta el numero de arcos salientes del vertice.
	 * @return Numero de arcos salientes del vertice.
	 */
	public int outdegree( )
	{
		return adjacentes.size( );
	}

	/**
	 * Cuenta el numero de arcos entrantes del vertice.
	 * @return Numero de arcos entrantes del vertice.
	 */
	public int indegree( )
	{
		return indegree;
	}

	/**
	 * Retorna el arco con el vértice vertex.
	 * @param vertex vertice del arco a buscar.
	 * @return Arco con el vértice vertex (si existe). Retorna null si no existe.
	 */
	public Edge<K,V,C> getEdge(K vertex)
	{
		Edge<K,V,C> retorno = null;
		for(int i = 0; i < adjacentes.size( ) && retorno == null; i++)
		{
			Edge<K,V,C> act = adjacentes.getElement(i); 
			if(act.getDest( ).getId( ).equals(vertex))
				retorno = act;
		}
		return retorno;
	}

	/**
	 * Retorna una lista con sus vértices adyacentes (salientes).
	 * @return Lista con sus vérticesa adyacentes (salientes).
	 */
	public Lista<Vertex<K,V,C>> vertices( )
	{
		Lista<Vertex<K,V,C>> retorno = new ListaEncadenada<Vertex<K,V,C>>( );
		for(int i = 0; i < adjacentes.size( ); i++)
			retorno.addLast(adjacentes.getElement(i).getDest( )); 
		
		return retorno;
	}

	/**
	 * Retorna una lista con sus arcos adyacentes (salientes).
	 * @return Lista con sus arcos adyacentes (salientes).
	 */
	public Lista<Edge<K,V,C>> edges( )
	{
		return adjacentes; 
	}

	/**
	 * Metodo inutil
	 */
	@Override
	public int compareTo(Vertex<K, V, C> o) {
		// TODO Auto-generated method stub
		return 0;
	}
}

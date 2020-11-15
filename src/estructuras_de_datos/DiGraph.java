package estructuras_de_datos;

public class DiGraph <K extends Comparable<K>,V,C>
{

	/**
	 * Representa la Tabla de Hash por SC con los vertices.
	 */
	private TablaHashSeparateChaining<K,Vertex<K,V,C>> tabla;
	
	/**
	 * Total de vertices insertados.
	 */
	private int vertices;
	
	/**
	 * Total de arcos insertados.
	 */
	private int arcos;
	
	/**
	 * Metodo constructor de la clase.
	 */
	public DiGraph( )
	{
		tabla = new TablaHashSeparateChaining<K,Vertex<K,V,C>>(1000);
		vertices = 0;
		arcos = 0;
	}

	/**
	 * Busca el vértice con el id suministrado.
	 * @param id del vertice a buscar.
	 * @return true si el vértice con id suministrado está en el grafo.
	 */
	public boolean containsVertex( K id )
	{
		return tabla.contains(id);
	}

	/**
	 * Devuelve el número de vértices en el grafo.
	 * @return Numero de vértices en el grafo.
	 */
	public int numVertices( )
	{
		return tabla.size( );
	}

	/**
	 * Devuelve el número de arcos en el grafo.
	 * @return Numero de arcos en el grafo.
	 */
	public int numEdges( )
	{
		Lista<NodoHash<K,Vertex<K,V,C>>> todo = tabla.getAll( );
		int resp = 0;
		for(int i = 1; i <= todo.size( ); i++)
		{
			NodoHash<K, Vertex<K,V,C>> act = todo.getElement(i);
			resp += act.getValue( ).outdegree( );
		}
		return resp;
	}

	/**
	 * 
	 * @param id
	 * @param value
	 */
	public void insertVertex( K id, V value )
	{
		Vertex<K,V,C> nuevo = new Vertex<K,V,C>(id, value);
		tabla.put(id, nuevo);
		vertices++;
	}

	/**
	 * Añade un arco dirigido pesado entre el vertice source y dest, con peso weight. Si el arco YA existe se modifica su peso.
	 * @param source Vertice de salida.
	 * @param dest Vertice de llegada.
	 * @param weight Peso del arco.
	 */
	public void addEdge( K source, K dest, double weight, C pInfo )
	{
		Vertex<K,V,C> salida = tabla.get(source);
		Vertex<K,V,C> destino = tabla.get(dest);
		
		Edge<K,V,C> nuevo = new Edge<K,V,C>(salida, destino, weight, pInfo);
		salida.addEdge(nuevo);
		
		destino.receiveEdge( );
		arcos++;
	}

	/**
	 * Retorna el vértice a partir de su identificador único.
	 * @param id Id del vertice a buscar.
	 * @return Vertice buscado.
	 */
	public Vertex<K,V,C> getVertex( K id )
	{
		return tabla.get(id); 
	}

	/**
	 * Busca el arco entre los vértices idS y idD (si existe). Retorna null si no existe.
	 * @param idS Id del vertice de salida.
	 * @param idD Id del vertice de llegada.
	 * @return Arco entre los vértices idS y idD. null si no existe.
	 */
	public Edge<K,V,C> getEdge( K idS, K idD )
	{
		return tabla.get(idS).getEdge(idD);
	}

	/**
	 * Devuelve una lista de vértices arcos (salientes) al vértice con el id dado por parametro.
	 * @param id Id del vertice con los arcos a retornar.
	 * @return Lista de arcos salientes al vértice con el id dado por parametro.
	 */
	public Lista<Edge<K,V,C>> adjacentEdges(K id)
	{
		return tabla.get(id).edges( );
	}

	/**
	 * Devuelve una lista de vértices adyacentes (entrantes) al vertice con el id dado por parametro.
	 * @param id Id del vertice con vertices adjacentes a retornar.
	 * @return Lista de vértices salientes al vértice con el id dado por parametro.
	 */
	public Lista<Vertex<K,V,C>> adjacentVertex(K id)
	{
		return tabla.get(id).vertices( );
	}

	/**
	 * Devuelve el grado de entrada del vértice vertex (número de arcos entrantes)
	 * @param vertex, vertice con el grado a buscar. 
	 * @return Grado de entrada del vértice vertex (número de arcos entrantes)
	 */
	public int indegree(K vertex)
	{
		return tabla.get(vertex).indegree( );
	}

	/**
	 * Devuelve el grado de salida del vértice vertex (número de arcos salientes)
	 * @param vertex, vertice con el grado a buscar. 
	 * @return Grado de salida del vértice vertex (número de arcos salientes)
	 */
	public int outdegree(K vertex)
	{
		return tabla.get(vertex).outdegree( );
	}

	/**
	 * Devuelve una lista con todos los arcos del grafo.
	 * @return Lista con todos los arcos del grafo.
	 */
	public Lista<Edge<K,V,C>> edges( )
	{
		Lista<NodoHash<K,Vertex<K,V,C>>> todo = tabla.getAll( );
		Lista<Edge<K,V,C>> retorno = new ArregloDinamico<Edge<K,V,C>>(arcos);
		for(int i = 1; i <= todo.size( ); i++)
		{
			NodoHash<K, Vertex<K,V,C>> act = todo.getElement(i);
			Lista<Edge<K,V,C>> arcos = act.getValue( ).edges( );
			for(int j = 0; j < arcos.size( ); j++)
				retorno.addLast(arcos.getElement(j));
		}
		return retorno;
	}

	/**
	 * Devuelve una lista con todos los vertices del grafo.
	 * @return Lista con todos los vertices del grafo.
	 */
	public Lista<Vertex<K,V,C>> vertices( )
	{
		Lista<NodoHash<K,Vertex<K,V,C>>> todo = tabla.getAll( );
		Lista<Vertex<K,V,C>> retorno = new ArregloDinamico<Vertex<K,V,C>>(vertices);
		for(int i = 1; i <= todo.size( ); i++)
		{
			NodoHash<K, Vertex<K,V,C>> act = todo.getElement(i);
			Vertex<K,V,C> vertice = act.getValue( );
			if(vertice != null) retorno.addLast(vertice);
		}
		return retorno;
	}
}
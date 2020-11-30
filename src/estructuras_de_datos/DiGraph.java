package estructuras_de_datos;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

import jdk.management.resource.internal.inst.SocketOutputStreamRMHooks;

public class DiGraph <K extends Comparable<K>,V,C> implements Cloneable
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
	 * Añade un arco dirigido pesado. Si el arco YA existe se modifica su peso.
	 * @param nuevo Nuevo arco. 
	 */
	public void addEdge(Edge<K,V,C> nuevo)
	{
		nuevo.getSource( ).addEdge(nuevo);
		nuevo.getDest( ).receiveEdge( );
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
			for(int j = 1; j <= arcos.size( ); j++)
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

	public void bfs(K idP, int idCCP)
	{

	}

	/**
	 * Realiza el DFO del grafo.
	 * @return Stack con un posible orden topologico.
	 */
	public ListaEncadenada<Vertex<K,V,C>> topologicalOrder( )
	{
		ListaEncadenada<Vertex<K,V,C>> pre = new ListaEncadenada<Vertex<K,V,C>>( );
		ListaEncadenada<Vertex<K,V,C>> post = new ListaEncadenada<Vertex<K,V,C>>( );
		ListaEncadenada<Vertex<K,V,C>> reversePost = new ListaEncadenada<Vertex<K,V,C>>( );

		Lista<Vertex<K, V, C>> vertices = vertices( );
		int i = 1;
		while( i <= vertices.size( ))
		{
			Vertex<K, V, C> inicial = vertices.getElement(i);
			if(!inicial.getMark( ))
				dfo(inicial, pre, post, reversePost);
			i++;
		}
		return reversePost;
	}

	/**
	 * Procesa recursivamente cada vertice del grafo.
	 * @param vertice Vertice actual
	 * @param pre Queue pre.
	 * @param post Queue post.
	 * @param reversePost Stack reverse post.
	 */
	public void dfo(Vertex<K, V, C> vertice, ListaEncadenada<Vertex<K, V, C>> pre, ListaEncadenada<Vertex<K, V, C>> post, ListaEncadenada<Vertex<K,V,C>> reversePost)
	{
		Lista<Vertex<K, V, C>> adyacentes = vertice.vertices( );
		vertice.mark( );
		pre.addFirst(vertice);
		for(int i = 1; i <= adyacentes.size( ); i++)
		{
			Vertex<K,V,C> act = adyacentes.getElement(i);
			if(!act.getMark( ))
				dfo(act, pre, post, reversePost);

		}
		post.addFirst(vertice);
		if(-1 == reversePost.isPresent(vertice)) reversePost.addFirst(vertice);
	}

	/**
	 * Returna un grafo invertido.
	 * @return Grafo invertido.
	 */
	public DiGraph<K,V,C> reverse( )
	{
		DiGraph<K,V,C> clone = null;

		try { clone  = (DiGraph<K, V, C>) this.clone( ); } 
		catch (CloneNotSupportedException e) { e.printStackTrace(); }

		Lista<Edge<K, V, C>> arcos = clone.edges( );
		Lista<Vertex<K, V, C>> vertices = clone.vertices( );

		for(int i = 1; i <= vertices.size( ); i++ )
			vertices.getElement(i).edges( ).clean( );

		for(int i = 1; i <= arcos.size( ); i++)
			clone.addEdge(arcos.getElement(i).reverseClone( ));

		return clone;
	}

	/**
	 * Retorna el numero de SCC del grafo.
	 * @return Numero de SCC del grafo.
	 */
	public int kosarajuSCC( )
	{
		ListaEncadenada<Vertex<K,V,C>> invertido = reverse( ).topologicalOrder( );
		unMark( );
		Vertex<K,V,C> actual = invertido.removeFirst( );
		int SCC = 1;
		while(actual != null)
		{
			if(!actual.getMark( ))
			{
				dfs(actual.getId( ), SCC);
				SCC++;
			}
			actual = invertido.size( ) != 0 ? invertido.removeFirst( ) : null;
		}
		return SCC;
	}

	/**
	 * Desmarca todos los vertices del grafo.
	 */
	public void unMark( ) 
	{
		Lista<Vertex<K, V, C>> vertices = vertices( );
		for(int i = 1; i <= vertices.size( ); i++)
			vertices.getElement(i).unmark( );
	}

	/**
	 * Hace el recorrido dfs recursivamente.
	 * @param idP Id estacion de inicio.
	 * @param idCCP Id Componente conectado.
	 */
	public void dfs(K idP, int idCCP)
	{
		Vertex<K,V,C> inicial = getVertex(idP);
		inicial.connect(idCCP);
		inicial.mark( );
		Lista<Vertex<K,V,C>> adyacentes = inicial.vertices( );
		for(int i = 1; i <= adyacentes.size( ); i++)
		{
			if(!adyacentes.getElement(i).getMark( ))
				dfs(adyacentes.getElement(i).getId( ), idCCP);
		}
	}

	public void dfsRestringido( Vertex<K,V,C> verticeActual, Vertex<K,V,C> inicio, ArregloDinamico<Vertex<K,V,C>> rutaActual,  ArregloDinamico<ArregloDinamico<Vertex<K,V,C>>> logrados) 
	{
		Lista<Vertex<K,V,C>> adyacentes = verticeActual.vertices( );
		rutaActual.addLast(verticeActual);
		for(int i = 1; i <= adyacentes.size( ); i++)
		{
			Vertex<K,V,C> siguiente = adyacentes.getElement(i);
			if(inicio.equals(siguiente))
				logrados.addLast(rutaActual);


			else if( siguiente.getIdCC( ) == verticeActual.getIdCC( ) && rutaActual.isPresent(siguiente) == -1)
			{
				ArregloDinamico<Vertex<K,V,C>> clone = rutaActual.clone( );
				dfsRestringido( siguiente, inicio, clone, logrados);
			}
		}
	}


	/**
	 * Retorna todo el componente fuertemente conectado.
	 * @param idP Id de un nodo pertenenciente al SCC.
	 * @return Lista de todos los vertices que lo componen.
	 */
	public Lista<Vertex<K, V, C>> getSCC(K idP)
	{
		int idCC = getVertex(idP).getIdCC( );
		Lista<NodoHash<K,Vertex<K,V,C>>> todo = tabla.getAll( );
		Lista<Vertex<K,V,C>> retorno = new ArregloDinamico<Vertex<K,V,C>>(vertices);
		for(int i = 1; i <= todo.size( ); i++)
		{
			NodoHash<K, Vertex<K,V,C>> act = todo.getElement(i);
			Vertex<K,V,C> vertice = act.getValue( );
			if(vertice != null && vertice.getIdCC( ) == idCC) retorno.addLast(vertice);
		}
		return retorno;
	}

	public Lista<Vertex<K,V,C>> Dijkstra( K idP , K idD)
	{
		Vertex<K,V,C> salida = getVertex(idP);
		IndexMinPQ<Vertex<K, V, C>> pq = new IndexMinPQ<Vertex<K,V,C>>(107594);
		salida.relajar(0.0, null);
		dijkstraRecursivo(salida, pq,0);

		Lista<Vertex<K,V,C>> ruta = new ListaEncadenada<Vertex<K,V,C>>( );
		Vertex<K,V,C> llegada = getVertex(idD);

		Edge<K,V,C> arco = llegada.getEdgeTo( );
		while(arco != null)
		{
			ruta.addFirst(arco.getSource( ));
			arco = arco.getSource( ).getEdgeTo( );
		}
		return ruta;
	}

	public void dijkstraRecursivo(Vertex<K,V,C> verticeActual, IndexMinPQ<Vertex<K, V, C>> pq, int inserted)
	{
		Lista<Edge<K, V, C>> arcos = verticeActual.edges( );
		for(int i = 1; i <= arcos.size( ); i++)
		{
			Edge<K,V,C> actual = arcos.getElement(i);
			if (actual.getDest( ).relajar(verticeActual.darCosto( ) + actual.weight( ), actual))
			{
				int pos = pq.getIndex(actual.getDest( ));
				if(pos != -1)
					pq.decreaseKey(pos, actual.getDest( ));
				else
				{
					pq.insert(inserted, actual.getDest( ));
					inserted++;
				}
			}

		}
		if(pq.size( ) != 0)
		{
			Edge<K, V, C> next = pq.minKey( ).getEdgeTo( );
			pq.delMin( );
			dijkstraRecursivo(next.getDest( ), pq, inserted);
		}
	}
}
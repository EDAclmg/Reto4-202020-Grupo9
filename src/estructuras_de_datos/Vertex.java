package estructuras_de_datos;

import java.util.Queue;
import java.util.Stack;

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
	private Lista<Edge<K,V, C>> adyacentes;

	/**
	 * Representa el numero de arcos entrantes.
	 */
	private int indegree;
	
	/**
	 * Arco por donde se llego al vertice.
	 */
	private Edge<K,V,C> edgeTo;
	
	/**
	 * Id del cluster al que pertenece.
	 */
	private int idCC;
	
	/**
	 * Distancia del vertice.
	 */
	private int distancia;
	
	/**
	 * Costo del vertice.
	 */
	private double costo;
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
		adyacentes = new ArregloDinamico<Edge<K,V,C>>(15);
		indegree = 0;
		edgeTo = null;
		idCC = -1;
		distancia = -1;
		costo = Double.MAX_VALUE;
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
			adyacentes.addLast(edge);
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
		return adyacentes.size( );
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
		for(int i = 1; i <= adyacentes.size( ) && retorno == null; i++)
		{
			Edge<K,V,C> act = adyacentes.getElement(i); 
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
		Lista<Vertex<K,V,C>> retorno = new ArregloDinamico<Vertex<K,V,C>>(adyacentes.size( ));
		for(int i = 1; i <= adyacentes.size( ); i++)
			retorno.addLast(adyacentes.getElement(i).getDest( )); 
		
		return retorno;
	}

	/**
	 * Retorna una lista con sus arcos adyacentes (salientes).
	 * @return Lista con sus arcos adyacentes (salientes).
	 */
	public Lista<Edge<K,V,C>> edges( )
	{
		return adyacentes; 
	}

	/**
	 * Retorna el arco por el que se llego.
	 * @return Edge to.
	 */
	public Edge<K,V,C> getEdgeTo( )
	{
		return edgeTo;
	}
	
	/**
	 * Relaja el costo para llegar al vertice si el parametro es menor al existente.
	 * @param pCosto Costo a comparar.
	 * @param pArco Arco nuevo.
	 */
	public boolean relajar(Double pCosto, Edge<K,V,C> pArco)
	{
		if(pCosto < costo)
		{
			costo = pCosto;
			edgeTo = pArco;
			return true;
		}
		return false;
	}
	
	/**
	 * Retorna el costo para llegar al vertice.
	 * @return Costo para llegar al vertice.
	 */
	public Double darCosto( ) 
	{
		return costo;
	}
	
	/**
	 * Le asigna el valor de su componente fuertemente concetado al vertice.
	 * @param idCCP Valor del SCC al que corresponde.
	 */
	public void connect(int idCCP) 
	{
		idCC = idCCP;
	}
	
	/**
	 * Retorna el id del cluster al que pertence.
	 * @return Id del cluster al que pertenece.
	 */
	public int getIdCC( )
	{
		return idCC;
	}
	
	/**
	 * Agrega los vertices pertenecientes al cluster.
	 * @param idCCP Id del cluster a buscar.
	 * @param cluster Lista con los elementos del cluster.
	 */
	public void getSCC(int idCCP, Lista<Vertex<K, V, C>> cluster)
	{
		for(int i = 1; i <= adyacentes.size( ); i++)
		{
			Vertex<K,V,C> act = adyacentes.getElement(i).getDest( );
			if(act.getIdCC( ) == idCCP  && cluster.isPresent(act) == -1)
				cluster.addLast(act);			
		}
	}
	
	public void bfs( int idCCP )
	{
		
	}
	
	public void dfsbsf( Queue<K> pre, Queue<K> post, Stack<K> reversePost)
	{
		
	}
	
	
	/**
	 * Metodo inutil
	 */
	@Override
	public int compareTo(Vertex<K, V, C> o) 
	{
		return (int) (costo - o.darCosto( ));
	}
}

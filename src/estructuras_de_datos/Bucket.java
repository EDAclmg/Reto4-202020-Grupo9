package estructuras_de_datos;


public class Bucket< K extends Comparable<K>, V extends Comparable<V>> implements Comparable<Bucket<K,V>>
{
	/**
	 * Nodos del bucket.
	 */
	private ArregloDinamico<NodoHash<K,V>> nodos;
	
	/**
	 * Metodo constructor.
	 */
	public Bucket(int size)
	{
		nodos = new ArregloDinamico<NodoHash<K,V>>(size);
	}
	
	/**
	 * Agrega un nodo al bucket.
	 */
	public void addToBucket(NodoHash<K,V> nodo)
	{
		int i = 1;
		boolean done = false;
		while(i <= nodos.size( ) && nodos.getElement(i) != null && !done)
		{
			if(nodo.getKey( ).equals(nodos.getElement(i).getKey()))
			{
				nodos.getElement(i).changeValue(nodo.getValue( ));
				i = nodos.size( );
				done = true;
			}
			i++;
		}
		if(!done)
			nodos.addLast(nodo);
		
	}
	
	/**
	 * Retorna el nodo buscado.
	 * @param key key del nodo a buscar. 
	 * @return Nodo buscado.
	 */
	public NodoHash<K, V> get(K key) 
	{
		int i = 1;
		NodoHash<K, V> buscado = null;
		while(i <= nodos.size( ) && buscado == null)
		{
			NodoHash<K,V> act = nodos.getElement(i);
			if( act.getKey( ).equals(key))
				buscado = act;
			i++;
		}
		return buscado;
	}
	
	/**
	 * Elmina el nodo con llave key.
	 * @param key key del nodo a eliminar. 
	 * @return Nodo eliminar.
	 */
	public NodoHash<K, V> remove(K key) 
	{
		int i = 1;
		NodoHash<K, V> buscado = null;
		while(i <= nodos.size( ) && buscado == null)
		{
			NodoHash<K,V> act = nodos.getElement(i);
			if( act.getKey( ).equals(key))
			{
				buscado = act;
				nodos.deleteElement(i);
			}
		}
		return buscado;
	}
	
	/**
	 * Retorna todos el bucket.
	 * @return this.
	 */
	public ArregloDinamico<NodoHash<K,V>> getAll()
	{
		return nodos;
	}

	/**
	 * No hace nada.
	 */
	@Override
	public int compareTo(Bucket<K, V> o) 
	{	
		return 0;
	}
}

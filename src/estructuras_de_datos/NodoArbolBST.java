package estructuras_de_datos;

public class NodoArbolBST<K extends Comparable<K>, V extends Comparable<V> > implements Comparable<NodoArbolBST<K,V>>
{
	/**
	 * Representa la llave del nodo.
	 */
	private K key;

	/**
	 * Representa el valor del nodo.
	 */
	private V value;

	/**
	 * Representa el nodo de la izquierda.
	 */
	private NodoArbolBST<K, V> izq;

	/**
	 * Representa el nodo de la derecha.
	 */
	private NodoArbolBST<K, V> der;

	/**
	 * Metodo constructor.
	 */
	public NodoArbolBST(K pKey,V pValue)
	{
		izq = null;
		der = null;
		key = pKey;
		value = pValue;
	}

	/**
	 * Retorna la llave del nodo.
	 */
	public K getKey( )
	{
		return key;
	}

	/**
	 * Retorna el valor del nodo.
	 */
	public V getValue( )
	{
		return value;
	}

	/**
	 * Retorna el nodo de la izquierda.
	 */
	public NodoArbolBST< K, V> getLeft()
	{
		return izq;
	}

	/**
	 * Retorna el nodo de la derecha.
	 */
	public NodoArbolBST< K, V> getRight()
	{
		return der;
	}
	/**
	 * Cambia el valor del nodo.
	 */
	public void changeValue( V nValue )
	{
		value = nValue;
	}

	/**
	 * Retorna el elemento con la llave buscada.
	 * @param key del elemento a buscar
	 * @return V valor del elemento a buscar
	 */
	public V get(K key) 
	{
		V retorno = null;
		if(key.equals(this.key))
			retorno = value;
		else if(key.compareTo(this.key) > 0)
			retorno = der == null? null : der.get(key);
		else
			retorno = izq == null? null : izq.get(key);

		return retorno;
	}

	public int getHeight(K key) 
	{
		int retorno = 0;
		if(key.equals(this.key))
			retorno = this.height();
		else if(key.compareTo(this.key) > 0)
			retorno = der == null? -1 : der.getHeight(key);
		else
			retorno = izq == null? -1 : izq.getHeight(key);
		return retorno;
	}

	/**
	 * Retorna el elemento con la llave buscada.
	 * @param key, llave del elemento a ingresar.
	 * @param pValue, valor del elemento a ingresar
	 */
	public void put(K pKey, V pValue) 
	{
		if(pKey.compareTo(key) > 0)
		{
			if(der == null)
				der = new NodoArbolBST<K, V>(pKey, pValue);
			else
				der.put(pKey, pValue);
		}
		else if(pKey.compareTo(key) < 0)
		{
			if(izq == null)
				izq = new NodoArbolBST<K, V>(pKey, pValue);
			else
				izq.put(pKey, pValue);
		}
		else
		{
			key = pKey;
			value = pValue;
		}
	}


	public int height() 
	{
		int i = 0;
		int d = 0;
		if (izq == null && der == null)
		    return 0;
		if(izq != null)
		i += izq.height();
		if(der != null)
		d += der.height();
		return Math.max(i, d) +1;
	}

	/**
	 * Retorna el la minima llave en el arbol.
	 * @return Minima llave en el arbol.
	 */
	public K min() 
	{
		return izq == null? key: izq.min();
	}

	/**
	 * Retorna el la maxima llave en el arbol.
	 * @return Maxima llave en el arbol.
	 */
	public K max() 
	{
		return der == null? key: der.max();
	}

	/**
	 * Retorna una lista de las llaves en el arbol.
	 * @return Lista de las llaves en el arbol.
	 */
	public void keySet(Lista<K> llaves) 
	{
		if(izq != null)
			izq.keySet(llaves);
		llaves.addLast(key);
		if(der != null)
			der.keySet(llaves);
	}

	/**
	 * Retorna las llaves en el rango dado.
	 * @param init llave del rango inicial.
	 * @param end llave del rango final.
	 * @return las llaves en el rango dado.
	 */
	public void keysInRange(ArregloDinamico<K> llaves, K init, K end) 
	{
		if(izq != null && init.compareTo(izq.getKey()) < 0)
			izq.keysInRange(llaves, init, end);
		if(key.compareTo(init) >= 0 && key.compareTo(end) <= 0)
			llaves.addLast(key);
		if(der != null && end.compareTo(der.getKey()) > 0)
			der.keysInRange(llaves, init, end);
	}

	/**
	 * Retorna las llaves en el valor dado.
	 * @param init valor del rango inicial.
	 * @param end valor del rango final.
	 * @return las valor en el rango dado.
	 */
	public void valuesInRange(ArregloDinamico<V> valores, K init, K end) 
	{
		if(izq != null && init.compareTo(izq.getKey()) < 0)
			izq.valuesInRange(valores, init, end);
		if(key.compareTo(init) >= 0 && key.compareTo(end) <= 0)
			valores.addLast(value);
		if(der != null && end.compareTo(der.getKey()) > 0)
			der.valuesInRange(valores, init, end);
	}
	
	/**
	 * Metodo aparentemente inutil.
	 */
	public int compareTo(NodoArbolBST<K, V> nodo) 
	{

		return nodo.getKey().compareTo(key);
	}
}

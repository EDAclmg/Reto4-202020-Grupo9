package estructuras_de_datos;

public class TablaHashLinearProbing < K extends Comparable<K>, V extends Comparable<V>> implements TablaSimbolos<K, V> 
{
	
	
	/**
	 * Representa el factor de carga actual.
	 */
	private double factorDeCarga;

	/**
	 * Representa el total de elementos del mapa.
	 */
	private int totalElementos;

	/**
	 * Representa el arreglo utilizado en el mapa;
	 */

	private ArregloDinamico<NodoHash<K,V>> mapa;

	/**
	 * Representa la constante a utilizada en el MAD.
	 */
	private int a;

	/**
	 * Representa la constante b utilizada en el MAD.
	 */
	private int b;

	/**
	 * Representa la constante p utilizada en el MAD.
	 */
	private int p;

	/**
	 * Representa la constante m utilizada en el MAD.
	 */
	private int m;
	
	/**
	 * Representa el numero de rehash desde que se creo.
	 */
	private int nreHash; 

	/**
	 * Constructor onstructor de la clase.
	 * @param size. Tamano inicial de la clase.
	 */
	public TablaHashLinearProbing( int size ) 
	{
		m = Extras.getNextPrime(2*size);
		p = Extras.getNextPrime(m);
		a  = (int) (Math.random() * (p-1)+1);
		b  = (int) (Math.random() * (p-1)+1);
		mapa = new ArregloDinamico<NodoHash<K,V>> (m);
	}

	/**
	 * Mete un elemento al mapa.
	 * @param key. LLave del nodo.
	 * @param value. Valor del nodo.
	 */
	public void put(K key, V value) 
	{
		if((darFactorDeCarga() + (1/m)) >= 0.75)
			rehash( );
		
		int pos = getPos(key);
		NodoHash<K,V> act = mapa.getElement(pos);
		
		if(act == null || act.getKey( ).equals("EMPTY"))
		{
			NodoHash<K,V> nuevo = new NodoHash<K,V>(key, value);
			mapa.addAtPos(nuevo, pos);
			totalElementos++;
		}
		
		else if(act.getKey().equals(key))
			act.changeValue(value);
		
		else
			putRecursiveVersion(pos + 1, key, value);

		verificarInvariante();
	}

	/**
	 * Mete un elemento al mapa recursivamente.
	 * @param pos. Posicion actual a revisar.
	 * @param key. LLave del nodo.
	 * @param value. Valor del nodo.
	 */
	private void putRecursiveVersion(int pos, K key, V value)
	{
		if(pos > m)
			pos = 1;
		NodoHash<K,V> act = mapa.getElement(pos);
		if(act == null || act.getKey().equals("EMPTY"))
		{
			NodoHash<K,V> nuevo = new NodoHash<K,V>(key, value);
			mapa.changeInfo(pos, nuevo);
			totalElementos++;
		}
		
		else if(act.getKey().equals(key))
			act.changeValue(value);
		
		else
			putRecursiveVersion(pos + 1, key, value);
	}

	/**
	 * Busca un elemento del mapa.
	 * @param key. LLave del nodo.
	 */
	@Override
	public V get( K key ) 
	{
		int pos = getPos(key);
		NodoHash<K,V> act = mapa.getElement(pos);
		
		if( act != null && key.equals(act.getKey( )) )
			return act.getValue();
		
		else if( act != null )
			return getRecursiveVersion(pos + 1, key);
		
		else
			return null;
	}

	/**
	 * Busca un elemento del mapa recursivamente.
	 * @param key. LLave del nodo.
	 */
	private V getRecursiveVersion(int pos, K key) 
	{
		V retorno = null;
		if(pos > m )
			pos = 1;
		
		NodoHash<K,V> act = mapa.getElement(pos);
		
		if(act != null && key.equals( act.getKey( ) ))
			retorno =  act.getValue();
		
		else if( act != null )
			
			retorno = getRecursiveVersion(pos+1, key);
		
		return retorno;
	}

	/**
	 * Elimina un elemento del mapa.
	 * @param key. LLave del nodo.
	 */
	@Override
	public V remove( K key) 
	{
		int pos = getPos(key);
		V retorno = null;
		NodoHash<K,V> act = mapa.getElement(pos);
		
		if(act != null && key.equals(act.getKey()))
		{
			retorno = act.getValue();
			act.deleteLP();
			totalElementos--;
		}
		
		else 
			return deleteRecursiveVersion(pos + 1, key);

		verificarInvariante();
		return retorno;
	}

	/**
	 * Elimina un elemento del mapa recursivamente.
	 * @param key. LLave del nodo.
	 */
	private V deleteRecursiveVersion(int pos, K key) 
	{
		if(pos >= (m-1))
			pos = 0;
		
		NodoHash<K,V> act = mapa.getElement(pos);
		if(act != null && key.equals(act.getKey()))
		{
			V retorno = act.getValue();
			act.deleteLP();
			totalElementos--;
			return retorno;
		}
		
		else if(key.equals("EMPTY") || (act != null && act.getKey() != null))
			return getRecursiveVersion(pos + 1, key);
		
		else
			return null; 
	}

	/**
	 * Busca un elemento en el mapa.
	 * @param key. LLave del nodo.
	 * @return true si esta, false de lo contrario.
	 */
	@Override
	public boolean contains( K key) 
	{
		return get(key) != null ? true:false;
	}

	/**
	 * Revisa si la tabla se encuentra vacia.
	 * @return True si esta vacia, false de lo contrario.
	 */
	public boolean isEmpty( )
	{
		return totalElementos > 0 ? false : true;
	}

	/**
	 * Retornar el numero de tuplas presentes en la tabla de simobolos.
	 * @return numero de tuplas presentes en la tabla de simobolos.
	 */
	public int size( )
	{
		return totalElementos;
	}

	public Lista<K> keySet() 
	{
		ArregloDinamico<K> result = new ArregloDinamico<K>(totalElementos);
		int i = 1;
		
		while(i <= m)
		{
			NodoHash<K,V> temp = mapa.getElement(i);
			
			if(temp != null && temp.getKey() != null && temp.getKey() != "EMPTY")
				result.addLast(temp.getKey());	
			
			i++;
		}
		return result;
	}

	/**
	 * Retorna una lista con todas los valores almacenados en la Tabla.
	 * @return Todas los valores almacenados en la Tabla.
	 */
	public Lista<V> valueSet( )
	{
		ArregloDinamico<V> result = new ArregloDinamico<V>(totalElementos);
		int i = 0;
		while(i < m)
		{
			NodoHash<K,V> temp = mapa.getElement(i);
			
			if(temp != null && temp.getValue() != null )
				result.addLast(temp.getValue());	
			
			i++;
		}
		return result;
	}
	
	public double darFactorDeCarga()
	{
		return (0.0 + totalElementos)/(0.0 + m);
	}
	
	public void rehash()
	{
		nreHash++;
		int j = m;
		m = Extras.getNextPrime((2*j));
		p = Extras.getNextPrime(m);
		a  = (int) (Math.random() * (p-1)+1);
		b  = (int) (Math.random() * (p-1)+1);
		Lista<NodoHash<K,V>> todo = getAll( );
		totalElementos = 0;
		mapa = new ArregloDinamico<NodoHash<K,V>>(m);
		
		for(int i = 1; i <= todo.size();i++)
		{
			NodoHash<K,V> act= todo.getElement(i);
			put(act.getKey(), act.getValue());
		}
	}
	
	public int numeroReHash()
	{
		return nreHash;
	}
	
	
	public Lista<NodoHash<K,V>> getAll() 
	{
		ArregloDinamico<NodoHash<K,V>> result = new ArregloDinamico<NodoHash<K,V>>(totalElementos);
		int i = 1;
		
		while(i <= m)
		{
			NodoHash<K,V> temp = mapa.getElement(i);
			
			if(temp != null)
				result.addLast(temp);
			i++;
		}
		return result;
	}

	public int getPos(K key)
	{
		int hashInicial =  (a * key.hashCode( ) + b) % p;
		int hashFinal = Math.abs(hashInicial) % m;
		return hashFinal;
	}

	private void verificarInvariante( )
	{
		assert factorDeCarga < 0.75;
		assert factorDeCarga >= 0;
	}
}

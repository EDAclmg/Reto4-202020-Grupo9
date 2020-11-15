package estructuras_de_datos;

public class ListaEncadenada <T extends Comparable<T>> implements Lista<T> 
{

	/**
	 * Primer nodo de la lista.
	 */
	private NodoEncadenado<T> primerNodo;

	/**
	 * Ultimo nodo de la lista.
	 */
	private NodoEncadenado<T> ultimoNodo;

	/**
	 * Retorna el numero actual de nodos.
	 */
	private int numNodos;
	/**
	 *Constructor de la lista, la inicia vacia por defecto.
	 */
	public ListaEncadenada( )
	{
		primerNodo = null;
		ultimoNodo = null; 
		numNodos = 0; 
	}
	/**
	 * Retornar el numero de elementos presentes en el arreglo
	 * @return el tamanio 
	 */
	public int size( )
	{
		return numNodos;
	}

	/**
	 * Agrega el elemento en la primera posicion.
	 * @param T element, element a agregar.
	 */
	public void addFirst(T element)
	{
		NodoEncadenado<T> nuevo = new NodoEncadenado<T>(element);
		nuevo.cambiarSiguiente(primerNodo);
		primerNodo = nuevo;
		numNodos++;
	}

	/**
	 * Agrega el elemento en la ultima posicion.
	 * @param T element, element a agregar.
	 */
	public void addLast(T element)
	{
		NodoEncadenado<T> nuevo = new NodoEncadenado<T>(element);

		if(primerNodo == null)
			primerNodo = nuevo;

		else 
			ultimoNodo.cambiarSiguiente(nuevo);

		ultimoNodo = nuevo;
		numNodos++;
	}

	/**
	 * Inserta el elemento por parametro en la posicion indicada.
	 * @param element, elemento a insertar.
	 * @param pos, posicion en la que insertar.
	 */
	public void insertElement(T element, int pos)
	{

		if(pos == 0)
			addFirst(element);
		else if(primerNodo != null)
		{
			NodoEncadenado<T> nuevo = new NodoEncadenado<T>(element);
			primerNodo.insertElement(nuevo, pos);
		}
		numNodos++;
	}

	/**
	 * Elimina el primer elemento.
	 * @return elemento eliminado.
	 */
	public T removeFirst( )
	{ 
		T temp = primerNodo.darInformacion( );
		primerNodo = primerNodo.darSiguiente( );
		numNodos--;
		return temp;	
	}

	/** 
	 * Elminina el ultimo elemento.
	 * @return elemento eliminado.
	 */
	public T removeLast( )
	{
		NodoEncadenado act = primerNodo;
		T temp = null;
		if(act!= null && act.darSiguiente() != null)
		{
			while(act.darSiguiente().darSiguiente() != null)
				act = act.darSiguiente( );

			temp = (T) act.darSiguiente().darInformacion();
			act.cambiarSiguiente(null);
			ultimoNodo = act;
			numNodos--;
		}
		else if(act != null)
		{
			temp = primerNodo.darInformacion();
			primerNodo = null;
		}

		return temp;
	}

	/**
	 * Elimina el elemento en la posicion dada por parametro.
	 * @param pos, posicion del elemento a eliminar.
	 * @return elemento eliminado.
	 */
	public T deleteElement( int pos )
	{
		T retorno = null;
		numNodos--;
		if(pos == 0)
		{
			numNodos++;
			retorno = removeFirst( );
		}

		else 
			retorno = primerNodo.deleteElement(pos);

		return retorno;
	}

	/**
	 * Retorna el primer elemento de la lista.
	 * @return Primer elemento de la lista.
	 */
	public T firstElement( )
	{
		return primerNodo == null? null:primerNodo.darInformacion( );
	}

	/**
	 * Retorna el ultimo elemento de la lista.
	 * @return Ultimo elemento de la lista. 
	 */
	public T lastElement( )
	{
		return ultimoNodo == null? null: ultimoNodo.darInformacion( );
	}


	/**
	 * Retorna la posicion del elemento dado por parametro, si este se encuentra en la lista.
	 * @param element, elemento a ubicar.
	 * @return Posicion del elemento buscado, -1 en el caso de no encontrarlo.
	 */
	public int isPresent(T element )
	{
		return primerNodo == null? -1:primerNodo.existe(element, 0);
	}


	/**
	 * Intercambia los elementos en las posiciones dadas por parametro.
	 * Pre pos1 < pos2
	 * @param pos1, posicion del primer elemento.
	 * @param pos2, posicion del segundo elemento.
	 */
	public void exchange(int pos1, int pos2)
	{
		if(pos1 <= numNodos && pos2 <= numNodos)
		{
		NodoEncadenado<T> nodo1 = getNodo(pos1);
		NodoEncadenado<T> nodo2 = getNodo(pos2); 
		T temp = nodo1.darInformacion();
		nodo1.cambiarInformacion(nodo2.darInformacion());
		nodo2.cambiarInformacion(temp);
		}
		
	}

	/**
	 * Cambia la informacion del elemento en la posicion dada.
	 * @param pos, posicion del elemento a actualizar.
	 * @param element, nueva informacion.
	 */
	public void changeInfo(int pos, T element )
	{
		if(primerNodo != null)
			primerNodo.changeInfo(element, pos);
	}

	/**
	 * Revisa si la estructura se encuentra vacia.
	 * @return True si esta vacia, false de lo contrario.
	 */
	public boolean isEmpty( )
	{
		return primerNodo == null? true : false;
	}

	/**
	 * Retornar el elemento en la posicion i
	 * @param i posicion de consulta
	 * @return elemento de consulta. null si no hay elemento en posicion.
	 */
	public T getElement( int i )
	{
		return ( T ) primerNodo.getElement(i);
	}

	/**
	 * Retornar el nodo en la posicion i
	 * @param i posicion de consulta
	 * @return nodo de consulta. null si no hay elemento en posicion.
	 */
	public NodoEncadenado<T> getNodo( int i )
	{
		return primerNodo.getNodo(i);
	}

}

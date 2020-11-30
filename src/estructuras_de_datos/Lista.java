package estructuras_de_datos;

public interface Lista <T extends Comparable<? extends T>> {

	/**
	 * Retornar el numero de elementos presentes en el arreglo
	 * @return el tamanio de la estructura.
	 */
	int size( );
	
	/**
	 * Agrega el elemento en la primera posicion.
	 * @param T element, element a agregar.
	 */
	void addFirst(T element);
	
	/**
	 * Agrega el elemento en la ultima posicion.
	 * @param T element, element a agregar.
	 */
	void addLast(T element);
	
	/**
	 * Inserta el elemento por parametro en la posicion indicada.
	 * @param element, elemento a insertar.
	 * @param pos, posicion en la que insertar.
	 */
	void insertElement(T element, int pos);
	
	/**
	 * Elimina el primer elemento.
	 * @return elemento eliminado.
	 */
	T removeFirst( );
	
	/** 
	 * Elminina el ultimo elemento.
	 * @return elemento eliminado.
	 */
	T removeLast( );
	
	/**
	 * Elimina el elemento en la posicion dada por parametro.
	 * @param pos, posicion del elemento a eliminar.
	 * @return elemento eliminado.
	 */
	T deleteElement( int pos );
	
	/**
	 * Retorna el primer elemento de la lista.
	 * @return Primer elemento de la lista.
	 */
	T firstElement( );
	
	/**
	 * Retorna el ultimo elemento de la lista.
	 * @return Ultimo elemento de la lista. 
	 */
	T lastElement( );
	
	/**
	 * Retorna la posicion del elemento dado por parametro, si este se encuentra en la lista.
	 * @param element, elemento a ubicar.
	 * @return Posicion del elemento buscado.
	 */
	int isPresent(T element );
	
	/**
	 * Intercambia los elementos en las posiciones dadas por parametro.
	 * @param pos1, posicion del primer elemento.
	 * @param pos2, posicion del segundo elemento.
	 */
	void exchange(int pos1, int pos2);
	
	/**
	 * Cambia la informacion del elemento en la posicion dada.
	 * @param pos, posicion del elemento a actualizar.
	 * @param element, nueva informacion.
	 */
	void changeInfo(int pos, T element );
	
	/**
	 * Revisa si la estructura se encuentra vacia.
	 * @return True si esta vacia, false de lo contrario.
	 */
	boolean isEmpty( );
	
	/**
	 * Retornar el elemento en la posicion i
	 * @param i posicion de consulta
	 * @return elemento de consulta. null si no hay elemento en posicion.
	 */
	T getElement( int i );

	/**
	 * Vacia la lista.
	 */
	void clean( );

}

package estructuras_de_datos;


/**
 * 2019-01-23
 * Estructura de Datos Arreglo Dinamico de Strings.
 * El arreglo al llenarse (llegar a su maxima capacidad) debe aumentar su capacidad.
 * @author Fernando De la Rosa
 *
 */
public class ArregloDinamico < T extends Comparable<T>> implements Comparable<ArregloDinamico<T>>, Lista<T>, Cloneable {

	/**
	 * Capacidad maxima del arreglo
	 */
	private int tamanoMax;
	/**
	 * Numero de elementos presentes en el arreglo (de forma compacta desde la posicion 0)
	 */
	private int tamanoAct;
	/**
	 * Arreglo de elementos de tamaNo maximo
	 */
	private T elementos[ ];

	/**
	 * Construir un arreglo con la capacidad maxima inicial.
	 * @param max Capacidad maxima inicial
	 */
	public ArregloDinamico( int max )
	{
		elementos = (T[]) new Comparable[max];
		tamanoMax = max;
		tamanoAct = 0;
	}


	public T getElement(int i) 
	{
		i--;
		return i < tamanoMax && i >= 0? elementos[i]: null;
	}

	public int size() 
	{
		return tamanoAct;
	}

	public void addFirst(T elemento)
	{
		T[ ] temp = elementos;
		if(tamanoAct == tamanoMax)
			tamanoMax *= 2;

		elementos =(T[]) new Comparable[tamanoMax];
		elementos[0] = elemento;

		for(int i = 0; i < tamanoAct;i++)
			elementos[i+1] = temp[i];

		tamanoAct++;


	}

	public void addLast(T elemento)
	{
		if ( tamanoAct == tamanoMax )
		{  // caso de arreglo lleno (aumentar tamaNo)
			tamanoMax = 2 * tamanoMax;
			T [ ] copia = elementos;
			elementos =(T[]) new Comparable[tamanoMax];
			for ( int i = 0; i < tamanoAct; i++)
			{
				elementos[i] = copia[i];
			} 
		}
		elementos[tamanoAct] = elemento;
		tamanoAct++;
	}

	public void  insertElement(T element, int pos)
	{
		
		T[ ] temp = elementos;
		elementos =(T[]) new Comparable[tamanoMax];
		if(tamanoAct == tamanoMax)
			tamanoMax *= 2;
		
		
		int posicion = pos <= tamanoMax && pos > 0 ? (pos-1):-1;
		if(elementos[posicion] == null) tamanoAct++; 
		elementos[posicion] = element;
		
		for(int i = 0; i < posicion;i++)
			elementos[i] = temp[i];
		for(int i = posicion; i < tamanoAct;i++)
			elementos[i+1] = temp[i];	
	}
	
	public void addAtPos (T elem, int pos)
	{
		int posicion = pos <= tamanoMax && pos > 0? (pos-1):-2;
		if(elementos[posicion] == null) tamanoAct++; 
		elementos[posicion] = elem;
	}
	public T removeFirst( )
	{
		T temp = elementos[0];
		elementos[0] = null;

		for(int i = 0; i+1 < tamanoAct && elementos[i+1] != null;i++)
			elementos[i] = elementos[i+1];

		tamanoAct--;
		return temp;
	}

	public T removeLast( )
	{
		T temp = elementos[tamanoAct-1];
		elementos[tamanoAct-1] = null;
		tamanoAct--;
		return temp;
	}

	public T deleteElement(int pos)
	{
		int posicion = pos <= tamanoAct && pos > 0? (pos-1) : tamanoAct + 1;
		T temp = elementos[posicion];
		elementos[posicion] = null;

		for(int i = posicion; i + 1 < tamanoAct;i++)
			elementos[i] = elementos[i+1];

		tamanoAct--;
		return temp;
	}

	public T firstElement( ) 
	{
		return elementos[0];
	}

	public T lastElement( )
	{
		return tamanoAct > 0 ? elementos[tamanoAct-1] :null;
	}

	public boolean isEmpty()
	{
		return elementos[0] == null?true:false;

	}

	public int isPresent (T element) 
	{
		int i = 0;
		int resp = -1;
		while(i < tamanoAct && resp == -1)
		{
			if(elementos[i].equals(element))
				resp = i;
			i++;
		}

		return resp;
	}
	
	public void exchange (int pos1, int pos2)
	{
		int posicion1 = pos1 <= tamanoAct && pos1 > 0? (pos1-1):null;
		int posicion2 = pos2 <= tamanoAct && pos2 > 0? (pos2-1):null;
		T temp = elementos[posicion1];
		elementos[posicion1] = elementos[posicion2];
		elementos[posicion2] = temp;
	}

	public void changeInfo (int pos, T elem)
	{
		int posicion = pos <= tamanoMax && pos > 0? (pos-1):-2;
		elementos[posicion] = elem;
	}
	
	public int darTamano() 
	{
		return tamanoAct;
	}

	public T[] darElementos()
	{
		return elementos;
	}

	/**
	 * Vacia la lista
	 */
	public void clean( ) 
	{
		tamanoAct = 0;
		elementos =(T[]) new Comparable[tamanoMax];
	}
	
	public ArregloDinamico<T> clone( )
	{
		try {
			return (ArregloDinamico<T>) super.clone( );
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			return null;
		}
	}
	@Override
	public int compareTo(ArregloDinamico o) 
	{
		// TODO Auto-generated method stub
		return 0;
	}

}
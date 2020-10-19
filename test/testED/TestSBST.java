package testED;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import estructuras_de_datos.ArregloDinamico;
import estructuras_de_datos.BST;
import estructuras_de_datos.Lista;
import estructuras_de_datos.NodoArbolBST;

public class TestSBST 
{

	private BST<String, String> arbol;

	@Before
	public void setUp1()
	{
		arbol = new BST<String, String>();
	}


	public void setUp2()
	{
		arbol = new BST<String, String>();
		int j = 101;
		arbol.put("100", "A"+ 100);
		for(int i = 100 ; i >= 0; i--)
		{ 
			arbol.put(""+i, "A" + i );
			arbol.put(""+j, "A" + j );
			j++;
		}
	}

	public void setUp3()
	{
		arbol = new BST<String, String>();
		int j = 11;
		arbol.put("10", "A"+ 10);
		for(int i = 9 ; i >= 0; i--)
		{ 
			arbol.put(""+i, "A" + i );
			arbol.put(""+j, "A" + j );
			j++;
		}
	}

	public void setUp4()
	{
		arbol = new BST<String, String>();
		String abc ="ABCDEFGHIJKLNOPQRSTUVWXYZ";
		int j = abc.length();
		j /= 2;
		arbol.put("M", "M"+ 11);
		for(int i = j ; i >= 0; i--)
		{ 
			arbol.put("" + abc.charAt(i), "" + abc.charAt(i) );
			arbol.put( "" + abc.charAt(j), "" + abc.charAt(j) );
			j++;
		}
	}

	/**
	 * Retornar el número de parejas [Llave,Valor] del árbol
	 * @return número de parejas [Llave,Valor] del árbol.
	 */
	@Test
	public void size() 
	{
		assertEquals(0,arbol.size());
		setUp2( );
		assertEquals(203, arbol.size( ));
	}

	/**
	 * Informa si el árbol es vacío
	 * @return true si esta vacio, false de lo contrario.
	 */
	@Test
	public void isEmpty()
	{
		assertTrue(arbol.isEmpty());
		setUp2();
		assertFalse(arbol.isEmpty());
	}

	/**
	 * Retorna el valor V asociado a la llave key dada.
	 * @param key llave del valor V a buscar.
	 * @return Retorna el valor V asociado a la llave key dada. Si la llave no se encuentra se retorna el valor null
	 */
	@Test
	public void get( ) 
	{
		assertNull(arbol.get("NO EXISTO"));
		setUp2();
		assertEquals("A100",arbol.get("100"));
		assertEquals("A1", arbol.get("1"));
		assertEquals("A150", arbol.get("150"));
	}

	@Test
	public void getHeight( ) 
	{
		assertEquals(-1, arbol.getHeight("AA"));
		arbol.put("250", "A250");
		assertEquals(0, arbol.getHeight("250"));
		setUp4();
		String abc ="ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		for(int i = 0 ; i < abc.length()/2; i++)
			assertEquals(abc.length() / 2 - i - 1, arbol.getHeight("" + abc.charAt(i + abc.length() / 2)));
	}

	/**
	 * Indica si la llave key se encuentra en el árbol
	 * @param key de la llave a buscar
	 * @return True si la llave esta en el arbol, false de lo contrario.
	 */
	@Test
	public void contains( ) 
	{
		assertFalse(arbol.contains("NO EXISTO"));
		arbol.put("250", "A250");
		assertTrue(arbol.contains("250"));
		setUp2();
		for(int i = 0; i < 200; i ++)
			assertTrue(arbol.contains(""+i));


	}

	/**
	 * Inserta la pareja [key, val] en el árbol. Si la llave ya existe se reemplaza el valor. 
	 * @param key de la llave a meter.
	 * @param Value valor a introducir al arbol.
	 */
	@Test
	public void put(  ) 
	{ 
		arbol.put("258", "A258");
		arbol.contains("258");
		arbol.put("8", "A8");
		arbol.contains("8");
		arbol.put("58", "A58");
		arbol.contains("58");
		arbol.put("300", "A300");
		arbol.contains("300");
		arbol.put("270", "A270");
		arbol.contains("270");
	}

	@Test
	public void height() 
	{
		assertEquals(-1, arbol.height());
		arbol.put("250", "A250");
		assertEquals(0, arbol.height());
		setUp4();
		assertEquals(13, arbol.height());
	}

	/**
	 * Llave más pequeña del árbol.
	 * @return Retorna la llave más pequeña del árbol. null si el árbol esta vacío.
	 */
	@Test
	public void min() 
	{
		arbol.put("C", "CA");
		assertEquals("C", arbol.min( ));
		setUp2();
		assertEquals("0", arbol.min( ));
		arbol.put("-1", "A-1");
		assertEquals("-1", arbol.min( ));
	}

	/**
	 * Llave más grande del árbol.
	 * @return Retorna la llave más grande del árbol. null si el árbol esta vacío.
	 */
	@Test
	public void max()
	{
		arbol.put("30", "A30");
		assertEquals("30", arbol.max( ));
		setUp4();
		assertEquals("Z", arbol.max( ));		
		arbol.put("ZZ", "AZ");
		assertEquals("ZZ", arbol.max( ));
	}

	/**
	 * Retorna una lista de las llaves en el arbol.
	 * @return Lista de las llaves en el arbol.
	 */
	@Test
	public void keySet( ) 
	{
		arbol.put("1", "A1");
		assertEquals("1", arbol.keySet( ).getElement(1));
		setUp4( );
		String abc ="ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		for(int i = 0 ; i < abc.length(); i++)
			assertTrue( -1 != arbol.keySet( ).isPresent(abc.charAt(i)+""));
		String abc2 ="ASDFGHJKQWERTYUIOZXCVBNNMOP";
		for(int i = 0 ; i < abc.length(); i++)
			assertTrue( -1 != arbol.keySet( ).isPresent(abc2.charAt(i)+""));

	}


	/**
	 * Retorna las llaves en el rango dado.
	 * @param init llave del rango inicial.
	 * @param end llave del rango final.
	 * @return las llaves en el rango dado.
	 */
	@Test
	public void keysInRange( ) 
	{
		arbol.put("1", "A1");
		assertEquals("1", arbol.keysInRange("0", "2").getElement(1));
		setUp4( );
		assertEquals("Z", arbol.keysInRange("X", "ZZ").lastElement());
		assertTrue( -1 != arbol.keysInRange("X", "ZZ").isPresent("X"));
		assertTrue( -1 != arbol.keysInRange("X", "ZZ").isPresent("Y"));
	}

	/**
	 * Retorna las llaves en el valor dado.
	 * @param init valor del rango inicial.
	 * @param end valor del rango final.
	 * @return las valor en el rango dado.
	 */
	@Test
	public void valuesInRange( ) 
	{
		arbol.put("1", "A1");
		assertEquals("A1", arbol.valuesInRange("0", "2").getElement(1));
		setUp4( );
		assertEquals("Z", arbol.valuesInRange("X", "ZZ").lastElement());
		assertTrue( -1 != arbol.valuesInRange("X", "ZZ").isPresent("X"));
		assertTrue( -1 != arbol.valuesInRange("X", "ZZ").isPresent("Y"));
	}



}

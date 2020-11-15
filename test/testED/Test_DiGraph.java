package testED;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import estructuras_de_datos.ArregloDinamico;
import estructuras_de_datos.BST;
import estructuras_de_datos.DiGraph;
import estructuras_de_datos.Edge;
import estructuras_de_datos.Lista;
import estructuras_de_datos.NodoHash;
import estructuras_de_datos.Vertex;


public class Test_DiGraph 
{
	private DiGraph<String, String,String> grafo;

	private String abc;
	
	@Before
	public void setUp1( )
	{
		grafo = new DiGraph<String, String,String>( );
		abc = "ABCDEFGHIJKLNOPQRSTUVWXYZ";
	}
	
	public void setUp2( )
	{
		for(int i = 0 ; i < abc.length( ); i++)
		{ 
			grafo.insertVertex("" + abc.charAt(i), "" + abc.charAt(i) );
		}
	}
	
	public void setUp3( )
	{
		for(int i = 0 ; i < abc.length( ) - 1; i++)
		{ 
			grafo.addEdge("" + abc.charAt(i), "" + abc.charAt(i + 1), i, "" + i);
		}
	}
		
	@Test
	public void containsVertex( )
	{
		setUp2( );
		for(int i = 0 ; i < abc.length( ); i++)
		{ 
			assertTrue( grafo.containsVertex( "" + abc.charAt(i) ) );
		}
	}

	@Test
	public void numVertices( )
	{
		assertEquals( 0, grafo.numVertices( ));
		setUp2( );
		assertEquals(abc.length( ), grafo.numVertices( ));
		grafo.insertVertex("00", "00");
		assertEquals(abc.length( ) + 1, grafo.numVertices( ));
	}

	@Test
	public void numEdges( )
	{
		assertEquals( 0, grafo.numEdges( ));
		setUp2( );
		assertEquals(0, grafo.numEdges( ));
		setUp3( );
		assertEquals(24, grafo.numEdges( ));
		grafo.addEdge("A", "H", 112, "AH");
		assertEquals(25, grafo.numEdges( ));
	}

	@Test
	public void insertVertex( )
	{
		setUp2( );
		for(int i = 0 ; i < abc.length( ); i++)
		{ 
			assertTrue( grafo.containsVertex( "" + abc.charAt(i) ) );
			assertEquals("" + abc.charAt(i), grafo.getVertex("" + abc.charAt(i) ).getInfo( ) );
		}
	}

	@Test
	public void addEdge( )
	{
		setUp2( );
		setUp3( );
		for(int i = 0 ; i < abc.length( ); i++)
		{ 
			assertTrue( grafo.containsVertex( "" + abc.charAt(i) ) );
			assertEquals("" + abc.charAt(i), grafo.getVertex("" + abc.charAt(i) ).getInfo( ) );
		}
	}

	@Test
	public void getVertex( )
	{
		setUp2( );
		for(int i = 0 ; i < abc.length( ); i++)
		{ 
			assertEquals("" + abc.charAt(i), grafo.getVertex("" + abc.charAt(i) ).getInfo( ) );
		}
		assertNull(grafo.getVertex("NO EXISTO"));
	}

	@Test
	public void getEdge(  )
	{
		setUp2( );
		setUp3( );
		for(int i = 0 ; i < abc.length( ) - 1; i++)
		{
			assertNotNull(grafo.getEdge("" + abc.charAt(i), "" + abc.charAt(i + 1)));	
		}
	}

	@Test
	public void adjacentEdges( )
	{
		setUp2( );
		setUp3( );
		assertEquals("0", grafo.adjacentEdges("A").getElement(0).getInfo( ));
		grafo.addEdge("A", "C", 123, "AC");
		assertEquals("AC", grafo.adjacentEdges("A").getElement(1).getInfo( ));
		assertEquals( 2, grafo.adjacentEdges("A").size( ));
	}

	@Test
	public void adjacentVertex( )
	{
		setUp2( );
		setUp3( );
		assertEquals("B", grafo.adjacentVertex("A").getElement(0).getId( ));
		grafo.addEdge("A", "C", 123, "AC");
		assertEquals("C", grafo.adjacentVertex("A").getElement(1).getId( ));
		assertEquals( 2, grafo.adjacentVertex("A").size( ));
	}

	@Test
	public void indegree( )
	{
		setUp2( );
		setUp3( );
		assertEquals(0 , grafo.indegree("A"));
		grafo.addEdge("B", "A", 123, "BA");
		assertEquals(1 , grafo.indegree("A"));
		assertEquals(1 , grafo.indegree("B"));
		grafo.addEdge("C", "A", 153, "CA");
		assertEquals(2 , grafo.indegree("A"));
	}

	@Test
	public void outdegree( )
	{
		setUp2( );
		setUp3( );
		assertEquals(0 , grafo.outdegree("Z"));
		grafo.addEdge("Z", "A", 123, "ZA");
		assertEquals(1 , grafo.outdegree("Z"));
		assertEquals(1 , grafo.outdegree("B"));
		grafo.addEdge("Z", "C", 153, "ZC");
		assertEquals(2 , grafo.outdegree("Z"));
	}

	@Test
	public void edges( )
	{
		assertEquals(0, grafo.edges( ).size( ));
		
		setUp2( );
		setUp3( );
		assertEquals(abc.length( ) - 1, grafo.edges( ).size( ));
		
		Lista<Edge<String, String, String>> arcos = grafo.edges( );
		for(int i = 0 ; i < abc.length( ) - 1; i++)
		{ 
			arcos.isPresent(grafo.getEdge("" + abc.charAt(i), "" + abc.charAt(i + 1)));
			assertNotEquals(-1, arcos.isPresent(grafo.getEdge("" + abc.charAt(i), "" + abc.charAt(i + 1))));
		}
	}

	@Test
	public void vertices( )
	{
		assertEquals(0, grafo.vertices( ).size( ));
		
		setUp2( );
		assertEquals(abc.length( ), grafo.vertices( ).size( ));
		
		Lista<Vertex<String, String, String>> vertices = grafo.vertices( );
		for(int i = 0 ; i < abc.length( ); i++)
		{ 
			assertNotEquals(-1, vertices.isPresent(grafo.getVertex("" + abc.charAt(i))));
		}
	}	
}

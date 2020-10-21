package view;

import controller.Controller;


public class View 
{
	    /**
	     * Metodo constructor
	     */
	    public View()
	    {
	    	
	    }
	    
	    public void printMenu()
		{
			System.out.println("Hola! Seleccione la opcion a su gusto.");
			System.out.println("Opcion1. Ver la informacion basica de los accidentes.");
			System.out.println("Opcion2. Ver el total de accidentes por fecha con un arbol BST.");
			System.out.println("Opcion3. Ver el total de accidentes por fecha con un arbol RBT.");
		}

		public void printMessage(String mensaje) {

			System.out.println(mensaje);
		}

		public void printYearSelection( ) 
		{
			System.out.println("Hola! Seleccione el anio a cargar");
			System.out.println("Opcion1. 2016");
			System.out.println("Opcion2. 2017");
			System.out.println("Opcion3. 2018");
			System.out.println("Opcion4. 2019");
			System.out.println("Opcion5. Todos los anteriores.");
			System.out.println("Opcion6. Cargar unicamente en un arbol BST y ver su tiempo de carga.");
			System.out.println("Opcion7. Cargar unicamente en un arbol RBT y ver su tiempo de carga.");
		}
		public void printYearSelectionCorto( ) 
		{
			System.out.println("Hola! Seleccione el anio a cargar, por el metodo decidido");
			System.out.println("Opcion1. 2016");
			System.out.println("Opcion2. 2017");
			System.out.println("Opcion3. 2018");
			System.out.println("Opcion4. 2019");
			System.out.println("Opcion5. Todos los anteriores.");
		}	
}

package view;

import javax.sound.midi.Soundbank;

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
	    	printSeparador();
			System.out.println("Hola! Seleccione la opcion a su gusto.");
			System.out.println(" ");
			System.out.println("1. Ver la informacion basica de los accidentes.");
			System.out.println("2. Ver el total de accidentes por fecha con un arbol BST.");
			System.out.println("3. Ver el total de accidentes por fecha con un arbol RBT.");
			System.out.println("4. Ver el total de accidentes anteriores a una fecha.");
			System.out.println("5. Ver el total de accidentes en un rango de fechas.");
			System.out.println("6. Ver el estado y fecha con mas accidentes.");
			System.out.println("7. Ver los accidentes por rango de horas.");
			System.out.println("8. Ver los accidentes dentro de un rango.");
			printSeparador();
		}

		public void printMessage(String mensaje) 
		{
			System.out.println(mensaje);
		}
		
		public void printSeparador( ) 
		{
			System.out.println("                   -                   -                    -                    -                    -                    -                    -                    -");
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
			System.out.println("1. 2016");
			System.out.println("2. 2017");
			System.out.println("3. 2018");
			System.out.println("4. 2019");
			System.out.println("5. Todos los anteriores.");
		}	
}

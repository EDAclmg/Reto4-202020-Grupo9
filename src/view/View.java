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
			System.out.println("Opcion2. Ver el total de accidentes por fecha.");
		}

		public void printMessage(String mensaje) {

			System.out.println(mensaje);
		}		
}

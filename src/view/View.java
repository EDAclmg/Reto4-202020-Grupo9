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
	    
	    /**
	     * Imprime el menu.
	     */
	    public void printMenu()
		{
	    	printSeparador( );
			System.out.println("Hola! Seleccione la opcion a su gusto.");
			System.out.println("1. Ver la informacion de carga.");
			System.out.println("2. Consultar el grado de una estacion.");
			System.out.println("3. Salir del programa");
			printSeparador( );
		}

	    /**
	     * Imprime un mensaje.
	     */
		public void printMessage(String mensaje) 
		{
			System.out.println(mensaje);
		}
		
		/**
	     * Imprime el separador.
	     */
		public void printSeparador( ) 
		{
			System.out.println("                   -                   -                    -                    -                    -                    -                    -                    -");
		}
		
		/**
	     * Imprime la seleccion de los archivos.
	     */
		public void printFileSelection( ) 
		{
			System.out.println("Hola! Seleccione el archivo a cargar.");
			System.out.println("1. Datos 201801-1 ");
			System.out.println("2. Datos 201801-2 ");
			System.out.println("3. Datos 201801-3");
			System.out.println("4. Datos 201801-4");
			System.out.println("5. Todos los anteriores.");
			System.out.println("6. Cargar mas de un archivo.");
		}

		public void elegirArchivos() 
		{
			System.out.println("Ingrese los numeros de los archivos a cargar separados por comas, y sin espacios. De la siguiente forma. #,#,#,#");
			System.out.println("1. Datos 201801-1 ");
			System.out.println("2. Datos 201801-2 ");
			System.out.println("3. Datos 201801-3");
			System.out.println("4. Datos 201801-4");
		
		}	
}

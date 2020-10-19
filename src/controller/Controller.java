package controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import estructuras_de_datos.ArregloDinamico;
import estructuras_de_datos.BST;
import estructuras_de_datos.Lista;
import modeloMundo.Accidente;
import modeloMundo.Central;
import view.View;

public class Controller {

	private Central modelo;

	private View view;
	/**
	 * Crear la vista y el modelo del proyecto
	 * @param capacidad tamaNo inicial del arreglo
	 */
	public Controller ()
	{
		view = new View();
	}

	public void run() 
	{
		Scanner lector = new Scanner(System.in);
		boolean fin = false;
		modelo = new Central( ); 
		modelo.cargarDatos( );
		while( !fin )
		{
			view.printMenu();
			SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
			int option = lector.nextInt();
			switch(option)
			{
			case 1:
				BST<Date, ArregloDinamico<Accidente>>  arbol = modelo.darArbol( );
				view.printMessage("Los datos basicos son: ");
				view.printMessage("");
				view.printMessage("El total de accidentes es: " + modelo.darTotal() + ".");
				view.printMessage("Se ingresaron " + arbol.size( ) + " llaves.");
				view.printMessage("La altura del arbol es " + arbol.height( ) +".");

				String min = formato.format(arbol.min());
				String max = formato.format(arbol.max());
				view.printMessage("El resultado con la menor llave es: " + min );
				view.printMessage("El resultado con la mayor llave es: " + max );
				break;

			case 2:
				view.printMessage("Ingrese la fecha a buscar en el siguiente formato: AAAA-MM-DD. Donde A es el anio, M es el mes y D es el dia");
				String year = lector.next();
				try 
				{
					Date fecha = formato.parse(year);
					Lista<Accidente> temp = modelo.accidentesEnFecha(fecha);
					
					if(temp != null)
					{
						view.printMessage("El numero de accidentes fueron: " + temp.size( ));
						Lista<Double> sev = modelo.accidentesPorSeveridad(temp);
						for(int i = 1; i <= 4; i++)
							view.printMessage("El numero de accidentes por severidad de grado " + i + " es: " + (int) (double) sev.getElement(i) + ".");
					}
					else
						view.printMessage("No se encontro el anio ingresado");
				}
				catch (ParseException e) 
				{
					view.printMessage("Se presento un error en el formato de la fecha.");
					view.printMessage("");

				}

				break;
			}
		}
	}
}
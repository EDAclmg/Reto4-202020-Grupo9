package controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import estructuras_de_datos.ArregloDinamico;
import estructuras_de_datos.BST;
import estructuras_de_datos.Lista;
import estructuras_de_datos.RBT;
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
		view.printYearSelection( );
		int anio = lector.nextInt( );
		if( anio == 6 || anio == 7)
		{
			view.printYearSelectionCorto();
			int anio2 = lector.nextInt();
			String datos = validarYear(anio2);
			if(anio == 6)
			{
				long startTime = System.currentTimeMillis();
				modelo.cargarDatosBST(datos);
				long endTime = System.currentTimeMillis();
				System.out.println("El tiempo de carga fue de: " + (endTime - startTime) + " milisegundos");
				System.out.println("Gracias por utilizar el programa");
				System.exit(0);
				run( );
			}


			else
			{
				long startTime = System.currentTimeMillis();
				modelo.cargarDatosRBT(datos);
				long endTime = System.currentTimeMillis();
				System.out.println("El tiempo de carga fue de: " + (endTime - startTime) + " milisegundos");
				System.out.println("Gracias por utilizar el programa");
				System.exit(0);
			}
		}
		else
		{
			String datos = validarYear(anio);
			modelo.cargarDatos(datos);
		}
		while( !fin )
		{
			view.printMenu( );
			SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
			int option = lector.nextInt( );
			String year = "";
			switch(option)
			{
			case 1:
				BST<Date, ArregloDinamico<Accidente>>  arbolBST = modelo.darArbolBST( );
				RBT<Date, ArregloDinamico<Accidente>>  arbolRBT = modelo.darArbolRBT( );
				view.printMessage("Los datos basicos son: ");
				view.printMessage("");
				view.printMessage("El total de accidentes es: " + modelo.darTotal() + ".");
				view.printMessage("Se ingresaron " + arbolBST.size( ) + " llaves en el arbol BST y " + arbolRBT.size( ) + " llaves en el arbol RBT.");
				view.printMessage("La altura del arbol BST es " + arbolBST.height( ) + " y del RBT es " + arbolRBT.height( ) + ".");

				String minBST = formato.format(arbolBST.min());
				String minRBT = formato.format(arbolRBT.min());

				String maxBST = formato.format(arbolBST.max());
				String maxRBT = formato.format(arbolRBT.max());

				view.printMessage("El resultado con la menor llave BST es: " + minBST );
				view.printMessage("El resultado con la menor llave RBT es: " + minRBT );

				view.printMessage("El resultado con la mayor llave BST es: " + maxBST );
				view.printMessage("El resultado con la mayor llave RBT es: " + maxRBT );
				break;

			case 2:
				view.printMessage("Ingrese la fecha a buscar en el siguiente formato: AAAA-MM-DD. Donde A es el anio, M es el mes y D es el dia");
				year = lector.next();
				try 
				{
					Date fecha = formato.parse(year);
					Lista<Accidente> temp = modelo.accidentesEnFechaBST(fecha);

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

			case 3:
				view.printMessage("Ingrese la fecha a buscar en el siguiente formato: AAAA-MM-DD. Donde A es el anio, M es el mes y D es el dia");
				year = lector.next( );
				try 
				{
					Date fecha = formato.parse(year);
					Lista<Accidente> temp = modelo.accidentesEnFechaRBT(fecha);

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
			default:
				view.printMessage("El numero ingresado no corresponde a una opcion, intente de nuevo.");
				break;
			}
		}
	}

	

		private String validarYear(int anio) 
		{
			String resp = "";
			switch(anio)
			{
			case 1:
				resp = "./data/us_accidents_dis_2016.csv";
				break;

			case 2:
				resp = "./data/us_accidents_dis_2017.csv";
				break;

			case 3:
				resp = "./data/us_accidents_dis_2018.csv";
				break;

			case 4:
				resp = "./data/us_accidents_dis_2019.csv";
				break;

			case 5:
				resp = "./data/US_Accidents_Dec19.csv";
				break;
			default:
				resp = "./data/us_accidents_small.csv";
				break;
			}
			return resp;
		}
	}
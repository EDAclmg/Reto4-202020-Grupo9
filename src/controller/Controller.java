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
		view.printYearSelectionCorto( );

		int anio = lector.nextInt( );
		String anioStr = validarYear(anio);
		modelo.cargarDatos(anioStr);

		ArregloDinamico<String> semana = new ArregloDinamico<String>(7);
		semana.addLast("Domingo"); semana.addLast("Lunes"); semana.addLast("Martes"); semana.addLast("Miercoles"); semana.addLast("Jueves"); semana.addLast("Viernes"); semana.addLast("Sabado");

		while( !fin )
		{
			view.printMenu( );
			SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat formatoHorario = new SimpleDateFormat("HH:mm");
			int option = lector.nextInt( );
			String year = "";
			String init = "";
			String end = "";
			BST<Date, ArregloDinamico<Accidente>>  arbolBST = modelo.darArbolBST( );
			RBT<Date, ArregloDinamico<Accidente>>  arbolRBT = modelo.darArbolRBT( );
			switch(option)
			{
			case 1:
				view.printSeparador();
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
				view.printSeparador();
				break;

			case 2:
				view.printSeparador();
				view.printMessage("Ingrese la fecha a buscar en el siguiente formato: AAAA-MM-DD. Donde A es el anio, M es el mes y D es el dia");
				year = lector.next();
				try 
				{
					Date fecha = formato.parse(year);
					System.out.println(fecha);
					Lista<Accidente> temp = modelo.accidentesEnFechaBST(fecha);

					if(temp != null)
					{
						view.printMessage(" ");
						view.printMessage("El numero de accidentes fueron: " + temp.size( ));
						Lista<Double> sev = modelo.accidentesPorSeveridad(temp);
						for(int i = 1; i <= 4; i++)
							view.printMessage("El numero de accidentes por severidad de grado " + i + " es: " + (int) (double) sev.getElement(i) + ".");
						view.printSeparador( );
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
				view.printSeparador( );
				view.printMessage("Ingrese la fecha a buscar en el siguiente formato: AAAA-MM-DD. Donde A es el anio, M es el mes y D es el dia");
				year = lector.next( );
				try 
				{
					Date fecha = formato.parse(year);
					Lista<Accidente> temp = modelo.accidentesEnFechaRBT(fecha);

					if(temp != null)
					{
						view.printMessage(" ");
						view.printMessage("El numero de accidentes fueron: " + temp.size( ));
						Lista<Double> sev = modelo.accidentesPorSeveridad(temp);
						for(int i = 1; i <= 4; i++)
							view.printMessage("El numero de accidentes por severidad de grado " + i + " es: " + (int) (double) sev.getElement(i) + ".");
						view.printSeparador( );
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
			case 4:
				view.printSeparador( );
				view.printMessage("Ingrese la fecha limite a buscar en el siguiente formato: AAAA-MM-DD. Donde A es el anio, M es el mes y D es el dia");
				year = lector.next( );
				try 
				{
					Date fecha = formato.parse(year);
					Lista<ArregloDinamico<Accidente>> temp = modelo.accidentesAntesDeFecha(fecha);

					if(temp != null)
					{
						ArregloDinamico<Accidente> acc = modelo.getMax(temp);
						String fMax = formato.format(acc.getElement(1).getStartTime());
						int numAcc = acc.size( ); 
						view.printMessage(" ");
						view.printMessage("El numero de accidentes antes de la fecha fueron: " + modelo.count(temp));
						view.printMessage("La fecha con mas accidentes fue: " + fMax + " con un total de " + numAcc + " accidentes.");
						view.printSeparador( );
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
			case 5:
				view.printMessage("Ingrese la fecha inicial a buscar en el siguiente formato: AAAA-MM-DD. Donde A es el anio, M es el mes y D es el dia");
				init = lector.next( );
				view.printMessage("Ingrese la fecha final a buscar en el siguiente formato: AAAA-MM-DD. Donde A es el anio, M es el mes y D es el dia");
				end = lector.next( );
				try 
				{
					Date fechaInit = formato.parse(init);
					Date fechaEnd = formato.parse(end);
					Lista<ArregloDinamico<Accidente>> temp = modelo.accidentesEnRango(fechaInit, fechaEnd);

					if(temp != null)
					{

						Lista<Double> sev = modelo.accidentesPorSeveridadMacro(temp);
						view.printSeparador( );
						view.printMessage("El numero de accidentes en el rango de fechas fue: " + modelo.count(temp));
						for(int i = 1; i <= 4; i++)
							view.printMessage("El numero de accidentes por severidad de grado " + i + " es: " + (int) (double) sev.getElement(i) + ".");
						int mayor = modelo.mayor(sev);
						view.printMessage(" ");
						view.printMessage("Por lo que se puede ver que el  categoria de accidentes corresponde a los de categoria " + mayor + " con un total de " + (int) (double) sev.getElement(mayor) + ".");
						view.printSeparador( );
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


			case 6:
				view.printMessage("Ingrese la fecha inicial a buscar en el siguiente formato: AAAA-MM-DD. Donde A es el anio, M es el mes y D es el dia");
				init = lector.next( );
				view.printMessage("Ingrese la fecha final a buscar en el siguiente formato: AAAA-MM-DD. Donde A es el anio, M es el mes y D es el dia");
				end = lector.next( );
				try 
				{
					Date fechaInit = formato.parse(init);
					Date fechaEnd = formato.parse(end);
					Lista<ArregloDinamico<Accidente>> temp = modelo.accidentesEnRango(fechaInit, fechaEnd);

					if(temp != null)
					{
						ArregloDinamico<String> print = modelo.accidentesPorEstado(temp);
						view.printSeparador();
						view.printMessage("El estado con mas accidentes es " + print.firstElement( ) + " con un total de " + print.lastElement( ) + " accidentes." );

						RBT<Date, ArregloDinamico<Accidente>> arbol = modelo.darArbolRBT( );
						Lista<ArregloDinamico<Accidente>> todo = arbol.valuesInRange(arbol.min(), arbol.max( ));

						ArregloDinamico<Accidente> acc = modelo.getMax(todo);
						String fMax = formato.format(acc.getElement(1).getStartTime());
						int numAcc = acc.size( ); 
						view.printMessage("La fecha con mas accidentes fue: " + fMax + " con un total de " + numAcc + " accidentes.");
						view.printSeparador( );
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

			case 7:
				view.printMessage("Ingrese la hora inicial a buscar en el siguiente formato: HH:mm. Donde H es la hora y m son los minutos. En el sistema horario de 24 horas.");
				init = lector.next( );
				view.printMessage("Ingrese la hora final a buscar en el siguiente formato: HH:mm. Donde H es la hora y m son los minutos. En el sistema horario de 24 horas.");
				end = lector.next( );

				try 
				{
					Date horaInit = formatoHorario.parse(aprox(formatoHorario.parse(init)));
					Date horaEnd = formatoHorario.parse(aprox(formatoHorario.parse(end)));
					ArregloDinamico<Accidente> temp = modelo.accidentesPorRangoDeHoras(horaInit, horaEnd);
					if(temp != null)
					{
						view.printSeparador( );

						Lista<Double> sev = modelo.accidentesPorSeveridad(temp);
						for(int i = 1; i <= 4; i++)
							view.printMessage("El numero de accidentes en el rango de horas dado, por severidad de grado " + i + " es: " + (int) (double) sev.getElement(i) + ".");

						view.printMessage(" ");
						double porcentaje = (temp.size() + 0.0) / (modelo.darTotal( ) + 0.0);
						porcentaje *=100;

						view.printMessage("Lo que representa un " + porcentaje +"% de los accidentes reportados." );				
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

			case 8:

				view.printMessage("Ingrese la latitud a ser considerada dentro del punto.");
				init = lector.next( );

				view.printMessage("Ingrese la longitud a ser considerada dentro del punto.");
				end = lector.next( );

				view.printMessage("Ingrese la distancia maxima de los accidentes a reportar.");
				String radio = lector.next( );

				ArregloDinamico<Integer> dias = modelo.accidentesEnRadio(((Double.parseDouble(init)*Math.PI)/180), ((Double.parseDouble(end)*Math.PI)/180), Double.parseDouble(radio));
				for(int i = 1; i <= 7; i++)
					view.printMessage("Se reportaron " + dias.getElement(i) + " accidentes el dia " + semana.getElement(i) + ".");

				break;
			default:
				view.printMessage("El numero ingresado no corresponde a una opcion, intente de nuevo.");
				break;
			}
		}
	}

	public String aprox(Date init)
	{
		int hora = init.getHours( );
		int min = init.getMinutes( );
		
		if(min > 45)
			hora++;
		else min = min < 15 ? 0: 30;
		
		String hour = hora < 10? "0" + hora: ""+ hora;
		String minutos = min < 10? "0" + min: ""+ min;
		
		return hour + ":" + minutos;
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
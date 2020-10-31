package modeloMundo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.sound.midi.Soundbank;

import estructuras_de_datos.ArregloDinamico;
import estructuras_de_datos.BST;
import estructuras_de_datos.Lista;
import estructuras_de_datos.RBT;



public class Central 
{
	/**
	 *  Constate que representa el radio de la tierra en KM.
	 */
	private final static Double RADIO_TERRESTRE = 3958.8;
	/**
	 * Representa el arbol BST.
	 */
	private BST<Date, ArregloDinamico<Accidente>> arbolBST;

	/**
	 * Representa el arbol RBT.
	 */
	private RBT<Date, ArregloDinamico<Accidente>> arbolRBT;

	/**
	 * Representa total de llaves ingresadas.
	 */
	private int total;
	
	/**
	 * Latitud central a considerar.
	 */
	private Double latCentral;
	
	/**
	 * Longitud central a considerar.
	 */
	private Double lonCentral;

	/**
	 * Metodo constuctor de la clase.
	 */
	public Central()
	{
		arbolBST = new BST<Date, ArregloDinamico<Accidente>>( );
		arbolRBT = new RBT<Date, ArregloDinamico<Accidente>>( );
		total = 0;
		latCentral = Double.MAX_VALUE;
		lonCentral = Double.MAX_VALUE;
	}

	/**
	 * Formatea la fecha la formato deseado.
	 * @param pre fecha antes del formateo
	 * @return fecha despues del formateo.
	 */
	public Date formatearFecha(Date pre)
	{
		SimpleDateFormat formato = new SimpleDateFormat("HH:mm");
		String temp = formato.format(pre);
		Date post;
		try 
		{
			post = formato.parse(temp);
			return post;
		}
		catch (ParseException e) 
		{
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Retorna el total de llaves ingresadas.
	 * @return total de llaves ingresadas.
	 */
	public int darTotal( )
	{
		return total;
	}

	/**
	 * Retorna el arbol BST.
	 * @return arbol BST.
	 */
	public BST<Date, ArregloDinamico<Accidente>>  darArbolBST( )
	{
		return arbolBST;
	}

	/**
	 * Retorna el arbol RBT.
	 * @return arbol RBT.
	 */
	public RBT<Date, ArregloDinamico<Accidente>>  darArbolRBT( )
	{
		return arbolRBT;
	}

	/**
	 * Retorna el numero de accidentes en una fecha, ademas de imprimir el tiempo de busqueda.
	 * @param fecha a buscar
	 * @return lista con la fecha con mas accidentes.
	 */
	public Lista<Accidente> accidentesEnFechaBST(Date fecha)
	{
		long startTime = System.currentTimeMillis();
		Lista<Accidente> retorno = arbolBST.get(fecha);
		long endTime = System.currentTimeMillis();
		System.out.println(" ");
		System.out.println("El tiempo de busqueda fue: " + (endTime - startTime));
		System.out.println(" ");
		return retorno;
	}

	/**
	 *  Retorna el numero de accidentes en una fecha, ademas de imprimir el tiempo de busqueda.
	 * @param fecha fecha a buscar.
	 * @return lista con la fecha con mas accidentes.
	 */
	public Lista<Accidente> accidentesEnFechaRBT(Date fecha)
	{
		long startTime = System.currentTimeMillis();
		Lista<Accidente> retorno = arbolRBT.get(fecha);
		long endTime = System.currentTimeMillis();
		System.out.println(" ");
		System.out.println("El tiempo de busqueda fue: " + (endTime - startTime));
		System.out.println(" ");
		return retorno;
	}

	/**
	 * Cuenta los accidentes dependiendo de su severidad.
	 * @param accidentes dia a contar.
	 * @return Arreglo de 4 posiciones con los accidentes por severidad.
	 */
	public Lista<Double> accidentesPorSeveridad( Lista<Accidente> accidentes)
	{
		ArregloDinamico<Double> resp = new ArregloDinamico<>(4);
		for(int i = 1; i < 5; i++)
			resp.changeInfo(i, 0.0);
		for(int i = 1; i <= accidentes.size(); i++)
		{
			double severity = (double) accidentes.getElement(i).getSeverity( );
			switch((int) severity)
			{
			case 1:
				resp.changeInfo(1, resp.getElement(1) + 1 );
				break;
			case 2:
				resp.changeInfo(2, resp.getElement(2) + 1 );
				break;
			case 3:
				resp.changeInfo(3, resp.getElement(3) + 1 );
				break;
			case 4:
				resp.changeInfo(4, resp.getElement(4) + 1 );
				break;
			}
		}

		return resp; 
	}
	/**
	 * Cuenta los accidentes dependiendo de su severidad.
	 * @param accidentes arreglo de fechas a contar.
	 * @return Arreglo de 4 posiciones con los accidentes por severidad.
	 */
	public Lista<Double> accidentesPorSeveridadMacro( Lista<ArregloDinamico<Accidente>> accidentes)
	{
		ArregloDinamico<Double> resp = new ArregloDinamico<>(4);
		for(int i = 1; i < 5; i++)
			resp.changeInfo(i, 0.0);
		for(int i = 1; i <= accidentes.size(); i++)
		{
			ArregloDinamico<Accidente> actual = accidentes.getElement(i);
			for(int j = 1; j <= actual.size(); j++)
			{
				double severity = (double) actual.getElement(j).getSeverity( );
				switch((int) severity)
				{
				case 1:
					resp.changeInfo(1, resp.getElement(1) + 1 );
					break;
				case 2:
					resp.changeInfo(2, resp.getElement(2) + 1 );
					break;
				case 3:
					resp.changeInfo(3, resp.getElement(3) + 1 );
					break;
				case 4:
					resp.changeInfo(4, resp.getElement(4) + 1 );
					break;
				}
			}
		}

		return resp; 
	}

	/**
	 * Retorna los accidentes antes de una fecha.
	 * @param fecha limite.
	 * @return accidentes antes de una fecha.
	 */
	public Lista<ArregloDinamico<Accidente>> accidentesAntesDeFecha(Date fecha)
	{
		return arbolRBT.valuesInRange(arbolRBT.min(), fecha);
	}
	/**
	 * Cuenta el numero de accidentes en un arreglo de fechas.
	 * @param temp arreglo de fechas
	 * @return Numero de accidentes en el arreglo dado.
	 */
	public int count(Lista<ArregloDinamico<Accidente>> temp) 
	{
		int total = 0;
		for(int i = 1; i <= temp.size(); i++)
			total += temp.getElement(i).size();
		return total;
	}

	/**
	 * Retorna la fecha con mas accidentes.
	 * @param temp rango de fechas a considerar.
	 * @return fecha con mas accidentes.
	 */
	public ArregloDinamico<Accidente> getMax(Lista<ArregloDinamico<Accidente>> temp) 
	{
		int max = -1;
		for(int i = 1; i <= temp.size(); i++)
			if(temp.getElement(i).size() > max) max = i;

		return temp.getElement(max);
	}

	/**
	 * Retorna los accidentes en un rango de fechas.
	 * @param init fecha con limite inicial.
	 * @param end fecha limite
	 * @return accidentes en un rango de fechas.
	 */
	public Lista<ArregloDinamico<Accidente>> accidentesEnRango(Date init, Date end)
	{
		return arbolRBT.valuesInRange(init, end);
	}

	/**
	 * Retorna la severidad mayor, en un arreglo de conteo de severidades.
	 * @param sev arreglo de conteo de severidades.
	 * @return  severidad mayor, en un arreglo de conteo de severidades
	 */
	public int mayor(Lista<Double> sev) 
	{
		int retorno = 1;
		for(int i = 1; i <= 4; i++)
			if(sev.getElement(i) > sev.getElement(retorno)) retorno = i;
		return retorno;
	}

	/**
	 * Retorna el 
	 * @param fechasEnRango
	 * @return
	 */
	public ArregloDinamico<String> accidentesPorEstado(Lista<ArregloDinamico<Accidente>> fechasEnRango)
	{
		ArregloDinamico<Integer> total = new ArregloDinamico<Integer>(20);
		ArregloDinamico<String> estados = new ArregloDinamico<String>(20);
		for(int i = 1; i <= fechasEnRango.size( ); i++)
		{
			ArregloDinamico<Accidente> actual = fechasEnRango.getElement(i);
			for(int j = 1; j <= actual.size( ); j++)
			{
				Accidente act = actual.getElement(j);
				String estado = act.getEstado( );
				if(estados.isPresent(estado) == -1)
				{
					estados.addLast(estado);
					total.addLast(1);
				}
				else
				{
					int index = estados.isPresent(estado);
					index++;
					total.changeInfo(index, total.getElement(index) + 1);
				}
			}
		}
		ArregloDinamico<String> retorno = new ArregloDinamico<String>(2);
		int indx = max(total);
		retorno.addLast(estados.getElement(indx));
		retorno.addLast(total.getElement(indx) + "");
		return retorno;
	}

	/**
	 * Retorna la severidad mayor, en un arreglo de conteo de severidades.
	 * @param sev arreglo de conteo de severidades.
	 * @return  severidad mayor, en un arreglo de conteo de severidades
	 */
	public int max(Lista<Integer> tot) 
	{
		int retorno = 1;
		for(int i = 1; i <= tot.size(); i++)
			if(tot.getElement(i) > tot.getElement(retorno)) retorno = i;
		return retorno;
	}
	
	/**
	 * Busca los accidentes en un rango de horas
	 * @param init hora inicial
	 * @param end hora final
	 * @return Arreglo con los accidentes que cumplen la condicion.
	 */
	public ArregloDinamico<Accidente> accidentesPorRangoDeHoras(Date init, Date end)
	{
		Lista<ArregloDinamico<Accidente>> todo = arbolRBT.valuesInRange(arbolRBT.min( ), arbolRBT.max( ));
		ArregloDinamico<Accidente> accidentes = new ArregloDinamico<Accidente>(50);
		for(int i = 1; i <= todo.size( ); i++)
		{
			ArregloDinamico<Accidente> actual = todo.getElement(i);
			for(int j = 1; j <= actual.size( ); j++)
			{
				Accidente act = actual.getElement(j);
				Date fecha = formatearFecha(act.getStartTime( ));
				if(fecha.after(init) && fecha.before(end))
					accidentes.addLast(act);		
			}
		}
		return accidentes;
	}
	
	/**
	 * Hace el calculo de la distancia mediante el metodo haversiano.
	 * <pre> lat y lon estan en radianes.
	 * @param lat Latitud del punto a considerar.
	 * @param lon Longitud del punto a considerar.
	 * @return Distancia del punto central y el punto dado por param.
	 */
	public Double distanciaHarvesiana(Double lat, Double lon)
	{
		Double t1 = Math.pow(Math.sin((lat - latCentral)/2),2);
		Double t2 = Math.cos(latCentral)*Math.cos(lat) * Math.pow(Math.sin((lon - lonCentral)/2),2);
		Double harvesiana = Math.sqrt(t1 + t2);
		Double distancia = (2 * RADIO_TERRESTRE) * Math.asin(harvesiana);
		return distancia;
	}
	
	/**
	 * Busca los accidentes dentro del circulo generado con los parametros dados.
	 * @param lat Latitud central.
	 * @param lon Longitud central.
	 * @param dMax distancia maxima.
	 * @return Arreglo con los accidentes que cumplen la condicion, organizados por el dia de la semana.
	 */
	public ArregloDinamico<Integer> accidentesEnRadio(Double lat, Double lon, Double dMax)
	{
		latCentral = lat;
		lonCentral = lon;
		Lista<ArregloDinamico<Accidente>> accidentes = arbolRBT.valuesInRange(arbolRBT.min(), arbolRBT.max());
		ArregloDinamico<Integer> retorno = new ArregloDinamico<Integer>(7);
		for(int i = 1; i <= accidentes.size( ); i ++)
		{
			ArregloDinamico<Accidente> actual = accidentes.getElement(i);
			for(int j = 1; j <= actual.size( ); j++)
			{
				Accidente act = actual.getElement(j);
				if(dMax >= distanciaHarvesiana(((act.getLat( )*Math.PI)/180), ((act.getLon( )* Math.PI)/180)))
					agregarAlDia(retorno, act);
			}
		}
		return retorno; 
	}
	/**
	 * Agrega la fecha al arreglo de dias.
	 * @param dias arreglo con los dias de la semana
	 * @param pAcc accidente a agregar.
	 */
	@SuppressWarnings("deprecation")
	public void agregarAlDia(ArregloDinamico<Integer> dias, Accidente pAcc)
	{
		Date fecha = pAcc.getStartTime();
		
		int a = (14 - fecha.getMonth())/12;
		int y = fecha.getYear() - a;
		int m = fecha.getMonth() + 12*a -2;
		int d = (fecha.getDay() + y + y/4 -y/100 +y/400 +(31*m)/12) % 7;
		d++;
		if(dias.getElement(d) == null)
			dias.changeInfo(d, 1);
		else
			dias.changeInfo(d, dias.getElement(d) + 1);
	}

	/**
	 * Carga datos small.
	 */
	public void cargarDatos( )
	{
		BufferedReader bufferLectura = null;

		try{
			bufferLectura = new BufferedReader(new FileReader("./data/us_accidents_small.csv"));

			String linea = bufferLectura.readLine();
			linea = bufferLectura.readLine();

			while (linea!= null)
			{
				String[] campos = linea.split(",");
				Accidente actual;

				if(campos.length == 49)
					actual = new Accidente(campos[0], campos[1], campos[2], Double.parseDouble(campos[3]), campos[4], campos[5], Double.parseDouble(campos[6]), Double.parseDouble(campos[7]), campos[8], campos[9], Double.parseDouble(campos[10]), campos[11], campos[12], campos[13], campos[14], campos[15], campos[16], campos[17], campos[18], campos[19], campos[20], campos[21], campos[22], campos[23], campos[24], campos[25], campos[26], campos[27], campos[28], campos[29], campos[30], campos[31], Boolean.parseBoolean(campos[32]), Boolean.parseBoolean(campos[33]), Boolean.parseBoolean(campos[34]), Boolean.parseBoolean(campos[35]), Boolean.parseBoolean(campos[36]), Boolean.parseBoolean(campos[37]), Boolean.parseBoolean(campos[38]), Boolean.parseBoolean(campos[39]), Boolean.parseBoolean(campos[40]), Boolean.parseBoolean(campos[41]), Boolean.parseBoolean(campos[42]), Boolean.parseBoolean(campos[43]), Boolean.parseBoolean(campos[44]), campos[45], campos[46], campos[47], campos[48]);
				else
					actual = new Accidente(campos[0], campos[1], campos[2], Double.parseDouble(campos[3]), campos[4], campos[5], Double.parseDouble(campos[6]), Double.parseDouble(campos[7]), campos[8], campos[9], Double.parseDouble(campos[10]), campos[11], campos[12], campos[13], campos[14], campos[15], campos[16], campos[17], campos[18], campos[19], campos[20], campos[21], campos[22], campos[23], campos[24], campos[25], campos[26], campos[27], campos[28], campos[29], campos[30], campos[31], Boolean.parseBoolean(campos[32]), Boolean.parseBoolean(campos[33]), Boolean.parseBoolean(campos[34]), Boolean.parseBoolean(campos[35]), Boolean.parseBoolean(campos[36]), Boolean.parseBoolean(campos[37]), Boolean.parseBoolean(campos[38]), Boolean.parseBoolean(campos[39]), Boolean.parseBoolean(campos[40]), Boolean.parseBoolean(campos[41]), Boolean.parseBoolean(campos[42]), Boolean.parseBoolean(campos[43]), Boolean.parseBoolean(campos[44]), null, null, null, null);

				SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
				Date temp = formato.parse(campos[4].substring(0,10));


				if(!arbolBST.contains(temp))
				{
					ArregloDinamico<Accidente> arr = new ArregloDinamico<Accidente>(5);
					arr.addLast(actual);
					arbolBST.put(temp, arr);
				}
				else
					arbolBST.get(temp).addLast(actual);

				if(!arbolRBT.contains(temp))
				{
					ArregloDinamico<Accidente> arr = new ArregloDinamico<Accidente>(5);
					arr.addLast(actual);
					arbolRBT.put(temp, arr);
				}
				else
					arbolRBT.get(temp).addLast(actual);

				total++;
				linea = bufferLectura.readLine( );
			}
			bufferLectura.close();

		}
		catch(IOException e)
		{
			e.printStackTrace();
		} 
		catch (ParseException e) 
		{
			e.printStackTrace();
		}
	}

	/**
	 * Carga los datos con la ruta dada por parametro.
	 * @param anio Ruta del anio a cargar.
	 */
	public void cargarDatos(String anio)
	{
		BufferedReader bufferLectura = null;

		try{
			bufferLectura = new BufferedReader(new FileReader(anio));

			String linea = bufferLectura.readLine();
			linea = bufferLectura.readLine();

			while (linea!= null)
			{
				String[] campos = linea.split(",");
				Accidente actual;

				if(campos.length == 49)
					actual = new Accidente(campos[0], campos[1], campos[2], Double.parseDouble(campos[3]), campos[4], campos[5], Double.parseDouble(campos[6]), Double.parseDouble(campos[7]), campos[8], campos[9], Double.parseDouble(campos[10]), campos[11], campos[12], campos[13], campos[14], campos[15], campos[16], campos[17], campos[18], campos[19], campos[20], campos[21], campos[22], campos[23], campos[24], campos[25], campos[26], campos[27], campos[28], campos[29], campos[30], campos[31], Boolean.parseBoolean(campos[32]), Boolean.parseBoolean(campos[33]), Boolean.parseBoolean(campos[34]), Boolean.parseBoolean(campos[35]), Boolean.parseBoolean(campos[36]), Boolean.parseBoolean(campos[37]), Boolean.parseBoolean(campos[38]), Boolean.parseBoolean(campos[39]), Boolean.parseBoolean(campos[40]), Boolean.parseBoolean(campos[41]), Boolean.parseBoolean(campos[42]), Boolean.parseBoolean(campos[43]), Boolean.parseBoolean(campos[44]), campos[45], campos[46], campos[47], campos[48]);
				else
					actual = new Accidente(campos[0], campos[1], campos[2], Double.parseDouble(campos[3]), campos[4], campos[5], Double.parseDouble(campos[6]), Double.parseDouble(campos[7]), campos[8], campos[9], Double.parseDouble(campos[10]), campos[11], campos[12], campos[13], campos[14], campos[15], campos[16], campos[17], campos[18], campos[19], campos[20], campos[21], campos[22], campos[23], campos[24], campos[25], campos[26], campos[27], campos[28], campos[29], campos[30], campos[31], Boolean.parseBoolean(campos[32]), Boolean.parseBoolean(campos[33]), Boolean.parseBoolean(campos[34]), Boolean.parseBoolean(campos[35]), Boolean.parseBoolean(campos[36]), Boolean.parseBoolean(campos[37]), Boolean.parseBoolean(campos[38]), Boolean.parseBoolean(campos[39]), Boolean.parseBoolean(campos[40]), Boolean.parseBoolean(campos[41]), Boolean.parseBoolean(campos[42]), Boolean.parseBoolean(campos[43]), Boolean.parseBoolean(campos[44]), null, null, null, null);

				SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
				Date temp = formato.parse(campos[4].substring(0,10));

				if(!arbolBST.contains(temp))
				{
					ArregloDinamico<Accidente> arr = new ArregloDinamico<Accidente>(5);
					arr.addLast(actual);
					arbolBST.put(temp, arr);
				}
				else
					arbolBST.get(temp).addLast(actual);

				if(!arbolRBT.contains(temp))
				{
					ArregloDinamico<Accidente> arr = new ArregloDinamico<Accidente>(5);
					arr.addLast(actual);
					arbolRBT.put(temp, arr);
				}
				else
					arbolRBT.get(temp).addLast(actual);

				total++;
				linea = bufferLectura.readLine( );
			}
			bufferLectura.close();

		}
		catch(IOException e)
		{
			e.printStackTrace();
		} 
		catch (ParseException e) 
		{
			e.printStackTrace();
		}
	}

	/**
	 * Carga unicamente el anio en un RBT
	 * @param anio Ruta del anio a cargar.
	 */
	public void cargarDatosRBT(String anio)
	{
		BufferedReader bufferLectura = null;

		try{
			bufferLectura = new BufferedReader(new FileReader(anio));

			String linea = bufferLectura.readLine();
			linea = bufferLectura.readLine();

			while (linea!= null)
			{
				String[] campos = linea.split(",");
				Accidente actual;

				if(campos.length == 49)
					actual = new Accidente(campos[0], campos[1], campos[2], Double.parseDouble(campos[3]), campos[4], campos[5], Double.parseDouble(campos[6]), Double.parseDouble(campos[7]), campos[8], campos[9], Double.parseDouble(campos[10]), campos[11], campos[12], campos[13], campos[14], campos[15], campos[16], campos[17], campos[18], campos[19], campos[20], campos[21], campos[22], campos[23], campos[24], campos[25], campos[26], campos[27], campos[28], campos[29], campos[30], campos[31], Boolean.parseBoolean(campos[32]), Boolean.parseBoolean(campos[33]), Boolean.parseBoolean(campos[34]), Boolean.parseBoolean(campos[35]), Boolean.parseBoolean(campos[36]), Boolean.parseBoolean(campos[37]), Boolean.parseBoolean(campos[38]), Boolean.parseBoolean(campos[39]), Boolean.parseBoolean(campos[40]), Boolean.parseBoolean(campos[41]), Boolean.parseBoolean(campos[42]), Boolean.parseBoolean(campos[43]), Boolean.parseBoolean(campos[44]), campos[45], campos[46], campos[47], campos[48]);
				else
					actual = new Accidente(campos[0], campos[1], campos[2], Double.parseDouble(campos[3]), campos[4], campos[5], Double.parseDouble(campos[6]), Double.parseDouble(campos[7]), campos[8], campos[9], Double.parseDouble(campos[10]), campos[11], campos[12], campos[13], campos[14], campos[15], campos[16], campos[17], campos[18], campos[19], campos[20], campos[21], campos[22], campos[23], campos[24], campos[25], campos[26], campos[27], campos[28], campos[29], campos[30], campos[31], Boolean.parseBoolean(campos[32]), Boolean.parseBoolean(campos[33]), Boolean.parseBoolean(campos[34]), Boolean.parseBoolean(campos[35]), Boolean.parseBoolean(campos[36]), Boolean.parseBoolean(campos[37]), Boolean.parseBoolean(campos[38]), Boolean.parseBoolean(campos[39]), Boolean.parseBoolean(campos[40]), Boolean.parseBoolean(campos[41]), Boolean.parseBoolean(campos[42]), Boolean.parseBoolean(campos[43]), Boolean.parseBoolean(campos[44]), null, null, null, null);

				SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
				Date temp = formato.parse(campos[4].substring(0,10));

				if(!arbolRBT.contains(temp))
				{
					ArregloDinamico<Accidente> arr = new ArregloDinamico<Accidente>(5);
					arr.addLast(actual);
					arbolRBT.put(temp, arr);
				}
				else
					arbolRBT.get(temp).addLast(actual);

				total++;
				linea = bufferLectura.readLine( );
			}
			bufferLectura.close();

		}
		catch(IOException e)
		{
			e.printStackTrace();
		} 
		catch (ParseException e) 
		{
			e.printStackTrace();
		}
	}

	/**
	 * Carga unicamente el anio en un BST
	 * @param anio Ruta del anio a cargar.
	 */
	public void cargarDatosBST(String anio)
	{
		BufferedReader bufferLectura = null;

		try{
			bufferLectura = new BufferedReader(new FileReader(anio));

			String linea = bufferLectura.readLine();
			linea = bufferLectura.readLine();
			Date act = null;
			while (linea!= null)
			{
				String[] campos = linea.split(",");
				Accidente actual;

				if(campos.length == 49)
					actual = new Accidente(campos[0], campos[1], campos[2], Double.parseDouble(campos[3]), campos[4], campos[5], Double.parseDouble(campos[6]), Double.parseDouble(campos[7]), campos[8], campos[9], Double.parseDouble(campos[10]), campos[11], campos[12], campos[13], campos[14], campos[15], campos[16], campos[17], campos[18], campos[19], campos[20], campos[21], campos[22], campos[23], campos[24], campos[25], campos[26], campos[27], campos[28], campos[29], campos[30], campos[31], Boolean.parseBoolean(campos[32]), Boolean.parseBoolean(campos[33]), Boolean.parseBoolean(campos[34]), Boolean.parseBoolean(campos[35]), Boolean.parseBoolean(campos[36]), Boolean.parseBoolean(campos[37]), Boolean.parseBoolean(campos[38]), Boolean.parseBoolean(campos[39]), Boolean.parseBoolean(campos[40]), Boolean.parseBoolean(campos[41]), Boolean.parseBoolean(campos[42]), Boolean.parseBoolean(campos[43]), Boolean.parseBoolean(campos[44]), campos[45], campos[46], campos[47], campos[48]);
				else
					actual = new Accidente(campos[0], campos[1], campos[2], Double.parseDouble(campos[3]), campos[4], campos[5], Double.parseDouble(campos[6]), Double.parseDouble(campos[7]), campos[8], campos[9], Double.parseDouble(campos[10]), campos[11], campos[12], campos[13], campos[14], campos[15], campos[16], campos[17], campos[18], campos[19], campos[20], campos[21], campos[22], campos[23], campos[24], campos[25], campos[26], campos[27], campos[28], campos[29], campos[30], campos[31], Boolean.parseBoolean(campos[32]), Boolean.parseBoolean(campos[33]), Boolean.parseBoolean(campos[34]), Boolean.parseBoolean(campos[35]), Boolean.parseBoolean(campos[36]), Boolean.parseBoolean(campos[37]), Boolean.parseBoolean(campos[38]), Boolean.parseBoolean(campos[39]), Boolean.parseBoolean(campos[40]), Boolean.parseBoolean(campos[41]), Boolean.parseBoolean(campos[42]), Boolean.parseBoolean(campos[43]), Boolean.parseBoolean(campos[44]), null, null, null, null);

				SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
				Date temp = formato.parse(campos[4].substring(0,10));
				
				if(!temp.equals(act))
				{
					act = temp;
					ArregloDinamico<Accidente> arr = new ArregloDinamico<Accidente>(5);
					arr.addLast(actual);
					arbolBST.put(temp, arr);
				}
				else
					arbolBST.get(temp).addLast(actual);

				total++;
				linea = bufferLectura.readLine( );
			}
			bufferLectura.close();

		}
		catch(IOException e)
		{
			e.printStackTrace();
		} 
		catch (ParseException e) 
		{
			e.printStackTrace();
		}
	}
}

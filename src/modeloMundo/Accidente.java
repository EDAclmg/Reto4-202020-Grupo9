package modeloMundo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Accidente implements Comparable<Accidente> 
{
	private String id;
	
	private String source;
	
	private Double tmc;
	
	private Double severity;
	
	private Date startTime;
	
	private Date endTime;
	
	private Double startLat;
	
	private Double startLng;
	
	private Double endLat;
	
	private Double endLng;
	
	private Double distance;
	
	private String description;
	
	private Double number;
	
	private String street;
	
	private String side;
	
	private String city;
	
	private String county;
	
	private String state;
	
	private String zipcode;
	
	private String country;
	
	private String timezone;
	
	private String airportCode;
	
	private String weatherTimestamp;
	
	private Double temperature;
	
	private Double windChill;
	
	private Double humidity;
	
	private Double pressure;
	
	private Double visibility;
	
	private String windDirection;
	
	private Double windSpeed;
	
	private Double precipitation;
	
	private String weatherCondition;
	
	private boolean amenity;
	
	private boolean bump;
	
	private boolean crossing;
	
	private boolean giveWay;
	
	private boolean junction;
	
	private boolean noExit;
	
	private boolean railway;
	
	private boolean roundabout;
	
	private boolean station;
	
	private boolean stop;
	
	private boolean trafficCalming;
	
	private boolean trafficSignal;
	
	private boolean turningLoop;
	
	private String sunriseSunset;
	
	private String civilTwilight;
	
	private String nauticalTwilight;
	
	private String astronomicalTwilight;
	
	public Accidente(
	String pId,
	String pSource,
	Double pTmc,
	Double pSeverity,
	Date pStartTime,
	Date pEndTime,
	Double pStartLat,
	Double pStartLng,
	Double pEndLat,
	Double pEndLng,
	Double pDistance,
	String pDescription,
	Double pNumber,
	String pStreet,
	String pSide,
	String pCity,
	String pCounty,
	String pState,
	String pZipcode,
	String pCountry,
	String pTimezone,
	String pAirportCode,
	String pWeatherTimestamp,
	Double pTemperature,
	Double pWindChill,
	Double pHumidity,
	Double pPressure,
	Double pVisibility,
	String pWindDirection,
	Double pWindSpeed,
	Double pPrecipitation,
	String pWeatherCondition,
	boolean pAmenity,
	boolean pBump,
	boolean pCrossing,
	boolean pGiveWay,
	boolean pJunction,
	boolean pNoExit,
	boolean pRailway,
	boolean pRoundabout,
	boolean pStation,
	boolean pStop,
	boolean pTrafficCalming,
	boolean pTrafficSignal,
	boolean pTurningLoop,
	String pSunriseSunset,
	String pCivilTwilight,
	String pNauticalTwilight,
	String pAstronomicalTwilight )
	{
		id = pId;
		source = pSource;
		tmc = pTmc;
		severity = pSeverity;
		startTime = pStartTime;
		endTime = pEndTime;
		startLat = pStartLat;
		startLng = pStartLng;
		endLat = pEndLat;
		endLng = pEndLng;
		distance =  pDistance;
		description = pDescription;
		number = pNumber;
		street = pStreet;
		side =  pSide;
		city = pCity;
		county = pCounty;
		state = pState;
		zipcode = pZipcode;
		country = pCountry;
		timezone = pTimezone;
		airportCode = pAirportCode;
		weatherTimestamp = pWeatherTimestamp;
		temperature = pTemperature;
		windChill = pWindChill;
		humidity = pHumidity;
		pressure = pPressure;
		visibility = pVisibility;
		windDirection = pWindDirection;
		windSpeed = pWindSpeed;
		precipitation = pPrecipitation;
		weatherCondition = pWeatherCondition;
		amenity = pAmenity;
		bump = pBump;
		crossing = pCrossing;
		giveWay = pGiveWay;
		junction = pJunction;
		noExit = pNoExit;
		railway =  pRailway;
		roundabout = pRoundabout;
		station = pStation;
		stop = pStop;
		trafficCalming = pTrafficCalming;
		trafficSignal = pTrafficSignal;
		turningLoop = pTurningLoop;
		sunriseSunset = pSunriseSunset;
		civilTwilight = pCivilTwilight;
		nauticalTwilight = pNauticalTwilight;
		astronomicalTwilight = pAstronomicalTwilight; 
	}

	public Accidente(String pId, String pSource, String pTmc, Double pSeverity, String pStartTime, String pEndTime,
			Double pStartLat, Double pStartLng, String pEndLat, String pEndLng, Double pDistance,
			String pDescription, String pNumber, String pStreet, String pSide, String pCity, String pCounty,
			String pState, String pZipcode, String pCountry, String pTimezone, String pAirportCode,
			String pWeatherTimestamp, String pTemperature, String pWindChill, String pHumidity, String pPressure, String pVisibility,
			String pWindDirection, String pWindSpeed, String pPrecipitation, String pWeatherCondition, boolean pAmenity,
			boolean pBump, boolean pCrossing, boolean pGiveWay, boolean pJunction,
			boolean pNoExit, boolean pRailway, boolean pRoundabout, boolean pStation,
			boolean pStop, boolean pTrafficCalming, boolean pTrafficSignal, boolean pTurningLoop,
			String pSunriseSunset, String pCivilTwilight, String pNauticalTwilight, String pAstronomicalTwilight) 
	{
		id = pId;
		source = pSource;
		tmc = pTmc != null ? Double.parseDouble(pTmc):0;
		severity = pSeverity;
		SimpleDateFormat forma = new SimpleDateFormat("yyyy-MM-dd");
		try {
			startTime = forma.parse(pStartTime);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			endTime = forma.parse(pEndTime);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		startLat = pStartLat;
		startLng = pStartLng;
		endLat = !pEndLat.equals("") ? Double.parseDouble(pEndLat):0;
		endLng = !pEndLng.equals("")? Double.parseDouble(pEndLng):0;
		distance =  pDistance;
		description = pDescription;
		number = !pNumber.equals("") ? Double.parseDouble(pNumber):0;
		street = pStreet;
		side =  pSide;
		city = pCity;
		county = pCounty;
		state = pState;
		zipcode = pZipcode;
		country = pCountry;
		timezone = pTimezone;
		airportCode = pAirportCode;
		weatherTimestamp = pWeatherTimestamp;
		temperature = !pTemperature.equals("")? Double.parseDouble(pTemperature):0;
		windChill = !pWindChill.equals("") ? Double.parseDouble(pWindChill):0;
		humidity = !pHumidity.equals("") ? Double.parseDouble(pHumidity):0;
		pressure = !pPressure.equals("") ? Double.parseDouble(pPressure):0;
		visibility = !pVisibility.equals("") ? Double.parseDouble(pVisibility):0;
		windDirection = pWindDirection;
		windSpeed = !pWindSpeed.equals("") ? Double.parseDouble(pWindSpeed):0;
		precipitation = !pPrecipitation.equals("") ? Double.parseDouble(pPrecipitation):0;
		weatherCondition = pWeatherCondition;
		amenity = pAmenity;
		bump = pBump;
		crossing = pCrossing;
		giveWay = pGiveWay;
		junction = pJunction;
		noExit = pNoExit;
		railway =  pRailway;
		roundabout = pRoundabout;
		station = pStation;
		stop = pStop;
		trafficCalming = pTrafficCalming;
		trafficSignal = pTrafficSignal;
		turningLoop = pTurningLoop;
		sunriseSunset = pSunriseSunset;
		civilTwilight = pCivilTwilight;
		nauticalTwilight = pNauticalTwilight;
		astronomicalTwilight = pAstronomicalTwilight; 
	}

	@Override
	public int compareTo(Accidente arg0) 
	{
		// TODO Auto-generated method stub
		return 0;
	}

	public Double getSeverity() {
		// TODO Auto-generated method stub
		return severity;
	}
}

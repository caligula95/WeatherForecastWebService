package net.webservicex;

public class Weather {
	
	public static void main(String [] args) {
		GetCitiesByCountry weather = new GetCitiesByCountry();
		weather.setCountryName("Ukraine");
		//System.out.println(weather.getCountryName());
		GlobalWeather gl = new GlobalWeather();
		GlobalWeatherSoap soap = gl.getGlobalWeatherSoap();
		System.out.println(soap.getCitiesByCountry("Ukraine"));
		System.out.println(soap.getWeather("Kyiv", "Ukraine"));
		
	}
}

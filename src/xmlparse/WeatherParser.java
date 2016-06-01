package xmlparse;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import net.webservicex.GlobalWeather;
import net.webservicex.GlobalWeatherSoap;

public class WeatherParser {
	GlobalWeather gl = new GlobalWeather();
	GlobalWeatherSoap soap = gl.getGlobalWeatherSoap();
	
	private String location, time, wind, visibility, skyCondition, temperature, humidity, pressure;
	public static void main(String[] args) throws ParserConfigurationException {
		WeatherParser t = new WeatherParser();
		t.readXml("Kyiv", "Ukraine");
		System.out.println("time");
		//t.readXml();
	}
	
	public String getLocation() {
		return location;
	}


	public String getTime() {
		return time;
	}


	public String getWind() {
		return wind;
	}

	public String getVisibility() {
		return visibility;
	}

	public String getSkyCondition() {
		return skyCondition;
	}


	public String getTemperature() {
		return temperature;
	}

	public String getHumidity() {
		return humidity;
	}

	public String getPressure() {
		return pressure;
	}



	public void readXml(String city, String country) throws ParserConfigurationException {
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = null;
		String  sp = soap.getWeather(city, country);
		System.out.println(sp);
	//	String s = sp.replace("<?xml version=\"1.0\" encoding=\"utf-16\"?>", "") ;	
		InputSource is = new InputSource(new StringReader(sp));
		try {
			if (!is.equals("Data Not Found"))
			doc = dBuilder.parse(is);
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		doc.getDocumentElement().normalize();
		NodeList nList = doc.getElementsByTagName("CurrentWeather");
		for (int temp = 0; temp < nList.getLength(); temp++) {
			Node nNode = nList.item(temp);
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) nNode;
				 location = eElement.getElementsByTagName("Location").item(0).getTextContent();
				 time = eElement.getElementsByTagName("Time").item(0).getTextContent();
				 wind = eElement.getElementsByTagName("Wind").item(0).getTextContent();
				 visibility = eElement.getElementsByTagName("Visibility").item(0).getTextContent();
				 skyCondition = eElement.getElementsByTagName("SkyConditions").item(0).getTextContent();
				 temperature = eElement.getElementsByTagName("Temperature").item(0).getTextContent();
				 humidity = eElement.getElementsByTagName("RelativeHumidity").item(0).getTextContent();
				 pressure = eElement.getElementsByTagName("Pressure").item(0).getTextContent();
			}
		}
	}

}
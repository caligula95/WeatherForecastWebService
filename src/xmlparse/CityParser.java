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

public class CityParser {
	GlobalWeather gl = new GlobalWeather();
	GlobalWeatherSoap soap = gl.getGlobalWeatherSoap();
	public ArrayList<String> city = new ArrayList<String>();
	public static void main(String[] args) throws ParserConfigurationException {
		CityParser t = new CityParser();
		t.readXml("Ukraine");
		
	}

	public void readXml(String country) throws ParserConfigurationException {
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = null;
		String  sp = soap.getCitiesByCountry(country);
		//if (sp!=null && !sp.equals("")) {			
		InputSource is = new InputSource(new StringReader(sp));
		try {
			doc = dBuilder.parse(is);
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		doc.getDocumentElement().normalize();
		NodeList nList = doc.getElementsByTagName("Table");
		for (int temp = 0; temp < nList.getLength(); temp++) {
			Node nNode = nList.item(temp);
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) nNode;
				String town = eElement.getElementsByTagName("City").item(0).getTextContent();
				city.add(town);
			}
		}
	}
	public ArrayList<String> getCiites(String country){
		city.clear();
		try {
			readXml(country);
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return city;
	}

}
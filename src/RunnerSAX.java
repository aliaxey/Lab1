import java.io.IOException;
import java.net.URL;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

import by.example.weather.Weather;
import by.example.weather.XMLHandler;

public class RunnerSAX {

	static final String SOURCE = "http://api.openweathermap.org/data/2.5/forecast?q=Gamel&lang=ru&appid=5753974d4f4e8e428ba77f4eb8816501&mode=xml&units=metric";
	public static void main(String[] args) {
		
		try {
			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser parser = factory.newSAXParser();
			XMLHandler handler = new XMLHandler();
			parser.parse(new URL(SOURCE).openStream(), handler);
			System.out.println(Weather.HEADER);
			for(Weather weather:handler.getWeathers()) {
				System.out.println(weather);
			}
		}catch (SAXException |ParserConfigurationException |IOException e) {
			e.getStackTrace();
		}

	}

}

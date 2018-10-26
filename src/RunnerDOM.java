import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import by.example.weather.Weather;

public class RunnerDOM {

	static final String SOURCE = "http://api.openweathermap.org/data/2.5/forecast?q=Gamel&lang=ru&appid=5753974d4f4e8e428ba77f4eb8816501&mode=xml&units=metric";
	public static void main(String[] args) {
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(new URL(SOURCE).openStream());
			List<Weather> weathers = new ArrayList<>();
			NodeList times =  doc.getDocumentElement().getElementsByTagName("time");
			for(int i = 0 ; i < times.getLength();i++) {
				Node node = times.item(i);
				weathers.add(new Weather(node));
			}
			System.out.println(Weather.HEADER);
			for(Weather weather:weathers) {
				System.out.println(weather);
			}
		}catch (IOException e) {
			System.out.println("Error");
		} catch (ParserConfigurationException e) {

			e.printStackTrace();
		} catch (SAXException e) {

			e.printStackTrace();
		}

	}

}

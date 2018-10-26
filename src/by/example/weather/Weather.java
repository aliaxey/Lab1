package by.example.weather;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.w3c.dom.DOMException;
import org.w3c.dom.Node;

public class Weather {
	static final SimpleDateFormat FORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
	static final String mask = "%-30s%-15s%-10s%-20s%-10s%-10s";
	public static final String HEADER = String.format(mask, "Time","State","Temp","Wind","Humidity","Pressure");
	
	private Date time;
	private String state; 
	private double temperature;
	private String wind;
	private double humidity;
	private double pressure;
	
	
	public Weather(Node node) {
		try {
			time = FORMAT.parse(node.getAttributes().getNamedItem("from").getNodeValue());
			state = node.getChildNodes().item(0).getAttributes().getNamedItem("name").getNodeValue();
			temperature = Double.parseDouble(node.getChildNodes().item(4).getAttributes().getNamedItem("value").getNodeValue());
			wind = node.getChildNodes().item(2).getAttributes().getNamedItem("name").getNodeValue();
			humidity = Double.parseDouble(node.getChildNodes().item(6).getAttributes().getNamedItem("value").getNodeValue());
			pressure = Double.parseDouble(node.getChildNodes().item(5).getAttributes().getNamedItem("value").getNodeValue());
		} catch (DOMException | ParseException e) {
			e.printStackTrace();
		}
	}
	public Weather(Date time, String state, double temperature, String wind, double humidity, double pressure) {
		super();
		this.time = time;
		this.state = state;
		this.temperature = temperature;
		this.wind = wind;
		this.humidity = humidity;
		this.pressure = pressure;
	}
	public Weather() {
		super();
		
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public double getTemperature() {
		return temperature;
	}
	public void setTemperature(double temperature) {
		this.temperature = temperature;
	}
	public String getWind() {
		return wind;
	}
	public void setWind(String wind) {
		this.wind = wind;
	}
	public double getHumidity() {
		return humidity;
	}
	public void setHumidity(double humidity) {
		this.humidity = humidity;
	}
	public double getPressure() {
		return pressure;
	}
	public void setPressure(double pressure) {
		this.pressure = pressure;
	}
	@Override
	public String toString() {

		return String.format(mask, time, state,temperature,wind,humidity,pressure);
	}
	
}

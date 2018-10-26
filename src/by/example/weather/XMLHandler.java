package by.example.weather;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class XMLHandler extends DefaultHandler {
	
	static final String VALUE = "value";
	static final String NAME = "name";
	static final String TARGET_ELEMENT = "time";
	static final String TYPE_ELEMENT = "symbol";
	static final String TEMP_ELEMENT = "temperature";
	static final String WIND_ELEMENT = "windDirection";
	static final String HUMIDITY_ELEMENT = "humidity";
	static final String PRESSURE_ELEMENT = "pressure";
	
	private List<Weather> weathers;
	private Weather element;
	

	
	public List<Weather> getWeathers() {
		return weathers;
	}

	public void setWeathers(List<Weather> weathers) {
		this.weathers = weathers;
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		if(qName.equals(TARGET_ELEMENT)) {
			weathers.add(element);
		}
		super.endElement(uri, localName, qName);
	}
	
	@Override
	public void startDocument() throws SAXException {
		weathers = new ArrayList<>();
		super.startDocument();
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		switch (qName) {
		case TARGET_ELEMENT:
			element = new Weather();
			try {
				element.setTime(Weather.FORMAT.parse(attributes.getValue("from")));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			break;
		case TYPE_ELEMENT:
			element.setState(attributes.getValue(NAME));
			break;
		case TEMP_ELEMENT:
			element.setTemperature(Double.parseDouble(attributes.getValue(VALUE)));
			break;
		case WIND_ELEMENT:
			element.setWind(attributes.getValue(NAME));
			break;
		case HUMIDITY_ELEMENT:
			element.setHumidity(Double.parseDouble(attributes.getValue(VALUE)));
			break;
		case PRESSURE_ELEMENT:
			element.setPressure(Double.parseDouble(attributes.getValue(VALUE)));
			break;
		}
		super.startElement(uri, localName, qName, attributes);
	}
}

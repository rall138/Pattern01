package pattern01.helpers;

import java.io.IOException;
import java.util.logging.Handler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.apache.tools.ant.BuildFileRule;
import org.apache.tools.ant.Task;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class ClassGenerator extends Task{

	private final BuildFileRule buildfilerule = new BuildFileRule();

	public void execute(){
		//buildfilerule.executeTarget("classGen");
		parsePatternDefinition();
	}
	
	private StringBuilder parsePatternDefinition(){
		StringBuilder builder = new StringBuilder();
		try {
			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser parser = factory.newSAXParser();
			
			DefaultHandler handler = new DefaultHandler(){

				@Override
				public void startElement(String uri, String localName, String qName, Attributes attributes)
						throws SAXException {
					
					System.out.println(uri);
					System.out.println(localName);
					System.out.println(qName);
				
					
				}

				@Override
				public void characters(char[] ch, int start, int length) throws SAXException {
					// TODO Auto-generated method stub
					super.characters(ch, start, length);
				}
				
			};
			parser.parse("/home/ricardo/Documents/Eclipse commiters workspace/Pattern01/GeneralConfig/PatternDefinition.xml", handler);
		} catch (SAXException | ParserConfigurationException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
}

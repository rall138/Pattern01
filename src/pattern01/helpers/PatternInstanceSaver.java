package pattern01.helpers;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.UUID;

import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Node;
import org.xml.sax.InputSource;

import pattern01.helpers.generated.PatternInstance;


public class PatternInstanceSaver {
	
	private static File getMapper() throws URISyntaxException{
		URI uri = new URI("file:///"+LocationHelper.getActiveWorkSpace());
		File workspaceFolder = new File(uri);
		File patternfolder = null, mapper = null;
		for(int index = 0; index < workspaceFolder.listFiles().length; index++){
			if(workspaceFolder.listFiles()[index].isDirectory()){
				int hindex = 0;
				boolean itemFound = false;
				patternfolder = workspaceFolder.listFiles()[index];					
				while (hindex < patternfolder.listFiles().length && !itemFound){
					if (patternfolder.listFiles()[hindex].isDirectory() &&
							patternfolder.listFiles()[hindex].getName().equals("patternfolder"))
						itemFound = true;
					else
						hindex++;
					
				}
				if (itemFound)
					mapper = new File(patternfolder.listFiles()[hindex].getAbsoluteFile()+System.getProperty("file.separator")+"mapper.xml");
			}
		}
		return mapper;
	}
	
	public static void addReferenceToMapper(PatternInstance patternInstance, UUID classUuid){
		try{
			XPath xpath = XPathFactory.newInstance().newXPath();
			String expression = "/Packages/Package/Class[@uuid='"+classUuid+"']";

			Node classNode = (Node) xpath.evaluate (expression, 
					new InputSource(getMapper().getAbsolutePath()), XPathConstants.NODE);
			
			Node newNode = classNode.getOwnerDocument().createElement("Pattern");
			Node reference = newNode.getOwnerDocument().createAttribute("reference");
			reference.setNodeValue(patternInstance.getUuid());
			newNode.getAttributes().setNamedItem(reference);
			classNode.appendChild(newNode);
			
			// Save to file the easiest way i've found
			Transformer transformer = TransformerFactory.newInstance().newTransformer();
			StreamResult output = new StreamResult(getMapper());
			Source input = new DOMSource(newNode.getOwnerDocument());

			transformer.transform(input, output);			
				
		}catch(URISyntaxException | XPathExpressionException | TransformerException ex){
			ex.printStackTrace(System.err);
		}
	}
}
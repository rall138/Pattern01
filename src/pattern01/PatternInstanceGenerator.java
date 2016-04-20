package pattern01;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import org.osgi.framework.Bundle;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import pattern01.plugin.components.navigator.ImageHelper;

public class PatternInstanceGenerator {

	private Tree instance = null;
	
	public void load(String className){
		
		SAXParserFactory factory = SAXParserFactory.newInstance();
		try {
			SAXParser parser = factory.newSAXParser();
			DefaultHandler handler = new DefaultHandler(){
				
				TreeItem item = null;				
				TreeItem parent = null;
				
				@Override
				public void startElement(String uri, String localName, 
						String qName, Attributes attributes)throws SAXException {
					
					if(qName.equalsIgnoreCase("patterninstance")){
						item = new TreeItem(instance, 0);
						item.setData("Type", "PatternInstance");
						item.setText("PatternInstance - "+getAttributeValue("name", attributes));
						item.setImage(ImageHelper.getImage(getAttributeValue("image", attributes)));
					}else if(qName.equalsIgnoreCase("relatedfiles")){
						parent = getTreeItemFromType(instance, "PatternInstance");
						if(parent != null){
							item = new TreeItem(parent, 0);
							item.setData("Type", "RelatedFiles");
							item.setText("RelatedFiles - "+getAttributeValue("name", attributes));
						}
					}else if(qName.equalsIgnoreCase("relatedfile")){
						parent = getTreeItemFromType(instance, "RelatedFiles");
						if(parent != null){
							item = new TreeItem(parent, 0);
							item.setData("Type", "RelatedFile");
							item.setText("RelatedFiles - "+getAttributeValue("name", attributes));
						}
					}
				}

			};
			Bundle bundle = Platform.getBundle("Pattern01");
			Path path = new Path("/patternfolder" + className);
			URL fileUrl = FileLocator.find(bundle, path, null);
			
			File file = new File(fileUrl.getFile());
			if(file.exists()){
				parser.parse(file, handler);
			}
			
		} catch (ParserConfigurationException | SAXException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private String getAttributeValue(String key, Attributes attributes){
		int index = 0;
		boolean itemFound = false;
		String value = "";
		while(!itemFound && index < attributes.getLength()){
			if(attributes.getQName(index).equalsIgnoreCase(key)){
				value = attributes.getValue(index);
			}
		}
		return value;
	}
	
	private TreeItem getTreeItemFromType(Tree parent, String type){
		int index = 0;
		boolean itemFound = false;
		String value = "";
		TreeItem item = null;
		while(!itemFound && index < parent.getItemCount()){
			if(String.valueOf(parent.getItem(index).getData("Type")).equalsIgnoreCase(type)){
				item = parent.getItem(index);
			}
		}
		return item;
	}

}

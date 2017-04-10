package pattern01.helpers.instancegen;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.URI;
import java.util.UUID;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.eclipse.swt.widgets.TreeItem;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;

import pattern01.helpers.CommonPathFix;
import pattern01.helpers.CommonPathFix.PATH_NAME;
import pattern01.helpers.LocationHelper;
import pattern01.helpers.LoggerThread;
import pattern01.helpers.generated.PatternInstance;
import pattern01.plugin.components.navigator.NodeType;
import pattern01.helpers.generated.*;

public class PatternInstanceParser {

	private TreeItem instance = null;
	private LoggerThread lgt = new LoggerThread();
	private TreeItem parentItem = null;
	
	public PatternInstanceParser(TreeItem parentItem){
		this.parentItem = parentItem;
	}
	
	/* Para aplicar la instancia por defecto */
	public void generateTreeFromDefaultDefinition(){
		XPath xpath = XPathFactory.newInstance().newXPath();
		String expression = "/PatternInstance";
		try {
			URI classInstanceXml_uri = CommonPathFix.getHardCodedPath(PATH_NAME.DEFAULTWWDEFINITION);
			InputSource is = new InputSource(classInstanceXml_uri.getPath());
			
			//Obtenemos el nodo padre (Siempre es patterninstance)
			Node parentNode = (Node) xpath.evaluate(expression, is, XPathConstants.NODE);
			if (parentNode != null){
				recursiveParseing(parentNode, this.parentItem, UUID.randomUUID().toString());
			}
		} catch (XPathExpressionException | IllegalStateException e) {
			e.printStackTrace();
		}
	}
	
	public void generateTreeFromDefinition(String patternUUID, String projectFolderPath){
		XPath xpath = XPathFactory.newInstance().newXPath();
		String expression = "/PatternInstance";
		lgt.writeSingleMessage("Reading instance: "+patternUUID);
		try {
			//String classInstanceXml_Path = LocationHelper.searchClassInstancesFile(patternfolderPath);
			File file = new File(LocationHelper.getPatternInstanceFromFile(patternUUID, projectFolderPath));
			if (file.exists()){
				InputSource is = new InputSource(new FileReader(file));
				
				//Obtenemos el nodo padre (Siempre es patterninstance)
				Node patternNode = (Node) xpath.evaluate(expression, is, XPathConstants.NODE);
				if (patternNode != null){
					recursiveParseing(patternNode, this.parentItem, patternUUID);
				}
			}else{
				lgt.writeSingleMessage("Warning no definition found for: "+patternUUID);
			}
		} catch (XPathExpressionException | IllegalStateException | FileNotFoundException e) {
			e.printStackTrace();
		}
	}



















/* [Begin] Auto-generated code for pattern instance parser do not remove */
	/**
	* Generated class via ClassGenerator.xml
	* Creation date: Sun Apr 09 23:06:09 UYT 2017
	* Creator: rlomez
	**/

	private pattern01.helpers.generated.PatternInstance patternInstance = null;

	private void recursiveParseing(org.w3c.dom.Node actualNode, org.eclipse.swt.widgets.TreeItem parent, String patternUUID){
		org.eclipse.swt.widgets.TreeItem item = new org.eclipse.swt.widgets.TreeItem(parent, 0);
		item.setData("reference",patternUUID);
		item.setText(actualNode.getNodeName());
		item.setData("type", NodeType.valueOf(actualNode.getNodeName().toUpperCase()));

		// Attribute traspasseing (from xpath node --> treeItem node)
		for(int index = 0; index < actualNode.getAttributes().getLength(); index++){
			item.setData(actualNode.getAttributes().item(index).getNodeName(),
				actualNode.getAttributes().item(index).getNodeValue().toString());
		}

		switch(NodeType.valueOf(actualNode.getNodeName().toUpperCase())){
			case PATTERNINSTANCE:
				PatternInstance patternInstance = new PatternInstance();
				patternInstance.setUuid((java.lang.String)getAttributeValueByName("uuid", actualNode));
				patternInstance.setGenerator(Generator.valueOf(getAttributeValueByName("generator", actualNode).toString()));
				patternInstance.setType(PatternType.valueOf(getAttributeValueByName("type", actualNode).toString()));
				patternInstance.setName((java.lang.String)getAttributeValueByName("name", actualNode));
				patternInstance.setDescription((java.lang.String)getAttributeValueByName("description", actualNode));
				break;
			case FORM:
				Form form = new Form();
				form.setUuid((java.lang.String)getAttributeValueByName("uuid", actualNode));
				form.setParentUUID((java.lang.String)getAttributeValueByName("parentUUID", actualNode));
				form.setId((java.lang.String)getAttributeValueByName("id", actualNode));
				break;
			case PANELGRID:
				PanelGrid panelGrid = new PanelGrid();
				panelGrid.setUuid((java.lang.String)getAttributeValueByName("uuid", actualNode));
				panelGrid.setParentUUID((java.lang.String)getAttributeValueByName("parentUUID", actualNode));
				break;
			case PANEL:
				Panel panel = new Panel();
				panel.setUuid((java.lang.String)getAttributeValueByName("uuid", actualNode));
				panel.setParentUUID((java.lang.String)getAttributeValueByName("parentUUID", actualNode));
				break;
			case PROPERTY:
				Property property = new Property();
				property.setUuid((java.lang.String)getAttributeValueByName("uuid", actualNode));
				property.setParentUUID((java.lang.String)getAttributeValueByName("parentUUID", actualNode));
				property.setName((java.lang.String)getAttributeValueByName("name", actualNode));
				property.setDescription((java.lang.String)getAttributeValueByName("description", actualNode));
				property.setType((java.lang.String)getAttributeValueByName("type", actualNode));
				property.setDefaultValue((java.lang.String)getAttributeValueByName("defaultValue", actualNode));
				break;
			case TEXT:
				Text text = new Text();
				text.setUuid((java.lang.String)getAttributeValueByName("uuid", actualNode));
				text.setParentUUID((java.lang.String)getAttributeValueByName("parentUUID", actualNode));
				text.setName((java.lang.String)getAttributeValueByName("name", actualNode));
				text.setValue((java.lang.String)getAttributeValueByName("value", actualNode));
				text.setType(TextType.valueOf(getAttributeValueByName("type", actualNode).toString()));
				text.setDescription((java.lang.String)getAttributeValueByName("description", actualNode));
				break;
			case COMMANDBUTTON:
				CommandButton commandButton = new CommandButton();
				commandButton.setUuid((java.lang.String)getAttributeValueByName("uuid", actualNode));
				commandButton.setParentUUID((java.lang.String)getAttributeValueByName("parentUUID", actualNode));
				commandButton.setName((java.lang.String)getAttributeValueByName("name", actualNode));
				commandButton.setAction((java.lang.String)getAttributeValueByName("action", actualNode));
				commandButton.setDescription((java.lang.String)getAttributeValueByName("description", actualNode));
				break;
			case DATATABLE:
				DataTable dataTable = new DataTable();
				dataTable.setUuid((java.lang.String)getAttributeValueByName("uuid", actualNode));
				dataTable.setParentUUID((java.lang.String)getAttributeValueByName("parentUUID", actualNode));
				dataTable.setName((java.lang.String)getAttributeValueByName("name", actualNode));
				dataTable.setManagedBean((java.lang.String)getAttributeValueByName("managedBean", actualNode));
				dataTable.setDescription((java.lang.String)getAttributeValueByName("description", actualNode));
				break;
			case COLUMN:
				Column column = new Column();
				column.setUuid((java.lang.String)getAttributeValueByName("uuid", actualNode));
				column.setParentUUID((java.lang.String)getAttributeValueByName("parentUUID", actualNode));
				column.setHeaderText((java.lang.String)getAttributeValueByName("headerText", actualNode));
				break;
			case MESSAGES:
				Messages messages = new Messages();
				messages.setUuid((java.lang.String)getAttributeValueByName("uuid", actualNode));
				messages.setParentUUID((java.lang.String)getAttributeValueByName("parentUUID", actualNode));
				break;
		}

		classInstanceStrategy(actualNode, item);

		// Recursion over child nodes
		if (actualNode.getChildNodes().getLength() > 0){
			for(int index = 0; index < actualNode.getChildNodes().getLength(); index++){
				if (actualNode.getChildNodes().item(index).getNodeType() == Node.ELEMENT_NODE){
					recursiveParseing(actualNode.getChildNodes().item(index), item, patternUUID);
				}
			}
		}

	}

	private void classInstanceStrategy(org.w3c.dom.Node actualNode, org.eclipse.swt.widgets.TreeItem item){
		if(actualNode.getNodeName().equalsIgnoreCase("patternInstance")){
			pattern01.helpers.generated.PatternInstance patternInstance = new pattern01.helpers.generated.PatternInstance();
			patternInstance.setUuid(actualNode.getAttributes().getNamedItem("uuid") != null ? actualNode.getAttributes().getNamedItem("uuid").getNodeValue().toString():"");
			patternInstance.setGenerator(actualNode.getAttributes().getNamedItem("generator") != null ? pattern01.helpers.generated.Generator.valueOf(actualNode.getAttributes().getNamedItem("generator").getNodeValue().toString()):pattern01.helpers.generated.Generator.WEB);
			patternInstance.setType(actualNode.getAttributes().getNamedItem("type") != null ? pattern01.helpers.generated.PatternType.valueOf(actualNode.getAttributes().getNamedItem("type").getNodeValue().toString()):pattern01.helpers.generated.PatternType.WORKWITH);
			patternInstance.setName(actualNode.getAttributes().getNamedItem("name") != null ? actualNode.getAttributes().getNamedItem("name").getNodeValue().toString():"");
			patternInstance.setDescription(actualNode.getAttributes().getNamedItem("description") != null ? actualNode.getAttributes().getNamedItem("description").getNodeValue().toString():"");
			this.patternInstance = new pattern01.helpers.generated.PatternInstance();
			this.patternInstance = patternInstance;
			try{
				item.setImage(pattern01.helpers.ImageHelper.getImage(actualNode.getAttributes().getNamedItem("image").getNodeValue().toString()));
			}catch(java.lang.NullPointerException ex){}
			item.setData("class_instance",patternInstance);
			item.setData("type","PATTERNINSTANCE");
		}else if(actualNode.getNodeName().equalsIgnoreCase("form")){
			pattern01.helpers.generated.Form form = new pattern01.helpers.generated.Form();
			form.setUuid(actualNode.getAttributes().getNamedItem("uuid") != null ? actualNode.getAttributes().getNamedItem("uuid").getNodeValue().toString():"");
			form.setParentUUID(actualNode.getAttributes().getNamedItem("parentUUID") != null ? actualNode.getAttributes().getNamedItem("parentUUID").getNodeValue().toString():"");
			form.setId(actualNode.getAttributes().getNamedItem("id") != null ? actualNode.getAttributes().getNamedItem("id").getNodeValue().toString():"primefaces.jpg");
			try{
				item.setImage(pattern01.helpers.ImageHelper.getImage(actualNode.getAttributes().getNamedItem("image").getNodeValue().toString()));
			}catch(java.lang.NullPointerException ex){}
			item.setData("class_instance",form);
			item.setData("parent_reference",this.patternInstance);
			item.setData("type","FORM");
			((pattern01.helpers.generated.IPatternElement)item.getParentItem().getData("class_instance")).setGenericElement(form);
		}else if(actualNode.getNodeName().equalsIgnoreCase("panelGrid")){
			pattern01.helpers.generated.PanelGrid panelGrid = new pattern01.helpers.generated.PanelGrid();
			panelGrid.setUuid(actualNode.getAttributes().getNamedItem("uuid") != null ? actualNode.getAttributes().getNamedItem("uuid").getNodeValue().toString():"");
			panelGrid.setParentUUID(actualNode.getAttributes().getNamedItem("parentUUID") != null ? actualNode.getAttributes().getNamedItem("parentUUID").getNodeValue().toString():"");
			try{
				item.setImage(pattern01.helpers.ImageHelper.getImage(actualNode.getAttributes().getNamedItem("image").getNodeValue().toString()));
			}catch(java.lang.NullPointerException ex){}
			item.setData("class_instance",panelGrid);
			item.setData("parent_reference",this.patternInstance);
			item.setData("type","PANELGRID");
			((pattern01.helpers.generated.IPatternElement)item.getParentItem().getData("class_instance")).setGenericElement(panelGrid);
		}else if(actualNode.getNodeName().equalsIgnoreCase("panel")){
			pattern01.helpers.generated.Panel panel = new pattern01.helpers.generated.Panel();
			panel.setUuid(actualNode.getAttributes().getNamedItem("uuid") != null ? actualNode.getAttributes().getNamedItem("uuid").getNodeValue().toString():"");
			panel.setParentUUID(actualNode.getAttributes().getNamedItem("parentUUID") != null ? actualNode.getAttributes().getNamedItem("parentUUID").getNodeValue().toString():"");
			try{
				item.setImage(pattern01.helpers.ImageHelper.getImage(actualNode.getAttributes().getNamedItem("image").getNodeValue().toString()));
			}catch(java.lang.NullPointerException ex){}
			item.setData("class_instance",panel);
			item.setData("parent_reference",this.patternInstance);
			item.setData("type","PANEL");
			((pattern01.helpers.generated.IPatternElement)item.getParentItem().getData("class_instance")).setGenericElement(panel);
		}else if(actualNode.getNodeName().equalsIgnoreCase("property")){
			pattern01.helpers.generated.Property property = new pattern01.helpers.generated.Property();
			property.setUuid(actualNode.getAttributes().getNamedItem("uuid") != null ? actualNode.getAttributes().getNamedItem("uuid").getNodeValue().toString():"");
			property.setParentUUID(actualNode.getAttributes().getNamedItem("parentUUID") != null ? actualNode.getAttributes().getNamedItem("parentUUID").getNodeValue().toString():"");
			property.setName(actualNode.getAttributes().getNamedItem("name") != null ? actualNode.getAttributes().getNamedItem("name").getNodeValue().toString():"");
			property.setDescription(actualNode.getAttributes().getNamedItem("description") != null ? actualNode.getAttributes().getNamedItem("description").getNodeValue().toString():"");
			property.setType(actualNode.getAttributes().getNamedItem("type") != null ? actualNode.getAttributes().getNamedItem("type").getNodeValue().toString():"");
			property.setDefaultValue(actualNode.getAttributes().getNamedItem("defaultValue") != null ? actualNode.getAttributes().getNamedItem("defaultValue").getNodeValue().toString():"");
			try{
				item.setImage(pattern01.helpers.ImageHelper.getImage(actualNode.getAttributes().getNamedItem("image").getNodeValue().toString()));
			}catch(java.lang.NullPointerException ex){}
			item.setData("class_instance",property);
			item.setData("parent_reference",this.patternInstance);
			item.setData("type","PROPERTY");
			((pattern01.helpers.generated.IPatternElement)item.getParentItem().getData("class_instance")).setGenericElement(property);
		}else if(actualNode.getNodeName().equalsIgnoreCase("text")){
			pattern01.helpers.generated.Text text = new pattern01.helpers.generated.Text();
			text.setUuid(actualNode.getAttributes().getNamedItem("uuid") != null ? actualNode.getAttributes().getNamedItem("uuid").getNodeValue().toString():"");
			text.setParentUUID(actualNode.getAttributes().getNamedItem("parentUUID") != null ? actualNode.getAttributes().getNamedItem("parentUUID").getNodeValue().toString():"");
			text.setName(actualNode.getAttributes().getNamedItem("name") != null ? actualNode.getAttributes().getNamedItem("name").getNodeValue().toString():"");
			text.setValue(actualNode.getAttributes().getNamedItem("value") != null ? actualNode.getAttributes().getNamedItem("value").getNodeValue().toString():"");
			text.setType(actualNode.getAttributes().getNamedItem("type") != null ? pattern01.helpers.generated.TextType.valueOf(actualNode.getAttributes().getNamedItem("type").getNodeValue().toString()):pattern01.helpers.generated.TextType.PLAINTEXT);
			text.setDescription(actualNode.getAttributes().getNamedItem("description") != null ? actualNode.getAttributes().getNamedItem("description").getNodeValue().toString():"");
			try{
				item.setImage(pattern01.helpers.ImageHelper.getImage(actualNode.getAttributes().getNamedItem("image").getNodeValue().toString()));
			}catch(java.lang.NullPointerException ex){}
			item.setData("class_instance",text);
			item.setData("parent_reference",this.patternInstance);
			item.setData("type","TEXT");
			((pattern01.helpers.generated.IPatternElement)item.getParentItem().getData("class_instance")).setGenericElement(text);
		}else if(actualNode.getNodeName().equalsIgnoreCase("commandButton")){
			pattern01.helpers.generated.CommandButton commandButton = new pattern01.helpers.generated.CommandButton();
			commandButton.setUuid(actualNode.getAttributes().getNamedItem("uuid") != null ? actualNode.getAttributes().getNamedItem("uuid").getNodeValue().toString():"");
			commandButton.setParentUUID(actualNode.getAttributes().getNamedItem("parentUUID") != null ? actualNode.getAttributes().getNamedItem("parentUUID").getNodeValue().toString():"");
			commandButton.setName(actualNode.getAttributes().getNamedItem("name") != null ? actualNode.getAttributes().getNamedItem("name").getNodeValue().toString():"");
			commandButton.setAction(actualNode.getAttributes().getNamedItem("action") != null ? actualNode.getAttributes().getNamedItem("action").getNodeValue().toString():"");
			commandButton.setDescription(actualNode.getAttributes().getNamedItem("description") != null ? actualNode.getAttributes().getNamedItem("description").getNodeValue().toString():"");
			try{
				item.setImage(pattern01.helpers.ImageHelper.getImage(actualNode.getAttributes().getNamedItem("image").getNodeValue().toString()));
			}catch(java.lang.NullPointerException ex){}
			item.setData("class_instance",commandButton);
			item.setData("parent_reference",this.patternInstance);
			item.setData("type","COMMANDBUTTON");
			((pattern01.helpers.generated.IPatternElement)item.getParentItem().getData("class_instance")).setGenericElement(commandButton);
		}else if(actualNode.getNodeName().equalsIgnoreCase("dataTable")){
			pattern01.helpers.generated.DataTable dataTable = new pattern01.helpers.generated.DataTable();
			dataTable.setUuid(actualNode.getAttributes().getNamedItem("uuid") != null ? actualNode.getAttributes().getNamedItem("uuid").getNodeValue().toString():"");
			dataTable.setParentUUID(actualNode.getAttributes().getNamedItem("parentUUID") != null ? actualNode.getAttributes().getNamedItem("parentUUID").getNodeValue().toString():"");
			dataTable.setName(actualNode.getAttributes().getNamedItem("name") != null ? actualNode.getAttributes().getNamedItem("name").getNodeValue().toString():"");
			dataTable.setManagedBean(actualNode.getAttributes().getNamedItem("managedBean") != null ? actualNode.getAttributes().getNamedItem("managedBean").getNodeValue().toString():"");
			dataTable.setDescription(actualNode.getAttributes().getNamedItem("description") != null ? actualNode.getAttributes().getNamedItem("description").getNodeValue().toString():"");
			try{
				item.setImage(pattern01.helpers.ImageHelper.getImage(actualNode.getAttributes().getNamedItem("image").getNodeValue().toString()));
			}catch(java.lang.NullPointerException ex){}
			item.setData("class_instance",dataTable);
			item.setData("parent_reference",this.patternInstance);
			item.setData("type","DATATABLE");
			((pattern01.helpers.generated.IPatternElement)item.getParentItem().getData("class_instance")).setGenericElement(dataTable);
		}else if(actualNode.getNodeName().equalsIgnoreCase("column")){
			pattern01.helpers.generated.Column column = new pattern01.helpers.generated.Column();
			column.setUuid(actualNode.getAttributes().getNamedItem("uuid") != null ? actualNode.getAttributes().getNamedItem("uuid").getNodeValue().toString():"");
			column.setParentUUID(actualNode.getAttributes().getNamedItem("parentUUID") != null ? actualNode.getAttributes().getNamedItem("parentUUID").getNodeValue().toString():"");
			column.setHeaderText(actualNode.getAttributes().getNamedItem("headerText") != null ? actualNode.getAttributes().getNamedItem("headerText").getNodeValue().toString():"");
			try{
				item.setImage(pattern01.helpers.ImageHelper.getImage(actualNode.getAttributes().getNamedItem("image").getNodeValue().toString()));
			}catch(java.lang.NullPointerException ex){}
			item.setData("class_instance",column);
			item.setData("parent_reference",this.patternInstance);
			item.setData("type","COLUMN");
			((pattern01.helpers.generated.IPatternElement)item.getParentItem().getData("class_instance")).setGenericElement(column);
		}else if(actualNode.getNodeName().equalsIgnoreCase("text")){
			pattern01.helpers.generated.Text text = new pattern01.helpers.generated.Text();
			text.setUuid(actualNode.getAttributes().getNamedItem("uuid") != null ? actualNode.getAttributes().getNamedItem("uuid").getNodeValue().toString():"");
			text.setParentUUID(actualNode.getAttributes().getNamedItem("parentUUID") != null ? actualNode.getAttributes().getNamedItem("parentUUID").getNodeValue().toString():"");
			text.setName(actualNode.getAttributes().getNamedItem("name") != null ? actualNode.getAttributes().getNamedItem("name").getNodeValue().toString():"");
			text.setValue(actualNode.getAttributes().getNamedItem("value") != null ? actualNode.getAttributes().getNamedItem("value").getNodeValue().toString():"");
			text.setType(actualNode.getAttributes().getNamedItem("type") != null ? pattern01.helpers.generated.TextType.valueOf(actualNode.getAttributes().getNamedItem("type").getNodeValue().toString()):pattern01.helpers.generated.TextType.PLAINTEXT);
			text.setDescription(actualNode.getAttributes().getNamedItem("description") != null ? actualNode.getAttributes().getNamedItem("description").getNodeValue().toString():"");
			try{
				item.setImage(pattern01.helpers.ImageHelper.getImage(actualNode.getAttributes().getNamedItem("image").getNodeValue().toString()));
			}catch(java.lang.NullPointerException ex){}
			item.setData("class_instance",text);
			item.setData("parent_reference",this.patternInstance);
			item.setData("type","TEXT");
			((pattern01.helpers.generated.IPatternElement)item.getParentItem().getData("class_instance")).setGenericElement(text);
		}else if(actualNode.getNodeName().equalsIgnoreCase("property")){
			pattern01.helpers.generated.Property property = new pattern01.helpers.generated.Property();
			property.setUuid(actualNode.getAttributes().getNamedItem("uuid") != null ? actualNode.getAttributes().getNamedItem("uuid").getNodeValue().toString():"");
			property.setParentUUID(actualNode.getAttributes().getNamedItem("parentUUID") != null ? actualNode.getAttributes().getNamedItem("parentUUID").getNodeValue().toString():"");
			property.setName(actualNode.getAttributes().getNamedItem("name") != null ? actualNode.getAttributes().getNamedItem("name").getNodeValue().toString():"");
			property.setDescription(actualNode.getAttributes().getNamedItem("description") != null ? actualNode.getAttributes().getNamedItem("description").getNodeValue().toString():"");
			property.setType(actualNode.getAttributes().getNamedItem("type") != null ? actualNode.getAttributes().getNamedItem("type").getNodeValue().toString():"");
			property.setDefaultValue(actualNode.getAttributes().getNamedItem("defaultValue") != null ? actualNode.getAttributes().getNamedItem("defaultValue").getNodeValue().toString():"");
			try{
				item.setImage(pattern01.helpers.ImageHelper.getImage(actualNode.getAttributes().getNamedItem("image").getNodeValue().toString()));
			}catch(java.lang.NullPointerException ex){}
			item.setData("class_instance",property);
			item.setData("parent_reference",this.patternInstance);
			item.setData("type","PROPERTY");
			((pattern01.helpers.generated.IPatternElement)item.getParentItem().getData("class_instance")).setGenericElement(property);
		}else if(actualNode.getNodeName().equalsIgnoreCase("text")){
			pattern01.helpers.generated.Text text = new pattern01.helpers.generated.Text();
			text.setUuid(actualNode.getAttributes().getNamedItem("uuid") != null ? actualNode.getAttributes().getNamedItem("uuid").getNodeValue().toString():"");
			text.setParentUUID(actualNode.getAttributes().getNamedItem("parentUUID") != null ? actualNode.getAttributes().getNamedItem("parentUUID").getNodeValue().toString():"");
			text.setName(actualNode.getAttributes().getNamedItem("name") != null ? actualNode.getAttributes().getNamedItem("name").getNodeValue().toString():"");
			text.setValue(actualNode.getAttributes().getNamedItem("value") != null ? actualNode.getAttributes().getNamedItem("value").getNodeValue().toString():"");
			text.setType(actualNode.getAttributes().getNamedItem("type") != null ? pattern01.helpers.generated.TextType.valueOf(actualNode.getAttributes().getNamedItem("type").getNodeValue().toString()):pattern01.helpers.generated.TextType.PLAINTEXT);
			text.setDescription(actualNode.getAttributes().getNamedItem("description") != null ? actualNode.getAttributes().getNamedItem("description").getNodeValue().toString():"");
			try{
				item.setImage(pattern01.helpers.ImageHelper.getImage(actualNode.getAttributes().getNamedItem("image").getNodeValue().toString()));
			}catch(java.lang.NullPointerException ex){}
			item.setData("class_instance",text);
			item.setData("parent_reference",this.patternInstance);
			item.setData("type","TEXT");
			((pattern01.helpers.generated.IPatternElement)item.getParentItem().getData("class_instance")).setGenericElement(text);
		}else if(actualNode.getNodeName().equalsIgnoreCase("commandButton")){
			pattern01.helpers.generated.CommandButton commandButton = new pattern01.helpers.generated.CommandButton();
			commandButton.setUuid(actualNode.getAttributes().getNamedItem("uuid") != null ? actualNode.getAttributes().getNamedItem("uuid").getNodeValue().toString():"");
			commandButton.setParentUUID(actualNode.getAttributes().getNamedItem("parentUUID") != null ? actualNode.getAttributes().getNamedItem("parentUUID").getNodeValue().toString():"");
			commandButton.setName(actualNode.getAttributes().getNamedItem("name") != null ? actualNode.getAttributes().getNamedItem("name").getNodeValue().toString():"");
			commandButton.setAction(actualNode.getAttributes().getNamedItem("action") != null ? actualNode.getAttributes().getNamedItem("action").getNodeValue().toString():"");
			commandButton.setDescription(actualNode.getAttributes().getNamedItem("description") != null ? actualNode.getAttributes().getNamedItem("description").getNodeValue().toString():"");
			try{
				item.setImage(pattern01.helpers.ImageHelper.getImage(actualNode.getAttributes().getNamedItem("image").getNodeValue().toString()));
			}catch(java.lang.NullPointerException ex){}
			item.setData("class_instance",commandButton);
			item.setData("parent_reference",this.patternInstance);
			item.setData("type","COMMANDBUTTON");
			((pattern01.helpers.generated.IPatternElement)item.getParentItem().getData("class_instance")).setGenericElement(commandButton);
		}else if(actualNode.getNodeName().equalsIgnoreCase("dataTable")){
			pattern01.helpers.generated.DataTable dataTable = new pattern01.helpers.generated.DataTable();
			dataTable.setUuid(actualNode.getAttributes().getNamedItem("uuid") != null ? actualNode.getAttributes().getNamedItem("uuid").getNodeValue().toString():"");
			dataTable.setParentUUID(actualNode.getAttributes().getNamedItem("parentUUID") != null ? actualNode.getAttributes().getNamedItem("parentUUID").getNodeValue().toString():"");
			dataTable.setName(actualNode.getAttributes().getNamedItem("name") != null ? actualNode.getAttributes().getNamedItem("name").getNodeValue().toString():"");
			dataTable.setManagedBean(actualNode.getAttributes().getNamedItem("managedBean") != null ? actualNode.getAttributes().getNamedItem("managedBean").getNodeValue().toString():"");
			dataTable.setDescription(actualNode.getAttributes().getNamedItem("description") != null ? actualNode.getAttributes().getNamedItem("description").getNodeValue().toString():"");
			try{
				item.setImage(pattern01.helpers.ImageHelper.getImage(actualNode.getAttributes().getNamedItem("image").getNodeValue().toString()));
			}catch(java.lang.NullPointerException ex){}
			item.setData("class_instance",dataTable);
			item.setData("parent_reference",this.patternInstance);
			item.setData("type","DATATABLE");
			((pattern01.helpers.generated.IPatternElement)item.getParentItem().getData("class_instance")).setGenericElement(dataTable);
		}else if(actualNode.getNodeName().equalsIgnoreCase("column")){
			pattern01.helpers.generated.Column column = new pattern01.helpers.generated.Column();
			column.setUuid(actualNode.getAttributes().getNamedItem("uuid") != null ? actualNode.getAttributes().getNamedItem("uuid").getNodeValue().toString():"");
			column.setParentUUID(actualNode.getAttributes().getNamedItem("parentUUID") != null ? actualNode.getAttributes().getNamedItem("parentUUID").getNodeValue().toString():"");
			column.setHeaderText(actualNode.getAttributes().getNamedItem("headerText") != null ? actualNode.getAttributes().getNamedItem("headerText").getNodeValue().toString():"");
			try{
				item.setImage(pattern01.helpers.ImageHelper.getImage(actualNode.getAttributes().getNamedItem("image").getNodeValue().toString()));
			}catch(java.lang.NullPointerException ex){}
			item.setData("class_instance",column);
			item.setData("parent_reference",this.patternInstance);
			item.setData("type","COLUMN");
			((pattern01.helpers.generated.IPatternElement)item.getParentItem().getData("class_instance")).setGenericElement(column);
		}else if(actualNode.getNodeName().equalsIgnoreCase("text")){
			pattern01.helpers.generated.Text text = new pattern01.helpers.generated.Text();
			text.setUuid(actualNode.getAttributes().getNamedItem("uuid") != null ? actualNode.getAttributes().getNamedItem("uuid").getNodeValue().toString():"");
			text.setParentUUID(actualNode.getAttributes().getNamedItem("parentUUID") != null ? actualNode.getAttributes().getNamedItem("parentUUID").getNodeValue().toString():"");
			text.setName(actualNode.getAttributes().getNamedItem("name") != null ? actualNode.getAttributes().getNamedItem("name").getNodeValue().toString():"");
			text.setValue(actualNode.getAttributes().getNamedItem("value") != null ? actualNode.getAttributes().getNamedItem("value").getNodeValue().toString():"");
			text.setType(actualNode.getAttributes().getNamedItem("type") != null ? pattern01.helpers.generated.TextType.valueOf(actualNode.getAttributes().getNamedItem("type").getNodeValue().toString()):pattern01.helpers.generated.TextType.PLAINTEXT);
			text.setDescription(actualNode.getAttributes().getNamedItem("description") != null ? actualNode.getAttributes().getNamedItem("description").getNodeValue().toString():"");
			try{
				item.setImage(pattern01.helpers.ImageHelper.getImage(actualNode.getAttributes().getNamedItem("image").getNodeValue().toString()));
			}catch(java.lang.NullPointerException ex){}
			item.setData("class_instance",text);
			item.setData("parent_reference",this.patternInstance);
			item.setData("type","TEXT");
			((pattern01.helpers.generated.IPatternElement)item.getParentItem().getData("class_instance")).setGenericElement(text);
		}else if(actualNode.getNodeName().equalsIgnoreCase("panel")){
			pattern01.helpers.generated.Panel panel = new pattern01.helpers.generated.Panel();
			panel.setUuid(actualNode.getAttributes().getNamedItem("uuid") != null ? actualNode.getAttributes().getNamedItem("uuid").getNodeValue().toString():"");
			panel.setParentUUID(actualNode.getAttributes().getNamedItem("parentUUID") != null ? actualNode.getAttributes().getNamedItem("parentUUID").getNodeValue().toString():"");
			try{
				item.setImage(pattern01.helpers.ImageHelper.getImage(actualNode.getAttributes().getNamedItem("image").getNodeValue().toString()));
			}catch(java.lang.NullPointerException ex){}
			item.setData("class_instance",panel);
			item.setData("parent_reference",this.patternInstance);
			item.setData("type","PANEL");
			((pattern01.helpers.generated.IPatternElement)item.getParentItem().getData("class_instance")).setGenericElement(panel);
		}else if(actualNode.getNodeName().equalsIgnoreCase("property")){
			pattern01.helpers.generated.Property property = new pattern01.helpers.generated.Property();
			property.setUuid(actualNode.getAttributes().getNamedItem("uuid") != null ? actualNode.getAttributes().getNamedItem("uuid").getNodeValue().toString():"");
			property.setParentUUID(actualNode.getAttributes().getNamedItem("parentUUID") != null ? actualNode.getAttributes().getNamedItem("parentUUID").getNodeValue().toString():"");
			property.setName(actualNode.getAttributes().getNamedItem("name") != null ? actualNode.getAttributes().getNamedItem("name").getNodeValue().toString():"");
			property.setDescription(actualNode.getAttributes().getNamedItem("description") != null ? actualNode.getAttributes().getNamedItem("description").getNodeValue().toString():"");
			property.setType(actualNode.getAttributes().getNamedItem("type") != null ? actualNode.getAttributes().getNamedItem("type").getNodeValue().toString():"");
			property.setDefaultValue(actualNode.getAttributes().getNamedItem("defaultValue") != null ? actualNode.getAttributes().getNamedItem("defaultValue").getNodeValue().toString():"");
			try{
				item.setImage(pattern01.helpers.ImageHelper.getImage(actualNode.getAttributes().getNamedItem("image").getNodeValue().toString()));
			}catch(java.lang.NullPointerException ex){}
			item.setData("class_instance",property);
			item.setData("parent_reference",this.patternInstance);
			item.setData("type","PROPERTY");
			((pattern01.helpers.generated.IPatternElement)item.getParentItem().getData("class_instance")).setGenericElement(property);
		}else if(actualNode.getNodeName().equalsIgnoreCase("text")){
			pattern01.helpers.generated.Text text = new pattern01.helpers.generated.Text();
			text.setUuid(actualNode.getAttributes().getNamedItem("uuid") != null ? actualNode.getAttributes().getNamedItem("uuid").getNodeValue().toString():"");
			text.setParentUUID(actualNode.getAttributes().getNamedItem("parentUUID") != null ? actualNode.getAttributes().getNamedItem("parentUUID").getNodeValue().toString():"");
			text.setName(actualNode.getAttributes().getNamedItem("name") != null ? actualNode.getAttributes().getNamedItem("name").getNodeValue().toString():"");
			text.setValue(actualNode.getAttributes().getNamedItem("value") != null ? actualNode.getAttributes().getNamedItem("value").getNodeValue().toString():"");
			text.setType(actualNode.getAttributes().getNamedItem("type") != null ? pattern01.helpers.generated.TextType.valueOf(actualNode.getAttributes().getNamedItem("type").getNodeValue().toString()):pattern01.helpers.generated.TextType.PLAINTEXT);
			text.setDescription(actualNode.getAttributes().getNamedItem("description") != null ? actualNode.getAttributes().getNamedItem("description").getNodeValue().toString():"");
			try{
				item.setImage(pattern01.helpers.ImageHelper.getImage(actualNode.getAttributes().getNamedItem("image").getNodeValue().toString()));
			}catch(java.lang.NullPointerException ex){}
			item.setData("class_instance",text);
			item.setData("parent_reference",this.patternInstance);
			item.setData("type","TEXT");
			((pattern01.helpers.generated.IPatternElement)item.getParentItem().getData("class_instance")).setGenericElement(text);
		}else if(actualNode.getNodeName().equalsIgnoreCase("commandButton")){
			pattern01.helpers.generated.CommandButton commandButton = new pattern01.helpers.generated.CommandButton();
			commandButton.setUuid(actualNode.getAttributes().getNamedItem("uuid") != null ? actualNode.getAttributes().getNamedItem("uuid").getNodeValue().toString():"");
			commandButton.setParentUUID(actualNode.getAttributes().getNamedItem("parentUUID") != null ? actualNode.getAttributes().getNamedItem("parentUUID").getNodeValue().toString():"");
			commandButton.setName(actualNode.getAttributes().getNamedItem("name") != null ? actualNode.getAttributes().getNamedItem("name").getNodeValue().toString():"");
			commandButton.setAction(actualNode.getAttributes().getNamedItem("action") != null ? actualNode.getAttributes().getNamedItem("action").getNodeValue().toString():"");
			commandButton.setDescription(actualNode.getAttributes().getNamedItem("description") != null ? actualNode.getAttributes().getNamedItem("description").getNodeValue().toString():"");
			try{
				item.setImage(pattern01.helpers.ImageHelper.getImage(actualNode.getAttributes().getNamedItem("image").getNodeValue().toString()));
			}catch(java.lang.NullPointerException ex){}
			item.setData("class_instance",commandButton);
			item.setData("parent_reference",this.patternInstance);
			item.setData("type","COMMANDBUTTON");
			((pattern01.helpers.generated.IPatternElement)item.getParentItem().getData("class_instance")).setGenericElement(commandButton);
		}else if(actualNode.getNodeName().equalsIgnoreCase("dataTable")){
			pattern01.helpers.generated.DataTable dataTable = new pattern01.helpers.generated.DataTable();
			dataTable.setUuid(actualNode.getAttributes().getNamedItem("uuid") != null ? actualNode.getAttributes().getNamedItem("uuid").getNodeValue().toString():"");
			dataTable.setParentUUID(actualNode.getAttributes().getNamedItem("parentUUID") != null ? actualNode.getAttributes().getNamedItem("parentUUID").getNodeValue().toString():"");
			dataTable.setName(actualNode.getAttributes().getNamedItem("name") != null ? actualNode.getAttributes().getNamedItem("name").getNodeValue().toString():"");
			dataTable.setManagedBean(actualNode.getAttributes().getNamedItem("managedBean") != null ? actualNode.getAttributes().getNamedItem("managedBean").getNodeValue().toString():"");
			dataTable.setDescription(actualNode.getAttributes().getNamedItem("description") != null ? actualNode.getAttributes().getNamedItem("description").getNodeValue().toString():"");
			try{
				item.setImage(pattern01.helpers.ImageHelper.getImage(actualNode.getAttributes().getNamedItem("image").getNodeValue().toString()));
			}catch(java.lang.NullPointerException ex){}
			item.setData("class_instance",dataTable);
			item.setData("parent_reference",this.patternInstance);
			item.setData("type","DATATABLE");
			((pattern01.helpers.generated.IPatternElement)item.getParentItem().getData("class_instance")).setGenericElement(dataTable);
		}else if(actualNode.getNodeName().equalsIgnoreCase("column")){
			pattern01.helpers.generated.Column column = new pattern01.helpers.generated.Column();
			column.setUuid(actualNode.getAttributes().getNamedItem("uuid") != null ? actualNode.getAttributes().getNamedItem("uuid").getNodeValue().toString():"");
			column.setParentUUID(actualNode.getAttributes().getNamedItem("parentUUID") != null ? actualNode.getAttributes().getNamedItem("parentUUID").getNodeValue().toString():"");
			column.setHeaderText(actualNode.getAttributes().getNamedItem("headerText") != null ? actualNode.getAttributes().getNamedItem("headerText").getNodeValue().toString():"");
			try{
				item.setImage(pattern01.helpers.ImageHelper.getImage(actualNode.getAttributes().getNamedItem("image").getNodeValue().toString()));
			}catch(java.lang.NullPointerException ex){}
			item.setData("class_instance",column);
			item.setData("parent_reference",this.patternInstance);
			item.setData("type","COLUMN");
			((pattern01.helpers.generated.IPatternElement)item.getParentItem().getData("class_instance")).setGenericElement(column);
		}else if(actualNode.getNodeName().equalsIgnoreCase("text")){
			pattern01.helpers.generated.Text text = new pattern01.helpers.generated.Text();
			text.setUuid(actualNode.getAttributes().getNamedItem("uuid") != null ? actualNode.getAttributes().getNamedItem("uuid").getNodeValue().toString():"");
			text.setParentUUID(actualNode.getAttributes().getNamedItem("parentUUID") != null ? actualNode.getAttributes().getNamedItem("parentUUID").getNodeValue().toString():"");
			text.setName(actualNode.getAttributes().getNamedItem("name") != null ? actualNode.getAttributes().getNamedItem("name").getNodeValue().toString():"");
			text.setValue(actualNode.getAttributes().getNamedItem("value") != null ? actualNode.getAttributes().getNamedItem("value").getNodeValue().toString():"");
			text.setType(actualNode.getAttributes().getNamedItem("type") != null ? pattern01.helpers.generated.TextType.valueOf(actualNode.getAttributes().getNamedItem("type").getNodeValue().toString()):pattern01.helpers.generated.TextType.PLAINTEXT);
			text.setDescription(actualNode.getAttributes().getNamedItem("description") != null ? actualNode.getAttributes().getNamedItem("description").getNodeValue().toString():"");
			try{
				item.setImage(pattern01.helpers.ImageHelper.getImage(actualNode.getAttributes().getNamedItem("image").getNodeValue().toString()));
			}catch(java.lang.NullPointerException ex){}
			item.setData("class_instance",text);
			item.setData("parent_reference",this.patternInstance);
			item.setData("type","TEXT");
			((pattern01.helpers.generated.IPatternElement)item.getParentItem().getData("class_instance")).setGenericElement(text);
		}else if(actualNode.getNodeName().equalsIgnoreCase("property")){
			pattern01.helpers.generated.Property property = new pattern01.helpers.generated.Property();
			property.setUuid(actualNode.getAttributes().getNamedItem("uuid") != null ? actualNode.getAttributes().getNamedItem("uuid").getNodeValue().toString():"");
			property.setParentUUID(actualNode.getAttributes().getNamedItem("parentUUID") != null ? actualNode.getAttributes().getNamedItem("parentUUID").getNodeValue().toString():"");
			property.setName(actualNode.getAttributes().getNamedItem("name") != null ? actualNode.getAttributes().getNamedItem("name").getNodeValue().toString():"");
			property.setDescription(actualNode.getAttributes().getNamedItem("description") != null ? actualNode.getAttributes().getNamedItem("description").getNodeValue().toString():"");
			property.setType(actualNode.getAttributes().getNamedItem("type") != null ? actualNode.getAttributes().getNamedItem("type").getNodeValue().toString():"");
			property.setDefaultValue(actualNode.getAttributes().getNamedItem("defaultValue") != null ? actualNode.getAttributes().getNamedItem("defaultValue").getNodeValue().toString():"");
			try{
				item.setImage(pattern01.helpers.ImageHelper.getImage(actualNode.getAttributes().getNamedItem("image").getNodeValue().toString()));
			}catch(java.lang.NullPointerException ex){}
			item.setData("class_instance",property);
			item.setData("parent_reference",this.patternInstance);
			item.setData("type","PROPERTY");
			((pattern01.helpers.generated.IPatternElement)item.getParentItem().getData("class_instance")).setGenericElement(property);
		}else if(actualNode.getNodeName().equalsIgnoreCase("text")){
			pattern01.helpers.generated.Text text = new pattern01.helpers.generated.Text();
			text.setUuid(actualNode.getAttributes().getNamedItem("uuid") != null ? actualNode.getAttributes().getNamedItem("uuid").getNodeValue().toString():"");
			text.setParentUUID(actualNode.getAttributes().getNamedItem("parentUUID") != null ? actualNode.getAttributes().getNamedItem("parentUUID").getNodeValue().toString():"");
			text.setName(actualNode.getAttributes().getNamedItem("name") != null ? actualNode.getAttributes().getNamedItem("name").getNodeValue().toString():"");
			text.setValue(actualNode.getAttributes().getNamedItem("value") != null ? actualNode.getAttributes().getNamedItem("value").getNodeValue().toString():"");
			text.setType(actualNode.getAttributes().getNamedItem("type") != null ? pattern01.helpers.generated.TextType.valueOf(actualNode.getAttributes().getNamedItem("type").getNodeValue().toString()):pattern01.helpers.generated.TextType.PLAINTEXT);
			text.setDescription(actualNode.getAttributes().getNamedItem("description") != null ? actualNode.getAttributes().getNamedItem("description").getNodeValue().toString():"");
			try{
				item.setImage(pattern01.helpers.ImageHelper.getImage(actualNode.getAttributes().getNamedItem("image").getNodeValue().toString()));
			}catch(java.lang.NullPointerException ex){}
			item.setData("class_instance",text);
			item.setData("parent_reference",this.patternInstance);
			item.setData("type","TEXT");
			((pattern01.helpers.generated.IPatternElement)item.getParentItem().getData("class_instance")).setGenericElement(text);
		}else if(actualNode.getNodeName().equalsIgnoreCase("messages")){
			pattern01.helpers.generated.Messages messages = new pattern01.helpers.generated.Messages();
			messages.setUuid(actualNode.getAttributes().getNamedItem("uuid") != null ? actualNode.getAttributes().getNamedItem("uuid").getNodeValue().toString():"");
			messages.setParentUUID(actualNode.getAttributes().getNamedItem("parentUUID") != null ? actualNode.getAttributes().getNamedItem("parentUUID").getNodeValue().toString():"");
			try{
				item.setImage(pattern01.helpers.ImageHelper.getImage(actualNode.getAttributes().getNamedItem("image").getNodeValue().toString()));
			}catch(java.lang.NullPointerException ex){}
			item.setData("class_instance",messages);
			item.setData("parent_reference",this.patternInstance);
			item.setData("type","MESSAGES");
			((pattern01.helpers.generated.IPatternElement)item.getParentItem().getData("class_instance")).setGenericElement(messages);
		}else if(actualNode.getNodeName().equalsIgnoreCase("dataTable")){
			pattern01.helpers.generated.DataTable dataTable = new pattern01.helpers.generated.DataTable();
			dataTable.setUuid(actualNode.getAttributes().getNamedItem("uuid") != null ? actualNode.getAttributes().getNamedItem("uuid").getNodeValue().toString():"");
			dataTable.setParentUUID(actualNode.getAttributes().getNamedItem("parentUUID") != null ? actualNode.getAttributes().getNamedItem("parentUUID").getNodeValue().toString():"");
			dataTable.setName(actualNode.getAttributes().getNamedItem("name") != null ? actualNode.getAttributes().getNamedItem("name").getNodeValue().toString():"");
			dataTable.setManagedBean(actualNode.getAttributes().getNamedItem("managedBean") != null ? actualNode.getAttributes().getNamedItem("managedBean").getNodeValue().toString():"");
			dataTable.setDescription(actualNode.getAttributes().getNamedItem("description") != null ? actualNode.getAttributes().getNamedItem("description").getNodeValue().toString():"");
			try{
				item.setImage(pattern01.helpers.ImageHelper.getImage(actualNode.getAttributes().getNamedItem("image").getNodeValue().toString()));
			}catch(java.lang.NullPointerException ex){}
			item.setData("class_instance",dataTable);
			item.setData("parent_reference",this.patternInstance);
			item.setData("type","DATATABLE");
			((pattern01.helpers.generated.IPatternElement)item.getParentItem().getData("class_instance")).setGenericElement(dataTable);
		}else if(actualNode.getNodeName().equalsIgnoreCase("column")){
			pattern01.helpers.generated.Column column = new pattern01.helpers.generated.Column();
			column.setUuid(actualNode.getAttributes().getNamedItem("uuid") != null ? actualNode.getAttributes().getNamedItem("uuid").getNodeValue().toString():"");
			column.setParentUUID(actualNode.getAttributes().getNamedItem("parentUUID") != null ? actualNode.getAttributes().getNamedItem("parentUUID").getNodeValue().toString():"");
			column.setHeaderText(actualNode.getAttributes().getNamedItem("headerText") != null ? actualNode.getAttributes().getNamedItem("headerText").getNodeValue().toString():"");
			try{
				item.setImage(pattern01.helpers.ImageHelper.getImage(actualNode.getAttributes().getNamedItem("image").getNodeValue().toString()));
			}catch(java.lang.NullPointerException ex){}
			item.setData("class_instance",column);
			item.setData("parent_reference",this.patternInstance);
			item.setData("type","COLUMN");
			((pattern01.helpers.generated.IPatternElement)item.getParentItem().getData("class_instance")).setGenericElement(column);
		}else if(actualNode.getNodeName().equalsIgnoreCase("text")){
			pattern01.helpers.generated.Text text = new pattern01.helpers.generated.Text();
			text.setUuid(actualNode.getAttributes().getNamedItem("uuid") != null ? actualNode.getAttributes().getNamedItem("uuid").getNodeValue().toString():"");
			text.setParentUUID(actualNode.getAttributes().getNamedItem("parentUUID") != null ? actualNode.getAttributes().getNamedItem("parentUUID").getNodeValue().toString():"");
			text.setName(actualNode.getAttributes().getNamedItem("name") != null ? actualNode.getAttributes().getNamedItem("name").getNodeValue().toString():"");
			text.setValue(actualNode.getAttributes().getNamedItem("value") != null ? actualNode.getAttributes().getNamedItem("value").getNodeValue().toString():"");
			text.setType(actualNode.getAttributes().getNamedItem("type") != null ? pattern01.helpers.generated.TextType.valueOf(actualNode.getAttributes().getNamedItem("type").getNodeValue().toString()):pattern01.helpers.generated.TextType.PLAINTEXT);
			text.setDescription(actualNode.getAttributes().getNamedItem("description") != null ? actualNode.getAttributes().getNamedItem("description").getNodeValue().toString():"");
			try{
				item.setImage(pattern01.helpers.ImageHelper.getImage(actualNode.getAttributes().getNamedItem("image").getNodeValue().toString()));
			}catch(java.lang.NullPointerException ex){}
			item.setData("class_instance",text);
			item.setData("parent_reference",this.patternInstance);
			item.setData("type","TEXT");
			((pattern01.helpers.generated.IPatternElement)item.getParentItem().getData("class_instance")).setGenericElement(text);
		}else if(actualNode.getNodeName().equalsIgnoreCase("commandButton")){
			pattern01.helpers.generated.CommandButton commandButton = new pattern01.helpers.generated.CommandButton();
			commandButton.setUuid(actualNode.getAttributes().getNamedItem("uuid") != null ? actualNode.getAttributes().getNamedItem("uuid").getNodeValue().toString():"");
			commandButton.setParentUUID(actualNode.getAttributes().getNamedItem("parentUUID") != null ? actualNode.getAttributes().getNamedItem("parentUUID").getNodeValue().toString():"");
			commandButton.setName(actualNode.getAttributes().getNamedItem("name") != null ? actualNode.getAttributes().getNamedItem("name").getNodeValue().toString():"");
			commandButton.setAction(actualNode.getAttributes().getNamedItem("action") != null ? actualNode.getAttributes().getNamedItem("action").getNodeValue().toString():"");
			commandButton.setDescription(actualNode.getAttributes().getNamedItem("description") != null ? actualNode.getAttributes().getNamedItem("description").getNodeValue().toString():"");
			try{
				item.setImage(pattern01.helpers.ImageHelper.getImage(actualNode.getAttributes().getNamedItem("image").getNodeValue().toString()));
			}catch(java.lang.NullPointerException ex){}
			item.setData("class_instance",commandButton);
			item.setData("parent_reference",this.patternInstance);
			item.setData("type","COMMANDBUTTON");
			((pattern01.helpers.generated.IPatternElement)item.getParentItem().getData("class_instance")).setGenericElement(commandButton);
		}
		item.setExpanded(true);
	}
	private Object getAttributeValueByName(String name, org.w3c.dom.Node actualNode){
		int index = 0; boolean itemfound = false; Object value=null;
		while(index < actualNode.getAttributes().getLength() && !itemfound){
			if(actualNode.getAttributes().item(index).getNodeName().equalsIgnoreCase(name)){
				value = actualNode.getAttributes().item(index).getNodeValue();
				itemfound = true;
			}else{
				index++;
			}
		}
		return value;
	}
/* [End] Auto-generated code for pattern instance parser do not remove */

	public TreeItem getInstance(){
		return this.instance;
	}

}

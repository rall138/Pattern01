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
import pattern01.plugin.components.navigator.NodeType;


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
	* Creation date: Mon Mar 20 17:47:47 GFT 2017
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
		}else if(actualNode.getNodeName().equalsIgnoreCase("patternInstance_form")){
			pattern01.helpers.generated.Form patternInstance_form = new pattern01.helpers.generated.Form();
			patternInstance_form.setId(actualNode.getAttributes().getNamedItem("id") != null ? actualNode.getAttributes().getNamedItem("id").getNodeValue().toString():"primefaces.jpg");
			try{
				item.setImage(pattern01.helpers.ImageHelper.getImage(actualNode.getAttributes().getNamedItem("image").getNodeValue().toString()));
			}catch(java.lang.NullPointerException ex){}
			item.setData("class_instance",patternInstance_form);
			item.setData("parent_reference",this.patternInstance);
			item.setData("type","PATTERNINSTANCE_FORM");
			((pattern01.helpers.generated.PatternInstance)item.getParentItem().getData("class_instance")).setForm(patternInstance_form);
		}else if(actualNode.getNodeName().equalsIgnoreCase("patternInstance_form_panelGrid")){
			pattern01.helpers.generated.PanelGrid patternInstance_form_panelGrid = new pattern01.helpers.generated.PanelGrid();
			try{
				item.setImage(pattern01.helpers.ImageHelper.getImage(actualNode.getAttributes().getNamedItem("image").getNodeValue().toString()));
			}catch(java.lang.NullPointerException ex){}
			item.setData("class_instance",patternInstance_form_panelGrid);
			item.setData("parent_reference",this.patternInstance);
			item.setData("type","PATTERNINSTANCE_FORM_PANELGRID");
			((pattern01.helpers.generated.Form)item.getParentItem().getData("class_instance")).getCollection_PanelGrid().add(patternInstance_form_panelGrid);
		}else if(actualNode.getNodeName().equalsIgnoreCase("patternInstance_form_panelGrid_panel")){
			pattern01.helpers.generated.Panel patternInstance_form_panelGrid_panel = new pattern01.helpers.generated.Panel();
			try{
				item.setImage(pattern01.helpers.ImageHelper.getImage(actualNode.getAttributes().getNamedItem("image").getNodeValue().toString()));
			}catch(java.lang.NullPointerException ex){}
			item.setData("class_instance",patternInstance_form_panelGrid_panel);
			item.setData("parent_reference",this.patternInstance);
			item.setData("type","PATTERNINSTANCE_FORM_PANELGRID_PANEL");
			((pattern01.helpers.generated.PanelGrid)item.getParentItem().getData("class_instance")).getCollection_Panel().add(patternInstance_form_panelGrid_panel);
		}else if(actualNode.getNodeName().equalsIgnoreCase("patternInstance_form_panelGrid_panel_property")){
			pattern01.helpers.generated.Property patternInstance_form_panelGrid_panel_property = new pattern01.helpers.generated.Property();
			patternInstance_form_panelGrid_panel_property.setName(actualNode.getAttributes().getNamedItem("name") != null ? actualNode.getAttributes().getNamedItem("name").getNodeValue().toString():"");
			patternInstance_form_panelGrid_panel_property.setDescription(actualNode.getAttributes().getNamedItem("description") != null ? actualNode.getAttributes().getNamedItem("description").getNodeValue().toString():"");
			patternInstance_form_panelGrid_panel_property.setType(actualNode.getAttributes().getNamedItem("type") != null ? actualNode.getAttributes().getNamedItem("type").getNodeValue().toString():"");
			patternInstance_form_panelGrid_panel_property.setDefaultValue(actualNode.getAttributes().getNamedItem("defaultValue") != null ? actualNode.getAttributes().getNamedItem("defaultValue").getNodeValue().toString():"");
			try{
				item.setImage(pattern01.helpers.ImageHelper.getImage(actualNode.getAttributes().getNamedItem("image").getNodeValue().toString()));
			}catch(java.lang.NullPointerException ex){}
			item.setData("class_instance",patternInstance_form_panelGrid_panel_property);
			item.setData("parent_reference",this.patternInstance);
			item.setData("type","PATTERNINSTANCE_FORM_PANELGRID_PANEL_PROPERTY");
			((pattern01.helpers.generated.Panel)item.getParentItem().getData("class_instance")).getCollection_Property().add(patternInstance_form_panelGrid_panel_property);
		}else if(actualNode.getNodeName().equalsIgnoreCase("patternInstance_form_panelGrid_panel_text")){
			pattern01.helpers.generated.Text patternInstance_form_panelGrid_panel_text = new pattern01.helpers.generated.Text();
			patternInstance_form_panelGrid_panel_text.setName(actualNode.getAttributes().getNamedItem("name") != null ? actualNode.getAttributes().getNamedItem("name").getNodeValue().toString():"");
			patternInstance_form_panelGrid_panel_text.setValue(actualNode.getAttributes().getNamedItem("value") != null ? actualNode.getAttributes().getNamedItem("value").getNodeValue().toString():"");
			patternInstance_form_panelGrid_panel_text.setType(actualNode.getAttributes().getNamedItem("type") != null ? pattern01.helpers.generated.TextType.valueOf(actualNode.getAttributes().getNamedItem("type").getNodeValue().toString()):pattern01.helpers.generated.TextType.PLAINTEXT);
			patternInstance_form_panelGrid_panel_text.setDescription(actualNode.getAttributes().getNamedItem("description") != null ? actualNode.getAttributes().getNamedItem("description").getNodeValue().toString():"");
			try{
				item.setImage(pattern01.helpers.ImageHelper.getImage(actualNode.getAttributes().getNamedItem("image").getNodeValue().toString()));
			}catch(java.lang.NullPointerException ex){}
			item.setData("class_instance",patternInstance_form_panelGrid_panel_text);
			item.setData("parent_reference",this.patternInstance);
			item.setData("type","PATTERNINSTANCE_FORM_PANELGRID_PANEL_TEXT");
			((pattern01.helpers.generated.Panel)item.getParentItem().getData("class_instance")).getCollection_Text().add(patternInstance_form_panelGrid_panel_text);
		}else if(actualNode.getNodeName().equalsIgnoreCase("patternInstance_form_panelGrid_panel_commandButton")){
			pattern01.helpers.generated.CommandButton patternInstance_form_panelGrid_panel_commandButton = new pattern01.helpers.generated.CommandButton();
			patternInstance_form_panelGrid_panel_commandButton.setName(actualNode.getAttributes().getNamedItem("name") != null ? actualNode.getAttributes().getNamedItem("name").getNodeValue().toString():"");
			patternInstance_form_panelGrid_panel_commandButton.setAction(actualNode.getAttributes().getNamedItem("action") != null ? actualNode.getAttributes().getNamedItem("action").getNodeValue().toString():"");
			patternInstance_form_panelGrid_panel_commandButton.setDescription(actualNode.getAttributes().getNamedItem("description") != null ? actualNode.getAttributes().getNamedItem("description").getNodeValue().toString():"");
			try{
				item.setImage(pattern01.helpers.ImageHelper.getImage(actualNode.getAttributes().getNamedItem("image").getNodeValue().toString()));
			}catch(java.lang.NullPointerException ex){}
			item.setData("class_instance",patternInstance_form_panelGrid_panel_commandButton);
			item.setData("parent_reference",this.patternInstance);
			item.setData("type","PATTERNINSTANCE_FORM_PANELGRID_PANEL_COMMANDBUTTON");
			((pattern01.helpers.generated.Panel)item.getParentItem().getData("class_instance")).getCollection_CommandButton().add(patternInstance_form_panelGrid_panel_commandButton);
		}else if(actualNode.getNodeName().equalsIgnoreCase("patternInstance_form_panelGrid_panel_dataTable")){
			pattern01.helpers.generated.DataTable patternInstance_form_panelGrid_panel_dataTable = new pattern01.helpers.generated.DataTable();
			patternInstance_form_panelGrid_panel_dataTable.setName(actualNode.getAttributes().getNamedItem("name") != null ? actualNode.getAttributes().getNamedItem("name").getNodeValue().toString():"");
			patternInstance_form_panelGrid_panel_dataTable.setManagedBean(actualNode.getAttributes().getNamedItem("managedBean") != null ? actualNode.getAttributes().getNamedItem("managedBean").getNodeValue().toString():"");
			patternInstance_form_panelGrid_panel_dataTable.setDescription(actualNode.getAttributes().getNamedItem("description") != null ? actualNode.getAttributes().getNamedItem("description").getNodeValue().toString():"");
			try{
				item.setImage(pattern01.helpers.ImageHelper.getImage(actualNode.getAttributes().getNamedItem("image").getNodeValue().toString()));
			}catch(java.lang.NullPointerException ex){}
			item.setData("class_instance",patternInstance_form_panelGrid_panel_dataTable);
			item.setData("parent_reference",this.patternInstance);
			item.setData("type","PATTERNINSTANCE_FORM_PANELGRID_PANEL_DATATABLE");
			((pattern01.helpers.generated.Panel)item.getParentItem().getData("class_instance")).getCollection_DataTable().add(patternInstance_form_panelGrid_panel_dataTable);
		}else if(actualNode.getNodeName().equalsIgnoreCase("patternInstance_form_panelGrid_panel_dataTable_column")){
			pattern01.helpers.generated.Column patternInstance_form_panelGrid_panel_dataTable_column = new pattern01.helpers.generated.Column();
			patternInstance_form_panelGrid_panel_dataTable_column.setHeaderText(actualNode.getAttributes().getNamedItem("headerText") != null ? actualNode.getAttributes().getNamedItem("headerText").getNodeValue().toString():"");
			try{
				item.setImage(pattern01.helpers.ImageHelper.getImage(actualNode.getAttributes().getNamedItem("image").getNodeValue().toString()));
			}catch(java.lang.NullPointerException ex){}
			item.setData("class_instance",patternInstance_form_panelGrid_panel_dataTable_column);
			item.setData("parent_reference",this.patternInstance);
			item.setData("type","PATTERNINSTANCE_FORM_PANELGRID_PANEL_DATATABLE_COLUMN");
			((pattern01.helpers.generated.DataTable)item.getParentItem().getData("class_instance")).getCollection_Column().add(patternInstance_form_panelGrid_panel_dataTable_column);
		}else if(actualNode.getNodeName().equalsIgnoreCase("patternInstance_form_panelGrid_panel_dataTable_column_text")){
			pattern01.helpers.generated.Text patternInstance_form_panelGrid_panel_dataTable_column_text = new pattern01.helpers.generated.Text();
			patternInstance_form_panelGrid_panel_dataTable_column_text.setName(actualNode.getAttributes().getNamedItem("name") != null ? actualNode.getAttributes().getNamedItem("name").getNodeValue().toString():"");
			patternInstance_form_panelGrid_panel_dataTable_column_text.setValue(actualNode.getAttributes().getNamedItem("value") != null ? actualNode.getAttributes().getNamedItem("value").getNodeValue().toString():"");
			patternInstance_form_panelGrid_panel_dataTable_column_text.setType(actualNode.getAttributes().getNamedItem("type") != null ? pattern01.helpers.generated.TextType.valueOf(actualNode.getAttributes().getNamedItem("type").getNodeValue().toString()):pattern01.helpers.generated.TextType.PLAINTEXT);
			patternInstance_form_panelGrid_panel_dataTable_column_text.setDescription(actualNode.getAttributes().getNamedItem("description") != null ? actualNode.getAttributes().getNamedItem("description").getNodeValue().toString():"");
			try{
				item.setImage(pattern01.helpers.ImageHelper.getImage(actualNode.getAttributes().getNamedItem("image").getNodeValue().toString()));
			}catch(java.lang.NullPointerException ex){}
			item.setData("class_instance",patternInstance_form_panelGrid_panel_dataTable_column_text);
			item.setData("parent_reference",this.patternInstance);
			item.setData("type","PATTERNINSTANCE_FORM_PANELGRID_PANEL_DATATABLE_COLUMN_TEXT");
			((pattern01.helpers.generated.Column)item.getParentItem().getData("class_instance")).getCollection_Text().add(patternInstance_form_panelGrid_panel_dataTable_column_text);
		}else if(actualNode.getNodeName().equalsIgnoreCase("patternInstance_form_panelGrid_property")){
			pattern01.helpers.generated.Property patternInstance_form_panelGrid_property = new pattern01.helpers.generated.Property();
			patternInstance_form_panelGrid_property.setName(actualNode.getAttributes().getNamedItem("name") != null ? actualNode.getAttributes().getNamedItem("name").getNodeValue().toString():"");
			patternInstance_form_panelGrid_property.setDescription(actualNode.getAttributes().getNamedItem("description") != null ? actualNode.getAttributes().getNamedItem("description").getNodeValue().toString():"");
			patternInstance_form_panelGrid_property.setType(actualNode.getAttributes().getNamedItem("type") != null ? actualNode.getAttributes().getNamedItem("type").getNodeValue().toString():"");
			patternInstance_form_panelGrid_property.setDefaultValue(actualNode.getAttributes().getNamedItem("defaultValue") != null ? actualNode.getAttributes().getNamedItem("defaultValue").getNodeValue().toString():"");
			try{
				item.setImage(pattern01.helpers.ImageHelper.getImage(actualNode.getAttributes().getNamedItem("image").getNodeValue().toString()));
			}catch(java.lang.NullPointerException ex){}
			item.setData("class_instance",patternInstance_form_panelGrid_property);
			item.setData("parent_reference",this.patternInstance);
			item.setData("type","PATTERNINSTANCE_FORM_PANELGRID_PROPERTY");
			((pattern01.helpers.generated.PanelGrid)item.getParentItem().getData("class_instance")).getCollection_Property().add(patternInstance_form_panelGrid_property);
		}else if(actualNode.getNodeName().equalsIgnoreCase("patternInstance_form_panelGrid_text")){
			pattern01.helpers.generated.Text patternInstance_form_panelGrid_text = new pattern01.helpers.generated.Text();
			patternInstance_form_panelGrid_text.setName(actualNode.getAttributes().getNamedItem("name") != null ? actualNode.getAttributes().getNamedItem("name").getNodeValue().toString():"");
			patternInstance_form_panelGrid_text.setValue(actualNode.getAttributes().getNamedItem("value") != null ? actualNode.getAttributes().getNamedItem("value").getNodeValue().toString():"");
			patternInstance_form_panelGrid_text.setType(actualNode.getAttributes().getNamedItem("type") != null ? pattern01.helpers.generated.TextType.valueOf(actualNode.getAttributes().getNamedItem("type").getNodeValue().toString()):pattern01.helpers.generated.TextType.PLAINTEXT);
			patternInstance_form_panelGrid_text.setDescription(actualNode.getAttributes().getNamedItem("description") != null ? actualNode.getAttributes().getNamedItem("description").getNodeValue().toString():"");
			try{
				item.setImage(pattern01.helpers.ImageHelper.getImage(actualNode.getAttributes().getNamedItem("image").getNodeValue().toString()));
			}catch(java.lang.NullPointerException ex){}
			item.setData("class_instance",patternInstance_form_panelGrid_text);
			item.setData("parent_reference",this.patternInstance);
			item.setData("type","PATTERNINSTANCE_FORM_PANELGRID_TEXT");
			((pattern01.helpers.generated.PanelGrid)item.getParentItem().getData("class_instance")).getCollection_Text().add(patternInstance_form_panelGrid_text);
		}else if(actualNode.getNodeName().equalsIgnoreCase("patternInstance_form_panelGrid_commandButton")){
			pattern01.helpers.generated.CommandButton patternInstance_form_panelGrid_commandButton = new pattern01.helpers.generated.CommandButton();
			patternInstance_form_panelGrid_commandButton.setName(actualNode.getAttributes().getNamedItem("name") != null ? actualNode.getAttributes().getNamedItem("name").getNodeValue().toString():"");
			patternInstance_form_panelGrid_commandButton.setAction(actualNode.getAttributes().getNamedItem("action") != null ? actualNode.getAttributes().getNamedItem("action").getNodeValue().toString():"");
			patternInstance_form_panelGrid_commandButton.setDescription(actualNode.getAttributes().getNamedItem("description") != null ? actualNode.getAttributes().getNamedItem("description").getNodeValue().toString():"");
			try{
				item.setImage(pattern01.helpers.ImageHelper.getImage(actualNode.getAttributes().getNamedItem("image").getNodeValue().toString()));
			}catch(java.lang.NullPointerException ex){}
			item.setData("class_instance",patternInstance_form_panelGrid_commandButton);
			item.setData("parent_reference",this.patternInstance);
			item.setData("type","PATTERNINSTANCE_FORM_PANELGRID_COMMANDBUTTON");
			((pattern01.helpers.generated.PanelGrid)item.getParentItem().getData("class_instance")).getCollection_CommandButton().add(patternInstance_form_panelGrid_commandButton);
		}else if(actualNode.getNodeName().equalsIgnoreCase("patternInstance_form_panelGrid_dataTable")){
			pattern01.helpers.generated.DataTable patternInstance_form_panelGrid_dataTable = new pattern01.helpers.generated.DataTable();
			patternInstance_form_panelGrid_dataTable.setName(actualNode.getAttributes().getNamedItem("name") != null ? actualNode.getAttributes().getNamedItem("name").getNodeValue().toString():"");
			patternInstance_form_panelGrid_dataTable.setManagedBean(actualNode.getAttributes().getNamedItem("managedBean") != null ? actualNode.getAttributes().getNamedItem("managedBean").getNodeValue().toString():"");
			patternInstance_form_panelGrid_dataTable.setDescription(actualNode.getAttributes().getNamedItem("description") != null ? actualNode.getAttributes().getNamedItem("description").getNodeValue().toString():"");
			try{
				item.setImage(pattern01.helpers.ImageHelper.getImage(actualNode.getAttributes().getNamedItem("image").getNodeValue().toString()));
			}catch(java.lang.NullPointerException ex){}
			item.setData("class_instance",patternInstance_form_panelGrid_dataTable);
			item.setData("parent_reference",this.patternInstance);
			item.setData("type","PATTERNINSTANCE_FORM_PANELGRID_DATATABLE");
			((pattern01.helpers.generated.PanelGrid)item.getParentItem().getData("class_instance")).getCollection_DataTable().add(patternInstance_form_panelGrid_dataTable);
		}else if(actualNode.getNodeName().equalsIgnoreCase("patternInstance_form_panelGrid_dataTable_column")){
			pattern01.helpers.generated.Column patternInstance_form_panelGrid_dataTable_column = new pattern01.helpers.generated.Column();
			patternInstance_form_panelGrid_dataTable_column.setHeaderText(actualNode.getAttributes().getNamedItem("headerText") != null ? actualNode.getAttributes().getNamedItem("headerText").getNodeValue().toString():"");
			try{
				item.setImage(pattern01.helpers.ImageHelper.getImage(actualNode.getAttributes().getNamedItem("image").getNodeValue().toString()));
			}catch(java.lang.NullPointerException ex){}
			item.setData("class_instance",patternInstance_form_panelGrid_dataTable_column);
			item.setData("parent_reference",this.patternInstance);
			item.setData("type","PATTERNINSTANCE_FORM_PANELGRID_DATATABLE_COLUMN");
			((pattern01.helpers.generated.DataTable)item.getParentItem().getData("class_instance")).getCollection_Column().add(patternInstance_form_panelGrid_dataTable_column);
		}else if(actualNode.getNodeName().equalsIgnoreCase("patternInstance_form_panelGrid_dataTable_column_text")){
			pattern01.helpers.generated.Text patternInstance_form_panelGrid_dataTable_column_text = new pattern01.helpers.generated.Text();
			patternInstance_form_panelGrid_dataTable_column_text.setName(actualNode.getAttributes().getNamedItem("name") != null ? actualNode.getAttributes().getNamedItem("name").getNodeValue().toString():"");
			patternInstance_form_panelGrid_dataTable_column_text.setValue(actualNode.getAttributes().getNamedItem("value") != null ? actualNode.getAttributes().getNamedItem("value").getNodeValue().toString():"");
			patternInstance_form_panelGrid_dataTable_column_text.setType(actualNode.getAttributes().getNamedItem("type") != null ? pattern01.helpers.generated.TextType.valueOf(actualNode.getAttributes().getNamedItem("type").getNodeValue().toString()):pattern01.helpers.generated.TextType.PLAINTEXT);
			patternInstance_form_panelGrid_dataTable_column_text.setDescription(actualNode.getAttributes().getNamedItem("description") != null ? actualNode.getAttributes().getNamedItem("description").getNodeValue().toString():"");
			try{
				item.setImage(pattern01.helpers.ImageHelper.getImage(actualNode.getAttributes().getNamedItem("image").getNodeValue().toString()));
			}catch(java.lang.NullPointerException ex){}
			item.setData("class_instance",patternInstance_form_panelGrid_dataTable_column_text);
			item.setData("parent_reference",this.patternInstance);
			item.setData("type","PATTERNINSTANCE_FORM_PANELGRID_DATATABLE_COLUMN_TEXT");
			((pattern01.helpers.generated.Column)item.getParentItem().getData("class_instance")).getCollection_Text().add(patternInstance_form_panelGrid_dataTable_column_text);
		}else if(actualNode.getNodeName().equalsIgnoreCase("patternInstance_form_panel")){
			pattern01.helpers.generated.Panel patternInstance_form_panel = new pattern01.helpers.generated.Panel();
			try{
				item.setImage(pattern01.helpers.ImageHelper.getImage(actualNode.getAttributes().getNamedItem("image").getNodeValue().toString()));
			}catch(java.lang.NullPointerException ex){}
			item.setData("class_instance",patternInstance_form_panel);
			item.setData("parent_reference",this.patternInstance);
			item.setData("type","PATTERNINSTANCE_FORM_PANEL");
			((pattern01.helpers.generated.Form)item.getParentItem().getData("class_instance")).getCollection_Panel().add(patternInstance_form_panel);
		}else if(actualNode.getNodeName().equalsIgnoreCase("patternInstance_form_panel_property")){
			pattern01.helpers.generated.Property patternInstance_form_panel_property = new pattern01.helpers.generated.Property();
			patternInstance_form_panel_property.setName(actualNode.getAttributes().getNamedItem("name") != null ? actualNode.getAttributes().getNamedItem("name").getNodeValue().toString():"");
			patternInstance_form_panel_property.setDescription(actualNode.getAttributes().getNamedItem("description") != null ? actualNode.getAttributes().getNamedItem("description").getNodeValue().toString():"");
			patternInstance_form_panel_property.setType(actualNode.getAttributes().getNamedItem("type") != null ? actualNode.getAttributes().getNamedItem("type").getNodeValue().toString():"");
			patternInstance_form_panel_property.setDefaultValue(actualNode.getAttributes().getNamedItem("defaultValue") != null ? actualNode.getAttributes().getNamedItem("defaultValue").getNodeValue().toString():"");
			try{
				item.setImage(pattern01.helpers.ImageHelper.getImage(actualNode.getAttributes().getNamedItem("image").getNodeValue().toString()));
			}catch(java.lang.NullPointerException ex){}
			item.setData("class_instance",patternInstance_form_panel_property);
			item.setData("parent_reference",this.patternInstance);
			item.setData("type","PATTERNINSTANCE_FORM_PANEL_PROPERTY");
			((pattern01.helpers.generated.Panel)item.getParentItem().getData("class_instance")).getCollection_Property().add(patternInstance_form_panel_property);
		}else if(actualNode.getNodeName().equalsIgnoreCase("patternInstance_form_panel_text")){
			pattern01.helpers.generated.Text patternInstance_form_panel_text = new pattern01.helpers.generated.Text();
			patternInstance_form_panel_text.setName(actualNode.getAttributes().getNamedItem("name") != null ? actualNode.getAttributes().getNamedItem("name").getNodeValue().toString():"");
			patternInstance_form_panel_text.setValue(actualNode.getAttributes().getNamedItem("value") != null ? actualNode.getAttributes().getNamedItem("value").getNodeValue().toString():"");
			patternInstance_form_panel_text.setType(actualNode.getAttributes().getNamedItem("type") != null ? pattern01.helpers.generated.TextType.valueOf(actualNode.getAttributes().getNamedItem("type").getNodeValue().toString()):pattern01.helpers.generated.TextType.PLAINTEXT);
			patternInstance_form_panel_text.setDescription(actualNode.getAttributes().getNamedItem("description") != null ? actualNode.getAttributes().getNamedItem("description").getNodeValue().toString():"");
			try{
				item.setImage(pattern01.helpers.ImageHelper.getImage(actualNode.getAttributes().getNamedItem("image").getNodeValue().toString()));
			}catch(java.lang.NullPointerException ex){}
			item.setData("class_instance",patternInstance_form_panel_text);
			item.setData("parent_reference",this.patternInstance);
			item.setData("type","PATTERNINSTANCE_FORM_PANEL_TEXT");
			((pattern01.helpers.generated.Panel)item.getParentItem().getData("class_instance")).getCollection_Text().add(patternInstance_form_panel_text);
		}else if(actualNode.getNodeName().equalsIgnoreCase("patternInstance_form_panel_commandButton")){
			pattern01.helpers.generated.CommandButton patternInstance_form_panel_commandButton = new pattern01.helpers.generated.CommandButton();
			patternInstance_form_panel_commandButton.setName(actualNode.getAttributes().getNamedItem("name") != null ? actualNode.getAttributes().getNamedItem("name").getNodeValue().toString():"");
			patternInstance_form_panel_commandButton.setAction(actualNode.getAttributes().getNamedItem("action") != null ? actualNode.getAttributes().getNamedItem("action").getNodeValue().toString():"");
			patternInstance_form_panel_commandButton.setDescription(actualNode.getAttributes().getNamedItem("description") != null ? actualNode.getAttributes().getNamedItem("description").getNodeValue().toString():"");
			try{
				item.setImage(pattern01.helpers.ImageHelper.getImage(actualNode.getAttributes().getNamedItem("image").getNodeValue().toString()));
			}catch(java.lang.NullPointerException ex){}
			item.setData("class_instance",patternInstance_form_panel_commandButton);
			item.setData("parent_reference",this.patternInstance);
			item.setData("type","PATTERNINSTANCE_FORM_PANEL_COMMANDBUTTON");
			((pattern01.helpers.generated.Panel)item.getParentItem().getData("class_instance")).getCollection_CommandButton().add(patternInstance_form_panel_commandButton);
		}else if(actualNode.getNodeName().equalsIgnoreCase("patternInstance_form_panel_dataTable")){
			pattern01.helpers.generated.DataTable patternInstance_form_panel_dataTable = new pattern01.helpers.generated.DataTable();
			patternInstance_form_panel_dataTable.setName(actualNode.getAttributes().getNamedItem("name") != null ? actualNode.getAttributes().getNamedItem("name").getNodeValue().toString():"");
			patternInstance_form_panel_dataTable.setManagedBean(actualNode.getAttributes().getNamedItem("managedBean") != null ? actualNode.getAttributes().getNamedItem("managedBean").getNodeValue().toString():"");
			patternInstance_form_panel_dataTable.setDescription(actualNode.getAttributes().getNamedItem("description") != null ? actualNode.getAttributes().getNamedItem("description").getNodeValue().toString():"");
			try{
				item.setImage(pattern01.helpers.ImageHelper.getImage(actualNode.getAttributes().getNamedItem("image").getNodeValue().toString()));
			}catch(java.lang.NullPointerException ex){}
			item.setData("class_instance",patternInstance_form_panel_dataTable);
			item.setData("parent_reference",this.patternInstance);
			item.setData("type","PATTERNINSTANCE_FORM_PANEL_DATATABLE");
			((pattern01.helpers.generated.Panel)item.getParentItem().getData("class_instance")).getCollection_DataTable().add(patternInstance_form_panel_dataTable);
		}else if(actualNode.getNodeName().equalsIgnoreCase("patternInstance_form_panel_dataTable_column")){
			pattern01.helpers.generated.Column patternInstance_form_panel_dataTable_column = new pattern01.helpers.generated.Column();
			patternInstance_form_panel_dataTable_column.setHeaderText(actualNode.getAttributes().getNamedItem("headerText") != null ? actualNode.getAttributes().getNamedItem("headerText").getNodeValue().toString():"");
			try{
				item.setImage(pattern01.helpers.ImageHelper.getImage(actualNode.getAttributes().getNamedItem("image").getNodeValue().toString()));
			}catch(java.lang.NullPointerException ex){}
			item.setData("class_instance",patternInstance_form_panel_dataTable_column);
			item.setData("parent_reference",this.patternInstance);
			item.setData("type","PATTERNINSTANCE_FORM_PANEL_DATATABLE_COLUMN");
			((pattern01.helpers.generated.DataTable)item.getParentItem().getData("class_instance")).getCollection_Column().add(patternInstance_form_panel_dataTable_column);
		}else if(actualNode.getNodeName().equalsIgnoreCase("patternInstance_form_panel_dataTable_column_text")){
			pattern01.helpers.generated.Text patternInstance_form_panel_dataTable_column_text = new pattern01.helpers.generated.Text();
			patternInstance_form_panel_dataTable_column_text.setName(actualNode.getAttributes().getNamedItem("name") != null ? actualNode.getAttributes().getNamedItem("name").getNodeValue().toString():"");
			patternInstance_form_panel_dataTable_column_text.setValue(actualNode.getAttributes().getNamedItem("value") != null ? actualNode.getAttributes().getNamedItem("value").getNodeValue().toString():"");
			patternInstance_form_panel_dataTable_column_text.setType(actualNode.getAttributes().getNamedItem("type") != null ? pattern01.helpers.generated.TextType.valueOf(actualNode.getAttributes().getNamedItem("type").getNodeValue().toString()):pattern01.helpers.generated.TextType.PLAINTEXT);
			patternInstance_form_panel_dataTable_column_text.setDescription(actualNode.getAttributes().getNamedItem("description") != null ? actualNode.getAttributes().getNamedItem("description").getNodeValue().toString():"");
			try{
				item.setImage(pattern01.helpers.ImageHelper.getImage(actualNode.getAttributes().getNamedItem("image").getNodeValue().toString()));
			}catch(java.lang.NullPointerException ex){}
			item.setData("class_instance",patternInstance_form_panel_dataTable_column_text);
			item.setData("parent_reference",this.patternInstance);
			item.setData("type","PATTERNINSTANCE_FORM_PANEL_DATATABLE_COLUMN_TEXT");
			((pattern01.helpers.generated.Column)item.getParentItem().getData("class_instance")).getCollection_Text().add(patternInstance_form_panel_dataTable_column_text);
		}else if(actualNode.getNodeName().equalsIgnoreCase("patternInstance_form_property")){
			pattern01.helpers.generated.Property patternInstance_form_property = new pattern01.helpers.generated.Property();
			patternInstance_form_property.setName(actualNode.getAttributes().getNamedItem("name") != null ? actualNode.getAttributes().getNamedItem("name").getNodeValue().toString():"");
			patternInstance_form_property.setDescription(actualNode.getAttributes().getNamedItem("description") != null ? actualNode.getAttributes().getNamedItem("description").getNodeValue().toString():"");
			patternInstance_form_property.setType(actualNode.getAttributes().getNamedItem("type") != null ? actualNode.getAttributes().getNamedItem("type").getNodeValue().toString():"");
			patternInstance_form_property.setDefaultValue(actualNode.getAttributes().getNamedItem("defaultValue") != null ? actualNode.getAttributes().getNamedItem("defaultValue").getNodeValue().toString():"");
			try{
				item.setImage(pattern01.helpers.ImageHelper.getImage(actualNode.getAttributes().getNamedItem("image").getNodeValue().toString()));
			}catch(java.lang.NullPointerException ex){}
			item.setData("class_instance",patternInstance_form_property);
			item.setData("parent_reference",this.patternInstance);
			item.setData("type","PATTERNINSTANCE_FORM_PROPERTY");
			((pattern01.helpers.generated.Form)item.getParentItem().getData("class_instance")).getCollection_Property().add(patternInstance_form_property);
		}else if(actualNode.getNodeName().equalsIgnoreCase("patternInstance_form_text")){
			pattern01.helpers.generated.Text patternInstance_form_text = new pattern01.helpers.generated.Text();
			patternInstance_form_text.setName(actualNode.getAttributes().getNamedItem("name") != null ? actualNode.getAttributes().getNamedItem("name").getNodeValue().toString():"");
			patternInstance_form_text.setValue(actualNode.getAttributes().getNamedItem("value") != null ? actualNode.getAttributes().getNamedItem("value").getNodeValue().toString():"");
			patternInstance_form_text.setType(actualNode.getAttributes().getNamedItem("type") != null ? pattern01.helpers.generated.TextType.valueOf(actualNode.getAttributes().getNamedItem("type").getNodeValue().toString()):pattern01.helpers.generated.TextType.PLAINTEXT);
			patternInstance_form_text.setDescription(actualNode.getAttributes().getNamedItem("description") != null ? actualNode.getAttributes().getNamedItem("description").getNodeValue().toString():"");
			try{
				item.setImage(pattern01.helpers.ImageHelper.getImage(actualNode.getAttributes().getNamedItem("image").getNodeValue().toString()));
			}catch(java.lang.NullPointerException ex){}
			item.setData("class_instance",patternInstance_form_text);
			item.setData("parent_reference",this.patternInstance);
			item.setData("type","PATTERNINSTANCE_FORM_TEXT");
			((pattern01.helpers.generated.Form)item.getParentItem().getData("class_instance")).getCollection_Text().add(patternInstance_form_text);
		}else if(actualNode.getNodeName().equalsIgnoreCase("patternInstance_form_messages")){
			pattern01.helpers.generated.Messages patternInstance_form_messages = new pattern01.helpers.generated.Messages();
			try{
				item.setImage(pattern01.helpers.ImageHelper.getImage(actualNode.getAttributes().getNamedItem("image").getNodeValue().toString()));
			}catch(java.lang.NullPointerException ex){}
			item.setData("class_instance",patternInstance_form_messages);
			item.setData("parent_reference",this.patternInstance);
			item.setData("type","PATTERNINSTANCE_FORM_MESSAGES");
			((pattern01.helpers.generated.Form)item.getParentItem().getData("class_instance")).getCollection_Messages().add(patternInstance_form_messages);
		}else if(actualNode.getNodeName().equalsIgnoreCase("patternInstance_form_dataTable")){
			pattern01.helpers.generated.DataTable patternInstance_form_dataTable = new pattern01.helpers.generated.DataTable();
			patternInstance_form_dataTable.setName(actualNode.getAttributes().getNamedItem("name") != null ? actualNode.getAttributes().getNamedItem("name").getNodeValue().toString():"");
			patternInstance_form_dataTable.setManagedBean(actualNode.getAttributes().getNamedItem("managedBean") != null ? actualNode.getAttributes().getNamedItem("managedBean").getNodeValue().toString():"");
			patternInstance_form_dataTable.setDescription(actualNode.getAttributes().getNamedItem("description") != null ? actualNode.getAttributes().getNamedItem("description").getNodeValue().toString():"");
			try{
				item.setImage(pattern01.helpers.ImageHelper.getImage(actualNode.getAttributes().getNamedItem("image").getNodeValue().toString()));
			}catch(java.lang.NullPointerException ex){}
			item.setData("class_instance",patternInstance_form_dataTable);
			item.setData("parent_reference",this.patternInstance);
			item.setData("type","PATTERNINSTANCE_FORM_DATATABLE");
			((pattern01.helpers.generated.Form)item.getParentItem().getData("class_instance")).getCollection_DataTable().add(patternInstance_form_dataTable);
		}else if(actualNode.getNodeName().equalsIgnoreCase("patternInstance_form_dataTable_column")){
			pattern01.helpers.generated.Column patternInstance_form_dataTable_column = new pattern01.helpers.generated.Column();
			patternInstance_form_dataTable_column.setHeaderText(actualNode.getAttributes().getNamedItem("headerText") != null ? actualNode.getAttributes().getNamedItem("headerText").getNodeValue().toString():"");
			try{
				item.setImage(pattern01.helpers.ImageHelper.getImage(actualNode.getAttributes().getNamedItem("image").getNodeValue().toString()));
			}catch(java.lang.NullPointerException ex){}
			item.setData("class_instance",patternInstance_form_dataTable_column);
			item.setData("parent_reference",this.patternInstance);
			item.setData("type","PATTERNINSTANCE_FORM_DATATABLE_COLUMN");
			((pattern01.helpers.generated.DataTable)item.getParentItem().getData("class_instance")).getCollection_Column().add(patternInstance_form_dataTable_column);
		}else if(actualNode.getNodeName().equalsIgnoreCase("patternInstance_form_dataTable_column_text")){
			pattern01.helpers.generated.Text patternInstance_form_dataTable_column_text = new pattern01.helpers.generated.Text();
			patternInstance_form_dataTable_column_text.setName(actualNode.getAttributes().getNamedItem("name") != null ? actualNode.getAttributes().getNamedItem("name").getNodeValue().toString():"");
			patternInstance_form_dataTable_column_text.setValue(actualNode.getAttributes().getNamedItem("value") != null ? actualNode.getAttributes().getNamedItem("value").getNodeValue().toString():"");
			patternInstance_form_dataTable_column_text.setType(actualNode.getAttributes().getNamedItem("type") != null ? pattern01.helpers.generated.TextType.valueOf(actualNode.getAttributes().getNamedItem("type").getNodeValue().toString()):pattern01.helpers.generated.TextType.PLAINTEXT);
			patternInstance_form_dataTable_column_text.setDescription(actualNode.getAttributes().getNamedItem("description") != null ? actualNode.getAttributes().getNamedItem("description").getNodeValue().toString():"");
			try{
				item.setImage(pattern01.helpers.ImageHelper.getImage(actualNode.getAttributes().getNamedItem("image").getNodeValue().toString()));
			}catch(java.lang.NullPointerException ex){}
			item.setData("class_instance",patternInstance_form_dataTable_column_text);
			item.setData("parent_reference",this.patternInstance);
			item.setData("type","PATTERNINSTANCE_FORM_DATATABLE_COLUMN_TEXT");
			((pattern01.helpers.generated.Column)item.getParentItem().getData("class_instance")).getCollection_Text().add(patternInstance_form_dataTable_column_text);
		}else if(actualNode.getNodeName().equalsIgnoreCase("patternInstance_form_commandButton")){
			pattern01.helpers.generated.CommandButton patternInstance_form_commandButton = new pattern01.helpers.generated.CommandButton();
			patternInstance_form_commandButton.setName(actualNode.getAttributes().getNamedItem("name") != null ? actualNode.getAttributes().getNamedItem("name").getNodeValue().toString():"");
			patternInstance_form_commandButton.setAction(actualNode.getAttributes().getNamedItem("action") != null ? actualNode.getAttributes().getNamedItem("action").getNodeValue().toString():"");
			patternInstance_form_commandButton.setDescription(actualNode.getAttributes().getNamedItem("description") != null ? actualNode.getAttributes().getNamedItem("description").getNodeValue().toString():"");
			try{
				item.setImage(pattern01.helpers.ImageHelper.getImage(actualNode.getAttributes().getNamedItem("image").getNodeValue().toString()));
			}catch(java.lang.NullPointerException ex){}
			item.setData("class_instance",patternInstance_form_commandButton);
			item.setData("parent_reference",this.patternInstance);
			item.setData("type","PATTERNINSTANCE_FORM_COMMANDBUTTON");
			((pattern01.helpers.generated.Form)item.getParentItem().getData("class_instance")).getCollection_CommandButton().add(patternInstance_form_commandButton);
		}
	}
/* [End] Auto-generated code for pattern instance parser do not remove */

	public TreeItem getInstance(){
		return this.instance;
	}

}

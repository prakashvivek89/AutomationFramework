package com.eperium.testframework.utils.xml;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlValue;

public class BasicXMLElements {

	protected String name,id,value,xmlValue;
	
	public String getName() {
		return name;
	}
	
	@XmlAttribute
	public void setName(String name) {
		this.name = name;
	}

	
	public String getId() {
		return id;
	}
	
	@XmlAttribute
	public void setId(String id) {
		this.id = id;
	}
	
	public String getValue() {
		return value;
	}
	
	@XmlAttribute
	public void setValue(String value) {
		this.value = value;
	}
	public String getXmlValue() {
		return xmlValue;
	}

	@XmlValue
	public void setXmlValue(String xmlValue) {
		this.xmlValue = xmlValue;
	}

	
}

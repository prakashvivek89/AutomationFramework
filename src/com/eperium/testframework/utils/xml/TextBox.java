package com.eperium.testframework.utils.xml;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;

public class TextBox extends BasicXMLElements {
	
	
	 private List<Text> textElements;
	 
		public List<Text> getTextElements() {
			return textElements;
		}
		
		 @XmlElement(name="text")
		public void setTextElements(List<Text> textElements) {
			this.textElements = textElements;
		}
}

package com.eperium.testframework.utils.xml;

import javax.xml.bind.annotation.XmlAttribute;

public class Selected  extends BasicXMLElements{
	private String index;

	public String getIndex() {
		return index;
	}

	@XmlAttribute
	public void setIndex(String index) {
		this.index = index;
	}

}

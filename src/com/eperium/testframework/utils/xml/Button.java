package com.eperium.testframework.utils.xml;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;


public class Button extends BasicXMLElements{
	
	private List<VerifyText> verifyTexts;  
	

	public List<VerifyText> getVerifyTexts() {
		return verifyTexts;
	}

	@XmlElement(name="verifyText")
	public void setVerifyTexts(List<VerifyText> verifyTexts) {
		this.verifyTexts = verifyTexts;
	}
	

}

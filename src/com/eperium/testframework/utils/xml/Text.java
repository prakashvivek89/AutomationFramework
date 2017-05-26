package com.eperium.testframework.utils.xml;

import javax.xml.bind.annotation.XmlAttribute;

import com.unitedinternet.portal.selenium.utils.logging.LoggingUtils;

public class Text extends BasicXMLElements {

	boolean email,random;

	public boolean getEmail() {
		return email;
	}

	@XmlAttribute
	public void setEmail(boolean email) {
		this.email = email;
	}
	
	public boolean getRandom() {
		return random;
	}

	@XmlAttribute
	public void setRandom(boolean random) {
		this.random = random;
	}
	
	public String getXmlValue() {
		if(this.email && this.random)
		{
			return xmlValue.split("@")[0]+"_"+LoggingUtils.timeStampForFileName().replaceAll("-", "")+"@"+xmlValue.split("@")[1];
		}
		else if(this.random)
		{
			 String text = "";
			 String possible = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
			 for( int i=0; i < 5; i++ )
			 {
				   text += possible.charAt((int)Math.floor(Math.random() * possible.length()));
			 }
			return xmlValue+text;
		}
		return xmlValue;
	}

}

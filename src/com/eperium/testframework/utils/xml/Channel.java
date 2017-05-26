package com.eperium.testframework.utils.xml;
 
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

 
@XmlRootElement
public class Channel extends BasicXMLElements {
 
	private List <Module> modules;
	
	public List <Module> getModules() {
		return modules;
	}
	
	@XmlElementWrapper(name="modules")
	@XmlElement(name="module") 
	public void setModules(List <Module> modules) {
		this.modules = modules;
	}
	
	

	
	 
}
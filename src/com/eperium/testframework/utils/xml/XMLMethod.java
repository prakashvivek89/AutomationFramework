package com.eperium.testframework.utils.xml;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
//---------Bunzl
@XmlRootElement
public class XMLMethod extends BasicXMLElements {

	private Integer timeOut = 0;
	private List<Click> click;
	private List<MouseOver> mouseOver;
	private List<VerifyText> verifyTexts;
	private List<TextBox> textBoxs;
	private List<Dropdown> dropdowns;
	private List<Elements> elements;

	public Integer getTimeOut() {
		return timeOut;
	}

	@XmlElement(name = "timeout")
	public void setTimeOut(Integer timeOut) {
		this.timeOut = timeOut;
	}

	public List<VerifyText> getVerifyTexts() {
		return verifyTexts;
	}

	@XmlElement(name = "verifyText")
	public void setVerifyTexts(List<VerifyText> verifyTexts) {
		this.verifyTexts = verifyTexts;
	}

	public List<Click> getClick() {
		return click;
	}

	@XmlElement(name = "Elements")
	public void setElements(List<Elements> elements) {
		this.elements = elements;
	}
	
	public List<Elements> getElements() {
		return elements;
	}

	@XmlElement(name = "click")
	public void setClick(List<Click> click) {
		this.click = click;
	}

	public List<MouseOver> getMouseOver() {
		return mouseOver;
	}

	@XmlElement(name = "mouseover")
	public void setMouseOver(List<MouseOver> mouseOver) {
		this.mouseOver = mouseOver;
	}

	public List<TextBox> getTextBoxs() {
		return textBoxs;
	}

	@XmlElement(name = "textbox")
	public void setTextBoxs(List<TextBox> textBoxs) {
		this.textBoxs = textBoxs;
	}

	public List<Dropdown> getDropdowns() {
		return dropdowns;
	}

	@XmlElement(name = "dropdown")
	public void setDropdowns(List<Dropdown> dropdowns) {
		this.dropdowns = dropdowns;
	}

}

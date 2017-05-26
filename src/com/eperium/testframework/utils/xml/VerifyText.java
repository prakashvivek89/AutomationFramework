package com.eperium.testframework.utils.xml;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;

public class VerifyText extends BasicXMLElements {

	private List<LoopMsg> loopMessage;
	private List<Mandatory> mandatory;
	private List<NonMandatory> nonMandatory;
	private List<Never> never;
	private List<VisibleTextElement> visibleTextElement;
	private List<JavascriptElement> javascriptElement;

	public List<LoopMsg> getLoopMessage() {
		return loopMessage;
	}

	@XmlElement(name = "loopMsg")
	public void setLoopMessage(List<LoopMsg> loopMessage) {
		this.loopMessage = loopMessage;
	}

	public List<Mandatory> getMandatory() {
		return mandatory;
	}

	@XmlElement(name = "Mandatory")
	public void setMandatory(List<Mandatory> mandatory) {
		this.mandatory = mandatory;
	}

	public List<NonMandatory> getNonMandatory() {
		return nonMandatory;
	}

	@XmlElement(name = "nonMandatory")
	public void setNonMandatory(List<NonMandatory> nonMandatory) {
		this.nonMandatory = nonMandatory;
	}

	public List<Never> getNever() {
		return never;
	}

	@XmlElement(name = "never")
	public void setNever(List<Never> never) {
		this.never = never;
	}

	public List<VisibleTextElement> getVisibleTextElement() {
		return visibleTextElement;
	}

	@XmlElement(name = "visibleTextElement")
	public void setVisibleTextElement(List<VisibleTextElement> visibleTextElement) {
		this.visibleTextElement = visibleTextElement;
	}

	public List<JavascriptElement> getJavascriptElement() {
		return javascriptElement;
	}

	@XmlElement(name = "javascriptElement")
	public void setJavascriptElement(List<JavascriptElement> javascriptElement) {
		this.javascriptElement = javascriptElement;
	}
}

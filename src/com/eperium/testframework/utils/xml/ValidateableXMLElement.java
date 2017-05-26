package com.eperium.testframework.utils.xml;

import org.openqa.selenium.WebDriver;

public abstract class ValidateableXMLElement extends BasicXMLElements {

	/**
	 * Method that determines if this element is valid within specified timeout.
	 * Each concrete implementation must specify its validation logic.
	 * 
	 * @param driver
	 * @param timeout
	 */
	public abstract void validate(WebDriver driver, int timeout);

	/**
	 * Method that determines if this element is valid within specified timeout
	 * after click. Each concrete implementation must specify its validation
	 * logic.
	 * 
	 * @param driver
	 * @param timeout
	 */
	public abstract void validateAfterClick(WebDriver driver, int timeout);

	/**
	 * Utility method to get processed element value
	 * 
	 * @return String
	 */
	protected String getCheckedValue() {
		String value = getValue();
		if (value == null) {
			value = getXmlValue();
		}
		if (value == null) {
			// fallback value
			value = "";
		} else {
			value = value.replace("&", "&amp;");
		}
		return value;
	}

	public String toString() {
		String id = getId();
		String val = getCheckedValue();
		return (id != null ? id : "") + "=" + val;
	}
}

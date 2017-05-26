package com.eperium.testframework.utils.xml;

import org.openqa.selenium.WebDriver;

public class NonMandatory extends ValidateableXMLElement {
	public static final String NODE_NAME = "nonMandatory";

	@Override
	public void validate(WebDriver driver, int timeout) {
		// TODO: What is the use of this tag? Nothing is logged whether element
		// match fails or passes.
		String value = getCheckedValue();
		driver.getPageSource().toLowerCase().contains(value.toLowerCase());
	}

	@Override
	public void validateAfterClick(WebDriver driver, int timeout) {
		// currently not different from without click
		validate(driver, timeout);

	}

}

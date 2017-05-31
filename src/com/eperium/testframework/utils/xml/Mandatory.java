package com.eperium.testframework.utils.xml;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.log4testng.Logger;

import com.eperium.testframework.utils.xml.VisibleTextElement;

public class Mandatory extends ValidateableXMLElement {
	private static final Logger log = Logger.getLogger(Mandatory.class);
	public static final String NODE_NAME = "Mandatory";

	@Override
	public void validate(WebDriver driver, int timeout) {
		String id = this.id;
		String value = getCheckedValue();
		if (VisibleTextElement.getWebElement(driver, id) == null) {
			Assert.assertTrue(driver.getPageSource().toLowerCase().contains(value.toLowerCase()),
					"Couldn't find mandatory text after click: " + value);
		} else {
			try {
				Assert.assertEquals(VisibleTextElement.getWebElement(driver, id).getText().trim(), value);
			} catch (Exception e) {
				log.debug("Text '" + value + "' is not displayed ");
			}
		}
	}

	@Override
	public void validateAfterClick(WebDriver driver, int timeout) {
		String id = this.id;
		String value = getCheckedValue();
		if (VisibleTextElement.getWebElement(driver, id) == null) {
			Assert.assertTrue(driver.getPageSource().toLowerCase().contains(value.toLowerCase()),
					"Couldn't find mandatory text after click: " + value);
		} else {
			try {
				Assert.assertEquals(VisibleTextElement.getWebElement(driver, id).getText().trim(), value);
			} catch (Exception e) {
				log.debug("Text '" + value + "' is not displayed ");
			}
		}
	}
}

package com.eperium.testframework.utils.xml;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class Mandatory extends ValidateableXMLElement {
	public static final String NODE_NAME = "Mandatory";

	@Override
	public void validate(WebDriver driver, int timeout) {
		String value = getCheckedValue();
		if (driver.findElements(By.linkText(value)).size() > 0) {
			Assert.assertTrue(driver.findElement(By.linkText(value)).isDisplayed(), "Couldn't find mandatory text: " + value);
		}
	}

	@Override
	public void validateAfterClick(WebDriver driver, int timeout) {
		String value = getCheckedValue();
		if (driver.findElements(By.linkText(value)).size() > 0) {
			Assert.assertTrue(driver.findElement(By.linkText(value)).isDisplayed(), "Couldn't find mandatory text after click: " + value);
		}
	}

}

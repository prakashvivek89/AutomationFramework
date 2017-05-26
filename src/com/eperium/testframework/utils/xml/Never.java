package com.eperium.testframework.utils.xml;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class Never extends ValidateableXMLElement {
	public static final String NODE_NAME = "never";

	@Override
	public void validateAfterClick(WebDriver driver, int timeout) {
		String value = getCheckedValue();
		if (driver.findElements(By.linkText(value)).size() > 0) {
			Assert.assertFalse(driver.findElement(By.linkText(value)).isDisplayed(), "Found unexpected text after click: " + value);
		} else {
			Assert.assertFalse(driver.getPageSource().toLowerCase().contains(value.toLowerCase()), "Found unexpected text after click: " + value);
		}
	}

	@Override
	public void validate(WebDriver driver, int timeout) {
		String value = getCheckedValue();
		if (driver.findElements(By.linkText(value)).size() > 0) {
			Assert.assertFalse(driver.findElement(By.linkText(value)).isDisplayed(), "Found unexpected text: " + value);
		} else {
			Assert.assertFalse(driver.getPageSource().toLowerCase().contains(value.toLowerCase()), "Found unexpected text: " + value);
		}
	}
}

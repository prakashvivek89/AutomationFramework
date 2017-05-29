package com.eperium.testframework.utils.xml;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class VisibleTextElement extends ValidateableXMLElement {
	public static final String NODE_NAME = "visibleTextElement";

	@Override
	public void validate(WebDriver driver, int timeout) {
		try {
			_validate(driver, timeout);
		} 
		catch (TimeoutException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void validateAfterClick(WebDriver driver, int timeout) {
		try {
			_validate(driver, timeout);
		} catch (TimeoutException e) {
		}
	}

	private void _validate(WebDriver driver, int timeout) throws TimeoutException {
		final String value = getCheckedValue();
		new WebDriverWait(driver, timeout).until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return driver.findElement(By.xpath(value)).isDisplayed();
			}
		});
	}
	
	public boolean getLocator(WebDriver driver, String value){
			return driver.findElement(By.xpath(value)).isDisplayed(); 
	}
}

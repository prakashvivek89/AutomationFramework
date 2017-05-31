package com.eperium.testframework.utils.xml;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.log4testng.Logger;

public class VisibleTextElement extends ValidateableXMLElement {
	private static final Logger log = Logger.getLogger(Mandatory.class);
	public static final String NODE_NAME = "visibleTextElement";

	@Override
	public void validate(WebDriver driver, int timeout) {
		try {
			_validate(driver, timeout);
		} catch (TimeoutException e) {
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
		if (getWebElement(driver, value) != null) {
			new WebDriverWait(driver, timeout).until(new ExpectedCondition<Boolean>() {
				public Boolean apply(WebDriver driver) {
					return getWebElement(driver, value).isDisplayed();
				}
			});
		} else {
			log.debug("Locator '" + value + "' is not incorrect");
		}

	}

	public static WebElement getWebElement(WebDriver driver, String value) {
		WebElement ele = null;
		try{
			if (!(driver.findElements(By.xpath(value)).isEmpty())) {
				ele = driver.findElement(By.xpath(value));
			} else if (!(driver.findElements(By.id(value)).isEmpty())) {
				ele = driver.findElement(By.id(value));
			} else if (!(driver.findElements(By.className(value)).isEmpty())) {
				ele = driver.findElement(By.className(value));
			} else if (!(driver.findElements(By.cssSelector(value)).isEmpty())) {
				ele = driver.findElement(By.cssSelector(value));
			} else if (!(driver.findElements(By.name(value)).isEmpty())) {
				ele = driver.findElement(By.name(value));
			} else if (!(driver.findElements(By.linkText(value)).isEmpty())) {
				ele = driver.findElement(By.linkText(value));
			} else if (!(driver.findElements(By.partialLinkText(value)).isEmpty())) {
				ele = driver.findElement(By.partialLinkText(value));
			} else if (!(driver.findElements(By.tagName(value)).isEmpty())) {
				ele = driver.findElement(By.tagName(value));
			}
		}
		
		catch (Exception e) {
			// TODO: handle exception
		}
		return ele;
	}
}

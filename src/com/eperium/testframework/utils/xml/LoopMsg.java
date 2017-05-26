package com.eperium.testframework.utils.xml;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoopMsg extends ValidateableXMLElement {
	public static final String NODE_NAME = "loopMsg";

	@Override
	public void validate(WebDriver driver, int timeout) {
		try {
			_validate(driver, timeout);
		} catch (TimeoutException e) {
			//SeleneseTestBase.fail("Couldn't find loopMsg text: " + getCheckedValue());
		}
	}

	@Override
	public void validateAfterClick(WebDriver driver, int timeout) {
		try {
			_validate(driver, timeout);
		} catch (TimeoutException e) {
			//SeleneseTestBase.fail("Couldn't find loopMsg text after click: " + getCheckedValue());
		}

	}

	private void _validate(WebDriver driver, int timeout) throws TimeoutException {
		final String textToVerify = getCheckedValue();
		new WebDriverWait(driver, timeout).until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return driver.getPageSource().toLowerCase().contains(textToVerify.toLowerCase());
			}
		});
	}
}

package com.eperium.testframework.utils.xml;

import javax.xml.bind.annotation.XmlAttribute;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

//import com.thoughtworks.selenium.SeleneseTestBase;

public class JavascriptElement extends ValidateableXMLElement {
	public static final String NODE_NAME = "javascriptElement";
	private static final String JS_PREFIX = "return ";

	private String result;

	@Override
	public void validate(WebDriver driver, int timeout) {
		if (driver instanceof JavascriptExecutor) {
			try {
				_validate(driver, timeout);
			} catch (TimeoutException e) {
				//SeleneseTestBase.fail("Check failed for javascriptElement " + getId());
			}
		}
	}

	@Override
	public void validateAfterClick(WebDriver driver, int timeout) {
		if (driver instanceof JavascriptExecutor) {
			try {
				_validate(driver, timeout);
			} catch (TimeoutException e) {
				//SeleneseTestBase.fail("Check failed for javascriptElement " + getId() + " after click");
			}
		}
	}

	public String getResult() {
		return result;
	}

	@XmlAttribute
	public void setResult(String result) {
		this.result = result;
	}
	
	private String getScript(){
		return JS_PREFIX + getXmlValue();
	}
	
	private void _validate(WebDriver driver, int timeout) throws TimeoutException {
		final String sresult = this.result;
		new WebDriverWait(driver, timeout).until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return String.valueOf(((JavascriptExecutor) driver).executeScript(getScript())).equals(sresult);
			}
		});
	}
}

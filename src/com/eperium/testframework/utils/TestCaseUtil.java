package com.eperium.testframework.utils;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.eperium.main.jumpstart.Setup;
import com.eperium.testframework.utils.xml.JavascriptElement;
import com.eperium.testframework.utils.xml.LoopMsg;
import com.eperium.testframework.utils.xml.Mandatory;
import com.eperium.testframework.utils.xml.Never;
import com.eperium.testframework.utils.xml.NonMandatory;
import com.eperium.testframework.utils.xml.ValidateableXMLElement;
import com.eperium.testframework.utils.xml.VisibleTextElement;

public class TestCaseUtil extends Setup{
	private static final String[] textNodesToCheck = { VisibleTextElement.NODE_NAME, LoopMsg.NODE_NAME, Mandatory.NODE_NAME, Never.NODE_NAME, NonMandatory.NODE_NAME, JavascriptElement.NODE_NAME };

	@Test
	public void checkAllTextMsgs(String moduleName, String methodName, String clickId, WebDriver driver) {
		List<? extends ValidateableXMLElement> verifyElementList = null;
		for (String node : textNodesToCheck) {
			if (!"noClick".equals(clickId)) {
				verifyElementList = AdvanceXMLUtil.getAllVerifyTextForClick(moduleName, methodName, clickId, node);
				if (verifyElementList != null && !verifyElementList.isEmpty()) {
					for (ValidateableXMLElement element : verifyElementList) {
						element.validateAfterClick(driver, Integer.parseInt(timeoutString));
					}
				}
			} else if("noClick".equals(clickId)) {
				verifyElementList = AdvanceXMLUtil.getAllVerifyTextForMethodByParamType(moduleName, methodName, node);
				if (verifyElementList != null && !verifyElementList.isEmpty()) {
					for (ValidateableXMLElement element : verifyElementList) {
						element.validate(driver, Integer.parseInt(timeoutString));
					}
				}
			}
			
			}			
		}
	

	public void checkAllFormElements(String moduleName, String methodName, WebDriver driver) {
		Map<String, String> mDropDownValue = AdvanceXMLUtil.getAllChildrenForDropDown(moduleName, methodName);
		LinkedHashMap<String, String> mTypeValue = AdvanceXMLUtil.getAllChildrenForTextbox(moduleName, methodName);

		if (!mDropDownValue.isEmpty()) {
			WebElement select = null;
			for (String key : mDropDownValue.keySet()) {
				if (driver.findElements(By.id(key)).size() != 0) {
					select = (new WebDriverWait(driver, Integer.parseInt(timeoutString))).until(ExpectedConditions.presenceOfElementLocated(By.id(key)));
				} else {
					select = (new WebDriverWait(driver, Integer.parseInt(timeoutString))).until(ExpectedConditions.presenceOfElementLocated(By.xpath(key)));
				}

				List<WebElement> options = select.findElements(By.tagName("option"));
				for (WebElement option : options) {
					if (option.getText().contains(mDropDownValue.get(key))) {
						option.click();
						break;
					}
				}
			}

		}
		if (!mTypeValue.isEmpty()) {
			WebElement textField = null;
			for (String key : mTypeValue.keySet()) {
				if (driver.findElements(By.xpath(key)).size() != 0) {
					textField = (new WebDriverWait(driver, Integer.parseInt(timeoutString))).until(ExpectedConditions.presenceOfElementLocated(By.xpath(key)));
					driver.findElement(By.xpath(key)).clear();
					driver.findElement(By.xpath(key)).sendKeys(mTypeValue.get(key));
				} else if (driver.findElements(By.name(key)).size() != 0) {
					textField = (new WebDriverWait(driver, Integer.parseInt(timeoutString))).until(ExpectedConditions.presenceOfElementLocated(By.name(key)));
					textField.clear();
					textField.sendKeys(mTypeValue.get(key));
				} else if (driver.findElements(By.id(key)).size() != 0) {
					textField = (new WebDriverWait(driver, Integer.parseInt(timeoutString))).until(ExpectedConditions.presenceOfElementLocated(By.id(key)));
					textField.clear();
					textField.sendKeys(mTypeValue.get(key));
				}
			}
		}
	}
	
	public void processElements(LinkedHashMap<String, String> mLink, String action, String moduleName, String methodName, WebDriver driver){
		   WebElement webElement = null;
		   for (String key : mLink.keySet()) {
		    webElement = null;
		    if (driver.findElements(By.xpath(mLink.get(key))).size() != 0) {
		     if (driver.findElements(By.xpath(mLink.get(key))).size() >= 1) {
		      for (WebElement element : driver.findElements(By.xpath(mLink.get(key)))) {
		       webElement = element;
		       if (webElement.isDisplayed()) {
		        break;
		       }
		      }
		     } else {
		      webElement = (new WebDriverWait(driver, Integer.parseInt(timeoutString))).until(ExpectedConditions.presenceOfElementLocated(By.xpath(mLink.get(key))));
		     }
		    }
		    else if (driver.findElements(By.id(mLink.get(key))).size() != 0) {
		     webElement = (new WebDriverWait(driver, Integer.parseInt(timeoutString))).until(ExpectedConditions.presenceOfElementLocated(By.id(mLink.get(key))));
		    }

		    else if (driver.findElements(By.linkText(mLink.get(key))).size() != 0) {
		     webElement = (new WebDriverWait(driver, Integer.parseInt(timeoutString))).until(ExpectedConditions.presenceOfElementLocated(By.linkText(mLink.get(key))));
		    }

		    else if (driver.findElements(By.cssSelector(mLink.get(key))).size() != 0) {
		     webElement = (new WebDriverWait(driver, Integer.parseInt(timeoutString))).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector((mLink.get(key)))));
		    }

		    if (webElement != null && action != null) {
		     if (action.equalsIgnoreCase("mouseover")) {
		      Actions mouseOver = new Actions(driver);
		      mouseOver.moveToElement(webElement).build().perform();
		     } else {
		      webElement.click(); // default action
		     }
		    } else {
		     Assert.assertTrue(false, "Could not find element: " + mLink.get(key));
		    }
		    if (browser.contains("iexplore")) { // certificate issue in IE 
		     if (driver.getPageSource().contains("Continue to this website")) {
		      driver.get("javascript:document.getElementById('overridelink').click();");
		     }

		    } else if (browser.contains("safari")) { // certificate issue in
		               // Safari
		     Alert alert = null;
		     try {
		      alert = driver.switchTo().alert();
		     } catch (NoAlertPresentException noAlertPresentException) {
		     }
		     if (alert != null) {
		      alert.accept();
		     }
		    }
		    if (checkInNewWindow) {
		     Iterator<String> ite = driver.getWindowHandles().iterator();
		     while (ite.hasNext()) {
		      String newWindowHandle = ite.next().toString();
		      if (!newWindowHandle.contains(baseWidowHandle)) {
		       driver.switchTo().window(newWindowHandle);
		      }
		     }
		    }
		    checkAllTextMsgs(moduleName, methodName, key, driver);
		   }
		  }
}

package com.eperium.main.jumpstart;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestContext;

public class JumpStartTestSuite extends Setup {
	String getvalue1 = null;

	@Test(description = "Launch Home Page")
	public void launchHomePage() {
		moduleName = "homePageModule";
		methodName = "launchHomePage";
		methodIinitialization(moduleName, methodName);
		String homePageURL = urlProp.getProperty("homePageURL");
		wDriver.navigate().to(homePageURL);
		wDriver.manage().window().maximize();
		Assert.assertEquals(wDriver.getCurrentUrl(), homePageURL);
		testCaseUtil.checkAllTextMsgs(moduleName, methodName, noClick, wDriver);
	}

	@Test(description = "Navigate to Registration page")
	public void navigateToRegistrationPage() {
		moduleName = "userAccountModule";
		methodName = "navigateToRegistrationPage";
		methodIinitialization(moduleName, methodName);
		testCaseUtil.processElements(mLink, "click", moduleName, methodName, wDriver);
//		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(mLink.get("lnkMyAccount")))).click();
//		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(mLink.get("btnCreateAccount")))).click();
//		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(mLink.get("registrationForm"))));
//		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id(mLink.get("emailAddress"))));
	}

	@Test(description = "Navigate to login page")
	public void navigateToLoginPage() {
		moduleName = "userAccountModule";
		methodName = "navigateToLoginPage";
		methodIinitialization(moduleName, methodName);
		testCaseUtil.processElements(mLink, "click", moduleName, methodName, wDriver);
		testCaseUtil.checkAllTextMsgs(moduleName, methodName, noClick, wDriver);
	}

	@Test(description = "Login with valid user")
	public void logInWithValidCredentials() {
		moduleName = "userAccountModule";
		methodName = "logInWithValidCredentials";
		methodIinitialization(moduleName, methodName);
		String emailAddress = dataProp.getProperty("EmailAddress");
		String password = dataProp.getProperty("Password");
		wDriver.findElement(By.xpath(mLink.get("txtboxEmailAddress"))).sendKeys(emailAddress);
		wDriver.findElement(By.xpath(mLink.get("txtboxPassword"))).sendKeys(password);
		wDriver.findElement(By.xpath(mLink.get("btnSignIn"))).click();
	}

	@Test(description = "Register with valid credentials")
	public void registrationValidCredentials() throws InterruptedException {
		moduleName = "userAccountModule";
		methodName = "registrationValidCredentials";
		methodIinitialization(moduleName, methodName);
		Actions moveTo = new Actions(wDriver);
		testCaseUtil.checkAllFormElements(moduleName, methodName, wDriver);
		getvalue1 = wDriver.findElement(By.xpath(mLink.get("emailTextbox"))).getAttribute("value");
		int count = 0;
		if (channel.contains("en-gb")) {
			boolean b1 = wDriver.findElements(By.xpath(mLink.get("verifiedAddress"))).isEmpty();
			boolean b2 = wDriver.findElements(By.className(mLink.get("QASAddresses"))).isEmpty();
			while (b1 || b2) {
				moveTo.moveToElement(wDriver.findElement(By.xpath(mLink.get("checkBoxOptInNewsLetter")))).build()
						.perform();
				js.executeScript("arguments[0].click()",
						wDriver.findElement(By.xpath(mLink.get("checkBoxOptInNewsLetter"))));
				moveTo.moveToElement(wDriver.findElement(By.id(mLink.get("txtboxHouseNo")))).build().perform();
				js.executeScript("arguments[0].click()", wDriver.findElement(By.id(mLink.get("txtboxHouseNo"))));
				moveTo.moveToElement(wDriver.findElement(By.id(mLink.get("txtboxPostalCode")))).build().perform();
				js.executeScript("arguments[0].click()", wDriver.findElement(By.id(mLink.get("txtboxPostalCode"))));
				moveTo.moveToElement(wDriver.findElement(By.xpath(mLink.get("checkBoxOptInNewsLetter")))).build()
						.perform();
				js.executeScript("arguments[0].click()",
						wDriver.findElement(By.xpath(mLink.get("checkBoxOptInNewsLetter"))));
				Thread.sleep(4000);
				if (!(wDriver.findElements(By.className(mLink.get("QASAddresses"))).isEmpty())) {
					js.executeScript("arguments[0].click()",
							wDriver.findElement(By.xpath(mLink.get("selectQASAddress"))));
				}
				count = count + 1;
				if (!(wDriver.findElements(By.className(mLink.get("QASAddresses"))).isEmpty())) {
					break;
				}
				if (count == 5) {
					break;
				}
			}
		}
	}

	@Test(description = "Click create account button")
	public void clickCreateAccount() {
		moduleName = "userAccountModule";
		methodName = "clickCreateAccount";
		methodIinitialization(moduleName, methodName);
		wDriver.findElement(By.xpath(mLink.get("btnCreateAccount"))).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(mLink.get("lnkLogout"))));
		Assert.assertTrue(wDriver.findElement(By.xpath(mLink.get("lnkLogout"))).isEnabled());
	}

	@AfterClass(alwaysRun = true)
	public void setupAfterSuite() {
		wDriver.quit();
	}
}

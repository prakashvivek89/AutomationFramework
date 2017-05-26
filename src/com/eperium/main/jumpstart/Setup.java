package com.eperium.main.jumpstart;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.ITestContext;
import com.eperium.testframework.utils.AdvanceXMLUtil;
import com.eperium.testframework.utils.PropertyLoader;
import com.eperium.testframework.utils.TestCaseUtil;

public class Setup {
	public static WebDriver driver = null;
	public static String browser;
	public static String version;
	public static String testcasename;
	public static String platform;
	public static String environment;
	public static String noClick = "noClick";
	public static Properties urlProp;
	public static Properties localizationProp;
	public static Properties dataProp;
	public static Properties skuProp;
	public static Properties ENVIRONMENT;
	static String channel;
	protected String timeoutString = null;
	String moduleName = null;
	String methodName = null;
	protected String baseWidowHandle = null;
	protected Boolean checkInNewWindow = false;
	TestCaseUtil testCaseUtil = null;
	LinkedHashMap<String, String> mLink = null, mElements = null;;
	LinkedHashMap<String, String> element = null;
	static FirefoxProfile firefoxProfile = null;
	static String site;
	static LoggingPreferences logs;

	public static void getParameter(ITestContext context) throws Exception {
		try {
			platform = context.getCurrentXmlTest().getParameter("platform");
			channel = context.getCurrentXmlTest().getParameter("channel");
			environment = context.getCurrentXmlTest().getParameter("environment");
			AdvanceXMLUtil.loadXml(channel);
			site = context.getCurrentXmlTest().getParameter("site");
			urlProp = PropertyLoader.getUrlPropertiesFile(environment);
			dataProp = PropertyLoader.getDatapropertiesFile(channel);
			skuProp = PropertyLoader.getSKUpropertiesFile(channel);
			browser = context.getCurrentXmlTest().getParameter("selenium.browser");
			version = context.getCurrentXmlTest().getParameter("selenium.browser.version");
			testcasename = context.getCurrentXmlTest().getParameter("testcasename");
			System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "/drivers/geckodriver.exe");
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/drivers/chromedriver.exe");
			System.setProperty("webdriver.ie.driver", System.getProperty("user.dir") + "/drivers/IEDriverServer.exe");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static LoggingPreferences setLoggingPreference() {
		logs = new LoggingPreferences();
		logs.enable(LogType.DRIVER, Level.SEVERE);
		logs.enable(LogType.CLIENT, Level.SEVERE);
		logs.enable(LogType.BROWSER, Level.SEVERE);
		logs.enable(LogType.SERVER, Level.SEVERE);
		logs.enable(LogType.SERVER, Level.SEVERE);
		Logger logger = Logger.getLogger("");
		logger.setLevel(Level.WARNING);
		return logs;
	}

	public static void driverInitialization() throws Exception {
		if (("firefox").equalsIgnoreCase(browser)) {
			startFirefoxDriver();
		}

		else if (("chrome").equalsIgnoreCase(browser)) {
			startChromeDriver();
		}

		else if (("iexplore").equalsIgnoreCase(browser)) {
			startIExploreDriver();
		}

		else if (("safari").equalsIgnoreCase(browser)) {
			startSafariDriver();
		}

		else if (("PhantomJS").equalsIgnoreCase(browser)) {
			startPhantomJSDriver();
		}

		else {
			throw new Exception("Browser " + browser + " or Platform " + platform + " type not supported");
		}
	}

	public static DesiredCapabilities fireFoxDesiredCapebilities() {
		DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
		firefoxProfile = new FirefoxProfile();
		firefoxProfile.setAcceptUntrustedCertificates(true);
		desiredCapabilities = DesiredCapabilities.firefox();
		desiredCapabilities.setCapability("firefox_profile", firefoxProfile);
		desiredCapabilities.setBrowserName(browser);
		desiredCapabilities.setVersion(version);
		desiredCapabilities.setCapability(CapabilityType.LOGGING_PREFS, setLoggingPreference());
		return desiredCapabilities;
	}

	public static DesiredCapabilities chromeDesiredCapebilities() {
		DesiredCapabilities desiredCapabilities = DesiredCapabilities.chrome();
		desiredCapabilities.setBrowserName(browser);
		desiredCapabilities.setVersion(version);
		desiredCapabilities.setCapability("chrome.switches", Arrays.asList("--disable-print-preview"));
		desiredCapabilities.setCapability("chrome.switches", Arrays.asList("--disable-print-preview"));
		desiredCapabilities.setCapability(CapabilityType.LOGGING_PREFS, setLoggingPreference());
		return desiredCapabilities;
	}

	public static DesiredCapabilities internetExplorerDesiredCapebilities() {
		DesiredCapabilities desiredCapabilities = DesiredCapabilities.internetExplorer();
		desiredCapabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,
				true);
		desiredCapabilities.setBrowserName(browser);
		desiredCapabilities.setVersion(version);
		desiredCapabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
		desiredCapabilities.setCapability(CapabilityType.LOGGING_PREFS, setLoggingPreference());
		desiredCapabilities.setCapability("requireWindowFocus", false);
		desiredCapabilities.setCapability(CapabilityType.SUPPORTS_JAVASCRIPT, false);
		desiredCapabilities.setCapability(CapabilityType.TAKES_SCREENSHOT, true);
		desiredCapabilities.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, "dismiss");
		desiredCapabilities.setCapability(CapabilityType.HAS_NATIVE_EVENTS, true);
		desiredCapabilities.setCapability(CapabilityType.SUPPORTS_FINDING_BY_CSS, true);
		desiredCapabilities.setCapability("ignoreZoomSetting", true);
		desiredCapabilities.setPlatform(Platform.WINDOWS);
		desiredCapabilities.setCapability(CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION, true);
		return desiredCapabilities;
	}

	public static DesiredCapabilities phantomJsDesiredCapebilities() {
		DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
		desiredCapabilities.setJavascriptEnabled(true);
		desiredCapabilities.setCapability("takesScreenshot", true);
		if (platform.contains("Windows")) {
			desiredCapabilities.setCapability(PhantomJSDriverService.PHANTOMJS_CLI_ARGS,
					new String[] { "--web-security=no", "--ignore-ssl-errors=yes" });
			desiredCapabilities.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY,
					System.getProperty("user.dir") + "/drivers/phantomjs.exe");

		} else if (platform.contains("Linux")) {
			desiredCapabilities.setCapability("phantomjs.binary.path",
					System.getProperty("user.dir") + "/drivers/phantomjs");
			desiredCapabilities.setCapability("phantomjs.cli.args",
					new String[] { "--web-security=no", "--ignore-ssl-errors=yes" });
		}
		return desiredCapabilities;
	}

	public static DesiredCapabilities safariDesiredCapebilities() {
		DesiredCapabilities desiredCapabilities = DesiredCapabilities.safari();
		desiredCapabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
		desiredCapabilities.setBrowserName(browser);
		desiredCapabilities.setVersion(version);
		return desiredCapabilities;
	}

	private static void startSafariDriver() {
		driver = new SafariDriver(safariDesiredCapebilities());
	}

	private static void startPhantomJSDriver() {
		driver = new PhantomJSDriver(phantomJsDesiredCapebilities());
	}

	private static void startIExploreDriver() {
		driver = new InternetExplorerDriver(internetExplorerDesiredCapebilities());
	}

	private static void startChromeDriver() {
		driver = new ChromeDriver(chromeDesiredCapebilities());
	}

	private static void startFirefoxDriver() {
		driver = new FirefoxDriver(fireFoxDesiredCapebilities());
	}

	public static WebDriver getDriver() {
		return driver;
	}

	public void methodIinitialization(String moduleName, String methodName) {
		element = AdvanceXMLUtil.getAllChildrenForElements(moduleName, methodName);
		mLink = AdvanceXMLUtil.getAllChildrenForClick(moduleName, methodName);
		timeoutString = AdvanceXMLUtil.getValueForTimeOutById(moduleName, methodName, "timeout");
		testCaseUtil = new TestCaseUtil();
		baseWidowHandle = driver.getWindowHandle();
	}

}

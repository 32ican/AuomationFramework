package base;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class Hooks extends BaseClass {


	@BeforeTest
	public void openUrl() {
		getDriver().get(getProperty("url"));
	}

	@AfterTest
	public void quitDriver() {
		WebDriverInstance.cleanupDriver();
	}
}

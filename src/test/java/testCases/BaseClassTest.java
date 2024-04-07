package testCases;

import java.time.Duration;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import base.BaseClass;
import base.Hooks;
import junit.framework.Assert;
import pages.HomePage;

public class BaseClassTest extends Hooks {

	
	@Test
	public void testBaseClas() {
		
		HomePage home = new HomePage(getDriver());
		JavascriptExecutor js = (JavascriptExecutor) getDriver();
		js.executeScript("arguments[0].scrollIntoView(true);", home.getTestStoreLink());

		home.getTestStoreLink().click();
		 takeScreenShot("testStore");
		

	}
}

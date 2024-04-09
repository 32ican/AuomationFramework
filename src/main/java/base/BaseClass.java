package base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.openqa.selenium.edge.EdgeDriver;
import org.apache.commons.io.FileUtils;

@Listeners(base.Listeners.class)

public class BaseClass {


	private String configFilePath = System.getProperty("user.dir") + "\\src\\main\\java\\resources\\config.properties";
	//public WebDriver driver = getDriver();
	public static WebDriver getDriver() {
				
		return WebDriverInstance.getDriver();
	}

	public String getProperty(String key) {

		Properties prop = new Properties();
		try {
			prop.load(new FileInputStream(configFilePath));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return prop.getProperty(key); 

	}

	public void takeScreenShot(String name) {
		String dateTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
		String path = System.getProperty("user.dir") + getProperty("screenshotDir") + name + "_" + dateTime + ".png";

		File srcFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
		File destFile = new File(path);
		try {
			FileUtils.copyFile(srcFile, destFile);
			;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}

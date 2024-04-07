package base;

import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class WebDriverInstance {

	public static ThreadLocal<WebDriver> driverT = new ThreadLocal<>();

	public static WebDriver getDriver() {

		if (driverT.get() == null) {
			driverT.set(createDriver());
		}
		
		return driverT.get();
	}

	public static WebDriver createDriver() {
		BaseClass base = new BaseClass();

		switch (base.getProperty("browser").toLowerCase()) {
		case "chrome":
			return initializeChromeDriver();

		case "firefox":
			return initializeFirefoxDriver();

		case "edge":
			return initializeEdgeDriver();

		default:
			System.out.println("No Driver to be initialized........");
			return null;
		}

	}

	public static WebDriver initializeChromeDriver() {
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		return driver;
	}

	public static WebDriver initializeFirefoxDriver() {
		WebDriver driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		return driver;
	}

	public static WebDriver initializeEdgeDriver() {
		WebDriver driver = new EdgeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		return driver;
	}
	
	public static void cleanupDriver() {
		driverT.get().quit();
		driverT.remove();
	}

}

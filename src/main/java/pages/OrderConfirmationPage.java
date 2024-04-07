package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class OrderConfirmationPage {
	WebDriver driver;
	By confirmationMsg = By.cssSelector(".card-title.h1");
	
	public OrderConfirmationPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public WebElement getConfirmationMsg() {
		return driver.findElement(confirmationMsg);
	}
}

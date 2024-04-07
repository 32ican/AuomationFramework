package testCases;

import java.io.IOException;

import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import base.BaseClass;
import base.Hooks;
import pages.HomePage;
import pages.OrderConfirmationPage;
import pages.OrderFormDelivery;
import pages.OrderFormPayment;
import pages.OrderFormPersInfo;
import pages.OrderFormShippingMethod;
import pages.ShopContentPanel;
import pages.ShopHomepage;
import pages.ShopProductPage;
import pages.ShoppingCart;


public class OrderCompleteTest extends Hooks {

	// Test Data
	private String email = getProperty("email");
	private String password = getProperty("password");
	private String firstName = getProperty("firstName");
	private String lastName = getProperty("lastName");
	private String street = getProperty("street");
	private String city = getProperty("city");
	private String state = getProperty("state");
	private String postalCode = getProperty("postalCode");
	private String promoCode =getProperty("promoCode");
	private String noteMsg = getProperty("noteMsg");
	

	@Test
	public void endToEndTest() throws InterruptedException {

		// creating an object of the automationtesting.co.uk webpage
		HomePage home = new HomePage(getDriver());

		//handles cookie prompt
		try {
			home.getCookie().click();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		home.getTestStoreLink().click();

		// creating an object of the test store homepage
		ShopHomepage shopHome = new ShopHomepage(getDriver());
		shopHome.getProdOne().click();

		// creating an object of the shop products page (when a product has been selected)
		ShopProductPage shopProd = new ShopProductPage(getDriver());
		Select option = new Select(shopProd.getSizeOption());
		option.selectByVisibleText("M");
		shopProd.getQuantIncrease().click();
		shopProd.getAddToCartBtn().click();

		// creating an object of the cart content panel (once an item was added)
		ShopContentPanel cPanel = new ShopContentPanel(getDriver());
		cPanel.getCheckoutBtn().click();

		// creating an object of the shopping cart page (all items selected)
		ShoppingCart cart = new ShoppingCart(getDriver());
		cart.getHavePromo().click();
		cart.getPromoTextbox().sendKeys(promoCode);
		cart.getPromoAddBtn().click();
		cart.getProceedCheckoutBtn().click();

		// creating an object of the order personal information page
		OrderFormPersInfo pInfo = new OrderFormPersInfo(getDriver());
		pInfo.getGenderMr().click();
		pInfo.getFirstNameField().sendKeys(firstName);
		pInfo.getLastnameField().sendKeys(lastName);
		pInfo.getEmailField().sendKeys(email);
		pInfo.getTermsConditionsCheckbox().click();
		pInfo.getContinueBtn().click();

		// creating an object of the order delivery info page
		OrderFormDelivery orderDelivery = new OrderFormDelivery(getDriver());
		orderDelivery.getAddressField().sendKeys(street);
		orderDelivery.getCityField().sendKeys(city);
		Select stateOption = new Select(orderDelivery.getStateDropdown());
		stateOption.selectByVisibleText(state);
		orderDelivery.getPostcodeField().sendKeys(postalCode);
		orderDelivery.getContinueBtn().click();

		// creating an object of the shipping method page
		OrderFormShippingMethod shipMethod = new OrderFormShippingMethod(getDriver());
		shipMethod.getDeliveryMsgTextbox().sendKeys(noteMsg);
		shipMethod.getContinueBtn().click();

		// creating an object of the payment options page
		OrderFormPayment orderPay = new OrderFormPayment(getDriver());
		orderPay.getPayByCheckRadioBtn().click();
		orderPay.getTermsConditionsCheckbox().click();
		orderPay.getOrderBtn().click();
		
		OrderConfirmationPage order = new OrderConfirmationPage(getDriver());
		Assert.assertTrue(order.getConfirmationMsg().getText().contains("YOUR ORDER IS CONFIRMED"));
	}

}

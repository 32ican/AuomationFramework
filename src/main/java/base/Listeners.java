package base;

import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class Listeners extends BaseClass implements ITestListener {

	public void onTestFailure(ITestResult result) {
		takeScreenShot(result.getName());

	}
}

package base;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentManager {

	private static ExtentReports extentReport;
	private static String extentReportPrefix;
	private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();

	public static ExtentReports getReport() {
		if (extentReport == null) {
			setUpReport("Automation Project");
		}
		return extentReport;
	}

	public static ExtentReports setUpReport(String name) {
		extentReport = new ExtentReports();
		String dateTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
		String path = System.getProperty("user.dir") + "\\test-output\\reports\\" + name + "_" + dateTime + ".html";
		ExtentSparkReporter spark = new ExtentSparkReporter(path);
		extentReport.attachReporter(spark);
		spark.config().setTheme(Theme.DARK);
		spark.config().setDocumentTitle("Automation Report");
		spark.config().setReportName("Extent Report Demo");

		return extentReport;
	}

	public static void flushReport() {
		extentReport.flush();
	}

	public synchronized static ExtentTest getTest() {
		return extentTest.get();
	}

	public synchronized static ExtentTest createTest(String name, String description) {
		ExtentTest test = extentReport.createTest(name);
		extentTest.set(test);
		return getTest();
	}

	public synchronized static void log(String message) {
		getTest().info(message);
	}

	public synchronized static void pass(String message) {
		getTest().pass(message);
	}

	public synchronized static void fail(String message) {
		getTest().fail(message);
	}

	public synchronized static void attachImage() {
		BaseClass base = new BaseClass();
		String screenshotPath = base.getScreenShotPath();
		getTest().addScreenCaptureFromPath(screenshotPath);
		System.out.println("Screenshot added........");
	}

}

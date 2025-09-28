package base;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Map;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import utils.ConfigReader;
import utils.DashboardGenerator;
import utils.DriverFactory;
import utils.ExtentManager;
import utils.TestChartGenerator;
import utils.TestResultLogger;

public class BaseTest {
	public WebDriver driver;
	public ExtentReports extent;
	public ExtentTest test;
	
	public static int passedCount = 0;
	public static int failedCount = 0;
	public static int skippedCount = 0;
	    
	    @BeforeSuite
	    public void setupReport() {
	        extent = ExtentManager.getReportInstance();
	    }

    @BeforeMethod
    public void setup(Method method) {
        ConfigReader.loadConfig();
        driver = DriverFactory.initDriver();
        driver.get(ConfigReader.get("baseUrl"));
        test = extent.createTest(method.getName());
    }

    @AfterMethod
    public void tearDown(ITestResult result) throws IOException {
        if (result.getStatus() == ITestResult.FAILURE) {
            test.fail(result.getThrowable());
            failedCount++;
        } else if (result.getStatus() == ITestResult.SUCCESS) {
            test.pass("Test passed");
            passedCount++;
        } else {
            test.skip("Test skipped");
            skippedCount++;
        }
        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        File dest = new File("test-output/screenshots/" + result.getName() + ".png");
        FileUtils.copyFile(src, dest);
        test.addScreenCaptureFromPath("screenshots/" + result.getName() + ".png");
        DriverFactory.quitDriver();
    }
    
    @AfterSuite
    public void flushReport() {
        extent.flush();// ✅ This writes the report to disk
        
     // Log results to CSV
        TestResultLogger.logResult(passedCount, failedCount, skippedCount);
     // Generate charts
        TestChartGenerator.generateBarChart(passedCount, failedCount, skippedCount);
        TestChartGenerator.generatePieChart(passedCount, failedCount, skippedCount);
       
        Map<String, Integer> runData = TestChartGenerator.readTrendData();
        TestChartGenerator.generateTrendChart(runData);
        
        DashboardGenerator.generateDashboard(passedCount, failedCount, skippedCount);

    }


}

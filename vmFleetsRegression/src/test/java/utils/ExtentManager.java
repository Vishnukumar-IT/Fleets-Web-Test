package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {
	private static ExtentReports extent;

    public static ExtentReports getReportInstance() {
        if (extent == null) {
        	ExtentSparkReporter sparkReporter = new ExtentSparkReporter("test-output/ExtentReport.html");

            sparkReporter.config().setReportName("Driver Management Test Report");
            sparkReporter.config().setDocumentTitle("Test Results");

            extent = new ExtentReports();
            extent.attachReporter(sparkReporter);
            extent.setSystemInfo("Environment", "QA");
            extent.setSystemInfo("Tester", "Vishnu");
        }
        return extent;
    }

}

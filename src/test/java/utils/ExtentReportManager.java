package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportManager {

    private static ExtentReports extent;

    public static ExtentReports getInstance() {
        if (extent == null) {
            String reportPath = System.getProperty("user.dir")
                              + "/reports/GUIAutomationReport.html";
            ExtentSparkReporter spark = new ExtentSparkReporter(reportPath);
            spark.config().setTheme(Theme.DARK);
            spark.config().setDocumentTitle("GUI Automation Report");
            spark.config().setReportName("Capstone Project - GUI Elements");
            spark.config().setTimeStampFormat("dd MMM yyyy HH:mm:ss");

            extent = new ExtentReports();
            extent.attachReporter(spark);
            extent.setSystemInfo("Tester", "Your Name");
            extent.setSystemInfo("Environment", "QA");
            extent.setSystemInfo("Browser", "Chrome");
            extent.setSystemInfo("URL", "testautomationpractice.blogspot.com");
        }
        return extent;
    }
}
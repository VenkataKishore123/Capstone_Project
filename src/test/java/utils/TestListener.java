package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {

    private static ExtentReports extent = ExtentReportManager.getInstance();
    public static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    @Override
    public void onTestStart(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        ExtentTest extentTest = extent.createTest(testName);
        test.set(extentTest);
        test.get().info("Test started: " + testName);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        // Auto screenshot on pass — real time industry approach
        // for key tests only (listener handles failure always)
        test.get().log(Status.PASS, "✔ Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        // Get driver from test class
        WebDriver driver = null;
        try {
            driver = (WebDriver) result.getInstance()
                    .getClass()
                    .getField("driver")
                    .get(result.getInstance());
        } catch (Exception e) {
            System.out.println("Could not get driver: " + e.getMessage());
        }

        // Auto screenshot on every failure
        if (driver != null) {
            String screenshotPath = ScreenshotUtil.capture(
                driver, result.getMethod().getMethodName());
            try {
                test.get().fail("✘ Test Failed: "
                    + result.getThrowable().getMessage(),
                    MediaEntityBuilder
                        .createScreenCaptureFromPath(screenshotPath)
                        .build());
            } catch (Exception e) {
                test.get().fail("✘ Test Failed: "
                    + result.getThrowable().getMessage());
            }
        } else {
            test.get().fail("✘ Test Failed: "
                + result.getThrowable().getMessage());
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        test.get().log(Status.SKIP, "↷ Test Skipped");
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
        System.out.println("\n✅ Report generated at: "
            + System.getProperty("user.dir") + "/reports/GUIAutomationReport.html");
    }
}
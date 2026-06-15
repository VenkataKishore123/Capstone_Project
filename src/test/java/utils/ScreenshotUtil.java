package utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotUtil {

    public static String capture(WebDriver driver, String testName) {
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String screenshotPath = System.getProperty("user.dir")
                + "/reports/screenshots/" + testName + "_" + timestamp + ".png";
        try {
            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(src, new File(screenshotPath));
        } catch (Exception e) {
            System.out.println("Screenshot failed: " + e.getMessage());
        }
        return screenshotPath;
    }
}
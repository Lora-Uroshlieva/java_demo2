package com.softserve.edu.test.helpers;

import com.softserve.edu.pages.Application;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import java.io.File;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Listener implements ITestListener {
    @Override
    public void onTestStart(ITestResult iTestResult) {

    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {

    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd_HH.mm.ss");
        Calendar now = Calendar.getInstance();
        String projectPath = Paths.get(".").toAbsolutePath().normalize().toString();
        String name = projectPath + "\\screenshots\\"
                + iTestResult.getName() + "_"
                + formatter.format(now.getTime()) + ".jpg";
        captureScreenshot(name);
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

    }

    @Override
    public void onStart(ITestContext iTestContext) {

    }

    @Override
    public void onFinish(ITestContext iTestContext) {

    }

    public static void captureScreenshot(String screenshotName) {
        try
        {
            TakesScreenshot ts=(TakesScreenshot) Application.get().getBrowser().getDriver();
            File source=ts.getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(source, new File(screenshotName));
            System.out.println("Screenshot taken");
        }
        catch (Exception e) {
            System.out.println("Exception while taking screenshot " + e.getMessage());
        }
    }
}

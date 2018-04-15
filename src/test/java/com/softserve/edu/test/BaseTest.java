package com.softserve.edu.test;

import data.applications.ApplicationSourceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.internal.TestResult;
import pages.Application;
import com.softserve.edu.test.helpers.Listener;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;

@Listeners(Listener.class)

public class BaseTest {
    public static final Logger logger = LoggerFactory.getLogger(BaseTest.class);

    @BeforeClass
    public void setupClass() {
        Application.get(ApplicationSourceRepository.OpencarttChrome());
        logger.info("setup class");
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        Application.remove();
        logger.info("Driver was destroyed. Browser closed");
    }

    @AfterMethod(alwaysRun = true)
    public void afterMethod(ITestResult testResult) {
        System.out.println("**********" + testResult + "\n" + testResult.getMethod());
    }
}

package com.softserve.edu.test;

import data.applications.ApplicationSourceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
        logger.error("setupClass Error");
        logger.warn("setupClass Warning");
        logger.info("setupClass Info");
        logger.debug("setupClass Debug");
        logger.trace("setupClass Trace");
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        Application.remove();
        logger.info("Driver was destroyed. Browser closed");
    }
}

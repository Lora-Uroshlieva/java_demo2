package com.softserve.edu.test;

import com.softserve.edu.data.applications.ApplicationSourceRepository;
import com.softserve.edu.pages.Application;
import com.softserve.edu.test.helpers.Listener;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;

@Listeners(Listener.class)

public class BaseTest {

    @BeforeClass
    public void setupClass() {
        Application.get(ApplicationSourceRepository.OpencarttChrome());
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        Application.remove();
    }
}

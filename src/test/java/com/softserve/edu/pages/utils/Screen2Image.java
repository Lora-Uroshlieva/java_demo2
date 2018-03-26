package com.softserve.edu.pages.utils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Screen2Image {
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd_HH.mm.ss");

    public void makeScreenshot() throws IOException {
        Calendar now = Calendar.getInstance();
        Robot robot = null;
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
        //TODO use Selenium API to capture sreenshot
        BufferedImage screenShot = robot.createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
        String projectPath = Paths.get(".").toAbsolutePath().normalize().toString();
        ImageIO.write(screenShot, "JPG", new File(projectPath + "\\screenshots\\"+formatter.format(now.getTime()) + ".jpg"));
        System.out.println(formatter.format(now.getTime()) + " - screen of error was captured.");
    }
}
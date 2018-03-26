package com.softserve.edu.pages.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class ConciseAPI {
    public WebDriver driver;
    private WebDriverWait myWait;

    public ConciseAPI(WebDriver driver) {
        this.driver = driver;
        this.myWait = new WebDriverWait(this.driver, 10);
    }

    //TODO refactor
    public WebElement waitElementVisibleByCss(String cssSelector) {
        return myWait.until(ExpectedConditions
                .visibilityOfElementLocated(By.cssSelector(cssSelector)));
    }

    public WebElement waitElementVisibleById(String idSelector) {
        return myWait.until(ExpectedConditions
                .visibilityOfElementLocated(By.id(idSelector)));
    }

    public WebElement waitElementVisibleByXpath(String xpath) {
        return myWait.until(ExpectedConditions
                .visibilityOfElementLocated(By.xpath(xpath)));
    }

    public List<WebElement> waitAllElementsVisibleByCss(String cssSelector) {
        return myWait.until(ExpectedConditions
                .visibilityOfAllElementsLocatedBy(By.cssSelector(cssSelector)));
    }

    public WebElement findElementInLayoutByCss(WebElement layoutElement, String cssSelector) {
        return myWait.until(ExpectedConditions.visibilityOf(layoutElement
                .findElement(By.cssSelector(cssSelector))));
    }
    //TODO move in separate class
    public boolean isTextEqual(String text1, String text2) {
        return text1.toLowerCase().trim().equals(text2.toLowerCase().trim());
    }

    public boolean isArraySortedByAsc(List<Double> array) {
        boolean result = true;
        for (int i = 0; i < array.size()-1; i++) {
            if (array.get(i) > array.get(i+1)) {
                result = false;
                break;
            }
        }
        return result;
    }

    public boolean isArraySortedByDesc(List<Double> array) {
        boolean result = true;
        for (int i = 0; i < array.size()-1; i++) {
            if (array.get(i) < array.get(i+1)) {
                result = false;
                break;
            }
        }
        return result;
    }
}

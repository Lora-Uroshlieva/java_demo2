package com.softserve.edu.pages;

import com.softserve.edu.pages.modules.FeaturedBlock;
import com.softserve.edu.pages.modules.Header;
import org.openqa.selenium.WebDriver;

public class HomePage {
    protected WebDriver driver;
    private Header header;
    private FeaturedBlock featuredBlock;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.header = new Header(this.driver);
        this.featuredBlock = new FeaturedBlock(this.driver);
    }

    public static HomePage load(WebDriver driver) {
        driver.get("http://opencartt.rf.gd/");
        return new HomePage(driver);
    }

    public Header getHeader() {
        return header;
    }

    public FeaturedBlock getFeaturedBlock() {
        return featuredBlock;
    }

    public void moveToSearchPage() {
        getHeader().clickSearchButton();
    }

    //TODO write business logic with Currency enum given as a parameter
    public HomePage selectCurrency(String currency) {
        header.clickCurrency();
        return new HomePage(driver);
    }

    public SearchPage searchByKeyword(String keyword) {
        header.clearSearchField();
        header.sendTextToSearchField(keyword);
        header.clickSearchButton();
        return new SearchPage(driver);
    }


    /* TODO implement next methods:
    clickLogo()
    register()
    login()
    moveToWishList()
    moveToShoppingCart()
    checkout()
    checkShoppingCart()
    selectProductsCategory()
     */

}

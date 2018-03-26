package com.softserve.edu.pages.modules;

import com.softserve.edu.pages.utils.ConciseAPI;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class FeaturedBlock extends ConciseAPI {
    private List<ProductComponent> productComponents;
    private final String PRODUCT_LAYOUT_BY_CSS = ".product-layout";

    public FeaturedBlock(WebDriver driver) {
        super(driver);
    }

    private void initProductComponents() {
        productComponents = new ArrayList<ProductComponent>();
        for (WebElement currentElement: $$(PRODUCT_LAYOUT_BY_CSS)) {
            productComponents.add(new ProductComponent(driver, currentElement));
        }
    }

    public List<ProductComponent> getProductComponents() {
        initProductComponents();
        return productComponents;
    }

    public boolean isProductFound() {
        return driver.findElements(By.cssSelector(PRODUCT_LAYOUT_BY_CSS)).size() >= 1;
    }

    public ProductComponent getProductComponentByName(String productName) {
        ProductComponent result = null;
        for (ProductComponent current: getProductComponents()) {
            if(isTextEqual(current.getNameText(), productName)) {
                result = current;
                break;
            }
        }
        if (result == null) {
            throw new RuntimeException("Product " + productName + " was not found on page.");
        }
        return result;
    }

    public List<String> getAllProductComponentsNames() {
        List<String> result = new ArrayList<String>();
        for (ProductComponent current: getProductComponents()) {
            result.add(current.getNameText());
        }
        return result;
    }

    public Double getPriceAmountByProductName(String productName) {
        return getProductComponentByName(productName).getPriceAmount();
    }

    public List<Double> getAllProductsPricesAmounts() {
        List<ProductComponent> products = getProductComponents();
        List<Double> prices = new ArrayList<>();
        for (ProductComponent current: products) {
            prices.add(current.getPriceAmount());
        }
        System.out.println("Prices of products present on page: ");
        System.out.println(prices);
        return prices;
    }

    public void clickAddToCartByProductName(String productName) {
        getProductComponentByName(productName).clickAddToCart();
    }

    public void clickAddToWishByProductName(String productName) {
        getProductComponentByName(productName).clickAddToWish();
    }

    public void clickCompareByProductName(String productName) {
        getProductComponentByName(productName).clickCompare();
    }

    /**
     * Method for checking if classname given as a parameter is present in element's classes list.
     * @param className - string class name
     * @return true or false
     */
    public boolean checkClassPresenceInProductComponent(String className) {
        boolean result = true;
        for (ProductComponent current: getProductComponents()) {
            if (!current.getElementClasses().contains(className)) {
                result = false;
                break;
            }
        }
        return result;
    }
}

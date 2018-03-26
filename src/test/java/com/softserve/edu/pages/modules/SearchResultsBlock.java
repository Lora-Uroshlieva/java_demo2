package com.softserve.edu.pages.modules;

import com.softserve.edu.data.ProductsLimitOnPage;
import com.softserve.edu.data.SortingType;
import com.softserve.edu.pages.utils.RegexUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class SearchResultsBlock extends FeaturedBlock {
    public SearchResultsBlock(WebDriver driver) {
        super(driver);
    }

    private final String PRODUCT_COMPARE_LINK_BY_ID = "compare-total";
    private final String LIST_VIEW_BUTTON_BY_ID = "list-view";
    private final String GRID_VIEW_BUTTON_BY_ID = "grid-view";
    private final String SORTING_TYPE_BY_ID = "input-sort";
    private final String QUANTITY_ON_PAGE_BY_ID = "input-limit";
    private final String NEXT_RESULT_PAGE_BY_XPATH = "//ul[@class='pagination'] /li /a[text()='>']";
    private final String PAGE_NUMBER_DESCRIPTION_BY_CSS = "div.text-right";

    public WebElement getProductCompareLink() {
        return waitElementVisibleById(PRODUCT_COMPARE_LINK_BY_ID);
    }

    public String getProductCompareLinkText() {
        return getProductCompareLink().getText();
    }

    public int getProductComparisonAmount() {
        return RegexUtils.extractFirstNumber(getProductCompareLinkText());
    }

    public void clickListViewButton() {
        waitElementVisibleById(LIST_VIEW_BUTTON_BY_ID).click();
    }

    public void clickGridViewButton() {
        waitElementVisibleById(GRID_VIEW_BUTTON_BY_ID).click();
    }

    public void selectSortingType(SortingType sortingType) {
        Select dropdown = new Select(waitElementVisibleById(SORTING_TYPE_BY_ID));
        dropdown.selectByVisibleText(sortingType.toString());
    }

    public void selectQuantityOnPage(ProductsLimitOnPage quantity) {
        Select dropdown = new Select(waitElementVisibleById(QUANTITY_ON_PAGE_BY_ID));
        dropdown.selectByVisibleText(quantity.toString());
    }

    public void clickNextSearchResultsPage() {
        waitElementVisibleByXpath(NEXT_RESULT_PAGE_BY_XPATH).click();
    }

    public String getPageNumberDescriptionText() {
        return waitElementVisibleByCss(PAGE_NUMBER_DESCRIPTION_BY_CSS).getText();
    }
}

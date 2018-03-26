package com.softserve.edu.pages;

import com.softserve.edu.data.Categories;
import com.softserve.edu.data.ProductsLimitOnPage;
import com.softserve.edu.data.SortingType;
import com.softserve.edu.pages.modules.ExtendedSearchBlock;
import com.softserve.edu.pages.modules.Header;
import com.softserve.edu.pages.modules.SearchResultsBlock;
import com.softserve.edu.pages.utils.ConciseAPI;
import org.openqa.selenium.WebDriver;

public class SearchPage extends ConciseAPI {

    private Header header;
    private ExtendedSearchBlock extendedSearchBlock;
    protected SearchResultsBlock searchResultsBlock;

    public SearchResultsBlock getSearchResultsBlock() {
        return searchResultsBlock;
    }

    public static String getURL() {
        return URL;
    }

    private final static String URL = "http://opencartt.rf.gd/index.php?route=product/search";
    private final String LIST_LAYOUT_CLASS = "product-list";
    private final String GRID_LAYOUT_CLASS = "product-grid";

    public static SearchPage load(WebDriver driver) {
        driver.get(URL);
        return new SearchPage(driver);
    }

    public SearchPage(WebDriver driver) {
        super(driver);
        this.header = new Header(this.driver);
        this.extendedSearchBlock = new ExtendedSearchBlock(this.driver);
        this.searchResultsBlock = new SearchResultsBlock(this.driver);
    }

    public SearchPage makeExtendedSearch(String keyWord, Categories category) {
        return extendedSearchBlock.clearSearchField()
                .inputToSearchField(keyWord)
                .selectCategory(category)
                .clickSelectInDescriptionsCheckbox()
                .clickSearchButton();
    }

    public void displayProductsAsList() {
        searchResultsBlock.clickListViewButton();
    }

    public void displayProductsAsGrid() {
        searchResultsBlock.clickGridViewButton();
    }

    public SearchPage sortProducts(SortingType type) {
        searchResultsBlock.selectSortingType(type);
        return new SearchPage(driver);
    }

    public  SearchPage changeProductsLimitOnPage(ProductsLimitOnPage limit) {
        searchResultsBlock.selectQuantityOnPage(limit);
        return new SearchPage(driver);
    }

    public SearchPage moveToNextSearchResultsPage() {
        searchResultsBlock.clickNextSearchResultsPage();
        return new SearchPage(driver);
    }

    public int countProductsFound() {
        return searchResultsBlock.getProductComponents().size();
    }

    public boolean isKeywordInProductsNames(String keyword) {
        boolean result = true;
        for(String currentName: getSearchResultsBlock().getAllProductComponentsNames()) {
            if(!currentName.contains(keyword)) {
                result = false;
            }
        }
        return result;
    }

    public boolean isPricesSortedByAsc() {
        return isArraySortedByAsc(searchResultsBlock.getAllProductsPricesAmounts());
    }

    public boolean isPricesSortedByDesc() {
        return isArraySortedByDesc(searchResultsBlock.getAllProductsPricesAmounts());
    }

    public boolean isProductsDisplayedByList() {
        return searchResultsBlock.checkClassPresenceInProductComponent(LIST_LAYOUT_CLASS);
    }

    public boolean isProductsDisplayedByGrid() {
        return searchResultsBlock.checkClassPresenceInProductComponent(GRID_LAYOUT_CLASS);
    }

    public boolean isTitleContainKeyword(String keyword) {
        return driver.getTitle().contains(keyword);
    }
}

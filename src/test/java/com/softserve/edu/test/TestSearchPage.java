package com.softserve.edu.test;

import com.softserve.edu.data.Categories;
import com.softserve.edu.data.ProductsLimitOnPage;
import com.softserve.edu.data.SortingType;
import com.softserve.edu.pages.Application;
import com.softserve.edu.pages.SearchPage;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TestSearchPage extends BaseTest {

    @Test
    public void moveToSearchPageFromHomeTest() {
        SearchPage searchPage = Application
                .get()
                .loadHomePage()
                .moveToSearchPage();
        String expectedUrl = Application
                .get()
                .getApplicationSource()
                .getSearchPageUrl();
        Assert.assertEquals(searchPage.getCurrentUrl(), expectedUrl);
    }

    @Test
    public void loadPageTest() {
        SearchPage searchPage = Application
                .get()
                .loadSearchPage();
        String expectedUrl = Application
                .get()
                .getApplicationSource()
                .getSearchPageUrl();
        Assert.assertEquals(searchPage.getCurrentUrl(), expectedUrl);
    }

    @DataProvider
    public Object[][] extendedSearchProvider() {
        return new Object[][] {
                {"i", Categories.DESCTOPS, 15},
                {"Sony VAIO", Categories.ALL, 1}
        };
    }

    @Test(dataProvider = "extendedSearchProvider")
    public void extendedSearchTest(String keyWord, Categories category, int expectedQuantity) {
        SearchPage searchPage = Application
                .get()
                .loadSearchPage()
                .makeExtendedSearch(keyWord, category);
        int actual = searchPage.countProductsFound();
        Assert.assertEquals(actual, expectedQuantity);
        Assert.assertTrue(searchPage.isTitleContainKeyword(keyWord));
    }

    @Test
    public void searchByEmptyFieldTest() {
        SearchPage searchPage = Application
                .get()
                .loadSearchPage()
                .makeExtendedSearch("", Categories.ALL);
        Assert.assertFalse(searchPage
                .getSearchResultsBlock()
                .isProductFound());
    }

    @DataProvider
    public Object[][] searchForSortingAndSwitchingProvider() {
        return new Object[][] {
                {"mac", Categories.ALL}
        };
    }

    @Test(dataProvider = "searchForSortingAndSwitchingProvider")
    public void sortingByPriceAscTest(String keyWord, Categories category) {
        SearchPage searchPage = Application
                .get()
                .loadSearchPage()
                .makeExtendedSearch(keyWord, category);
        searchPage.sortProducts(SortingType.PRICE_ASC);
        Assert.assertTrue(searchPage.isPricesSortedByAsc());
    }

    @Test(dataProvider = "searchForSortingAndSwitchingProvider")
    public void sortingByPriceDescTest(String keyWord, Categories category) {
        SearchPage searchPage = Application
                .get()
                .loadSearchPage()
                .makeExtendedSearch(keyWord, category);
        searchPage.sortProducts(SortingType.PRICE_DESC);
        Assert.assertTrue(searchPage.isPricesSortedByDesc());
    }

    @Test(dataProvider = "searchForSortingAndSwitchingProvider")
    public void switchingToListViewTest(String keyWord, Categories category) {
        SearchPage searchPage = Application
                .get()
                .loadSearchPage()
                .makeExtendedSearch(keyWord, category);
        searchPage.displayProductsAsList();
        Assert.assertTrue(searchPage.isProductDisplayedAsList());
    }

    @Test(dataProvider = "searchForSortingAndSwitchingProvider")
    public void switchingToGridViewTest(String keyWord, Categories category) {
        SearchPage searchPage = Application
                .get()
                .loadSearchPage()
                .makeExtendedSearch(keyWord, category);
        searchPage.displayProductsAsGrid();
        Assert.assertTrue(searchPage.isProductDisplayedAsGrid());
    }

    @DataProvider
    public Object[][] changeLimitsProvider() {
        return new Object[][] {
                {"i", Categories.ALL}
        };
    }

    @Test(dataProvider = "changeLimitsProvider")
    public void changeProductsLimitOnPageTest(String keyWord, Categories category) {
        SearchPage searchPage = Application
                .get()
                .loadSearchPage()
                .makeExtendedSearch(keyWord, category);
        Assert.assertEquals(searchPage.countProductsFound(),
                ProductsLimitOnPage.DEFAULT_LIMIT_15.toInt());
        searchPage.changeProductsLimitOnPage(ProductsLimitOnPage.LIMIT_25);
        Assert.assertEquals(searchPage.countProductsFound(),
                ProductsLimitOnPage.LIMIT_25.toInt());
    }

    @DataProvider
    public Object[][] paginationProvider() {
        return new Object[][] {
                {"i", Categories.ALL, "Showing 16 to 30 of 32 (3 Pages)"}
        };
    }

    @Test(dataProvider = "paginationProvider")
    public void goToNextSearchResultsPageTest(String keyWord,
                                              Categories category,
                                              String expectedPageNumberDescription) {
        SearchPage searchPage = Application
                .get()
                .loadSearchPage()
                .makeExtendedSearch(keyWord, category);
        Assert.assertEquals(searchPage.countProductsFound(),
                ProductsLimitOnPage.DEFAULT_LIMIT_15.toInt());
        searchPage.moveToNextSearchResultsPage();
        Assert.assertEquals(searchPage.getSearchResultsBlock()
                        .getPageNumberDescriptionText(),
                        expectedPageNumberDescription);
    }
}

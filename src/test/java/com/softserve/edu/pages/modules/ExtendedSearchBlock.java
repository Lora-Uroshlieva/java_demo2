package com.softserve.edu.pages.modules;

import com.softserve.edu.data.Categories;
import com.softserve.edu.pages.SearchPage;
import com.softserve.edu.pages.utils.ConciseAPI;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class ExtendedSearchBlock extends ConciseAPI {
    public ExtendedSearchBlock(WebDriver driver) {
        super(driver);
    }

    private final String SEARCH_FIELD_BY_ID = "input-search";
    private final String CATEGORY_BY_CSS = "#content select.form-control[name='category_id']";
    private final String SELECT_IN_DESCRIPTIONS_CHECKBOX_BY_ID = "description";
    private final String SEARCH_BUTTON_BY_ID = "button-search";

    public WebElement getSearchField() {
        return waitElementVisibleById(SEARCH_FIELD_BY_ID);
    }

    public ExtendedSearchBlock clearSearchField() {
        getSearchField().clear();
        return this;
    }

    public ExtendedSearchBlock inputToSearchField(String text) {
        getSearchField().sendKeys(text);
        return this;
    }

    public ExtendedSearchBlock selectCategory(Categories category) {
        Select dropdown = new Select(waitElementVisibleByCss(CATEGORY_BY_CSS));
        dropdown.selectByVisibleText(category.toString()); //category is chosen as visible text in html code
        return this;
    }

    public ExtendedSearchBlock clickSelectInDescriptionsCheckbox() {
        waitElementVisibleById(SELECT_IN_DESCRIPTIONS_CHECKBOX_BY_ID).click();
        return this;
    }

    public SearchPage clickSearchButton() {
        waitElementVisibleById(SEARCH_BUTTON_BY_ID).click();
        return new SearchPage(driver);
    }
}

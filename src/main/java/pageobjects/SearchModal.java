package pageobjects;

import base.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;


public class SearchModal extends BasePage {

    public SearchModal() {
        super();
    }

    @FindBy(css = "#docsearch-input")
    private WebElement searchInputField;

    @FindBy(css = ".DocSearch-Dropdown .DocSearch-Hits ul[role='listbox'] li.DocSearch-Hit")
    private List<WebElement> searchResults;

    @FindBy(className = "DocSearch-Modal")
    private WebElement searchModal;

    @FindBy(css = ".DocSearch-Title")
    private WebElement searchTitle;


    public void clickInSearchField() {
        waitForElementToBeClickable(searchInputField);
        searchInputField.click();
    }

    public void enterSearchText(String searchText) {
        typeIn(searchInputField, searchText);
    }

    public String getSearchTitleText() {
        waitPageToLoad();
        waitForElementVisibility(searchTitle);
        String searchTitleText = searchTitle.getText();
        return searchTitleText;
    }

    public void confirmSearchQuery() {
        keyboardPressEnterKey();
    }

    public void performSearch(String searchText) {
        waitPageToLoad();
        clickInSearchField();
        enterSearchText(searchText);
        clickInSearchField();
        waitPageToLoad();
        confirmSearchQuery();
    }

    public void performSearchUsingKeyboard(String searchText) {
        waitPageToLoad();
        keyboardPressControlAndKKey();
        moveToElementAndClick(searchInputField);
        keyboardDeleteText();
        actionsTypeIn(searchText);
        waitPageToLoad();
        confirmSearchQuery();
    }

    public boolean isSearchModalPresent() {
        return searchModal.isDisplayed();
    }

    public List<WebElement> getSearchResults() {
        waitForElementsToBeVisible(searchResults);
        return searchResults;
    }

    public boolean isSearchTextPresent(String searchText) {
        List<WebElement> results = getSearchResults();

        return results.stream()
                .allMatch(result -> result.getText().contains(searchText.toLowerCase()));
    }

    public boolean isPartOfSearchWordPresentOnPage(String searchWord) {
        String pageSource = getDriver().getPageSource().toLowerCase();
        return pageSource.contains(searchWord.toLowerCase());
    }
}

package org.selenium.tests.searchtests;

import base.Hooks;
import org.testng.annotations.Test;
import pageobjects.NavigationPage;
import pageobjects.SearchModal;
import utils.DataProviderUtils;

public class NoResultsInvalidSearchWordsTest extends Hooks {

    public NoResultsInvalidSearchWordsTest() {
        super();
    }

    NavigationPage navigationPage = new NavigationPage();
    SearchModal searchModal = new SearchModal();

    @Test(groups = {"Regression"}, dataProvider = "InvalidSearchWords", dataProviderClass = DataProviderUtils.class)
    public void verifyCorrectResultsWithExactQueryMatch(String searchWord) {

        String expectedSearchTitle = "No results for \"" + searchWord + "\"";

        //Open the Search modal
        navigationPage.mouseClickSearchButton();

        //Search for a word that would result in no results
        searchModal.performSearch(searchWord);

        //Verify that a text is present indicating that there are no results for the searched word
        softAssert.assertEquals(searchModal.getSearchTitleText(), expectedSearchTitle);

        softAssert.assertAll();

    }
}

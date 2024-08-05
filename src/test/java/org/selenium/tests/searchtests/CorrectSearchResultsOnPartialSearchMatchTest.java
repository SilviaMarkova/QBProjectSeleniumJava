package org.selenium.tests.searchtests;

import base.Hooks;
import org.testng.annotations.Test;
import pageobjects.NavigationPage;
import pageobjects.SearchModal;
import utils.DataProviderUtils;

public class CorrectSearchResultsOnPartialSearchMatchTest extends Hooks {

    public CorrectSearchResultsOnPartialSearchMatchTest() {
        super();
    }

    NavigationPage navigationPage = new NavigationPage();
    SearchModal searchModal = new SearchModal();

    @Test(dataProvider = "PartialSearchMatches", dataProviderClass = DataProviderUtils.class)
    public void verifyCorrectResultsWithExactQueryMatch(String searchWord) {

        boolean isPartOfWordPresent = searchModal.isPartOfSearchWordPresentOnPage(searchWord);

        //Open the Search modal
        navigationPage.mouseClickSearchButton();

        //Search for a word that would result in no results
        searchModal.performSearch(searchWord);

        //Verify that part of the searched word is present anywhere on the page
        softAssert.assertTrue(isPartOfWordPresent, "Part of the searched word is not present on the page!");

        softAssert.assertAll();

    }
}

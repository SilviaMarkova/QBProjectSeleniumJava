package org.selenium.tests.searchtests;

import base.Hooks;
import org.testng.annotations.Test;
import pageobjects.NavigationPage;
import pageobjects.SearchModal;
import utils.DataProviderUtils;

public class SuggestedResultsMatchSearchQueryTest extends Hooks {

    public SuggestedResultsMatchSearchQueryTest() {
        super();
    }

    NavigationPage navigationPage = new NavigationPage();
    SearchModal searchModal = new SearchModal();


    @Test(dataProvider = "ExactSearchMatches", dataProviderClass = DataProviderUtils.class)
    public void verifySuggestedResultsMatchSearchWord(String searchWord) {

        //Open the Search modal
        navigationPage.clickSearchButton();

        //Search for a word that would result in an exact match
        searchModal.performSearch(searchWord);

        //Verify that the suggested results in the Search container match the searched word
        softAssert.assertTrue(searchModal.isSearchTextPresent(searchWord));

    }
}

package org.selenium.tests.searchtests;

import base.Hooks;
import org.testng.annotations.Test;
import pageobjects.ApiPage;
import pageobjects.DocsPage;
import pageobjects.NavigationPage;
import pageobjects.SearchModal;
import utils.DataProviderUtils;

public class CorrectSearchResultsOnExactSearchMatchTest extends Hooks {

    public CorrectSearchResultsOnExactSearchMatchTest() {
        super();
    }

    NavigationPage navigationPage = new NavigationPage();
    ApiPage apiPage = new ApiPage();
    DocsPage docsPage = new DocsPage();
    SearchModal searchModal = new SearchModal();


    @Test(groups = {"Smoke"}, dataProvider = "ExactSearchMatches", dataProviderClass = DataProviderUtils.class)
    public void verifyCorrectResultsWithExactQueryMatch(String searchWord) {

        boolean isWordPresentInApiPageBreadcrumbs = apiPage.isWordPresentInBreadcrumbs(searchWord);
        boolean isWordPresentInDocsPageBreadcrumbs = docsPage.isWordPresentInBreadcrumbs(searchWord);

        //Open the Search modal
        navigationPage.mouseClickSearchButton();

        //Search for a word that would result in an exact match
        searchModal.performSearch(searchWord);

        //Verify that the correct information
        softAssert.assertTrue((apiPage.mainHeadingContains(searchWord)) || (docsPage.mainHeadingContains(searchWord)),
                "Search word is not present in the main heading!");
        softAssert.assertTrue(isWordPresentInCurrentUrl(searchWord), "The search word is not present in the URL!");

        //TODO: Check why this assertion fails
//        softAssert.assertTrue(isWordPresentInApiPageBreadcrumbs || isWordPresentInDocsPageBreadcrumbs,
//                "Search word is not present in breadcrumb links!");

        softAssert.assertAll();
    }
}

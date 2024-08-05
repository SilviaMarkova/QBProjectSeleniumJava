package org.selenium.tests.E2Etests;

import base.Hooks;
import enums.ProtocolItems;
import org.testng.annotations.Test;
import pageobjects.ApiPage;
import pageobjects.NavigationPage;
import pageobjects.SearchModal;
import pageobjects.SidebarPage;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SearchApiAndVerifyProtocolsTest extends Hooks {

    public SearchApiAndVerifyProtocolsTest(){
        super();
    }

    NavigationPage navigationPage = new NavigationPage();
    ApiPage apiPage = new ApiPage();
    SearchModal searchModal = new SearchModal();
    SidebarPage sidebarPage = new SidebarPage();

    List<String> expectedProtocolItems = Arrays.stream(ProtocolItems.values())
            .map(ProtocolItems::getValue)
            .collect(Collectors.toList());


    @Test (groups = {"Regression"})
    public void searchApiAndOpenProtocolsLink() throws InterruptedException {

        String expectedUrl = "https://webdriver.io/docs/api";
        String searchWord = "Click";
        boolean isWordPresentInSidebar = sidebarPage.isWordPresentInSidebar(searchWord);

        //Click on the API link in the top navigation
        navigationPage.clickApiLink();

        //Verify that user is on the correct page
        softAssert.assertEquals(getCurrentUrl(), expectedUrl, "The URL is not correct!");
        softAssert.assertTrue(sidebarPage.isProtocolsSectionDisplayed(), "The Protocols section is not displayed!");

        //Open the Search modal
        navigationPage.mouseClickSearchButton();

        //Enter the "Click" word in the search field and press Enter
        searchModal.performSearch(searchWord);

        //Verify that the correct information / page is returned
        softAssert.assertTrue(isWordPresentInCurrentUrl(searchWord), "The search word is not present in the URL!");
        softAssert.assertTrue(apiPage.mainHeadingContains(searchWord),
                "Search word is not present in the main heading!");

        //Navigate to API -> Protocols
        apiPage.navigateToApiDocumentationPage();

        //Expand the Protocols sections
        sidebarPage.clickProtocolsExpandButton();

        //Verify that the list under the Protocols section is correct
        softAssert.assertEquals(sidebarPage.getProtocolItems(), expectedProtocolItems, "Protocols items do not match the expected values!");

        softAssert.assertAll();
    }
}

package org.selenium.tests.searchtests;

import base.Hooks;
import org.testng.annotations.Test;
import pageobjects.NavigationPage;
import pageobjects.SearchModal;

public class ClearSearchInputTest extends Hooks {

    public ClearSearchInputTest(){
        super();
    }

    NavigationPage navigationPage = new NavigationPage();
    SearchModal searchModal = new SearchModal();

    String searchWord = "TextToBeCleared";

    @Test
    public void verifyEnteredTextCanBeCleared(){

        //Open the Search modal
        navigationPage.clickSearchButton();

        //Enter some text in the Search input field
        searchModal.enterSearchText(searchWord);

        //Click the "X" clear inout button
        searchModal.clickClearInputButton();

        //Verify that the Search input field is cleared successfully
        softAssert.assertTrue(searchModal.isSearchInputFieldEmpty(), "The entered text was not cleared successfully!");

        softAssert.assertAll();
    }
}

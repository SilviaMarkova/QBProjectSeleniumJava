package tests.navigationtests;

import base.Hooks;
import org.testng.annotations.Test;
import pageobjects.NavigationPage;

public class VerifyGithubLinkTest extends Hooks {

    public VerifyGithubLinkTest() {
        super();
    }

    NavigationPage navigationPage = new NavigationPage();

    String expectedGitUrl = "https://github.com/webdriverio/webdriverio";
    String expectedGithubPageTitle = "GitHub - webdriverio/webdriverio: Next-gen browser and mobile automation test framework for Node.js";

    @Test(groups = {"Smoke"})
    public void verifyGithubLinkIsCorrect() {

        //Click on the Github link
        navigationPage.clickGithubLink();

        //Switch to the newly opened tab
        switchToNextTab();

        //Verify that user is redirected to the correct Url, and the correct page is opened
        softAssert.assertEquals(getCurrentUrl(), expectedGitUrl, "The link does not match the expected Github link");
        softAssert.assertEquals(getPageTitle(), expectedGithubPageTitle);

    }
}

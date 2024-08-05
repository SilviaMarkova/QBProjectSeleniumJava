package pageobjects;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import base.BasePage;


@Getter
@Slf4j
public class NavigationPage extends BasePage {

    public NavigationPage() {
        super();
    }

    @FindBy(css = ".navbar__logo .themedComponent_mlkZ")
    private WebElement webdriverLogo;

    @FindBy(css = "div[class='navbar__items'] a:nth-of-type(2)")
    private WebElement docsLink;

    @FindBy(css = "div[class='navbar__items'] a:nth-of-type(3)")
    private WebElement apiLink;

    @FindBy(css = "div[class='navbar__items'] a:nth-of-type(4)")
    private WebElement blogLink;

    @FindBy(css = "div[class='navbar__items'] a:nth-of-type(5)")
    private WebElement contributeLink;

    @FindBy(css = "div[class='navbar__items'] a:nth-of-type(6)")
    private WebElement communityLink;

    @FindBy(css = "div[class='navbar__items'] a:nth-of-type(7)")
    private WebElement sponsorLink;

    @FindBy(css = "div[class='navbar__items navbar__items--right'] a[class='navbar__item navbar__link']")
    private WebElement versionsLink;

    @FindBy(css = ".dropdown")
    private WebElement languageDropdownLink;

    @FindBy(css = ".header-github-link")
    private WebElement githubLink;

    @FindBy(css = ".header-twitter-link")
    private WebElement twitterLink;

    @FindBy(css = ".header-youtube-link")
    private WebElement youtubeLink;

    @FindBy(css = ".header-discord-link")
    private WebElement discordLink;

    @FindBy(css = ".toggleButton_gllP")
    private WebElement switchThemeButton;

    @FindBy(css = ".DocSearch.DocSearch-Button")
    private WebElement searchButton;

    @FindBy(css = "span[class='DocSearch-Button-Keys'] kbd:nth-of-type(1)")
    private WebElement ctrlSearchButton;

    @FindBy(css = "span[class='DocSearch-Button-Keys'] kbd:nth-of-type(2)")
    private WebElement kSearchButton;


    public void clickSearchButton() {
        waitForElementToBeClickable(searchButton);
        searchButton.click();
    }

    public void jsClickSearchButton() {
        clickElementUsingJS(searchButton);

    }

    public void clickSwitchThemeToggle() {
        waitForElementToBeClickable(switchThemeButton);
        switchThemeButton.click();
        waitPageToLoad();
    }

    public void keyboardClickSearchButton() {
        keyboardPressCtrAndKKey();
    }

    public void clickDocsLink() {
        docsLink.click();
    }

    public void clickApiLink() {
        waitForElementToBeClickable(apiLink);
        apiLink.click();
    }

    public void clickBlogLink() {
        blogLink.click();
    }

    public void clickContributeLink() {
        contributeLink.click();
    }

    public void clickCommunityLink() {
        communityLink.click();
    }

    public void clickSponsorLink() {
        sponsorLink.click();
    }

    public void clickGithubLink() {
        waitForElementToBeClickable(githubLink);
        githubLink.click();
    }

}

package base;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;
import utils.EnvironmentsConstants;

import java.time.Duration;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


public class BasePage {

    protected WebDriverWait wait;
    protected Actions actions;
    protected JavascriptExecutor jsExecutor;
    protected SoftAssert softAssert;

    public BasePage() {
        PageFactory.initElements(getDriver(), this);
        wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        this.softAssert = new SoftAssert();
        this.actions = new Actions(getDriver());
        this.jsExecutor = (JavascriptExecutor) getDriver();
    }

    public static WebDriver getDriver() {
        return DriverManager.getDriver();
    }


    public void navigateToBaseURL() {
        getDriver().get(EnvironmentsConstants.BASE_URL);
        waitPageToLoad();
    }

    public void navigateToUrl(String url) {
        getDriver().get(url);
    }

    protected String getCurrentUrl() {
        return getDriver().getCurrentUrl();
    }

    protected String getPageTitle() {
        return getDriver().getTitle();
    }

    protected boolean isWordPresentInCurrentUrl(String word) {
        String currentUrl = getDriver().getCurrentUrl();
        return currentUrl.toLowerCase().contains(word.toLowerCase());
    }

    protected String getWebElementText(WebElement element) {
        return element.getText();
    }

    protected void typeIn(WebElement element, String text) {
        element.sendKeys(text);
    }

    protected void clearInputField(WebElement inputField) {
        inputField.click();
        inputField.clear();
    }

    protected void switchToNextTab() {
        String originalTab = getDriver().getWindowHandle();
        Set<String> allTabs = getDriver().getWindowHandles();

        for (String tab : allTabs) {
            if (!tab.equals(originalTab)) {
                getDriver().switchTo().window(tab);
            }
        }
    }

    protected List<String> iterateOverBreadcrumbLinks(List<WebElement> breadcrumbs) {
        return breadcrumbs.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }


    protected void waitForElementVisibility(WebElement element) {
        try {
            wait.until(ExpectedConditions.visibilityOf(element));
        } catch (TimeoutException e) {
            e.getMessage();
        }
    }

    protected List<WebElement> waitForElementsToBeVisible(List<WebElement> elements) {
        try {
            return wait.until(ExpectedConditions.visibilityOfAllElements(elements));
        } catch (TimeoutException e) {
            e.getMessage();
            return Collections.emptyList();
        }
    }


    protected void waitForElementToBeClickable(WebElement element) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(element));
        } catch (TimeoutException e) {
            e.getMessage();
        }
    }

    protected void waitPageToLoad() {
        wait.until(driver -> this.jsExecutor.executeScript("return document.readyState").equals("complete"));
    }

    protected void clickElementUsingJS(WebElement element) {
        try {
            jsExecutor.executeScript("arguments[0].click();", element);
        } catch (Exception e) {
            System.out.println("Exception occurred while clicking the element using JS: " + e.getMessage());
        }
    }

    protected void scrollToTop() {
        jsExecutor.executeScript("window.scrollTo(0, 0);");
    }

    protected void scrollToBottom() {
        jsExecutor.executeScript("window.scrollTo(0, document.body.scrollHeight);");
    }

    protected void moveToElementAndClick(WebElement element) {
        actions.moveToElement(element).click().perform();
    }

    protected void keyboardPressArrowDown() {
        actions.sendKeys(Keys.ARROW_DOWN)
                .perform();
    }

    protected void keyboardPressArrowUp() {
        actions.sendKeys(Keys.ARROW_UP)
                .perform();
    }

    protected void keyboardPressEnterKey() {
        actions.sendKeys(Keys.ENTER).perform();
    }

    protected void keyboardPressBackspaceKey() {
        actions.sendKeys(Keys.CONTROL, "a").sendKeys(Keys.BACK_SPACE).perform();
    }

    protected void keyboardPressEscapeKey() {
        actions.sendKeys(Keys.ESCAPE).perform();
    }

    protected void keyboardPressControlAndKKey() {
        actions.keyDown(Keys.CONTROL)
                .sendKeys("k")
                .keyUp(Keys.CONTROL)
                .perform();
    }

    protected void keyboardPressCtrAndKKey() {
        String ctrlK = Keys.chord(Keys.CONTROL, "k");
        actions.sendKeys(ctrlK).perform();
    }

    protected void actionsTypeIn(String text) {
        actions.sendKeys(text).perform();
    }

    protected void keyboardDeleteText() {
        actions.keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).sendKeys(Keys.DELETE).perform();
    }

}

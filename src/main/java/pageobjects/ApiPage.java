package pageobjects;

import base.BasePage;
import lombok.Getter;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

@Getter
public class ApiPage extends BasePage {

    public ApiPage(){
        super();
    }

    @FindBy(css=".breadcrumbs .breadcrumbs__item:nth-of-type(2)")
    private WebElement breadcrumbLink;

    @FindBy(css="breadcrumbs")
    private List<WebElement> breadcrumbs;

    @FindBy(css="header h1")
    private WebElement mainHeading;


    public void navigateToApiDocumentationPage(){
        navigateToUrl("https://webdriver.io/docs/api");
        }


    public boolean mainHeadingContains(String word) {
        waitPageToLoad();
        waitForElementVisibility(mainHeading);
        String headingText = mainHeading.getText();
        return headingText.toLowerCase().contains(word.toLowerCase());
    }

    public boolean isWordPresentInBreadcrumbs(String word) {
        List<String> breadcrumbTexts = iterateOverBreadcrumbLinks(breadcrumbs);
        return breadcrumbTexts.stream()
                .anyMatch(text -> text.toLowerCase().contains(word.toLowerCase()));
    }

}

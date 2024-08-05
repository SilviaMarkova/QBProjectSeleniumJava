package pageobjects;

import base.BasePage;
import lombok.Getter;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

@Getter
public class DocsPage extends BasePage {

    public DocsPage() {
        super();
    }

    @FindBy(css = "breadcrumbs")
    private List<WebElement> breadcrumbs;

    @FindBy(css = "header h1")
    private WebElement mainHeading;

    public boolean mainHeadingContains(String word) {
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

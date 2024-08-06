package pageobjects;

import base.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.stream.Collectors;

public class SidebarPage extends BasePage {

    public SidebarPage() {
        super();
    }

    @FindBy(css = "nav.menu .theme-doc-sidebar-menu .menu__list-item")
    private List<WebElement> sidebarItems;

    @FindBy(css = ".menu__list-item-collapsible a[href*='/docs/api/protocols']")
    private WebElement protocolsSection;

    @FindBy(css = "li.theme-doc-sidebar-item-category .menu__list-item-collapsible .menu__caret")
    private WebElement protocolsExpandButton;

    @FindBy(css = "li.theme-doc-sidebar-item-category:has(a[href*='/docs/api/protocols']) ul.menu__list li a")
    private List<WebElement> protocolItems;

    public boolean isWordPresentInSidebar(String searchWord) {
        return sidebarItems.stream()
                .anyMatch(item -> item.getText().toLowerCase().trim().contains(searchWord.toLowerCase().trim()));
    }

    public void printSidebarItems() {
        sidebarItems.forEach(item -> System.out.println(item.getText()));
    }

    public List<String> getProtocolItems() {
        waitForElementsToBeVisible(protocolItems);
        return protocolItems.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    public void clickProtocolsLink() {
        waitForElementToBeClickable(protocolsSection);
        protocolsSection.click();
    }

    public void clickProtocolsExpandButton() {
        waitForElementToBeClickable(protocolsExpandButton);
        protocolsSection.click();
    }

    public boolean isProtocolsSectionDisplayed() {
        try {
            waitPageToLoad();
            return protocolsSection.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}

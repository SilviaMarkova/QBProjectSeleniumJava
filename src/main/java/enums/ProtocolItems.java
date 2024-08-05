package enums;

public enum ProtocolItems {

    APPIUM("Appium"),
    CHROMIUM("Chromium"),
    FIREFOX("Firefox"),
    JSON_WIRE_PROTOCOL("JSON Wire Protocol"),
    MOBILE_JSON_WIRE_PROTOCOL("Mobile JSON Wire Protocol"),
    SAUCE_LABS("Sauce Labs"),
    SELENIUM_STANDALONE("Selenium Standalone"),
    WEBDRIVER_PROTOCOL("WebDriver Protocol"),
    WEBDRIVER_BIDI_PROTOCOL("WebDriver Bidi Protocol");

    private final String protocolNames;

    ProtocolItems(String protocolNames) {
        this.protocolNames = protocolNames;
    }

    public String getValue() {
        return this.protocolNames;
    }
}

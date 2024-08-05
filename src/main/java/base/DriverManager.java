package base;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

@Slf4j
public class DriverManager {

    private static final ThreadLocal<WebDriver> webDriver = new ThreadLocal<>();
    //private static final ThreadLocal<WebDriverWait> wait = new ThreadLocal<>();


    public static WebDriver getDriver() {
        if (webDriver.get() == null) {
            webDriver.set(initDriver());
        }
        return webDriver.get();
    }

    public static WebDriver initDriver() {
        WebDriver driver = null;

        switch (getBrowserType().toLowerCase()) {
            case "chrome" -> {
                log.info("Setting up ChromeDriver");
                System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + File.separator + "src" + File.separator + "main" + File.separator +
                        "java" + File.separator + "drivers" + File.separator + "chromedriver.exe");
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--disable-search-engine-choice-screen");
                chromeOptions.setPageLoadStrategy(PageLoadStrategy.EAGER);
                driver = new ChromeDriver(chromeOptions);
            }
            case "firefox" -> {
                log.info("Setting up FirefoxDriver");
                System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + File.separator + "src" + File.separator + "main" + File.separator +
                        "java" + File.separator + "drivers" + File.separator + "geckodriver.exe");
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                //firefoxOptions.addArguments("--remote-allow-origins=*");
                firefoxOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
                driver = new FirefoxDriver(firefoxOptions);

            }
            case "edge" -> {
                log.info("Setting up EdgeDriver");
                System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + File.separator + "src" + File.separator + "main" + File.separator +
                        "java" + File.separator + "drivers" + File.separator + "msedgedriver.exe");
                EdgeOptions edgeOptions = new EdgeOptions();
                edgeOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
                driver = new EdgeDriver(edgeOptions);

            }

        }
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        return driver;

    }

    private static String getBrowserType() {
        String browserType = null;

        try {
            Properties properties = new Properties();
            FileInputStream file = new FileInputStream(System.getProperty("user.dir") + "/src/main/java/resources/config.properties");
            properties.load(file);
            browserType = properties.getProperty("browser").toLowerCase().trim();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            log.error("IOException occurred while reading config.properties: {}", e.getMessage(), e);
        }
        return browserType;
    }

    public static void closeDriver() {
        if (webDriver.get() != null) {
            webDriver.get().quit();
            webDriver.remove();
        }
    }
}

package base;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;

public class Hooks extends BasePage {


    public Hooks() {
        super();
    }

    @BeforeClass
    public void setup() {
        getDriver();
    }


    @BeforeMethod(alwaysRun = true)
    public void setupMethod() {
        navigateToBaseURL();
    }


    @AfterMethod(alwaysRun = true)
    public void tearDownMethod(ITestResult result) throws IOException {
        if (result.getStatus() == ITestResult.FAILURE) {
            File destFile = new File("screenshot" + File.separator + result.getTestClass().getRealClass().getSimpleName()
                    + "_" + result.getMethod().getMethodName() + ".png");
            takeScreenshot(destFile);
        }

    }

    @AfterClass
    public void tearDownClass() {
        DriverManager.closeDriver();
    }

    private void takeScreenshot(File destFile) throws IOException {
        TakesScreenshot takeScreenshot = (TakesScreenshot) getDriver();
        File srcFile = takeScreenshot.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(srcFile, destFile);
    }

    @AfterSuite(alwaysRun = true)
    public void tearDownSuite() {
        DriverManager.closeDriver();
    }
}

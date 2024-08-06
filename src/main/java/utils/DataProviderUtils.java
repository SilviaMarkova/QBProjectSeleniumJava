package utils;

import org.testng.annotations.DataProvider;

import java.io.File;
import java.util.Arrays;
import java.util.List;

public class DataProviderUtils {

    @DataProvider(name = "ExactSearchMatches")
    public static Object[][] exactSearchMatches() {
        List<String[]> data = CsvReader.getCSVData(System.getProperty("user.dir") + File.separator + "src" + File.separator + "main" +
                File.separator + "resources" + File.separator + "test_data" + File.separator + "ExactSearchMatches.csv");
        for (String[] entry : data) {
            System.out.println(Arrays.toString(entry));
        }
        return data.toArray(new Object[0][]);
    }

    @DataProvider(name = "InvalidSearchWords")
    public static Object[][] noSearchMatches() {
        List<String[]> data = CsvReader.getCSVData(System.getProperty("user.dir") + File.separator + "src" + File.separator + "main" +
                File.separator + "resources" + File.separator + "test_data" + File.separator + "InvalidSearchWords.csv");
        return data.toArray(new Object[0][]);
    }

    @DataProvider(name = "PartialSearchMatches")
    public static Object[][] partialSearchMatches() {
        List<String[]> data = CsvReader.getCSVData(System.getProperty("user.dir") + File.separator + "src" + File.separator + "main" +
                File.separator + "resources" + File.separator + "test_data" + File.separator + "PartialSearchMatches.csv");
        return data.toArray(new Object[0][]);
    }
}

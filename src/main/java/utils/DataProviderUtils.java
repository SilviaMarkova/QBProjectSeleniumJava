package utils;

import org.testng.annotations.DataProvider;

import java.util.Arrays;
import java.util.List;

public class DataProviderUtils {

    @DataProvider(name = "ExactSearchMatches")
    public static Object[][] exactSearchMatches() {
        List<String[]> data = CsvReader.getCSVData(System.getProperty("user.dir") + "/src/main/resources/test_data/ExactSearchMatches.csv");
        for (String[] entry : data) {
            System.out.println(Arrays.toString(entry));
        }
        return data.toArray(new Object[0][]);
    }

    @DataProvider(name = "InvalidSearchWords")
    public static Object[][] noSearchMatches() {
        List<String[]> data = CsvReader.getCSVData(System.getProperty("user.dir") + "/src/main/resources/test_data/InvalidSearchWords.csv");
        return data.toArray(new Object[0][]);
    }

    @DataProvider(name = "PartialSearchMatches")
    public static Object[][] partialSearchMatches() {
        List<String[]> data = CsvReader.getCSVData(System.getProperty("user.dir") + "/src/main/resources/test_data/PartialSearchMatches.csv");
        return data.toArray(new Object[0][]);
    }
}

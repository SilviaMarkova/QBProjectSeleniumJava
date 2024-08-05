package dataproviders;

import org.testng.annotations.DataProvider;

public class SearchDataProvider {

    @DataProvider(name = "searchWordsProvider")
    public static Object[][] provideSearchWords() {
        return new Object[][] {
                {"setValue"},
                {"Click"},
        };
    }
}

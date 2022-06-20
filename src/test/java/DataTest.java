import org.apache.commons.lang3.RandomStringUtils;

public class DataTest {

    public static String getCategoryName()
    {
        String generatedString = RandomStringUtils.randomAlphabetic(1);
        return ("Labrador" + generatedString);
    }

    public static String getPetName()
    {
        String generatedString = RandomStringUtils.randomAlphabetic(1);
        return ("Blacky" + generatedString);
    }

    public static String getTagsName()
    {
        String generatedString = RandomStringUtils.randomAlphabetic(1);
        return ("Domestico" + generatedString);
    }
}

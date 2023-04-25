import java.util.Arrays;

public class SpamAnalyzer extends KeywordAnalyzer {

    public Label GetLabel()
    {
        return Label.SPAM;
    }

    public String[] GetKeywords()
    {
        return super.words;
    }

    public SpamAnalyzer(String[] textArray)
    {
        super(textArray, new String[] { "dddd", "aaa", "bb" } );
    }
}

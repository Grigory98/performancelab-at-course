import java.util.Arrays;

public class NegativeTextAnalyzer extends KeywordAnalyzer {

    public Label GetLabel()
    {
        return Label.NEGATIVE_TEXT;
    }

    public String[] GetKeywords()
    {
        return super.words;
    }

    public NegativeTextAnalyzer(String[] textArray)
    {
        super(textArray, new String[] { ":(", "=(", ":|" } );
    }
}

import java.util.Arrays;

abstract public class KeywordAnalyzer implements TextAnalyzer {

    protected String[] words;

    /// Возвращает набор ключевых слов для анализа.
    public abstract String[] GetKeywords();

    /// Возвращает метку, если рестарт анализа положительный.
    public abstract Label GetLabel();

    public Label processText(String text) {
        String[] keywords = GetKeywords();
        for (int i = 0; i < keywords.length; i++) {
            if (text.indexOf(keywords[i]) != -1) {
                return GetLabel();
            }
        }
        return Label.OK;
    }

    public KeywordAnalyzer(String[] words, String[] filter)
    {
        this.words = words;
        for (var w : this.words) {
            if(Arrays.asList(filter).contains(w))
                System.out.println(this.GetLabel());
            else
                System.out.println(Label.OK);
        }
    }
}

public class TooLongTextAnalyzer implements TextAnalyzer{

    private int limit;

    public Label GetLabel()
    {
        return Label.TOO_LONG;
    }

    public Label processText(String text) {
        return text.length() > limit ? Label.TOO_LONG : Label.OK;
    }

    public TooLongTextAnalyzer(int limit)
    {
        this.limit = limit;
    }
}

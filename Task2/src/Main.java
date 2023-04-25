public class Main {

    public static void main(String[] args) {

        var test1 = new SpamAnalyzer(new String[] { "text1", "bb" });
        var test2 = new NegativeTextAnalyzer(new String[] { "abc", "text12", "bb", ":(" });
        var test3 = new TooLongTextAnalyzer(5);
        var test4 = test3.processText("abcdefghijklmnopqrstuvwxyz");
        System.out.println(test4);

        var test5 = CheckText(new TextAnalyzer[] {test1, test2, test3}, "sss");
        System.out.println(test5);
    }

    public static Label CheckText(TextAnalyzer[] analyzers, String text) {
        for (TextAnalyzer analyzer : analyzers) {
            Label label = analyzer.processText(text);
            if (label != Label.OK) {
                return label;
            }
        }
        return Label.OK;
    }
}
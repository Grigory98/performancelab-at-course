package steps;

import java.util.HashMap;

public class PageSteps extends Steps {

    //region Конструктор

    public PageSteps(HashMap<String, Object> scContext) {
        super(scContext);
    }

    //endregion


    //region Вложенные классы

    public static class Context {
        public static final String Page = "Page";
    }

    //endregion
}

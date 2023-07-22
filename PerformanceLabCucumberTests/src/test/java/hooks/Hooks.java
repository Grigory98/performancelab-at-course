package hooks;

import core.DriversManager;
import io.cucumber.java.After;
import steps.Steps;
import java.util.HashMap;

public class Hooks extends Steps {

    //region Конструктор
    public Hooks(HashMap<String, Object> scContext) {
        super(scContext);
    }
    //endregion

    @After
    public void tearEach()
    {
        DriversManager.closeBrowser();
    }
}

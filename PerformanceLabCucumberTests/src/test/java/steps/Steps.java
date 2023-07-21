package steps;

import java.util.HashMap;

public class Steps
{
    public HashMap<String, Object> scContext = new HashMap<>();

    public Steps(HashMap<String, Object> scenarioContext)
    {
        this.scContext = scenarioContext;
    }
}

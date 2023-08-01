package api.schemes;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Users {

    @JsonProperty("age")
    public int age;

    @JsonProperty("firstName")
    public String firstName;

    @JsonProperty("money")
    public float money;

    @JsonProperty("secondName")
    public String secondName;

    @JsonProperty("sex")
    public String sex;

    @JsonProperty("id")
    public int id;
}

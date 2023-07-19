package config;

public class ApplicationConfig {
    public final String baseUrl;
    public final String username;
    public final String userPassword;

    public ApplicationConfig() {
        this.baseUrl = "http://77.50.236.203:4881/";
        this.username =  "user@pflb.ru";
        this.userPassword = "user";
    }
}

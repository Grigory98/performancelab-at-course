package config;

public class ApplicationConfig {
    public final String baseUrl;
    public final String apiUrl;
    public final int apiPort;
    public final String username;
    public final String password;


    public ApplicationConfig()
    {
        this.baseUrl = "http://77.50.236.203:4881/";
        this.apiUrl = "http://77.50.236.203";
        this.apiPort = 4879;
        this.username = "user@pflb.ru";
        this.password = "user";
    }
}

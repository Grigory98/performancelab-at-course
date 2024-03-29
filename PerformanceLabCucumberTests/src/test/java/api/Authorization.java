package api;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Authorization {
    public static class AuthRequest {

        public String username;
        public String password;

        public AuthRequest(String username, String password) {
            this.username = username;
            this.password = password;
        }

        public AuthRequest() {}
    }

    public static class AuthResponse {
        @JsonProperty("access_token")
        public String accessToken;
    }
}
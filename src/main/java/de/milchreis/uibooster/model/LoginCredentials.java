package de.milchreis.uibooster.model;

public class LoginCredentials {

    private String username;
    private String password;

    public LoginCredentials(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public boolean isFilled() {
        return username != null && !username.isEmpty() && password != null && !password.isEmpty();
    }
}

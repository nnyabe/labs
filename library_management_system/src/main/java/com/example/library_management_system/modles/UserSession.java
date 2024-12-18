package com.example.library_management_system.modles;

public class UserSession {
    private static UserSession instance;  // Singleton instance
    private String username;  // Username for the current session

    public UserSession() {}

    public static UserSession getInstance() {
        if (instance == null) {
            instance = new UserSession();
        }
        return instance;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}

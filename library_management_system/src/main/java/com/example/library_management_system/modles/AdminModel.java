package com.example.library_management_system.modles;

import java.util.Date;

/**
 * Represents an administrator user in the library management system.
 * This class extends the UserModel to include additional properties such as role and password.
 */
public class AdminModel extends UserModel {

    private Enums.Roles role;
    private String password;


    public AdminModel(int id, String username, String email,
                      Date created_at, Date updated_at,
                      Enums.Roles role, String password) {
        super(id, username, email, created_at, updated_at);
        this.password = password;
        this.role = role;
    }

    public String getPassword() {
        return password;
    }


    public void setPassword(String password) {
        this.password = password;
    }


    public Enums.Roles getRole() {
        return role;
    }


    public void setRole(Enums.Roles role) {
        this.role = role;
    }
}

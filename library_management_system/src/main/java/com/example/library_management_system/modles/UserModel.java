package com.example.library_management_system.modles;

import java.util.Date;

public abstract class UserModel {
    private int id;
    private String username;
    private String email;
    private Date created_at;
    private Date updated_at;

    public UserModel() {}

    public UserModel(int id, String username,
                     String email, Date created_at,
                     Date updated_at) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public Date getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Date updated_at) {
        this.updated_at = updated_at;
    }
}

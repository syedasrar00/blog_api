package com.asrar_blog.payloads;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

public class UserDTO {
    private int id;
    @NotNull
    private String name;
    @Email
    private String email;
    @NotNull
    private String password;
    @NotNull
    private String about;

    public UserDTO() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }
}


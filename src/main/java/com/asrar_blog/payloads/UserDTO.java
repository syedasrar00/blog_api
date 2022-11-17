package com.asrar_blog.payloads;


import javax.validation.constraints.*;

public class UserDTO {
    private int id;
    @NotEmpty
    @Size(min=4, message="Username must be at least 4 characters")
    private String name;
    @Email(message = "Not a valid email.")
    private String email;
    @NotEmpty
    @Size(min=4, max=10, message ="password length must be between 4_10 characters")
    private String password;
    @NotEmpty
    @Size(min=7, message="about must be at least 7 characters")
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


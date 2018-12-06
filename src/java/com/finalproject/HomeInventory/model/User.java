package com.finalproject.HomeInventory.model;

public class User {

    String username;
    String password;
    String passwordConfirm;
    String email;
    String firstname;
    String lastname;
    int active;
    int isadmin;
    
    
    public User() {
        super();
    }
    public User(String username, String password, String email,
            String firstname, String lastname, int active, int isAdmin) {
        super();
        this.username = username;
        this.password = password;
        this.email = email;
        this.firstname = firstname;
        this.lastname = lastname;
        this.active = active;
        this.isadmin = isAdmin;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getFirstname() {
        return firstname;
    }
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }
    public String getLastname() {
        return lastname;
    }
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
    public int getActive() {
        return active;
    }
    public void setActive(int active) {
        this.active = active;
    }

    public int getIsadmin() {
        return isadmin;
    }

    public void setIsadmin(int isadmin) {
        this.isadmin = isadmin;
    }
    public String getPasswordConfirm() {
        return passwordConfirm;
    }
    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }
   
    
    
}

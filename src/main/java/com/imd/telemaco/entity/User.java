/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imd.telemaco.entity;

import java.util.Date;

/**
 *
 * @author franklin
 */
public class User {
    private int id;
    private String name;
    private String lastName;
    private String email;
    private String password;
    private Date birth;
    private String gender;
    
    public User() { }

    public User(String name, String lastName, String email, String password, Date birth, String gender) {
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.birth = birth;
        this.gender = gender;
    }
    
    //* Getters and Setters [START]
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

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {    
        this.gender = gender;
    }
    //* Getters and Setters [END]
    
    /**
     * TODO
     * @return 
     */
    @Override
    public String toString() {
        return "User{" + "name=" + name + ", email=" + email + ", password=" + password + '}';
    }
}

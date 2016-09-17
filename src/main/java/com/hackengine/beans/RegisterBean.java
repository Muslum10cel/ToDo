/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hackengine.beans;

import com.hackengine.entities.Users;
import com.hackengine.transactions.Operations;
import java.io.Serializable;
import java.util.Calendar;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author muslumoncel
 */
@ManagedBean
@RequestScoped
public class RegisterBean implements Serializable {

    private String username;

    private String password;

    private String mail;

    private Calendar joinedDate;

    public RegisterBean() {
    }

    public RegisterBean(String username, String password, String mail) {
        this.username = username;
        this.password = password;
        this.mail = mail;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getMail() {
        return mail;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String register() {
        return (new Operations()).register(new Users(username, password, mail, Calendar.getInstance()));
    }
}

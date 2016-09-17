/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hackengine.beans;

import com.hackengine.entities.ToDo;
import com.hackengine.entities.Users;
import com.hackengine.tags.Tags;
import com.hackengine.transactions.Transactions;
import com.hackengine.utils.SessionUtils;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author muslumoncel
 */
@ManagedBean
@SessionScoped
public class UserBean {

    private Transactions operations = null;
    private Users user = null;

    private String title;

    public UserBean() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @PostConstruct
    public void init() {
        operations = new Transactions();
        user = (Users) SessionUtils.getSession().getAttribute(Tags.LOGGED_USER);
    }

    public void addToDo() {
        operations.addToDo(user, new ToDo(title));
    }

    public String logOut() {
        Transactions.closeSession();
        SessionUtils.getSession().invalidate();
        return Tags.THANKS;
    }
}

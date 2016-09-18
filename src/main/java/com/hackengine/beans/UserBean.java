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
import java.util.List;
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
    private List<ToDo> allToDos;

    private String title;

    public UserBean() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAllToDos(List<ToDo> allToDos) {
        this.allToDos = allToDos;
    }

    public List<ToDo> getAllToDos() {
        return allToDos;
    }

    @PostConstruct
    public void init() {
        operations = new Transactions();
        user = (Users) SessionUtils.getSession().getAttribute(Tags.LOGGED_USER);
        allToDos = Transactions.getAllToDos(user.getID());
    }

    public void addToDo() {
        operations.addToDo(user, new ToDo(title));
        allToDos = Transactions.getAllToDos(user.getID());
    }

    public String goToDetails(ToDo todo) {
        SessionUtils.getSession().setAttribute(Tags.TO_DO, todo);
        return Tags.DETAILS_PAGE;
    }

    public String logOut() {
        Transactions.closeSession();
        SessionUtils.getSession().invalidate();
        return Tags.THANKS;
    }
}

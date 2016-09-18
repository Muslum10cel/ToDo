/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hackengine.transactions;

import com.hackengine.entities.Details;
import com.hackengine.entities.ToDo;
import com.hackengine.entities.Users;
import com.hackengine.queries.Queries;
import com.hackengine.tags.Tags;
import com.hackengine.utils.SessionUtils;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author muslumoncel
 */
public class Transactions {

    private static final SessionFactory factory = new Configuration().configure().buildSessionFactory();
    private static Session session = null;

    private static void openSession() {
        session = factory.openSession();
    }

    public String register(Users register) {
        try {
            openSession();
            session.beginTransaction();
            session.save(register);
            session.getTransaction().commit();
            System.out.println(register);
            return Tags.SUCCESS;
        } catch (Exception e) {
            return Tags.FAIL;
        }
    }

    public String logIn(String username, String password) {
        openSession();
        Users u = (Users) session.createQuery(Queries.LOG_IN_QUERY).setString(0, username).uniqueResult();
        if (u != null && u.getPassword().equals(password)) {
            SessionUtils.getSession().setAttribute(Tags.LOGGED_USER, u);
            return Tags.SUCCESS;
        }
        return Tags.FAIL;
    }

    public void addToDo(Users users, ToDo toDo) {
        openSession();
        session.beginTransaction();
        session.save(toDo);
        toDo.setUsers(users);
        users.getToDos().add(toDo);
        session.getTransaction().commit();
    }

    public static List<ToDo> getAllToDos(int id) {
        return session.createQuery(Queries.GET_ALL_TODOS).setInteger(0, id).list();
    }

    public static List<Details> getAllDetails(int id) {
        return session.createQuery(Queries.GET_ALL_DETAILS).setInteger(0, id).list();
    }

    public void mapSubEventToDo(ToDo td, Details details) {
        openSession();
        session.beginTransaction();
        session.save(details);
        details.setTodos(td);
        td.getDetails().add(details);
        session.getTransaction().commit();
    }

    public void deleteDetails(Details details) {
        openSession();
        session.beginTransaction();
        session.delete(details);
        session.getTransaction().commit();
    }

    public void deleteToDo(ToDo todo) {
        session.createQuery(Queries.DELETE_DETAILS).setInteger(0, todo.getID()).executeUpdate();
        openSession();
        session.beginTransaction();
        session.delete(todo);
        session.getTransaction().commit();
    }

    public static void closeSession() {
        session.clear();
        session.disconnect();
        session.close();
    }
}

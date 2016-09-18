/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hackengine.queries;

/**
 *
 * @author muslumoncel
 */
public class Queries {

    public static final String LOG_IN_QUERY = "FROM Users U WHERE U.username = ?";
    public static final String GET_ALL_TODOS = "FROM ToDo TD WHERE TD.users.ID = ?";
    public static final String GET_ALL_DETAILS = "FROM Details D WHERE D.todos.ID = ?";
    public static final String DELETE_DETAILS = "DELETE FROM Details D WHERE D.todos.ID = ?";
}

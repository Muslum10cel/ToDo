/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hackengine.entities;

import com.hackengine.db.ColumnNames;
import com.hackengine.db.TableNames;
import com.hackengine.priorities.Priority;
import java.io.Serializable;
import java.util.Calendar;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author muslumoncel
 */
@Entity
@Table(name = TableNames.DETAILS)
public class Details implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int ID;

    @Column(name = ColumnNames.EVENT, nullable = false, updatable = true, unique = true)
    private String event;

    @Enumerated(EnumType.STRING)
    @Column(name = ColumnNames.PRIORITY, nullable = false, updatable = true)
    private Priority priority;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = ColumnNames.EVENT_DATE, nullable = false, updatable = true)
    private Calendar eventDate;
    
    @ManyToOne
    @JoinColumn(name = ColumnNames.TO_DO_ID)
    private ToDo todos;

    public Details() {
    }

    public Details(String event, Priority priority, Calendar eventDate) {
        this.event = event;
        this.priority = priority;
        this.eventDate = eventDate;
    }
    
    public int getID() {
        return ID;
    }

    public String getEvent() {
        return event;
    }

    public Priority getPriority() {
        return priority;
    }

    public Calendar getEventDate() {
        return eventDate;
    }

    public ToDo getTodos() {
        return todos;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public void setEventDate(Calendar eventDate) {
        this.eventDate = eventDate;
    }

    public void setTodos(ToDo todos) {
        this.todos = todos;
    }

    @Override
    public String toString() {
        return "Details{" + "event=" + event + ", priority=" + priority + ", eventDate=" + eventDate + '}';
    }
}

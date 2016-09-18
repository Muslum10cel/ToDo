/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hackengine.beans;

import com.hackengine.entities.Details;
import com.hackengine.entities.ToDo;
import com.hackengine.priorities.Priority;
import com.hackengine.tags.Tags;
import com.hackengine.transactions.Transactions;
import com.hackengine.utils.SessionUtils;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author muslumoncel
 */
@ManagedBean
@RequestScoped
public class DetailsBean implements Serializable {

    private String event;
    private Priority priority;
    private ToDo td = null;
    private Transactions transactions = null;
    private List<Details> allDetails;
    private Date eventDate;

    @PostConstruct
    public void init() {
        td = (ToDo) SessionUtils.getSession().getAttribute(Tags.TO_DO);
        transactions = new Transactions();
        allDetails = Transactions.getAllDetails(td.getID());
    }

    public Priority[] getPriorities() {
        return Priority.values();
    }

    public String getEvent() {
        return event;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public List<Details> getAllDetails() {
        return allDetails;
    }

    public void setAllDetails(List<Details> allDetails) {
        this.allDetails = allDetails;
    }

    public Date getEventDate() {
        return eventDate;
    }

    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }
    
    public void addSubEvent() {
        transactions.mapSubEventToDo(td, new Details(event, priority, eventDate));
        allDetails = Transactions.getAllDetails(td.getID());
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sypron.managed;

import com.sypron.dto.UserDTO;
import com.sypron.entity.Action;
import com.sypron.entity.Complaint;
import com.sypron.entity.User;
import com.sypron.facade.ActionFacade;
import com.sypron.facade.ComplaintFacade;
import com.sypron.facade.StatusFacade;
import com.sypron.util.SessionUtils;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

/**
 *
 * @author hisham
 */
@ManagedBean
@ViewScoped
public class ViewComplaintBackingBean {

    @Inject
    private ComplaintFacade complaintFacade;
    
    @Inject
    private ActionFacade actionFacade;
    
    @Inject
    private StatusFacade statusFacade;
    private UserDTO currentUserDTO;
    private Complaint currentComplaint;
    private Integer complaintIdParam;
    private List<Action> complaintActions;
    private Action newComplaintAction;
    
    /**
     * Creates a new instance of viewComplaint
     */
    public ViewComplaintBackingBean() {
        currentUserDTO = SessionUtils.getLoggedUser();
        newComplaintAction = new Action();
//        complaintActions = new ArrayList<>();
    }

    public Complaint getCurrentComplaint() {
        if(currentComplaint == null){
            currentComplaint = complaintFacade.find(complaintIdParam);
        }
        return currentComplaint;
    }

    public void setCurrentComplaint(Complaint currentComplaint) {
        this.currentComplaint = currentComplaint;
    }

    public Integer getComplaintIdParam() {
        return complaintIdParam;
    }

    public void setComplaintIdParam(Integer complaintIdParam) {
        this.complaintIdParam = complaintIdParam;
    }

    public List<Action> getComplaintActions() {
        if(complaintActions == null){
            complaintActions = actionFacade.getComplaintActions(complaintIdParam);
            System.err.println(">>>>>>>>>>>>List"+complaintActions.size());
        }
        return complaintActions;
    }

    public void setComplaintActions(List<Action> complaintActions) {
        this.complaintActions = complaintActions;
    }

    public Action getNewComplaintAction() {
        return newComplaintAction;
    }

    public void setNewComplaintAction(Action newComplaintAction) {
        this.newComplaintAction = newComplaintAction;
    }
    
    public void addAction(){
        newComplaintAction.setActionDate(new Date());
        newComplaintAction.setComplaint(currentComplaint);
        newComplaintAction.setCreateDate(new Date());
        newComplaintAction.setUser(new User(currentUserDTO.getId()));
        actionFacade.create(newComplaintAction);
        if(complaintActions == null){
            complaintActions = new ArrayList<>();
        }
        complaintActions.add(newComplaintAction);
    }
    
}

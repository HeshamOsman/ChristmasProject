/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sypron.managed;

import com.sypron.dto.UserDTO;
import com.sypron.entity.Action;
import com.sypron.entity.Complaint;
import com.sypron.facade.ActionFacade;
import com.sypron.facade.StatusFacade;
import com.sypron.facade.SuggestionFacade;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;

/**
 *
 * @author hisham
 */
@Named(value = "viewSuggestion")
@Dependent
public class ViewSuggestion {
    @Inject
    private SuggestionFacade suggestionFacade;
    @Inject
    private ActionFacade actionFacade;

    @Inject
    private StatusFacade statusFacade;
    private UserDTO currentUserDTO;
    private Complaint currentComplaint;
    private Integer complaintIdParam;
    private List<Action> complaintActions;
    private Action newComplaintAction;
    private boolean renderAddAction;
    /**
     * Creates a new instance of ViewSuggestion
     */
    public ViewSuggestion() {
    }
    
}

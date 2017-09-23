/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sypron.managed;

import com.sypron.dto.UserDTO;
import com.sypron.entity.Complaint;
import com.sypron.entity.Complaint_;
import com.sypron.facade.ComplaintFacade;
import com.sypron.util.SessionUtils;
import java.io.IOException;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author hisham
 */
@ManagedBean
@ViewScoped
public class ComplaintListingBackingBean {
    
    @Inject
    private ComplaintFacade complaintFacade;
    
//    @ManagedProperty("#{param.scope}")
    private String scope;
    private String ini = "int";
    private List<Complaint> complaints;
    private Complaint selectedComplaint;
    private UserDTO currentUserDTO;
    /**
     * Creates a new instance of ComplaintListingBackingBean
     */
    public ComplaintListingBackingBean() {
    }
    @PostConstruct
    public void onInit(){
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>scope "+scope);
        currentUserDTO = SessionUtils.getLoggedUser();
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getIni() {
        return ini;
    }

    public void setIni(String ini) {
        this.ini = ini;
    }

    public Complaint getSelectedComplaint() {
        return selectedComplaint;
    }

    public void setSelectedComplaint(Complaint selectedComplaint) {
        this.selectedComplaint = selectedComplaint;
    }
    
    

    public List<Complaint> getComplaints() {
        if(complaints == null){
            if(currentUserDTO.getPermissionsMap()
                    .get("complaint", "list",scope)==null){
                FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN,
                            "Not Auth",
                            null));
            }else{
                complaints = complaintFacade.getComplaintsByScope(scope,
                        currentUserDTO.getId(), currentUserDTO.getUserDepartmentId());
            }
        }
        return complaints;
    }

    public void setComplaints(List<Complaint> complaints) {
        this.complaints = complaints;
    }
    
    public void onRowSelected(SelectEvent event) {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest origRequest = (HttpServletRequest) context.getExternalContext().getRequest();
        String contextPath = origRequest.getContextPath();
        try {
            FacesContext.getCurrentInstance().getExternalContext()
                    .redirect(contextPath + "/viewComplaint.xhtml?complaintId="+((Complaint) event.getObject()).getId());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}

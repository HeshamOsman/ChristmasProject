/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sypron.managed;

import com.sypron.entity.User;
import com.sypron.facade.UserFacade;
import java.io.Serializable;
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
@ManagedBean()
@ViewScoped
public class UserListingBackingBean implements Serializable{

    /**
     * Creates a new instance of UserListingBackingBean
     */
    @Inject
    UserFacade userFacade;
    List<User> users;
    public UserListingBackingBean() {
    }

    public List<User> getUsers() {
        if(users == null){
            users = userFacade.findAll();
        }
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
    
    
    
}

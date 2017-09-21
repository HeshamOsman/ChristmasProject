/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sypron.facade;

import com.sypron.entity.Complaint;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author hisham
 */
@Stateless
public class ComplaintFacade extends AbstractFacade<Complaint> {

    @PersistenceContext(unitName = "TheContributor-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ComplaintFacade() {
        super(Complaint.class);
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sypron.facade;

import com.sypron.entity.Suggestion;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author hisham
 */
@Stateless
public class SuggestionFacade extends AbstractFacade<Suggestion> {

    @PersistenceContext(unitName = "TheContributor-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SuggestionFacade() {
        super(Suggestion.class);
    }
    
}

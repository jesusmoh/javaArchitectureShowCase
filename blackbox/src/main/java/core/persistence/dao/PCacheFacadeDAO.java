/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.persistence.dao;

import core.model.entities.PCache;
import core.persistence.AbstractFacade;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author JOrtiz
 */
@Stateless
public class PCacheFacadeDAO extends AbstractFacade<PCache> {

    @PersistenceContext(unitName = "core_cServer_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PCacheFacadeDAO() {
        super(PCache.class);
    }
    
}

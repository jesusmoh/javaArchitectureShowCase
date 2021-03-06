/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.persistence;

import core.domain.entities.WebMaUsuario;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author JOrtiz
 */
@Stateless
public class WebMaUsuarioFacadeDAO extends AbstractFacade<WebMaUsuario> {

    @PersistenceContext(unitName = "my_persistence_unit")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public WebMaUsuarioFacadeDAO() {
        super(WebMaUsuario.class);
    }
    
}

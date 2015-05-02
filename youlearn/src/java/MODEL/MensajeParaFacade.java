/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MODEL;

import ENTITIES.MensajePara;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Felipe
 */
@Stateless
public class MensajeParaFacade extends AbstractFacade<MensajePara> {
    @PersistenceContext(unitName = "youlearnPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MensajeParaFacade() {
        super(MensajePara.class);
    }
    
}

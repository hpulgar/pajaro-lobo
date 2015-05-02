/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MODEL;

import ENTITIES.PosteosComentario;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Felipe
 */
@Stateless
public class PosteosComentarioFacade extends AbstractFacade<PosteosComentario> {
    @PersistenceContext(unitName = "youlearnPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PosteosComentarioFacade() {
        super(PosteosComentario.class);
    }
    
}

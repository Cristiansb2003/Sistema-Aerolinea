/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facade;

import Entity.Usuarios;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Cristian
 */
@Stateless
@LocalBean
public class UsuariosFacade {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @PersistenceContext(unitName = "Evidencia_1-ejbPU")
    private EntityManager em;

    //Mostrar datos
    public List<Usuarios> findAll() {
        TypedQuery<Usuarios> query;
        query = em.createQuery("SELECT U FROM Usuarios U", Usuarios.class);
        return query.getResultList();
    }

    public List<Usuarios> iniciarSesion(String Email, String contra) {
        TypedQuery<Usuarios> query;
        query = em.createQuery("SELECT U FROM Usuarios U WHERE U.email=:email AND U.contrasena=:contrasena", Usuarios.class);
        query.setParameter("email", Email);
        query.setParameter("contrasena", contra);
        return query.getResultList();

    }
    
    public List<Usuarios> perfil(String Email) {
        TypedQuery<Usuarios> query;
        query = em.createQuery("SELECT U FROM Usuarios U WHERE U.email=:email AND U.perfil=:perfil", Usuarios.class);
        query.setParameter("email", Email);
        query.setParameter("perfil", 3);
        return query.getResultList();

    }


    //Insert
    public void insert(Usuarios u) {
        em.persist(u);
    }

    //Update
    public void update(Usuarios u) {
        em.merge(u);
    }

    public void delete(Usuarios u) {
        em.remove(em.merge(u));
    }
}

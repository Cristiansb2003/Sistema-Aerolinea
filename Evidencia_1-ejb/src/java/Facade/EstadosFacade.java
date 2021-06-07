/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facade;

import Entity.Estados;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author Cristian
 */
@Stateless
@LocalBean
public class EstadosFacade {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @PersistenceContext(unitName = "Evidencia_1-ejbPU")
    private EntityManager em;

    public List<Estados> findAll() {
        TypedQuery<Estados> query;
        query = em.createQuery("SELECT E FROM Estados E", Estados.class);
        return query.getResultList();
    }

    public List<Estados> findAll2() {
        Query query;
        query = em.createNamedQuery("findEstados");
        return query.getResultList();
    }

    //Con TypedQuery
    public List<Estados> findByName(String nombre) {
        TypedQuery<Estados> query;
        query = em.createQuery("SELECT E FROM Estados E WHERE E.nombre=:nombre", Estados.class);
        query.setParameter("nombre", nombre);
        return query.getResultList();
    }

    public List<Estados> findByPais(String pais) {
        TypedQuery<Estados> query;
        query = em.createQuery("SELECT E FROM Estados E WHERE E.pais.nombre=:nombre", Estados.class);
        query.setParameter("nombre", pais);
        return query.getResultList();
    }

    //Con NamedQuery
    public List<Estados> findByNameNQ(String nombre) {
        Query query;
        query = em.createNamedQuery("findByNameEstadoNQ");
        query.setParameter("nombre", nombre);

        return query.getResultList();
    }

    public List<Estados> findByPaisNQ(String pais) {
        Query query;
        query = em.createNamedQuery("findByPaisNQ");
        query.setParameter("nombre", pais);
        return query.getResultList();
    }

    //Insertar
    public void insert(Estados e) {
        em.persist(e);//Aqui con esto se sube a la base de datos
    }
    //Update

    public void update(Estados e) {
        em.merge(e);
    }

    public Estados find(Long id) {
        return em.find(Estados.class, id);
    }

    public void delete(Estados e) {
        em.remove(em.merge(e));
    }

}

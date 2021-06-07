/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facade;

import Entity.Paises;
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
public class PaisesFacade {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @PersistenceContext(unitName = "Evidencia_1-ejbPU")
    private EntityManager em;

    public List<Paises> findAll() {
        TypedQuery<Paises> query;
        query = em.createQuery("SELECT P FROM Paises P", Paises.class);
        return query.getResultList();
    }

    public List<Paises> findAll2() {
        Query query;
        query = em.createNamedQuery("findPaises");
        return query.getResultList();
    }

    //Con TypedQuery
    public List<Paises> findByName(String nombre) {
        TypedQuery<Paises> query;
        query = em.createQuery("SELECT P FROM Paises P WHERE P.nombre=:nombre", Paises.class);
        query.setParameter("nombre", nombre);
        return query.getResultList();
    }

    //Con NamedQuery
    public Paises findByNameNQ2(String nombre) {
        Query query;
        query = em.createNamedQuery("findByNameNQ2");
        query.setParameter("nombre", nombre);

        return (Paises) query.getSingleResult();

    }

    public List<Paises> buscarPaisNombre(String nombre) {
        TypedQuery<Paises> query;
        query = em.createQuery("SELECT P FROM Paises P WHERE UPPER(P.nombre)=:nombre", Paises.class);
        query.setParameter("nombre", nombre.toUpperCase());
        return query.getResultList();
    }

    //Insertar
    public void insert(Paises p) {
        em.persist(p);//Aqui con esto se sube a la base de datos
    }

    //Update
    public void update(Paises p) {
        em.merge(p);
    }

    public void delete(Paises p) {
        em.remove(em.merge(p));
    }

    public Paises find(Long id) {
        return em.find(Paises.class, id);
    }

}

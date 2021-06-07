/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facade;

import Entity.Vuelos;
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
public class VuelosFacade {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @PersistenceContext(unitName = "Evidencia_1-ejbPU")
    private EntityManager em;

    public List<Vuelos> findAll() {
        TypedQuery<Vuelos> query;
        query = em.createQuery("SELECT V FROM Vuelos V", Vuelos.class);
        return query.getResultList();
    }

    public List<Vuelos> findAll2() {
        Query query;
        query = em.createNamedQuery("findVuelos");
        return query.getResultList();
    }

    //Con TypedQuery
    public Vuelos findByNumVuelo(String numVuelo) {
        TypedQuery<Vuelos> query;
        query = em.createQuery("SELECT V FROM Vuelos V WHERE V.numeroVuelos=:numeroVuelos", Vuelos.class);
        query.setParameter("numeroVuelos", numVuelo);
        return (Vuelos) query.getSingleResult();
    }

    public List<Vuelos> findByNumAvion(String numAvion) {
        TypedQuery<Vuelos> query;
        query = em.createQuery("SELECT V FROM Vuelos V WHERE V.numeroAvion.aerolinea=:aerolinea", Vuelos.class);
        query.setParameter("aerolinea", numAvion);
        return query.getResultList();
    }

    //Con NamedQuery
    public List<Vuelos> finByOrigenNQ(String origen) {
        Query query;
        query = em.createNamedQuery("finByOrigenNQ");
        query.setParameter("nombre", origen);
        return query.getResultList();
    }

    public Vuelos findByIdNQ2(Long idParameter) {
        Query query;
        query = em.createNamedQuery("findByIdNQ2");
        query.setParameter("id", idParameter);
        return (Vuelos) query.getSingleResult();
    }
    
    public List<Vuelos> buscarNumeroVuelo(String numAvion){
        TypedQuery<Vuelos> query;
        query = em.createQuery("SELECT V FROM Vuelos V WHERE V.numeroVuelos=:numeroVuelos", Vuelos.class);
        query.setParameter("numeroVuelos", numAvion);
        return query.getResultList();
    }
    
    public List<Vuelos> buscarDestino(String destino){
        TypedQuery<Vuelos> query;
        query = em.createQuery("SELECT V FROM Vuelos V WHERE V.origen=:origen", Vuelos.class);
        query.setParameter("origen", destino);
        return query.getResultList();
    }

    //Insertar
    public void insert(Vuelos v) {
        em.persist(v);//Aqui con esto se sube a la base de datos
    }
    //Update

    public void update(Vuelos v) {
        em.merge(v);
    }

    public void delete(Vuelos v) {
        em.remove(em.merge(v));
    }

}

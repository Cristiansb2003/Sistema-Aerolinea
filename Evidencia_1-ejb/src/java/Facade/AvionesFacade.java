/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facade;

import Entity.Aviones;
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
public class AvionesFacade {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @PersistenceContext(unitName = "Evidencia_1-ejbPU")
    private EntityManager em;

    public List<Aviones> findAll() {
        TypedQuery<Aviones> query;
        query = em.createQuery("SELECT A FROM Aviones A", Aviones.class);
        return query.getResultList();
    }

    public List<Aviones> findAll2() {
        Query query;
        query = em.createNamedQuery("findAviones");
        return query.getResultList();
    }

    //Por TypedQuery
    public List<Aviones> findByNumAvion(String NumeroAvion) {
        TypedQuery<Aviones> query;
        query = em.createQuery("SELECT A FROM Aviones A WHERE A.numeroAvion=:numeroAvion", Aviones.class);
        query.setParameter("numeroAvion", NumeroAvion);
        return query.getResultList();
    }

    public List<Aviones> findByAerolinea(String aerolinea) {
        TypedQuery<Aviones> query;
        query = em.createQuery("SELECT A FROM Aviones A WHERE A.aerolinea=:aerolinea", Aviones.class);
        query.setParameter("aerolinea", aerolinea);
        return query.getResultList();
    }
//Por NamedQuery

    public List<Aviones> findByIdNQ(long idParameter) {
        Query query;
        query = em.createNamedQuery("findByIdNQ");
        query.setParameter("id", idParameter);
        return query.getResultList();
    }

    public List<Aviones> findByNumAndCapNQ(String numeroAvion, int capacidadPasajeros) {
        Query query;
        query = em.createNamedQuery("findByNumAndCapNQ");
        query.setParameter("numeroAvion", numeroAvion);
        query.setParameter("capacidadPasajeros", capacidadPasajeros);

        return query.getResultList();
    }

    public List<Aviones> findByModelo(String modelo) {
        Query query;
        query = em.createNamedQuery("findByModelo");
        query.setParameter("modelo", modelo);

        return query.getResultList();
    }

    public List<Aviones> buscarNumeroAvion(String numeroA) {
        TypedQuery<Aviones> query;
        query = em.createQuery("SELECT A FROM Aviones A WHERE A.numeroAvion=:numeroAvion", Aviones.class);
        query.setParameter("numeroAvion", numeroA);
        return query.getResultList();
    }

    //Insertar
    public void insert(Aviones a) {
        em.persist(a);//Aqui con esto se sube a la base de datos
    }
    //Update

    public void update(Aviones a) {
        em.merge(a);
    }

    public void delete(Aviones a) {
        em.remove(em.merge(a));
    }

    public Aviones find(Long id) {
        return em.find(Aviones.class, id);
    }
}

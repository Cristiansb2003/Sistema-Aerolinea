/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facade;

import Entity.Ciudades;
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
public class CiudadesFacade {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @PersistenceContext(unitName = "Evidencia_1-ejbPU")
    private EntityManager em;

    public List<Ciudades> findAll() {
        TypedQuery<Ciudades> query;
        query = em.createQuery("SELECT C FROM Ciudades C", Ciudades.class);
        return query.getResultList();
    }

    public List<Ciudades> findAll2() {
        Query query;
        query = em.createNamedQuery("findCiudades");
        return query.getResultList();
    }
    //Con typedQuery

    public List<Ciudades> findByNameEstado(String nameEstado) {
        TypedQuery<Ciudades> query;
        query = em.createQuery("SELECT C FROM Ciudades C WHERE C.estado.nombre=:nombre", Ciudades.class);
        query.setParameter("nombre", nameEstado);
        return query.getResultList();
    }

    public List<Ciudades> findByNameT(String nameCiudad) {
        TypedQuery<Ciudades> query;
        query = em.createQuery("SELECT C FROM Ciudades C WHERE C.nombre=:nombre", Ciudades.class);
        query.setParameter("nombre", nameCiudad);
        return query.getResultList();
    }
    //Con NamedQuery

    public List<Ciudades> findByNameNQ(String nombre) {
        Query query;
        query = em.createNamedQuery("findByNameNQ");
        query.setParameter("nombre", nombre.toUpperCase());

        return query.getResultList();

    }

    public List<Ciudades> findByEstado(String Estado) {
        Query query;
        query = em.createNamedQuery("findByEstado");
        query.setParameter("nombre", Estado.toUpperCase());

        return query.getResultList();
    }

    public List<Ciudades> findByEstadoYCiudad(String Estado, String Ciudad) {
        Query query;
        query = em.createNamedQuery("findByEstadoYCiudad");
        query.setParameter("nombre", Estado);
        query.setParameter("C.nombre", Ciudad);

        return query.getResultList();
    }

    public List<Ciudades> buscarCiudadYEstado(String ciudad, String estado) {
        TypedQuery<Ciudades> query;
        query = em.createQuery("SELECT C FROM Ciudades C WHERE C.nombre=:nombre AND C.estado.nombre=:nombre", Ciudades.class);
        query.setParameter("nombre", ciudad);
        query.setParameter("nombre", estado);
        return query.getResultList();
    }

    //Insert
    //Insertar
    public void insert(Ciudades c) {
        em.persist(c);//Aqui con esto se sube a la base de datos
    }

    //Update
    public void update(Ciudades c) {
        em.merge(c);
    }

    public void delete(Ciudades c) {
        em.remove(em.merge(c));
    }

    public Ciudades find(Long id) {
        return em.find(Ciudades.class, id);
    }
}

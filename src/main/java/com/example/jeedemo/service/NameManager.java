package com.example.jeedemo.service;

import com.example.jeedemo.domain.Name;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class NameManager {

    @PersistenceContext
    EntityManager em;

    public List<Name> getAllNames() {
        return em.createNamedQuery("name.all").getResultList();
    }

    public Name getNameById(Long id) {
        return em.find(Name.class, id);
    }

    public void create(Name Name) {
        em.persist(Name);
    }

    public void update(Name Name) {
        em.merge(Name);
    }

    public void delete(Name Name) {
        em.remove(em.merge(Name));
    }
}

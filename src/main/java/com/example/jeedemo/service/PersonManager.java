package com.example.jeedemo.service;

import com.example.jeedemo.domain.Person;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class PersonManager {
    @PersistenceContext
    EntityManager em;

    public List<Person> getAllPeople() {
        return em.createNamedQuery("person.all").getResultList();
    }

    public Person getPersonById(Long id) {
        return em.find(Person.class, id);
    }

    public void create(Person person) {
        em.persist(person);
    }

    public void update(Person person) {
        em.merge(person);
    }

    public void delete(Person person) {
        em.remove(em.merge(person));
    }
}

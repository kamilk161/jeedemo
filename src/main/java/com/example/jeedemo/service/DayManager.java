package com.example.jeedemo.service;

import com.example.jeedemo.domain.Day;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class DayManager {

    @PersistenceContext
    EntityManager em;

    public List<Day> getAllDays() {
        return em.createNamedQuery("day.all").getResultList();
    }

    public Day getDayById(Long id) {
        return em.find(Day.class, id);
    }

    public void create(Day day) {
        em.persist(day);
    }

    public void update(Day day) {
        em.merge(day);
    }

    public void delete(Day day) {
        em.remove(em.merge(day));
    }
}

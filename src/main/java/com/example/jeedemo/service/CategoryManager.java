package com.example.jeedemo.service;

import com.example.jeedemo.domain.Category;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class CategoryManager {

    @PersistenceContext
    EntityManager em;

    public List<Category> getAllCategories() {
        return em.createNamedQuery("category.all").getResultList();
    }

    public Category getCategoryById(Long id) {
        return em.find(Category.class, id);
    }

    public void create(Category category) {
        em.persist(category);
    }

    public void update(Category category) {
        em.merge(category);
    }

    public void delete(Category category) {
        em.remove(em.merge(category));
    }
}

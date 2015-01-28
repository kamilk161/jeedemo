package com.example.jeedemo.service;

import com.example.jeedemo.domain.Author;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class AuthorManager {
    
    @PersistenceContext
    EntityManager em;

    public List<Author> getAllAuthors() {
        return em.createNamedQuery("author.all").getResultList();
    }

    public List<Object> getMostPopularAuthors() {
        return em.createNamedQuery("author.count").getResultList();
    }

    public Author getAuthorById(Long id) {
        return em.find(Author.class, id);
    }

    public void create(Author author) {
        em.persist(author);
    }

    public void update(Author author) {
        em.merge(author);
    }

    public void delete(Author author) {
        em.remove(em.merge(author));
    }

}

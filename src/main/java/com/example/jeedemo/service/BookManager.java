package com.example.jeedemo.service;

import com.example.jeedemo.domain.Book;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.util.List;

@Stateless
public class BookManager implements Serializable {
    @PersistenceContext
    EntityManager em;

    public List<Book> getAllBooks() {
        return em.createNamedQuery("book.all").getResultList();
    }

    public Book getBookById(Long id) {
        return em.find(Book.class, id);
    }

    public List<Book> getBookByCategoryId(Long id) {
        return em.createNamedQuery("book.bycategory").setParameter("id", id).getResultList();
    }

    public void create(Book book) {
        em.persist(book);
    }

    public void update(Book book) {
        em.merge(book);
    }

    public void delete(Book book) {
        em.remove(em.merge(book));
    }
}

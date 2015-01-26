package com.example.jeedemo.domain;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity
@NamedQuery(name = "book.all", query = "Select b from Book b")
public class Book {

    @Id
    @GeneratedValue
    private Long id;

    private String title;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Author> authors;

    @ManyToOne
    private Category category;

    private Date dateOfRelease;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getDateOfRelease() {
        return dateOfRelease;
    }

    public void setDateOfRelease(Date dateOfRelease) {
        this.dateOfRelease = dateOfRelease;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}

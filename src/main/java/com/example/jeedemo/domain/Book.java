package com.example.jeedemo.domain;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity
@NamedQueries({
    @NamedQuery(name = "book.all", query = "Select b from Book b"),
    @NamedQuery(name = "book.bycategory", query = "Select b from Book b where b.category.id = :id")
})
public class Book {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable=false)
    private String title;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Author> authors;

    @ManyToOne
    private Category category;

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "books")
    private List<Person> people;

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

    public List<Person> getPeople() {
        return people;
    }

    public void setPeople(List<Person> people) {
        this.people = people;
    }
}

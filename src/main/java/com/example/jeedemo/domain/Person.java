package com.example.jeedemo.domain;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity
@NamedQueries({
    @NamedQuery(name = "person.all", query = "Select p from Person p"),
    @NamedQuery(name = "person.count", query = "Select p.firstName, p.lastName, count(p.books) " +
            "from Person p")
})
public class Person {

    @Id
    @GeneratedValue
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    private Date dateOfBirth;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Book> books;

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}

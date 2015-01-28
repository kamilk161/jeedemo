package com.example.jeedemo.domain;

import javax.persistence.*;
import java.util.List;

@NamedQueries({
        @NamedQuery(name = "author.all", query = "Select a from Author a"),
        @NamedQuery(name = "author.count", query = "Select a.firstName, a.lastName, count(people) as c " +
                "from Author a join a.books as book join book.people as people group by a.firstName, a.lastName order by c desc")
})
@Entity
public class Author {

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

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "authors")
    private List<Book> books;

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
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

    public String getFullName() {
        return firstName + " " + lastName;
    }
}

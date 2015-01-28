package com.example.jeedemo.web;


import com.example.jeedemo.domain.Author;
import com.example.jeedemo.domain.Book;
import com.example.jeedemo.domain.Person;
import com.example.jeedemo.service.BookManager;
import com.example.jeedemo.service.PersonManager;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@ManagedBean
@ViewScoped
public class PersonBean {
    private Long id;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @Past
    private Date dateOfBirth;

    private List<Long> booksId;

    @Inject
    private PersonManager pm;

    @Inject
    protected BookManager bm;

    @PostConstruct
    public void init() {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        Map<String, String> requestMap = externalContext.getRequestParameterMap();
        if(requestMap.get("id") != null) {
            id = Long.parseLong(requestMap.get("id"));
            Person person = pm.getPersonById(id);
            firstName = person.getFirstName();
            lastName = person.getLastName();
            dateOfBirth = person.getDateOfBirth();
            booksId = person.getBooks().stream().map(Book::getId).collect(Collectors.toList());
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public PersonManager getPm() {
        return pm;
    }

    public void setPm(PersonManager pm) {
        this.pm = pm;
    }

    public List<Long> getBooksId() {
        return booksId;
    }

    public void setBooksId(List<Long> booksId) {
        this.booksId = booksId;
    }

    public BookManager getBm() {
        return bm;
    }

    public void setBm(BookManager bm) {
        this.bm = bm;
    }

    public String save() {
        Person person = new Person();
        person.setFirstName(firstName);
        person.setLastName(lastName);
        if(dateOfBirth != null) {
            person.setDateOfBirth(new java.sql.Date(dateOfBirth.getTime()));
        }
        person.setBooks(booksId.stream().map(bm::getBookById).collect(Collectors.toList()));
        if(id == null) {
            pm.create(person);
        } else {
            pm.update(person);
        }
        return "index";
    }

    public String delete(Long id) {
        Person person = pm.getPersonById(id);
        pm.delete(person);
        return "index";
    }
}

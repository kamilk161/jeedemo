package com.example.jeedemo.web;

import com.example.jeedemo.domain.Author;
import com.example.jeedemo.service.AuthorManager;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import java.util.Map;

@ManagedBean
@ViewScoped
public class AuthorBean {

    private Long id;

    private String firstName;

    private String lastName;

    @Inject
    private AuthorManager am;

    @PostConstruct
    public void init() {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        Map<String, String> requestMap = externalContext.getRequestParameterMap();
        if(requestMap.get("id") != null) {
            id = Long.parseLong(requestMap.get("id"));
            Author author = am.getAuthorById(id);
            firstName = author.getFirstName();
            lastName = author.getLastName();
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

    public AuthorManager getAm() {
        return am;
    }

    public void setAm(AuthorManager am) {
        this.am = am;
    }

    public String save() {
        Author author = new Author();
        author.setId(id);
        author.setFirstName(firstName);
        author.setLastName(lastName);

        if(id == null) {
            am.create(author);
        } else {
            am.update(author);
        }
        return "index";
    }

    public String delete(Long id) {
        Author author = am.getAuthorById(id);
        am.delete(author);
        return "index";
    }
}

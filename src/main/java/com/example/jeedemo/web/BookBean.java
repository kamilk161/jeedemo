package com.example.jeedemo.web;

import com.example.jeedemo.domain.Author;
import com.example.jeedemo.domain.Book;
import com.example.jeedemo.service.AuthorManager;
import com.example.jeedemo.service.BookManager;
import com.example.jeedemo.service.CategoryManager;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@ManagedBean
@ViewScoped
public class BookBean {

    private Long id;

    @NotNull
    private String title;

    @NotNull
    private List<Long> authorsId;

    private Long categoryId;

    private Date dateOfRelease;

    @Inject
    private BookManager bm;

    @Inject
    private AuthorManager am;

    @Inject
    private CategoryManager cm;

    @PostConstruct
    public void init() {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        Map<String, String> requestMap = externalContext.getRequestParameterMap();
        if(requestMap.get("id") != null) {
            id = Long.parseLong(requestMap.get("id"));
            Book book = bm.getBookById(id);
            title = book.getTitle();
            dateOfRelease = book.getDateOfRelease();
            authorsId = book.getAuthors().stream().map(Author::getId).collect(Collectors.toList());
            categoryId = book.getCategory().getId();
        }
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Long> getAuthorsId() {
        return authorsId;
    }

    public void setAuthorsId(List<Long> authorsId) {
        this.authorsId = authorsId;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Date getDateOfRelease() {
        return dateOfRelease;
    }

    public void setDateOfRelease(Date dateOfRelease) {
        this.dateOfRelease = dateOfRelease;
    }

    public BookManager getBm() {
        return bm;
    }

    public void setBm(BookManager bm) {
        this.bm = bm;
    }

    public String save() {
        Book book = new Book();
        List<Author> authors = authorsId.stream().map(am::getAuthorById).collect(Collectors.toList());
        book.setId(id);
        book.setAuthors(authors);
        if(dateOfRelease != null) {
            book.setDateOfRelease(new java.sql.Date(dateOfRelease.getTime()));
        }
        book.setTitle(title);
        book.setCategory(cm.getCategoryById(categoryId));
        if(id == null) {
            bm.create(book);
        } else {
            bm.update(book);
        }
        return "index";
    }
}

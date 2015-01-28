package com.example.jeedemo.web;

import com.example.jeedemo.service.AuthorManager;
import com.example.jeedemo.service.BookManager;
import com.example.jeedemo.service.CategoryManager;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

@ManagedBean
@ApplicationScoped
public class FrontendBean {

    @Inject
    private CategoryManager cm;

    @Inject
    private BookManager bm;

    @Inject
    private AuthorManager am;

    public AuthorManager getAm() {
        return am;
    }

    public void setAm(AuthorManager am) {
        this.am = am;
    }

    public CategoryManager getCm() {
        return cm;
    }

    public void setCm(CategoryManager cm) {
        this.cm = cm;
    }

    public BookManager getBm() {
        return bm;
    }

    public void setBm(BookManager bm) {
        this.bm = bm;
    }
}

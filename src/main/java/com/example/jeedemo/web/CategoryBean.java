package com.example.jeedemo.web;

import com.example.jeedemo.domain.Category;
import com.example.jeedemo.service.CategoryManager;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.validation.constraints.NotNull;
import java.util.Map;

@ManagedBean
@ViewScoped
public class CategoryBean {

    private Long id;

    @NotNull
    private String name;

    @Inject
    private CategoryManager cm;

    @PostConstruct
    public void init() {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        Map<String, String> requestMap = externalContext.getRequestParameterMap();
        if(requestMap.get("id") != null) {
            id = Long.parseLong(requestMap.get("id"));
            name = cm.getCategoryById(id).getName();
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CategoryManager getCm() {
        return cm;
    }

    public void setCm(CategoryManager cm) {
        this.cm = cm;
    }

    public String save() {
        Category category = new Category();
        category.setName(name);
        category.setId(id);

        if(id == null) {
            cm.create(category);
        } else {
            cm.update(category);
        }
        return "index";
    }

    public String delete(Long id) {
        Category category = cm.getCategoryById(id);
        cm.delete(category);
        return "index";
    }

}

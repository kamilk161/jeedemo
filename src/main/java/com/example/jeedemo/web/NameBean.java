package com.example.jeedemo.web;

import com.example.jeedemo.domain.Name;
import com.example.jeedemo.service.NameManager;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

@ManagedBean
@ViewScoped
public class NameBean {

    private Long id;

    @NotNull
    private String name;

    @Inject
    private NameManager wm;

    @PostConstruct
    public void init() {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        Map<String, String> requestMap = externalContext.getRequestParameterMap();
        if(requestMap.get("id") != null) {
            id = Long.parseLong(requestMap.get("id"));
            name = wm.getNameById(id).getName();
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Name> getAllNames() {
        return wm.getAllNames();
    }

    public NameManager getWm() {
        return wm;
    }

    public void setWm(NameManager wm) {
        this.wm = wm;
    }

    public String save() {
        Name Name;
        if(id != null) {
            Name = wm.getNameById(id);
            Name.setName(name);
            wm.update(Name);
        } else {
            Name = new Name();
            Name.setName(name);
            wm.create(Name);
        }

        name = "";
        id = null;
        return "index";
    }

    public String delete(Long id) {
        Name name = wm.getNameById(id);
        wm.delete(name);
        return "index";
    }
}

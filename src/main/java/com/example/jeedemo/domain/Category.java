package com.example.jeedemo.domain;

import javax.persistence.*;

@Entity
@NamedQuery(name = "category.all", query = "Select c from Category c")
public class Category {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

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
}

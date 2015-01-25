package com.example.jeedemo.domain;

import javax.persistence.*;

@Entity
@NamedQueries({
        @NamedQuery(name = "name.all", query = "Select n from Name n")
})
public class Name {

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true, nullable = false)
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

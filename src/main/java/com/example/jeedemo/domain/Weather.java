package com.example.jeedemo.domain;

import javax.persistence.*;

@Entity
@NamedQueries({
    @NamedQuery(name = "weather.all", query = "Select w from Weather w")
})
public class Weather {

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

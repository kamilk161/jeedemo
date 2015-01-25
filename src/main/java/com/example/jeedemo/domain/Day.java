package com.example.jeedemo.domain;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity
@NamedQueries({
    @NamedQuery(name = "day.all", query = "Select d from Day d")
})
public class Day {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private Date date;

    @ManyToOne
    private Weather weather;

    public List<Name> getNames() {
        return names;
    }

    public void setNames(List<Name> names) {
        this.names = names;
    }

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Name> names;

    public Weather getWeather() {
        return weather;
    }

    public void setWeather(Weather weather) {
        this.weather = weather;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

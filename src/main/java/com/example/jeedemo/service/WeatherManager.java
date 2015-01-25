package com.example.jeedemo.service;

import com.example.jeedemo.domain.Weather;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class WeatherManager {
    @PersistenceContext
    EntityManager em;

    public List<Weather> getAllWeathers() {
        return em.createNamedQuery("weather.all").getResultList();
    }

    public Weather getWeatherById(Long id) {
        return em.find(Weather.class, id);
    }

    public void create(Weather weather) {
        em.persist(weather);
    }

    public void update(Weather weather) {
        em.merge(weather);
    }

    public void delete(Weather weather) {
        em.remove(em.merge(weather));
    }
}

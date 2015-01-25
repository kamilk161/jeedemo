package com.example.jeedemo.web;

import com.example.jeedemo.domain.Weather;
import com.example.jeedemo.service.WeatherManager;

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
public class WeatherBean {

    private Long id;

    @NotNull
    private String name;

    @Inject
    private WeatherManager wm;

    @PostConstruct
    public void init() {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        Map<String, String> requestMap = externalContext.getRequestParameterMap();
        if(requestMap.get("id") != null) {
            id = Long.parseLong(requestMap.get("id"));
            name = wm.getWeatherById(id).getName();
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Weather> getAllWeathers() {
        return wm.getAllWeathers();
    }

    public WeatherManager getWm() {
        return wm;
    }

    public void setWm(WeatherManager wm) {
        this.wm = wm;
    }

    public String save() {
        Weather weather;
        if(id != null) {
            weather = wm.getWeatherById(id);
            weather.setName(name);
            wm.update(weather);
        } else {
            weather = new Weather();
            weather.setName(name);
            wm.create(weather);
        }

        name = "";
        id = null;
        return "index";
    }

    public String delete(Long id) {
        wm.delete(wm.getWeatherById(id));
        return "index";
    }
}

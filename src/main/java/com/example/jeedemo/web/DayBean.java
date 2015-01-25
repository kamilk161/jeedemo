package com.example.jeedemo.web;

import com.example.jeedemo.domain.Day;
import com.example.jeedemo.domain.Name;
import com.example.jeedemo.domain.Weather;
import com.example.jeedemo.service.DayManager;
import com.example.jeedemo.service.NameManager;
import com.example.jeedemo.service.WeatherManager;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@ManagedBean
@ViewScoped
public class DayBean {

    @Inject
    private DayManager dm;

    @Inject
    private NameManager nm;

    @Inject
    private WeatherManager wm;

    private String name;

    private Date date;

    private Long weatherId;

    private Long[] namesIds;

    private Long id;

    @PostConstruct
    public void init() {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        Map<String, String> requestMap = externalContext.getRequestParameterMap();
        if(requestMap.get("id") != null) {
            id = Long.parseLong(requestMap.get("id"));
            Day day = dm.getDayById(id);
            name = day.getName();
            date = day.getDate();
            weatherId = day.getWeather().getId();
            List<Long> namesIds = new ArrayList<Long>();
            for(Name name : day.getNames()) {
                namesIds.add(name.getId());
            }
            this.namesIds = new Long[namesIds.size()];
            this.namesIds = namesIds.toArray(this.namesIds);
        }
    }

    public DayManager getDm() {
        return dm;
    }

    public void setDm(DayManager dm) {
        this.dm = dm;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getWeatherId() {
        return weatherId;
    }

    public void setWeatherId(Long weatherId) {
        this.weatherId = weatherId;
    }

    public Long[] getNamesIds() {
        return namesIds;
    }

    public void setNamesIds(Long[] namesIds) {
        this.namesIds = namesIds;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public NameManager getNm() {
        return nm;
    }

    public void setNm(NameManager nm) {
        this.nm = nm;
    }

    public WeatherManager getWm() {
        return wm;
    }

    public void setWm(WeatherManager wm) {
        this.wm = wm;
    }

    public List<Weather> getAllWeathers() {
        return wm.getAllWeathers();
    }

    public List<Name> getAllNames() {
        return nm.getAllNames();
    }

    public List<Day> getAllDays() {
        return dm.getAllDays();
    }

    public String save() {
        Day day = new Day();
        day.setName(name);
        day.setDate(new java.sql.Date(date.getTime()));
        if(weatherId != null) {
            day.setWeather(wm.getWeatherById(weatherId));
        }

        List<Name> names = new ArrayList<Name>();
        for(Long id : namesIds) {
            names.add(nm.getNameById(id));
        }
        day.setNames(names);
        if(id == null) {
            dm.create(day);
        } else {
            day.setId(id);
            dm.update(day);
        }
        id = null;
        return "index";
    }

    public String delete(Long id) {
        Day day = dm.getDayById(id);
        dm.delete(day);
        return "index";
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imd.telemaco.model;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author franklin
 */
public class SerieDAO {
    private final Map<String, Serie> series = new HashMap<String, Serie>();
    
    public void insertSerie(Serie serie) {
        this.series.put(serie.toString(), serie);
    }
    
    public void removeSerie(Serie serie) {
        this.series.remove(serie.toString());
    }
}

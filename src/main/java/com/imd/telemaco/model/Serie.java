/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imd.telemaco.model;

import java.util.ArrayList;

/**
 * Represents a series.
 * @author valmir
 */
public class Serie {
    public String name;
    public String year;
    public ArrayList<Season> seasons;
    public String description;
    //private Image image;

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the year
     */
    public String getYear() {
        return year;
    }

    /**
     * @param year the year to set
     */
    public void setYear(String year) {
        this.year = year;
    }

//    /**
//     * Verifica todo array de seasoin para ver se esta compelto
//     * @return the completed
//     */
//    public boolean isCompleted() {
//        return completed;
//    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Details of the series.
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the seasons
     */
    public ArrayList<Season> getSeasons() {
        return seasons;
    }

    /**
     * @param seasons the seasons to set
     */
    public void setSeasons(ArrayList<Season> seasons) {
        this.seasons = seasons;
    }
    
}

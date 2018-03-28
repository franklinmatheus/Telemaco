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
    private String name;
    private String year;
    private ArrayList<Season> seasons;
    public Rating rating;
    
    public Serie() { }
    
    public Serie( String name, String year, ArrayList<Season> seasons, Rating rating) { 
        this.name = name;
        this.year = year;
        this.seasons = seasons;
        this.rating = rating;
    }
    
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

     /**
     * Verifica todo array de seasons para ver se esta compelto.
     * @return the completed
     */
    public boolean isCompleted() {
        
        for (Season s : seasons) {
            if (s.isCompleted() == false) {
                return false;
            }
        }
        
        return true;
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
    
    /**
     * @return the stars of rating
     */
    public int stars() {
        return this.rating.stars;
    }

    /**
     * @return the rating
     */
    public Rating getRating() {
        return rating;
    }

    /**
     * @param rating the rating to set
     */
    public void setRating(Rating rating) {
        this.rating = rating;
    }
    
    @Override
    public String toString() {
        return "Serie{" + "nome=" + name + ", Ano=" + year + '}';
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imd.telemaco.entity;
import com.imd.telemaco.entity.enums.Classification;
import java.util.ArrayList;

/**
 * Represents a series.
 * @author valmir
 */
public class Serie {
    private int id;
    private String name;
    private String year;
    private ArrayList<Season> seasons;
    public Rating rating;
    private int id_creator;
    private Classification classification;
    
    public Serie() { }

    public Serie(int id, String name, String year, ArrayList<Season> seasons, Rating rating, int id_creator, Classification classification) {
        this.id = id;
        this.name = name;
        this.year = year;
        this.seasons = seasons;
        this.rating = rating;
        this.id_creator = id_creator;
        this.classification = classification;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public ArrayList<Season> getSeasons() {
        return seasons;
    }

    public void setSeasons(ArrayList<Season> seasons) {
        this.seasons = seasons;
    }

    public Rating getRating() {
        return rating;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }

    public int getId_creator() {
        return id_creator;
    }

    public void setId_creator(int id_creator) {
        this.id_creator = id_creator;
    }

    public Classification getClassification() {
        return classification;
    }

    public void setClassification(Classification classification) {
        this.classification = classification;
    }
    
    @Override
    public String toString() {
        return "Serie{" + "nome=" + name + ", Ano=" + year + '}';
    }
}

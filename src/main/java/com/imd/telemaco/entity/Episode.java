/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imd.telemaco.entity;

/**
 *
 * @author valmir
 */
public class Episode {
    private String name;
    private boolean completed;
    
    /**
     * Default constructor
     */
    public Episode() { }
    
    /**
     * Parametric constructor
     */
    public Episode(String name, boolean completed, int rating) { 
        this.name = name;
        this.completed = completed;
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
     * @return the completed
     */
    public boolean isCompleted() {
        return completed;
    }

    /**
     * @param completed the completed to set
     */
    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
    
     @Override
    public String toString() {
        return "Episódio{" + "nome=" + name + '}';
    }
}

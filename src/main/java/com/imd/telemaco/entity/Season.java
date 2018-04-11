package com.imd.telemaco.entity;

import java.util.ArrayList;

/**
 * Class to represent a season of a series. A season has the
 * following fields: id, number and episodes amount.
 * 
 * @author  Valmir Correia
 * @author  Shirley Ohara (shirleyohara@ufrn.edu.br)
 * @version 10.04.2018
 */
public class Season {
	private int id;
    private int number;
    private int epAmount;
    private ArrayList<Episode> episodes;
    
    /**
     * Default constructor 
     */
    public Season() { }
    
    /**
     * Parametric constructor 
     * @param number
     * @param episodes
     */
    public Season(int id, int number, ArrayList<Episode> episodes) { 
        this.id = id;
    	this.number   = number;
        this.episodes = episodes;
        this.epAmount = episodes.size();
    }

    /**
     * Returns the season id
     * @return id
     */
    public int getId () {
    	return id;
    }
    
    /**
     * Returns the number season
     * @return number
     */
    public int getNumber () {
        return number;
    }
    
    /**
     * Returns the season episodes amount
     * @return epAmount
     */
    public int getEpAmount () {
    	return epAmount;
    }

    /**
     * Change the season number
     * @param number
     */
    public void setNumber (int number) {
        this.number = number;
    }

    /**
     * @return the episodes
     */
    public ArrayList<Episode> getEpisodes() {
        return episodes;
    }

    /**
     * @param episodes the episodes to set
     */
    public void setEpisodes(ArrayList<Episode> episodes) {
        this.episodes = episodes;
        this.epAmount = episodes.size();
    }
    
    @Override
    public String toString () {
        return "Temporada " +  number;
    }
    
}

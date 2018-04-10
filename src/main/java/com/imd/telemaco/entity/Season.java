package com.imd.telemaco.entity;

import java.util.ArrayList;

/**
 *
 * @author  Valmir Correia
 * @author  Shirley Ohara (shirleyohara@ufrn.edu.br)
 * @version 10.04.2018
 */
public class Season {
    private int number;
    private int epAmount;
    private ArrayList<Episode> episodes;
    
    /**
     * Default constructor 
     */
    public Season() { }
    
    /**
     * Parametric constructor 
     */
    public Season(int number, ArrayList<Episode> episodes) { 
        this.number   = number;
        this.episodes = episodes;
        this.epAmount = episodes.size();
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
    
     /**
      * Verify all series array to identify if is full 
      * @return the completed
      */
    public boolean isCompleted () {
        for (Episode e : episodes) {
            if (e.isCompleted() == false) {
                return false;
            }
        }
        return true;
    }
    
    @Override
    public String toString () {
        return "Temporada{" + "nome=" + number + '}';
    }
    
}

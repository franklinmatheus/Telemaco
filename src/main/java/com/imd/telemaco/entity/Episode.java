package com.imd.telemaco.entity;

/**
 * Class to represent an episode of a series. An episode has the
 * following fields: id, name, number, time and synopsis.
 * 
 * @author  Valmir Correa
 * @author  Shirley Ohara (shirleyohara@ufrn.edu.br)
 * @version 10.04.2018
 */
public class Episode {
	private int 	id; 	  /*< The identify of the Episode */
    private String 	name;	  /*< The name of the Episode */
    private int 	number;   /*< The number of the Episode */
    private int 	time;	  /*< The time (in minutes) of the Episode */
    private String 	synopsis; /*< The synopsis of the Episode */
    
    /**
     * Default constructor
     */
    public Episode() { }
    
    /**
     * Parametric constructor 
     * @param id
     * @param name
     * @param number
     * @param time
     * @param synopsis
     */
    public Episode(int id, String name, int number, int time, String synopsis) { 
        this.id 	  = id;
    	this.name 	  = name;
        this.number   = number;
        this.time     = time;
        this.synopsis = synopsis;
    }

    /**
     * Returns the episode id
     * @return id
     */
    public int getId () { 
    	return id; 
    }
    
    /**
     * Returns the episode name
     * @return name
     */
    public String getName() {
        return name;
    }
    
    /**
     * Returns the episode number
     * @return number
     */
    public int getNumber () {
    	return number;
    }
        
    /**
     * Returns the episode time
     * @return time
     */
    public int getTime () {
    	return time;
    }

    /**
     * Returns the episode synopsis 
     * @return
     */
    public String getSynopsis () {
    	return synopsis;
    }
    
    // TODO is it necessary this method?
    /**
     * Change the id value
     * @param id
     */
    public void setId (int id) {
    	this.id = id;
    }

    /**
     * Change the name value 
     * @param name
     */
    public void setName (String name) {
        this.name = name;
    }
    
    /**
     * Change the number value
     * @param number
     */
    public void setNumber (int number) {
    	this.number = number;
    }
    
    /**
     * Change the time value
     * @param time
     */
    public void setTime (int time) {
    	this.time = time;
    }
    
    /**
     * Change the synopsis value
     * @param synopsis
     */
    public void setSynopsis (String synopsis) {
    	this.synopsis = synopsis;
    }
    
    @Override
    public String toString() {
        return "Episódio " + this.number + ": " + name;
    }
}

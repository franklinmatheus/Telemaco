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
    private int year;
    private String status;
    private String creator;
    private Classification classification;
    private String genre;
    private String synopsis;
    private String image;
    private ArrayList<Season> seasons;
    private ArrayList<Rating> ratings;
    
    public Serie() { }

    public Serie(String name, int year, String status, String creator, Classification classification, String genre, String synopsis, String image) { 
    	this.name = name;
    	this.year = year;
    	this.status = status;
    	this.creator = creator;
    	this.genre = genre;
    	this.synopsis = synopsis;
    	this.classification = classification;
    	this.image = image; 
    	this.seasons = null;
    	this.ratings = null;
    }
    
    public Serie(int id, String name, int year, String status, String creator, Classification classification, String genre, String synopsis, String image, ArrayList<Season> seasons) {
    	this.id = id;
    	this.name = name;
    	this.year = year;
    	this.status = status;
    	this.creator = creator;
    	this.genre = genre;
    	this.synopsis = synopsis;
    	this.classification = classification;
    	this.image = image; 
    	this.seasons = seasons;
    	this.ratings = null;
    }
    
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getYear() {
        return year;
    }
    
    public String getStatus () {
    	return status;
    }
    
    public String getCreator () {
    	return creator;
    }
    
    public String getGenre () {
    	return genre;
    }
    
    public String getSynopsis () {
    	return synopsis;
    }
    
    public Classification getClassification () {
    	return classification;
    }
    
    public String getImage () {
    	return image;
    }

    public ArrayList<Season> getSeasons() {
        return seasons;
    }

    public ArrayList<Rating> getRating() {
        return ratings;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public void setYear(int year) {
        this.year = year;
    }

    public void setStatus (String status) {
    	this.status = status;
    }
    
    public void setCreator (String creator) {
    	this.creator = creator;
    }
    
    public void setGenre (String genre) {
    	this.genre = genre;
    }
    
    public void setSynopsis (String synopsis) {
    	this.synopsis = synopsis ;
    }

    public void setClassification(Classification classification) {
        this.classification = classification;
    }
    
    public void setImage (String image) {
    	this.image = image;
    }
    
    public void setSeasons(ArrayList<Season> seasons) {
        this.seasons = seasons;
    }
    
    public void setRating(ArrayList<Rating> ratings) {
        this.ratings = ratings;
    }
    
	public String classifToString () {
		String cString;
		switch (classification) {
			case GENERAL:
				cString = "L";
				break;
			case P10:
				cString = "10";
				break;
			case P12:
				cString = "12";
				break;
			case P14:
				cString = "14";
				break;
			case P16:
				cString = "16";
				break;
			default:
				cString = "18";
		}
		return cString;
	}
    
	public Classification stringToClassif (String classif) {
		Classification classification;
		
		switch (classif) {
			case "L":
				classification = Classification.GENERAL;
				break;
			case "10":
				classification = Classification.P10;
				break;
			case "12":
				classification = Classification.P12;
				break;
			case "14":
				classification = Classification.P14;
				break;
			case "16":
				classification = Classification.P16;
				break;
			default:
				classification = Classification.P18;
				break;
				
		}
		
		return classification;
	}
	
    @Override
    public String toString() {
        return "Serie{" + "nome=" + name + ", Ano=" + year + '}';
    }
}

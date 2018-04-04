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
public class Rating {
    public String rating;
    public double stars;
    
    public void rating() { }
    
    public void rating(String rating, int stars) { 
        this.setRating(rating);
        this.setStars(stars);
    }

    /**
     * @return the rating
     */
    public String getRating() {
        return rating;
    }

    /**
     * @param rating the rating to set
     */
    public void setRating(String rating) {
        this.rating = rating;
    }

    /**
     * @return the stars
     */
    public double getStars() {
        return stars;
    }

    /**
     * @param stars the stars to set
     */
    public void setStars(int stars) {
        this.stars = stars;
    }
    
     @Override
    public String toString() {
        return "Avaliação{" + "Comentario=" + rating + "estrelas" + stars + '}';
    }
    
}

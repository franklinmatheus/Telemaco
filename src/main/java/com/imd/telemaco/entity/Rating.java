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
    public String comment;
    public double stars;
    
    public void Rating() { }
    
    public void Rating(String comment, int stars) { 
        this.setComment(comment);
        this.setStars(stars);
    }

    /**
     * @return the rating
     */
    public String getComment() {
        return comment;
    }

    /**
     * @param rating the rating to set
     */
    public void setComment(String comment) {
        this.comment = comment;
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
        return "Avaliação{" + "Comentario=" + comment + "estrelas" + stars + '}';
    }
    
}

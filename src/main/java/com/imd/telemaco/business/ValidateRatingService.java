package com.imd.telemaco.business;

import com.imd.telemaco.entity.Rating;
import com.imd.telemaco.business.exception.RatingInvalidException;;

/**
 * Class to validate all services reference to the Rating.
 * @author valmir
 */
public class ValidateRatingService {
    
    /**
     * Default Constructor
     */
    public ValidateRatingService() {}
    
    /**
     * Verify if the Rating is correct
     * @param rating
     * @throws RatingInvalidException 
     */
    public void validRating (Rating rating) throws RatingInvalidException {
        try {
            this.validRatingComment(rating.getComment());
            this.validRatingStars(rating.getStars());
        } catch (RatingInvalidException r) {
            throw new RatingInvalidException();
        }
    }

    /**
     * Verify if the comment of the rating is correct.
     * @param comment 
     */
    public void validRatingComment(String comment) throws RatingInvalidException {
        if(null == comment) {
            throw new RatingInvalidException ("Avaliação inválida!");
        }
    }
    
    /**
     * Verify if the stars of the comment is valid.
     * @param stars
     * @throws RatingInvalidException 
     */
    public void validRatingStars(double stars) throws RatingInvalidException {
        if(stars > 5 || stars < 0) {
            throw new RatingInvalidException ("Avaliação inválida!");
        }
    }
}

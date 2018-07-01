package main.java.com.imd.telemaco.business;

import java.util.ArrayList;

import main.java.com.imd.telemaco.business.exception.CloseConnectionException;
import main.java.com.imd.telemaco.business.exception.DatabaseException;
import main.java.com.imd.telemaco.business.exception.EpisodeInvalidException;
import main.java.com.imd.telemaco.business.exception.NoResultsException;
import main.java.com.imd.telemaco.business.exception.RatingInvalidException;
import main.java.com.imd.telemaco.business.exception.SeasonExistsException;
import main.java.com.imd.telemaco.business.exception.SerieExistsException;
import main.java.com.imd.telemaco.business.exception.SerieInvalidException;
import main.java.com.imd.telemaco.data.DAORatingSpecialOperations;
import main.java.com.imd.telemaco.data.RatingDAO;
import main.java.com.imd.telemaco.data.SerieDAO;
import main.java.com.imd.telemaco.entity.Rating;
import main.java.com.imd.telemaco.entity.Serie;
import main.java.com.imd.telemaco.entity.User;

/**
 * Class to validate all services referent to the Series Class.
 *
 * @author valmir
 */
public class ValidateSerieServices {

    /**
     * Default constructor
     */
    public ValidateSerieServices() { }

    /**
     * Verify if the series parameters are corrects.
     *
     * @param serie
     * @throws SerieInvalidException
     */
    public void validSerieName(Serie serie) throws SerieInvalidException {

        if (serie.getName().isEmpty() || serie.getName() == null) {
            throw new SerieInvalidException("Nome da série inválido!");
        }
    }

    /**
     * Valid series to insert in data base.
     *
     * @param serie
     * @throws com.imd.telemaco.business.exception.EpisodeInvalidException
     */
    public void validateSerieInsert(Serie serie) throws EpisodeInvalidException {
        try {
            this.validSerieName(serie);
        } catch (SerieInvalidException s) {
            throw new EpisodeInvalidException("Não foi possivel inserir a serie!");
        }
    }

    /**
     * Valid series ID thats match with tha user ID.
     *
     * @param serie
     * @param user
     * @throws com.imd.telemaco.business.exception.SerieInvalidException
     */
    public void validSerieID(Serie serie, User user) throws SerieInvalidException {
        if (!(serie.getId() == user.getId())) {
            throw new SerieInvalidException();
        }
    }

    /**
     * Verify if the series already exists in the data base.
     *
     * @param serie
     * @throws SerieExistsException
     */
    private void serieExist(Serie serie) throws SerieExistsException, DatabaseException, CloseConnectionException {
        SerieDAO dao = SerieDAO.getInstance();
        Serie exists = dao.select(serie.getId());
        if (null == exists) {
            throw new SerieExistsException("Essa serie não existe!");
        }
    }

    /**
     * Valid series to register in the user profile.
     *
     * @param serie
     * @throws com.imd.telemaco.business.exception.SerieInvalidException
     * @throws SerieExistsException
     * @throws com.imd.telemaco.business.exception.DatabaseException
     * @throws com.imd.telemaco.business.exception.CloseConnectionException
     */
    public void validSerieInsert (Serie serie) throws SerieInvalidException, SerieExistsException, DatabaseException, CloseConnectionException {
    	//FIXME tirar os comentários da validação
    	//this.validSerieName(serie);
        //this.serieExist(serie);
        SerieDAO serieDAO = new SerieDAO();
        serieDAO.insert(serie);
    }

    /**
     * Valid series to remove of the data base.
     *
     * @param serie
     * @throws SeasonExistsException
     */
    public void validSerieRemove(Serie serie) throws SeasonExistsException, DatabaseException, CloseConnectionException {
        SerieDAO serieDAO = SerieDAO.getInstance();
        Serie serieRemove = serieDAO.select(serie.getId());
        if (null != serieRemove) {
            throw new SeasonExistsException("A serie '" + serie.getName() + "' não existe!");
        }
    }

    /**
     * Valid series to Update in the data base.
     *
     * @param serie
     * @throws SerieInvalidException
     * @throws com.imd.telemaco.business.exception.SerieExistsException
     * @throws com.imd.telemaco.business.exception.DatabaseException
     * @throws com.imd.telemaco.business.exception.CloseConnectionException
     */
    public void validSerieUpdate(Serie serie) throws SerieInvalidException, SerieExistsException, DatabaseException, CloseConnectionException {
        this.validSerieName(serie);
        SerieDAO serieDAO = SerieDAO.getInstance();
        Serie serieUpdate = serieDAO.select(serie.getId());
        if (serieUpdate == null) {
            throw new SerieExistsException("Impossível atualizar, esta serie não existe!");
        }
    }

    /**
     * Valid Rating of the series .
     *
     * @param serie
     * @throws RatingInvalidException
     */
    public void validSerieRating(Serie serie) throws RatingInvalidException {
        // TODO 
        /*try {
           ValidateRatingService validade = new ValidateRatingService();
           validade.validRating(serie.getRating());
       } catch (RatingInvalidException r) {
           throw new RatingInvalidException();
       }*/
    }
    
    /**
     * Return an array filled with series that matched with input.
     * @param input
     * @return
     * @throws DatabaseException
     * @throws CloseConnectionException
     * @throws NoResultsException 
     */
    public ArrayList<Serie> search(String input) throws DatabaseException, CloseConnectionException, NoResultsException {
        SerieDAO dao = SerieDAO.getInstance();
        ArrayList<Serie> results = dao.search(input);
        
        if(results.isEmpty())
            throw new NoResultsException();
        
        return results;
    }
    
    /**
     * Select all comments of serie.
     * @param id
     * @return
     * @throws DatabaseException
     * @throws CloseConnectionException
     */
    public ArrayList<Rating> getRatings(int id) throws DatabaseException, CloseConnectionException {
        DAORatingSpecialOperations commentDAO = RatingDAO.getInstance();
        ArrayList<Rating> ratings = commentDAO.selectBySerie(id);
        
        return ratings;
    }
    
    /**
     * Add a rating in database to related serie.
     * @param rating
     * @throws DatabaseException
     * @throws CloseConnectionException 
     */
    public void addRating(Rating rating) throws DatabaseException, CloseConnectionException {
        DAORatingSpecialOperations ratingDAO = RatingDAO.getInstance();
        ratingDAO.insert(rating);
    }
    
    /**
     * Remove a rating in database.
     * @param rating
     * @throws DatabaseException
     * @throws CloseConnectionException 
     */
    public void removeRating(Rating rating) throws DatabaseException, CloseConnectionException {
        DAORatingSpecialOperations ratingDAO = RatingDAO.getInstance();
        RatingDAO.delete(rating);
    }
}

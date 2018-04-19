package com.imd.telemaco.business;

import com.imd.telemaco.business.exception.CloseConnectionException;
import com.imd.telemaco.business.exception.DatabaseException;
import com.imd.telemaco.business.exception.EpisodeInvalidException;
import com.imd.telemaco.business.exception.RatingInvalidException;
import com.imd.telemaco.business.exception.SeasonExistsException;
import com.imd.telemaco.business.exception.SerieExistsException;
import com.imd.telemaco.business.exception.SerieInvalidException;
import com.imd.telemaco.data.SerieDAO;
import com.imd.telemaco.entity.Serie;
import com.imd.telemaco.entity.User;
import java.sql.SQLException;

/**
 * Class to validate all services referent to the Series Class.
 *
 * @author valmir
 */
public class ValidateSerieServices {

    /**
     * Default constructor
     */
    public ValidateSerieServices() {
    }

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
    public void validSerieRegister(Serie serie) throws SerieInvalidException, SerieExistsException, DatabaseException, CloseConnectionException {
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

//   /**
//    * Verify if the classification is correct.
//    * @param classification 
//    */
//  public void validSerieClassification (Classification classification) {
//
//  }
//   /**
//    * Valid all the seasons thats be part of the series
//    * @param serie 
//    */
//   public void validSerieSeasons(Serie serie) {
//      
//   }
}

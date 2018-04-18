package com.imd.telemaco.business;

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
 * @author valmir
 */
public class ValidateSerieServices {
    
    /** 
    * Default constructor
    */
    public ValidateSerieServices() { }
    
    /**
     * Verify if the series parameters are corrects.
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
     * @param serie
     * @throws SerieInvalidException 
     */
    public void validateSerieInsert(Serie serie) throws EpisodeInvalidException {
        try {
            this.validSerieName(serie);
        } catch (SerieInvalidException s) {
            throw new EpisodeInvalidException ("Não foi possivel inserir a serie!");
        }   
    }
    
    /**
     * Valid series ID thats match with tha user ID.
     * @param serie
     * @param user 
     * @throws com.imd.telemaco.business.SerieInvalidException 
     */
    public void validSerieID(Serie serie, User user) throws SerieInvalidException {
        if (!(serie.getId() == user.getId())) {
            throw new SerieInvalidException();
        }
    }
    
    /**
     * Verify if the series already exists in the data base.
     * @param serie
     * @throws SerieExistsException 
     */
    private void serieExist(Serie serie) throws SerieExistsException {
        
        try {
        
           SerieDAO dao = SerieDAO.getInstance();
           Serie exists = dao.select(serie.getId());
           
           if (null == exists) {
               throw new SerieExistsException ("Essa serie não existe!");
           }
        
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Valid series to register in the user profile.
     * @param serie
     * @throws com.imd.telemaco.business.SerieInvalidException
     * @throws SerieExistsException
     */
    public void validSerieRegister(Serie serie) throws SerieInvalidException, SerieExistsException {
        try {
            this.validSerieName(serie);
            this.serieExist(serie);
        } catch (SerieExistsException s) {
            throw new SerieExistsException (s.getMessage());
        } catch (SerieInvalidException s) {
            throw new SerieInvalidException();
        }
        
    }
    
    /**
     *  Valid series to remove of the data base.
     * @param serie
     * @throws SeasonExistsException 
     */
    public void validSerieRemove (Serie serie) throws SeasonExistsException {
        try{
            SerieDAO serieDAO = SerieDAO.getInstance();
            Serie serieRemove = serieDAO.select(serie.getId());
            
            if (null != serieRemove) {
                throw new SeasonExistsException("A serie '" + serie.getName() + "' não existe!" );
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    /**
     *  Valid series to Update in the data base.
     * @param serie
     * @throws SerieInvalidException 
     * @throws com.imd.telemaco.business.SerieExistsException 
     */
    public void validSerieUpdate (Serie serie) throws SerieInvalidException, SerieExistsException {
       try {
           
           this.validSerieName(serie);
           
           SerieDAO serieDAO = SerieDAO.getInstance();
           Serie serieUpdate = serieDAO.select(serie.getId());
           
            if (serieUpdate == null) {
                throw new SerieExistsException("Impossível atualizar, esta serie não existe!");
            }
       } catch (SQLException e) {
           e.printStackTrace();
       }
   }
   
   /**
    *  Valid Rating of the series .
     * @param serie
    * @throws RatingInvalidException 
    */
   public void validSerieRating (Serie serie) throws RatingInvalidException {
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

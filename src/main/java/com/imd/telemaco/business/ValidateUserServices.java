/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imd.telemaco.business;

import com.imd.telemaco.business.exception.CloseConnectionException;
import com.imd.telemaco.business.exception.DatabaseException;
import com.imd.telemaco.business.exception.ConfirmInputsException;
import com.imd.telemaco.business.exception.UserAlreadyExistsException;
import com.imd.telemaco.business.exception.UserNotExistsException;
import com.imd.telemaco.business.exception.ValidateException;
import com.imd.telemaco.data.DAOSerieSpecialOperations;
import com.imd.telemaco.data.DAOUserSpecialOperations;
import com.imd.telemaco.data.SerieDAO;
import com.imd.telemaco.data.UserDAO;
import com.imd.telemaco.entity.Serie;
import com.imd.telemaco.entity.User;
import java.util.ArrayList;

/**
 *
 * @author franklin
 */
public class ValidateUserServices {
    
    public ValidateUserServices() { }
    
    /**
     * TODO
     * @param user
     * @param cemail
     * @param cpassword 
     * @throws com.imd.telemaco.business.exception.DatabaseException 
     * @throws com.imd.telemaco.business.exception.CloseConnectionException 
     * @throws com.imd.telemaco.business.exception.UserAlreadyExistsException 
     * @throws com.imd.telemaco.business.exception.ConfirmInputsException 
     * @throws com.imd.telemaco.business.exception.ValidateException 
     */
    public void insert(User user, String cemail, String cpassword) throws DatabaseException, CloseConnectionException, 
            UserAlreadyExistsException, ConfirmInputsException, ValidateException {
        if(this.valid(user))
            if(this.confirmPassword(user.getPassword(), cpassword) && this.confirmEmail(user.getEmail(), cemail))
                if(!this.emailAlreadyExists(user)) {
                    DAOUserSpecialOperations dao = UserDAO.getInstance();
                    dao.insert(user);
                } else
                    throw new UserAlreadyExistsException();
            else
                throw new ConfirmInputsException();
        else
            throw new ValidateException();       
    }
    
    /**
     * TODO
     * @param email
     * @param password
     * @return 
     * @throws com.imd.telemaco.business.exception.DatabaseException 
     * @throws com.imd.telemaco.business.exception.CloseConnectionException 
     * @throws com.imd.telemaco.business.exception.UserNotExistsException 
     */
    public User login(String email, String password) throws DatabaseException, CloseConnectionException, UserNotExistsException {
        DAOUserSpecialOperations dao = UserDAO.getInstance();
        User user = dao.select(email, password);
        
        if(user == null)
            throw new UserNotExistsException();
        return user;
    }
    
    /**
     * Select an user by id.
     * @param id
     * @return
     * @throws DatabaseException
     * @throws CloseConnectionException
     * @throws UserNotExistsException 
     */
    public User select(int id) throws DatabaseException, CloseConnectionException, UserNotExistsException {
        DAOUserSpecialOperations dao = UserDAO.getInstance();
        User user = dao.select(id);
        
        if(user == null)
            throw new UserNotExistsException();
        
        return user;
    }
    
    /**
     * TODO
     * @param user
     * @param cOldPassword
     * @param newPassword
     * @param cNewPassword 
     * @throws com.imd.telemaco.business.exception.DatabaseException 
     * @throws com.imd.telemaco.business.exception.CloseConnectionException 
     * @throws com.imd.telemaco.business.exception.ConfirmInputsException 
     */
    public void updatePassword(User user, String cOldPassword, String newPassword, String cNewPassword) 
            throws DatabaseException, CloseConnectionException, ConfirmInputsException {
        if(this.confirmPassword(newPassword, cNewPassword))
            if(this.confirmPassword(user.getPassword(), cOldPassword)) {
                DAOUserSpecialOperations dao = UserDAO.getInstance();
                user.setPassword(newPassword);
                dao.update(user);
            } else
                throw new ConfirmInputsException();
        else
            throw new ConfirmInputsException();
    }
    
    /**
     * TODO
     * @param user 
     * @throws com.imd.telemaco.business.exception.DatabaseException 
     * @throws com.imd.telemaco.business.exception.CloseConnectionException 
     * @throws com.imd.telemaco.business.exception.UserNotExistsException 
     */
    public void remove(User user) throws DatabaseException, CloseConnectionException, UserNotExistsException {
        if(this.userExists(user)) {
            DAOUserSpecialOperations dao = UserDAO.getInstance();
            dao.delete(user); 
        } else
            throw new UserNotExistsException();
    }
    
    /**
     * TODO
     * @param idUser
     * @param idSerie
     * @throws DatabaseException
     * @throws CloseConnectionException 
     */
    public void addSerieToList(int idUser, int idSerie) throws DatabaseException, CloseConnectionException {
        DAOUserSpecialOperations dao = UserDAO.getInstance();
        dao.insertSerie(idUser, idSerie);
    }
    
    /**
     * TODO
     * @param idUser
     * @param idSerie
     * @throws DatabaseException
     * @throws CloseConnectionException 
     */
    public void removeSerieFromList(int idUser, int idSerie) throws DatabaseException, CloseConnectionException {
        DAOUserSpecialOperations dao = UserDAO.getInstance();
        dao.deleteSerie(idUser, idSerie);
    }
    
    /**
     * TODO.
     * @param idUser
     * @return
     * @throws DatabaseException
     * @throws CloseConnectionException 
     */
    public ArrayList<Serie> getSeriesList(int idUser) throws DatabaseException, CloseConnectionException {
        DAOUserSpecialOperations userDAO = UserDAO.getInstance();
        ArrayList<Integer> seriesIds = userDAO.selectSeries(idUser);
        
        DAOSerieSpecialOperations serieDAO = SerieDAO.getInstance();
        ArrayList<Serie> series = new ArrayList<Serie>();
        for(Integer currentSerieId : seriesIds) {
            Serie serie = serieDAO.select(currentSerieId);
            series.add(serie);
        }
        return series;
    }
    
    /**
     * TODO
     * @param user
     * @return 
     */
    private boolean valid(User user) {
        return !((user.getName() == null || user.getName().isEmpty() || user.getName().trim().equals("")) || 
                (user.getEmail() == null || user.getEmail().isEmpty() || user.getEmail().trim().equals("")) ||
                (user.getPassword()== null || user.getPassword().isEmpty() || user.getPassword().trim().equals("")));
    }
    
    /**
     * TODO
     * @param password
     * @param cpassword
     * @return 
     */
    private boolean confirmPassword(String password, String cpassword) {
        return password.equals(cpassword);
    }
    
    /**
     * TODO
     * @param email
     * @param cemail
     * @return 
     */
    private boolean confirmEmail(String email, String cemail) {
        return email.equals(cemail);
    }
    
    /**
     * TODO
     * @param user
     * @return 
     */
    private boolean userExists(User user) throws DatabaseException, CloseConnectionException {
        DAOUserSpecialOperations dao = UserDAO.getInstance();
        User exists = dao.select(user.getId());
        
        return exists != null;
    }
    
    /**
     * TODO
     * @param user
     * @return 
     * @throws DatabaseException
     * @throws CloseConnectionException
     */
    private boolean emailAlreadyExists(User user) throws DatabaseException, CloseConnectionException {
        DAOUserSpecialOperations dao = UserDAO.getInstance();
        User exists = dao.select(user.getEmail());
        
        return exists != null;
    }
}

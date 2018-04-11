/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imd.telemaco.business;

import com.imd.telemaco.data.FacadeDAO;
import com.imd.telemaco.entity.Serie;

/**
 *
 * @author franklin
 */
public class ValidateSerieServices {
    
    /**
     * TODO
     * @param serie 
     */
    public void validSerieRegister(Serie serie) {
        FacadeDAO facade = FacadeDAO.getInstance();
        facade.insertSerie(serie);
    }
}

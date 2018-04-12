/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imd.telemaco.business;

import com.imd.telemaco.entity.Serie;

/**
 *
 * @author valmir
 */
public class ValidateSerieServices {
    
    public ValidateSerieServices() { }
    
    public boolean validateSerieInsert (Serie serie) {
        return this.valid(serie);
    }
    
    public boolean valid(Serie serie) {
        return !(serie.getName().isEmpty() || serie.getName() == null);
    }

    public boolean validSerieRegister(Serie serie) {
        return this.validateSerieInsert(serie);
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imd.telemaco.data;

import com.imd.telemaco.entity.Serie;
import java.util.ArrayList;

/**
 *
 * @author franklin
 */
public interface DAOSerieSpecialOperations extends DAO<Serie> {
    public ArrayList<Serie> select();
}

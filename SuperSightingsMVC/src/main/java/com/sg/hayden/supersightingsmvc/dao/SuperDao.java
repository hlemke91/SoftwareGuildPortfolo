/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.hayden.supersightingsmvc.dao;

import com.sg.hayden.supersightingsmvc.dto.Super;
import java.util.List;

/**
 *
 * @author Hayden
 */
public interface SuperDao {
    public Super getSuperById(int superId);
    public void updateSuper(Super s);
    public void insertSuper(Super s);
    public void deleteSuper(int superId);
    public void nukeTable();
    
    public List<Super> getAllSupers();
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.hayden.supersightingsmvc.dao;

import com.sg.hayden.supersightingsmvc.dto.Sighting;
import com.sg.hayden.supersightingsmvc.dto.Super;
import java.util.List;

/**
 *
 * @author Hayden
 */
public interface SuperSightingBridgeDao {
    
    public void createEntry(Super sh, Sighting sight);
    public void updateEntry(Super sh, Sighting sight);
    public void deleteSuper(Super s);
    public void deleteSighting(Sighting s);
    public List<List<Integer>> getAllBridges();
    public void nukeTable();
    
    public List<Integer> getSuperIDsForSighting(Sighting s);
    public List<Integer> getSightingIDsforSuper(Super s);    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.hayden.supersightingsmvc.dao;

import com.sg.hayden.supersightingsmvc.dto.Organization;
import com.sg.hayden.supersightingsmvc.dto.Super;
import java.util.List;

/**
 *
 * @author Hayden
 */
public interface SuperOrgDao {
    public void createEntry(Super s, Organization o);
    public void updateEntry(Super s, Organization o);
    public void nukeTable();
    public void deleteBySuper(Super s);
    public void deleteByOrg(Organization o);
   
    public List<Integer> getSupersForOrg(Organization o);
    public List<Integer> getOrgsForSuper(Super s);
    public List<List<Integer>> getAllEntries();
    
    
}

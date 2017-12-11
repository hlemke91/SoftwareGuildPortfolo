/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.hayden.supersightingsmvc.dao;

import com.sg.hayden.supersightingsmvc.dto.Location;
import com.sg.hayden.supersightingsmvc.dto.Organization;
import com.sg.hayden.supersightingsmvc.dto.Super;
import java.util.List;

/**
 *
 * @author Hayden
 */
public interface OrgDao {
    
    public Organization getOrgById(int orgId);
    public void updateOrg(Organization o);
    public void insertOrg(Organization o);
    public void deleteOrg(int orgId);  
    public void nukeTable();
    public List<Integer> getOrgsForLoc(Location l);
    
    public List<Organization> getAllOrgs();
    
}

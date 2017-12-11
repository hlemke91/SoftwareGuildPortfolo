/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.hayden.supersightingsmvc.service;

import com.sg.hayden.supersightingsmvc.dto.Location;
import com.sg.hayden.supersightingsmvc.dto.Organization;
import com.sg.hayden.supersightingsmvc.dto.Sighting;
import com.sg.hayden.supersightingsmvc.dto.Super;
import java.util.List;

/**
 *
 * @author Hayden
 */
public interface SuperService {
    //methods for the Super page
    List<Super> getAllSupers();
    void removeSuper(Super s);
    void updateSuper(Super s);
    void createSuper(Super s);
    Super readSuper(int superID);
    List<Organization> getOrgs(Super s);
    List<Sighting> getSightings(Super s);
    
    
    //methods for the location page
    List<Location> getAllLocations();
    void removeLocation(Location l);
    void updateLocation(Location l);
    void createLocation(Location l);
    Location readLocation(int locID);
    List<Sighting> getSightings(Location l);
    List<Organization> getOrgs(Location l);
    
    //methods for the org page
    List<Organization> getAllOrgs();
    void createOrg(Organization o);
    Organization readOrg(int orgID);
    void updateOrg(Organization o);
    void removeOrg(Organization o);
    
    
    //methods for sighting page
    List<Sighting> getAllSightings();
    void createSighting(Sighting s);
    Sighting readSighting(int sightID);
    void updateSighting(Sighting s);
    void deleteSighting(Sighting s);
    List<Super> getSupers(Sighting s);  
    List<Sighting> getTop5();
    
}

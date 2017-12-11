/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.hayden.supersightingsmvc.dao;

import com.sg.hayden.supersightingsmvc.dto.Location;
import com.sg.hayden.supersightingsmvc.dto.Super;
import java.util.List;

/**
 *
 * @author Hayden
 */
public interface LocationDao {

    public Location getLocationById(int locationId);

    public void createLocation(Location l);

    public void updateLocation(Location l);

    public void deleteLocation(int locationId);
    
    public void nukeTable();
    
    public List<Location> getAllLocations();

}

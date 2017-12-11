/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.hayden.supersightingsmvc.dao;


import com.sg.hayden.supersightingsmvc.dto.Location;
import com.sg.hayden.supersightingsmvc.dto.Sighting;
import java.util.List;


/**
 *
 * @author Hayden
 */
public interface SightingDao {
    public void createSighting(Sighting s);
    public Sighting getSightingById(int sightingId);
    public void updateSighting(Sighting s);
    public void deleteSighting(int sightingId);
    public void nukeTable();
    public List<Integer> getSightingsForLoc(Location l);
    public List<Sighting> getAllSightings();
    public List<Sighting> getTop5();
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.hayden.supersightinsmvc.metadao;

import com.sg.hayden.supersightingsmvc.dao.LocationDao;
import com.sg.hayden.supersightingsmvc.dao.SightingDao;
import com.sg.hayden.supersightingsmvc.dao.SuperDao;
import com.sg.hayden.supersightingsmvc.dao.SuperSightingBridgeDao;
import com.sg.hayden.supersightingsmvc.dto.Sighting;
import com.sg.hayden.supersightingsmvc.dto.Super;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Hayden
 */
public class SightingCompiler {
    SightingDao sightingDao;
    LocationDao locPartialDao;
    SuperDao superDao;
    SuperSightingBridgeDao superSightingDao;

    public SightingCompiler(SightingDao sightingDao, LocationDao locPartialDao, SuperDao superDao, SuperSightingBridgeDao superSightingDao) {
        this.sightingDao = sightingDao;
        this.locPartialDao = locPartialDao;
        this.superDao = superDao;
        this.superSightingDao = superSightingDao;
    }

   
    
    
    public void createSighting(Sighting s){
        sightingDao.createSighting(s);
        for(Super sh:s.getSupers()){
            superSightingDao.createEntry(sh, s);
        }
    }
    
    public void updateSighting(Sighting s){
        superSightingDao.deleteSighting(s);
         sightingDao.updateSighting(s);
        for(Super sh: s.getSupers()){
           superSightingDao.createEntry(sh,s);
        }
    }
    
    public void deleteSighting(Sighting s){
        sightingDao.deleteSighting(s.getID());
        superSightingDao.deleteSighting(s);
    }
    
    public Sighting getSighting(int SightingID){
        Sighting s = sightingDao.getSightingById(SightingID);
        for(int i: superSightingDao.getSuperIDsForSighting(s)){
            s.addSuper(superDao.getSuperById(i));
        }
        s.setLocation(locPartialDao.getLocationById(s.getLocID()));
        return s;        
    }
    
    public List<Sighting> getAllSightings(){
        List<Sighting> sightingList = sightingDao.getAllSightings();
        List<Sighting> completes = new ArrayList<>();
        for(Sighting s: sightingList){
            int sightingID=s.getID();
           completes.add(getSighting(sightingID));
        }
        return completes;
    }
    
    public List<Sighting> getTop5(){
       List<Sighting> incomplete= sightingDao.getTop5();
       List<Sighting> complete = new ArrayList<>();
       for(Sighting s: incomplete){
           Sighting real = getSighting(s.getID());
           complete.add(real);
       }
       return complete;
    }
    
}

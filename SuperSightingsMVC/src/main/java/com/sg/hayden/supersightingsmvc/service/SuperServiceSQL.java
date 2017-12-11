/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.hayden.supersightingsmvc.service;

import com.sg.hayden.supersightingsmvc.dao.LocationDao;
import com.sg.hayden.supersightingsmvc.dao.OrgDao;
import com.sg.hayden.supersightingsmvc.dao.SightingDao;
import com.sg.hayden.supersightingsmvc.dao.SuperDao;
import com.sg.hayden.supersightingsmvc.dao.SuperOrgDao;
import com.sg.hayden.supersightingsmvc.dao.SuperSightingBridgeDao;
import com.sg.hayden.supersightingsmvc.dto.Location;
import com.sg.hayden.supersightingsmvc.dto.Organization;
import com.sg.hayden.supersightingsmvc.dto.Sighting;
import com.sg.hayden.supersightingsmvc.dto.Super;
import com.sg.hayden.supersightinsmvc.metadao.OrgCompiler;
import com.sg.hayden.supersightinsmvc.metadao.SightingCompiler;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author Hayden
 */

public class SuperServiceSQL implements SuperService{
    
    OrgCompiler orgDao;
    SightingCompiler sightingDao;
    LocationDao locDao;
    SuperDao superDao;
    SightingDao sightingPartial;
    OrgDao orgPartial;
    SuperOrgDao soDao;
    SuperSightingBridgeDao ssDao;

    public SuperServiceSQL(OrgCompiler orgDao, SightingCompiler sightingDao, LocationDao locDao, SuperDao superDao, SightingDao sightingPartial, OrgDao orgPartial, SuperOrgDao soDao, SuperSightingBridgeDao ssDao) {
        this.orgDao = orgDao;
        this.sightingDao = sightingDao;
        this.locDao = locDao;
        this.superDao = superDao;
        this.sightingPartial = sightingPartial;
        this.orgPartial = orgPartial;
        this.soDao = soDao;
        this.ssDao = ssDao;
    }

    
    @Override
    public List<Super> getAllSupers() {
        return superDao.getAllSupers();
    }

    @Override
    public void removeSuper(Super s) {
        superDao.deleteSuper(s.getID());
    }

    @Override
    public void updateSuper(Super s) {
       superDao.updateSuper(s);
    }

    @Override
    public void createSuper(Super s) {
       superDao.insertSuper(s);
    }

    @Override
    public Super readSuper(int superID) {
        return superDao.getSuperById(superID);
    }

  

    @Override
    public List<Location> getAllLocations() {
        return locDao.getAllLocations();
    }

    @Override
    public void removeLocation(Location l) {
        locDao.deleteLocation(l.getID());
    }

    @Override
    public void updateLocation(Location l) {
        locDao.updateLocation(l);
    }

    @Override
    public void createLocation(Location l) {
        locDao.createLocation(l);
    }

    @Override
    public Location readLocation(int locID) {
       return locDao.getLocationById(locID);
    } 

    @Override
    public List<Organization> getAllOrgs() {
        return orgDao.getAllOrgs();
    }

    @Override
    public void createOrg(Organization o) {
        orgDao.createOrg(o);
    }

    @Override
    public Organization readOrg(int orgID) {
       return orgDao.readOrg(orgID);
    }

    @Override
    public void updateOrg(Organization o) {
        orgDao.updateOrg(o);
    }

    @Override
    public void removeOrg(Organization o) {
        orgDao.deleteOrg(o);
    }

    @Override
    public List<Sighting> getAllSightings() {
        return sightingDao.getAllSightings();
    }

    @Override
    public void createSighting(Sighting s) {
        sightingDao.createSighting(s);
    }

    @Override
    public Sighting readSighting(int sightID) {
        return sightingDao.getSighting(sightID);
    }

    @Override
    public void updateSighting(Sighting s) {
       sightingDao.updateSighting(s);
    }

    @Override
    public void deleteSighting(Sighting s) {
        sightingDao.deleteSighting(s);
    }
    
     @Override
    public List<Sighting> getSightings(Location l) {
        List<Integer> sightingIDs = sightingPartial.getSightingsForLoc(l);
        List<Sighting> sightingsByLoc = new ArrayList<>();
        for(int i: sightingIDs){
            sightingsByLoc.add(sightingDao.getSighting(i));
        }
        return sightingsByLoc;
    }

    @Override
    public List<Organization> getOrgs(Location l) {
        List<Organization> orgList = new ArrayList<>();
        List<Integer> idList = orgPartial.getOrgsForLoc(l);
        for(int i: idList){
            orgList.add(orgDao.readOrg(i));
        }
        return orgList;
    }
    
      @Override
    public List<Organization> getOrgs(Super s) {
        List<Organization>orgList = new ArrayList<>();
       List<Integer> idList = soDao.getOrgsForSuper(s);
       for(int i: idList){
           orgList.add(orgDao.readOrg(i));
       }
       return orgList;
    }

    @Override
    public List<Sighting> getSightings(Super s) {
       List<Sighting> sightingList = new ArrayList<>();
       List<Integer> idList = ssDao.getSightingIDsforSuper(s);
       for(int i: idList){
           sightingList.add(sightingDao.getSighting(i));
       }      
       return sightingList;
    }

    @Override
    public List<Super> getSupers(Sighting s) {
        List<Super> superList = new ArrayList<>();
        List<Integer> idList = ssDao.getSuperIDsForSighting(s);
        for(int i: idList){
            superList.add(superDao.getSuperById(i));
        }
        return superList;
    }

    @Override
    public List<Sighting> getTop5() {
        return sightingDao.getTop5();
    }
    
}

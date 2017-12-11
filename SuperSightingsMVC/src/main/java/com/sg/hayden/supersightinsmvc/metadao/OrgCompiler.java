/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.hayden.supersightinsmvc.metadao;

import com.sg.hayden.supersightingsmvc.dao.LocationDao;
import com.sg.hayden.supersightingsmvc.dao.OrgDao;
import com.sg.hayden.supersightingsmvc.dao.SuperDao;
import com.sg.hayden.supersightingsmvc.dao.SuperOrgDao;
import com.sg.hayden.supersightingsmvc.dto.Organization;
import com.sg.hayden.supersightingsmvc.dto.Super;
import java.util.List;

/**
 *
 * @author Hayden
 */
public class OrgCompiler {
    SuperDao superDao;
    LocationDao locPartialDao;
    SuperOrgDao superOrgDao;
    OrgDao orgDao;

    public OrgCompiler(SuperDao superDao, LocationDao locPartialDao, SuperOrgDao superOrgDao, OrgDao orgDao) {
        this.superDao = superDao;
        this.locPartialDao = locPartialDao;
        this.superOrgDao = superOrgDao;
        this.orgDao = orgDao;
    }

   
    
    public void createOrg(Organization o){
        orgDao.insertOrg(o);
        for(Super s: o.getRoster()){
            superOrgDao.createEntry(s,o);
        }
    }
    
    public void deleteOrg(Organization o){
        superOrgDao.deleteByOrg(o);
        orgDao.deleteOrg(o.getId());
    }
    
    public void updateOrg(Organization o){
        orgDao.updateOrg(o);
       List<Integer> superList = superOrgDao.getSupersForOrg(o);
       superOrgDao.deleteByOrg(o);
        for(Super s : o.getRoster()){
            superOrgDao.createEntry(s,o);
        }
    }
    
    public Organization readOrg(int orgID){
        Organization o = orgDao.getOrgById(orgID);
        for(int i: superOrgDao.getSupersForOrg(o))
        {
            o.addToRoster(superDao.getSuperById(i));
        }
        o.setHq(locPartialDao.getLocationById(o.getLocationID()));        
        return o;
    }
    
    public List<Organization> getAllOrgs(){
        List<Organization> orgList = orgDao.getAllOrgs();
        for(Organization o: orgList){
            int orgID = o.getId();
            o=readOrg(orgID);
        }
        
        return orgList;
    }
}

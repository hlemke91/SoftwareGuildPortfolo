/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.hayden.supersightingsmvc.dao;

import com.sg.hayden.supersightingsmvc.dto.Location;
import com.sg.hayden.supersightingsmvc.dto.Organization;
import com.sg.hayden.supersightingsmvc.dto.Super;
import java.util.ArrayList;
import java.util.List;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Hayden
 */
public class OrgPartialDaoMySQL implements OrgDao {

    private final String QUERY_BY_ID = "select * from Organization  where idOrganization =?";
    private final String DELETE_BY_ID = "delete from Organization where idOrganization =?";
    private final String UPDATE = "update Organization set `Name` =?, description =?, idOrganization =?,LocationID=? where idOrganization=?";
    private final String INSERT = "insert into Organization (`Name`,Description,LocationID) values(?,?,?)";
    private final String LIST_ALL_ORGS = "select * from Organization";
    private final String NUKE = "delete from Organization";
    private final String GET_HQ = "select * from Organization where LocationID=?";
    private final String TURN_OFF_FK = "set foreign_key_checks=0";
    private final String TURN_FK_ON = "set foreign_key_checks=1";
    
    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

   
    @Override
    public void nukeTable() {
        jdbcTemplate.update(NUKE);
    }

    @Override
    public Organization getOrgById(int orgId) {
        try {
            return jdbcTemplate.queryForObject(QUERY_BY_ID, new OrgMapper(), orgId);
        } catch (EmptyResultDataAccessException ex) {
            // there were no results for the given contact id - we just 
            // want to return null in this case
            return null;
        }
    }

    @Override
    public void updateOrg(Organization o) {
        jdbcTemplate.execute(TURN_OFF_FK);
        jdbcTemplate.update(UPDATE,o.getName(), o.getDescription(), o.getId(),o.getLocationID(),o.getId());
        jdbcTemplate.execute(TURN_FK_ON);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void insertOrg(Organization o) {
        jdbcTemplate.update(INSERT,o.getName(),o.getDescription(),o.getLocationID());
        
         int newId = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);
        o.setId(newId);
    }

    @Override
    public void deleteOrg(int orgId) {
        jdbcTemplate.update(DELETE_BY_ID, orgId);

    }

    @Override
    public List<Organization> getAllOrgs() {
        return jdbcTemplate.query(LIST_ALL_ORGS, new OrgMapper());
    }


    @Override
    public List<Integer> getOrgsForLoc(Location l) {
       List<Organization> orgList = jdbcTemplate.query(GET_HQ,new OrgMapper(), l.getID());
       List<Integer> idList = new ArrayList<>();
       
       for(Organization o: orgList){
           idList.add(o.getId());
       }
       
       return idList;
    }

    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.hayden.supersightingsmvc.dao;

import com.sg.hayden.supersightingsmvc.dto.Location;
import com.sg.hayden.supersightingsmvc.dto.Sighting;
import java.time.format.DateTimeFormatter;
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
public class SightingDaoMySQL implements SightingDao{

    private final String INSERT = "insert into Sighting (SightingDate,LocationID) values(?,?)";
    private final String GET_BY_ID = "select * from Sighting where idSighting =?";
    private final String UPDATE ="update Sighting set SightingDate=?,LocationID=? where idSighting=?";
    private final String DELETE="delete  from Sighting where idSighting=?";
    private final String GET_ALL = "select * from Sighting";
    private final String GET_MATCHING_LOC = "select * from Sighting where LocationID=?";
    private final String NUKE = "delete from Sighting";
    private final String TOP_FIVE = "select * from sighting order by SightingDate desc limit 5";
     private JdbcTemplate jdbcTemplate;
    
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

   
            
    @Override
     @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void createSighting(Sighting s) {
        jdbcTemplate.update(INSERT,s.getDate(),s.getLocID());
         int newId = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);
        s.setID(newId);
    }

    @Override
    public Sighting getSightingById(int sightingId) {
         try {
            return jdbcTemplate.queryForObject(GET_BY_ID, new SightingMapper(), sightingId);
        } catch (EmptyResultDataAccessException ex) {
            // there were no results for the given contact id - we just 
            // want to return null in this case
            return null;
        }
    }

    @Override
    public void updateSighting(Sighting s) {
        jdbcTemplate.update(UPDATE,s.getDate(),s.getLocID(),s.getID());
    }

    @Override
    public void deleteSighting(int sightingId) {
        jdbcTemplate.update(DELETE,sightingId);
    }

    @Override
    public List<Sighting> getAllSightings() {
        return jdbcTemplate.query(GET_ALL, new SightingMapper());
    }
    
    @Override
    public void nukeTable() {
        jdbcTemplate.update(NUKE);
    }

    @Override
    public List<Integer> getSightingsForLoc(Location l) {
        List<Integer> matchIds = new ArrayList<>();
       List<Sighting> matches = jdbcTemplate.query(GET_MATCHING_LOC, new SightingMapper(),l.getID());
       for(Sighting s: matches){
           matchIds.add(s.getID());
       }       
       return matchIds;
    }

    @Override
    public List<Sighting> getTop5() {
        List<Sighting> ids = jdbcTemplate.query(TOP_FIVE, new SightingMapper());
        return ids;
    }

    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.hayden.supersightingsmvc.dao;

import com.sg.hayden.supersightingsmvc.dto.Location;
import java.util.List;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Hayden
 */
public class LocationDaoMySQL implements LocationDao{

    private final String LIST_ALL = "select * from Location";
    private final String QUERY_BY_ID = "select * from Location where idLocation=?";
    private final String INSERT_LOCATION = "insert into Location (`Name`,Address,Latitude,Longitude) values (?,?,?,?)";
    private final String DELETE_LOCATION = "delete from Location  where idLocation=?";
    private final String UPDATE_LOCATION = "update Location set Name=?,Address=?,Latitude=?,Longitude=? where idLocation =?";
    private final String NUKE = "delete from Location";
    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    
    
    
    @Override
    public Location getLocationById(int locationId) {
      try {
            return jdbcTemplate.queryForObject(QUERY_BY_ID, new LocationMapper(), locationId);
        } catch (EmptyResultDataAccessException ex) {
            // there were no results for the given contact id - we just 
            // want to return null in this case
            return null;
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)    
    public void createLocation(Location l) {
       jdbcTemplate.update(INSERT_LOCATION, l.getName(),l.getAddress(),l.getLatitude(),l.getLongitude());
       
       int newId = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);
        l.setID(newId);
    }

    @Override
    public void updateLocation(Location l) {
        jdbcTemplate.update(UPDATE_LOCATION,l.getName(),l.getAddress(),l.getLatitude(),l.getLongitude(),l.getID());
    }

    @Override
    public void deleteLocation(int locationId) {
        jdbcTemplate.update(DELETE_LOCATION, locationId);
    }

    @Override
    public List<Location> getAllLocations() {
       return jdbcTemplate.query(LIST_ALL, new LocationMapper());
    }

    @Override
    public void nukeTable() {
        jdbcTemplate.update(NUKE);
    }
    
}

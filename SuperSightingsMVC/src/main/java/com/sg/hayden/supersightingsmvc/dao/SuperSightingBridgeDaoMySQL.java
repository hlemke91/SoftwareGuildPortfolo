/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.hayden.supersightingsmvc.dao;

import com.sg.hayden.supersightingsmvc.dto.Sighting;
import com.sg.hayden.supersightingsmvc.dto.Super;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author Hayden
 */
public class SuperSightingBridgeDaoMySQL implements SuperSightingBridgeDao{
    
    private final String CREATE = "insert into Sighting_Super (SightingID,SuperID) values(?,?)";
    private final String UPDATE = "update Sighting_Super set SightingID=?,SuperID=?";
    private final String DELETE_BY_SUPER = "delete from Sighting_Super where SuperID=?";
    private final String DELETE_BY_SIGHTING = "delete from Sighting_Super where SightingID=?";
    private final String QUERY_BY_SIGHTING = "select * from Sighting_Super where SightingID=?";
    private final String QUERY_BY_SUPER = "select * from Sighting_Super where SuperID=?";
    private final String GET_ALL = "select * from Sighting_Super";
    private final String NUKE_TABLE ="truncate table Sighting_Super";
    
    private JdbcTemplate jdbcTemplate;
    
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Integer> getSuperIDsForSighting(Sighting s) {
       ArrayList<Integer> superIds = new ArrayList<>();
       List<List<Integer>> matches = jdbcTemplate.query(QUERY_BY_SIGHTING, new BridgeMapper(), s.getID());
       for(List l: matches){
           int superID = (Integer) l.get(0);
           superIds.add(superID);
       }
       
       return superIds;
    }

    @Override
    public List<Integer> getSightingIDsforSuper(Super s) {
          ArrayList<Integer> sightingIds = new ArrayList<>();
       List<List<Integer>> matches = jdbcTemplate.query(QUERY_BY_SUPER, new BridgeMapper(), s.getID());
       for(List l: matches){
           int sightingID = (Integer) l.get(1);
           sightingIds.add(sightingID);
       }
       
       return sightingIds;
    }

    @Override
    public void createEntry(Super sh, Sighting sight) {
        jdbcTemplate.update(CREATE,sight.getID(),sh.getID());
    }

    @Override
    public void updateEntry(Super sh, Sighting sight) {
        jdbcTemplate.update(UPDATE, sight.getID(), sh.getID());
    }

    @Override
    public void deleteSuper(Super s) {
        jdbcTemplate.update(DELETE_BY_SUPER, s.getID());
    }

    @Override
    public void deleteSighting(Sighting s) {
           jdbcTemplate.update(DELETE_BY_SIGHTING, s.getID());
    }

    @Override
    public List<List<Integer>> getAllBridges() {
        return jdbcTemplate.query(GET_ALL, new BridgeMapper());
    }

    @Override
    public void nukeTable() {
        jdbcTemplate.update(NUKE_TABLE);
    }

   
    
    private class BridgeMapper implements RowMapper<List<Integer>>{

        @Override
        public List<Integer> mapRow(ResultSet rs, int i) throws SQLException {
            List<Integer> bridgeValues =new  ArrayList<>();            
            bridgeValues.add(rs.getInt("SuperID"));
            bridgeValues.add(rs.getInt("SightingID"));            
            return bridgeValues;
            
        }
        
    }
    
}

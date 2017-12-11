/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.hayden.supersightingsmvc.dao;

import com.sg.hayden.supersightingsmvc.dto.Organization;
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
public class SuperOrgDaoMySQL implements SuperOrgDao {
    private final String INSERT = "insert into Super_Org (SuperID,Org_ID) values(?,?)";
    private final String UPDATE ="update Super_Org set SuperID=?,OrgID=?";
    private final String DELETE_BY_SUPER = "delete from Super_Org where SuperID=?";
    private final String DELETE_BY_ORG = "delete from Super_Org where Org_ID=?";
    private final String NUKE = "delete from Super_Org";
    private final String GET_ALL = "select * from Super_Org";
    private final String GET_SUPERS_FOR_ORG = "select * from Super_Org where Org_ID=?";
    private final String GET_ORGS_FOR_SUPER = "select * from Super_Org where SuperID=?";
    

    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    

    @Override
    public void createEntry(Super s, Organization o) {
        jdbcTemplate.update(INSERT,s.getID(),o.getId());
    }

    @Override
    public void updateEntry(Super s, Organization o) {
        jdbcTemplate.update(UPDATE, s.getID(), o.getId());
    }

    @Override
    public void nukeTable() {
        jdbcTemplate.update(NUKE);
    }

    @Override
    public List<Integer> getSupersForOrg(Organization o) {
        List<List<Integer>> matches = jdbcTemplate.query(GET_SUPERS_FOR_ORG, new BridgeMapper(), o.getId());
        List<Integer> supers = new ArrayList<>();
        for(List<Integer> l: matches){
            supers.add(l.get(0));
        }
        return supers;
    }

    @Override
    public List<Integer> getOrgsForSuper(Super s) {
        List<List<Integer>> matches = jdbcTemplate.query(GET_ORGS_FOR_SUPER, new BridgeMapper(),s.getID());
        List<Integer> orgs = new ArrayList<>();
        for(List<Integer> l:matches){
            orgs.add(l.get(1));
        }
        
        return orgs;
    }

    @Override
    public List<List<Integer>> getAllEntries() {
        return jdbcTemplate.query(GET_ALL, new BridgeMapper());
    }

    @Override
    public void deleteBySuper(Super s) {
        jdbcTemplate.update(DELETE_BY_SUPER,s.getID());
    }

    @Override
    public void deleteByOrg(Organization o) {
        jdbcTemplate.update(DELETE_BY_ORG,o.getId());
    }

    private class BridgeMapper implements RowMapper<List<Integer>>{

        @Override
        public List<Integer> mapRow(ResultSet rs, int i) throws SQLException {
            List<Integer> superOrgRow = new ArrayList<>();
            superOrgRow.add(rs.getInt("SuperID"));
            superOrgRow.add(rs.getInt("Org_ID"));
            
            return superOrgRow;
            
        }
    
}
}

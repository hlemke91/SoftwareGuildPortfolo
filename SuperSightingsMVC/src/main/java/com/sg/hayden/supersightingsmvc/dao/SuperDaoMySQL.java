/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.hayden.supersightingsmvc.dao;

import com.sg.hayden.supersightingsmvc.dto.Super;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Hayden
 */
public class SuperDaoMySQL implements SuperDao{

    private final String QUERY_BY_ID = "select * from SuperPerson where idSuperPerson =?";
    private final String UPDATE = "update SuperPerson set Name=?,Identity=?,SuperPower=?,Description=? where idSuperPerson=?";
    private final String INSERT = "insert into SuperPerson (Name,Identity,SuperPower,Description) values(?,?,?,?)";
    private final String DELETE="delete from SuperPerson where idSuperPerson=?";
    private final String GET_ALL="select * from SuperPerson";
    private final String NUKE = "delete from SuperPerson";
    
     private JdbcTemplate jdbcTemplate;
    
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    @Override
    public Super getSuperById(int superId) {
        return jdbcTemplate.queryForObject(QUERY_BY_ID, new SuperMapper(), superId);
    }

    @Override
    public void updateSuper(Super s) {
        
        jdbcTemplate.update(UPDATE,
                s.getName(),
                s.getIdentity(),
                s.getSuperPower(),
                s.getDescription(),
                 s.getID());
    }

    @Override
     @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void insertSuper(Super s) {
        jdbcTemplate.update(INSERT, s.getName(),s.getIdentity(),s.getSuperPower(),s.getDescription());
        
         int newId = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);
        s.setID(newId);
    }

    @Override
    public void deleteSuper(int superId) {
        jdbcTemplate.update(DELETE,superId);
    }

    @Override
    public List<Super> getAllSupers() {
        return jdbcTemplate.query(GET_ALL, new SuperMapper());
    }
    
    @Override
    public void nukeTable() {
        jdbcTemplate.update(NUKE);
    }
}

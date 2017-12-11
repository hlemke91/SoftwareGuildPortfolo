/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.hayden.supersightingsmvc.dao;

import com.sg.hayden.supersightingsmvc.dto.Super;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author Hayden
 */
public class SuperMapper implements RowMapper<Super>{

    @Override
    public Super mapRow(ResultSet rs, int i) throws SQLException {
        Super s = new Super();
        s.setID(rs.getInt("idSuperPerson"));
        s.setName(rs.getString("Name"));
        s.setIdentity(rs.getString("Identity"));
        s.setSuperPower(rs.getString("SuperPower"));
        s.setDescription(rs.getString("Description"));
        
        return s;                
    }
    
}

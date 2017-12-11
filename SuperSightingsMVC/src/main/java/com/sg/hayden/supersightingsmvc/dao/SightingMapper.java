/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.hayden.supersightingsmvc.dao;

import com.sg.hayden.supersightingsmvc.dto.Sighting;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author Hayden
 */
public class SightingMapper implements RowMapper<Sighting> {

    @Override
    public Sighting mapRow(ResultSet rs, int i) throws SQLException {
       Sighting s = new Sighting();
       s.setID(rs.getInt("idSighting"));
       s.setDate(rs.getDate("SightingDate"));
       s.setLocID(rs.getInt("LocationID"));
       return s;
    }

    
    
}

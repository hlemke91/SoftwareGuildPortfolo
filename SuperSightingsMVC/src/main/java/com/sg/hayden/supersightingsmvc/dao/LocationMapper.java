/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.hayden.supersightingsmvc.dao;

import com.sg.hayden.supersightingsmvc.dto.Location;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author Hayden
 */
public class LocationMapper implements RowMapper<Location>{
     public Location mapRow(ResultSet rs, int rowNum) throws SQLException {
            Location l = new Location();
            l.setID(rs.getInt("idLocation"));
            l.setLatitude(rs.getDouble("Latitude"));
            l.setLongitude(rs.getDouble("Longitude"));
            l.setAddress(rs.getString("Address"));
            l.setName(rs.getString("Name"));
            
            return l;
        }
    
}

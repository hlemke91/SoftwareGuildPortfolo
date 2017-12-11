/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.hayden.supersightingsmvc.dao;

import com.sg.hayden.supersightingsmvc.dto.Organization;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author Hayden
 */
public class OrgMapper implements RowMapper<Organization> {
     public Organization mapRow(ResultSet rs, int rowNum) throws SQLException {
             Organization org = new Organization();
            org.setId(rs.getInt("idOrganization"));
            org.setName(rs.getString("Name"));
            org.setLocationID(rs.getInt("LocationID"));
            org.setDescription(rs.getString("Description"));
            return org;
        }
    }
    


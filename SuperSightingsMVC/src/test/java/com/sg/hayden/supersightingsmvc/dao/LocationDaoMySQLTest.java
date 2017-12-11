/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.hayden.supersightingsmvc.dao;

import com.sg.hayden.supersightingsmvc.dto.Location;
import com.sg.hayden.supersightingsmvc.dto.Organization;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author Hayden
 */
public class LocationDaoMySQLTest {
    
    LocationDao dao;
    
    public LocationDaoMySQLTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
         ApplicationContext ctx = new ClassPathXmlApplicationContext("test-applicationContext.xml");

        dao = ctx.getBean("locPartialDao", LocationDao.class);
        
        List<Location> allLocs = dao.getAllLocations();
        
        for(Location l: allLocs ){
            dao.deleteLocation(l.getID());
        }
    }
    
    @After
    public void tearDown() {
    }

   @Test
   public void testInsertandRead(){
       Location generated = new Location();
       generated.setName("Fortress of Solitude");
       generated.setAddress("123 Washout Ln");
       generated.setLatitude(-5);
       generated.setLongitude(11);
       
       dao.createLocation(generated);
       int newId = generated.getID();
       
       Location fromDao = dao.getLocationById(newId);
       assertEquals(fromDao,generated);       
   }
   
   @Test
   public void testDelete(){
        Location generated = new Location();
       generated.setName("Fortress of Solitude");
       generated.setAddress("123 Washout Ln");
       generated.setLatitude(-5);
       generated.setLongitude(11);
       
       dao.createLocation(generated);
       int newId = generated.getID();
       
       dao.deleteLocation(newId);
       
       assert(dao.getAllLocations().size()==0);       
   }
   
   @Test
   public void testUpdate(){
        Location generated = new Location();
       generated.setName("Fortress of Solitude");
       generated.setAddress("123 Washout Ln");
       generated.setLatitude(-5);
       generated.setLongitude(11);
       
       dao.createLocation(generated);
       int newId = generated.getID();
       generated.setName("The Batcave");
       
       dao.updateLocation(generated);
       Location fromDao = dao.getLocationById(newId);
       
       assertEquals(generated,fromDao);
   }
    
}

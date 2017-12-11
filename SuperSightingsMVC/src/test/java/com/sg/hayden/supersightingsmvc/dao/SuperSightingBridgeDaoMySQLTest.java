/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.hayden.supersightingsmvc.dao;

import com.sg.hayden.supersightingsmvc.dto.Location;
import com.sg.hayden.supersightingsmvc.dto.Sighting;
import com.sg.hayden.supersightingsmvc.dto.Super;
import java.sql.Date;
import java.time.LocalDate;
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
public class SuperSightingBridgeDaoMySQLTest {
    
    SuperSightingBridgeDao dao;
    SightingDao sightingDao;
    SuperDao superDao;
    LocationDao ldao;
    
    public SuperSightingBridgeDaoMySQLTest() {
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

        dao = ctx.getBean("superSightingDao", SuperSightingBridgeDao.class);       
       superDao = ctx.getBean("superDao", SuperDao.class);
       sightingDao = ctx.getBean("sightingDao", SightingDao.class);
       ldao = ctx.getBean("locPartialDao", LocationDao.class);
        
       ldao.nukeTable();
       sightingDao.nukeTable();
       superDao.nukeTable();    
       dao.nukeTable();
    }
    
    @After
    public void tearDown() {
    }

    @Test 
     public void testCreate(){
        Super sh = new Super();
        Location l = new Location();
        l.setName("Some place");
       ldao.createLocation(l);
       
        sh.setName("Crimson Chin");
        superDao.insertSuper(sh);
        
        Sighting s = new Sighting();
        s.setLocation(l);
        s.setLocID(l.getID());
        s.setDate(Date.valueOf(LocalDate.now()));        
        sightingDao.createSighting(s);
     
        dao.createEntry(sh, s);
        assert(dao.getAllBridges().size()==1);
    }
    
     @Test
     public void testDeleteHero(){
       
        Location l = new Location();
        l.setName("Some place");
       ldao.createLocation(l);
       
          Super sh = new Super();             
        sh.setName("Crimson Chin");
        superDao.insertSuper(sh);
        
        Sighting s = new Sighting();
        s.setLocID(l.getID());
        sightingDao.createSighting(s);
     
        dao.createEntry(sh, s);
        dao.deleteSuper(sh);
        
        assert(dao.getAllBridges().size()==0);
         
     }
     
      @Test
     public void testDeleteSighting(){
       
        Location l = new Location();
        l.setName("Some place");
       ldao.createLocation(l);
         
         
          Super sh = new Super();
       
        sh.setName("Crimson Chin");
        superDao.insertSuper(sh);
        
        Sighting s = new Sighting();
        s.setLocID(l.getID());
        sightingDao.createSighting(s);
        
     
        dao.createEntry(sh, s);
        dao.deleteSighting(s);
        
        assert(dao.getAllBridges().size()==0);
         
     }
     
     @Test
     public void testGetSupersForSighting(){
         Super sh = new Super();
          
        Location l = new Location();
        l.setName("Some place");
       ldao.createLocation(l);
       
        sh.setName("Crimson Chin");
        superDao.insertSuper(sh);
        int shID = sh.getID();
        
        
        Super other = new Super();
        other.setName("BIG WHEEL");
        superDao.insertSuper(other);
        int otherID = other.getID();
        
        
        
        Sighting s = new Sighting();
        s.setLocID(l.getID());
        sightingDao.createSighting(s);
     
        dao.createEntry(sh, s);
        dao.createEntry(other,s);
        
        List<Integer> superIDs = dao.getSuperIDsForSighting(s);
        
        assert(superIDs.contains(shID)&&superIDs.contains(otherID));  
     }
     
     @Test
     public void testGetSightingsForSupers(){
            Super sh = new Super();
        Location l = new Location();
        l.setName("Some place");
       ldao.createLocation(l);
       
        sh.setName("Crimson Chin");
        superDao.insertSuper(sh);
        int shID = sh.getID();
        
        
        Super other = new Super();
        other.setName("BIG WHEEL");
        superDao.insertSuper(other);
        int otherID = other.getID();
        
        
        
        Sighting s = new Sighting();
        s.setLocID(l.getID());
        sightingDao.createSighting(s);
     
        
        Sighting otherS = new Sighting();
        otherS.setLocID(l.getID());
        sightingDao.createSighting(otherS);
        
        dao.createEntry(sh, s);
        dao.createEntry(sh, otherS);
        
        List<Integer> sightings = dao.getSightingIDsforSuper(sh);
        
        assert(sightings.contains(s.getID())&&sightings.contains(otherS.getID()));
     }
}

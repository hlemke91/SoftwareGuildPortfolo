/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.hayden.supersightinsmvc.metadao;

import com.sg.hayden.supersightingsmvc.dao.LocationDao;
import com.sg.hayden.supersightingsmvc.dao.SightingDao;
import com.sg.hayden.supersightingsmvc.dao.SuperDao;
import com.sg.hayden.supersightingsmvc.dao.SuperSightingBridgeDao;
import com.sg.hayden.supersightingsmvc.dto.Location;
import com.sg.hayden.supersightingsmvc.dto.Sighting;
import com.sg.hayden.supersightingsmvc.dto.Super;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author Hayden
 */
public class SightingComplierTest {
    SightingCompiler sc;
    SightingDao sightDao;
    SuperSightingBridgeDao ssbDao;
    SuperDao superDao;
    LocationDao locDao;
    public SightingComplierTest() {
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
         
         sc = ctx.getBean("sightingCompiler",SightingCompiler.class);
        superDao=ctx.getBean("superDao", SuperDao.class);
        locDao = ctx.getBean("locPartialDao",LocationDao.class);
        ssbDao = ctx.getBean("superSightingDao", SuperSightingBridgeDao.class);
        sightDao = ctx.getBean("sightingDao",SightingDao.class);
        
        ssbDao.nukeTable();
        sightDao.nukeTable();
        superDao.nukeTable();
        locDao.nukeTable();        
        
    }
    
    @After
    public void tearDown() {
    }
@Test
public void testInsertAndRead(){
    Super dr = new Super();    
    dr.setName("Dr. Horrible");
    
    Super capn = new Super();
    capn.setName("Captain Hammer");
    
    superDao.insertSuper(dr);
    superDao.insertSuper(capn);
    
    Location street = new Location();
    street.setName("The Street");
    locDao.createLocation(street);
    
    Sighting s = new Sighting();
    s.setLocation(street);
    s.addSuper(dr);
    s.addSuper(capn);
    
    sc.createSighting(s);
    int id = s.getID();
    
    Sighting fromDao = sc.getSighting(id);
    
    assertEquals(s,fromDao);
}

@Test
public void testUpdate(){
    
     Super dr = new Super();    
    dr.setName("Dr. Horrible");
    
    Super capn = new Super();
    capn.setName("Captain Hammer");
    
    superDao.insertSuper(dr);
    superDao.insertSuper(capn);
    
    Location street = new Location();
    street.setName("The Street");
    locDao.createLocation(street);
    
    Location bridge = new Location();
    bridge.setName("Hero Memorial Bridge");
    locDao.createLocation(bridge);
    
    Sighting s = new Sighting();
    s.setLocation(street);
    s.addSuper(dr);
    s.addSuper(capn);
    
    sc.createSighting(s);
        s.setLocation(bridge);
    sc.updateSighting(s);
    int id = s.getID();
    Sighting fromDao = sc.getSighting(id);
    
    assertEquals(s,fromDao);
    
}

@Test
public void testDelete(){
    
     Super dr = new Super();    
    dr.setName("Dr. Horrible");
    
    Super capn = new Super();
    capn.setName("Captain Hammer");
    
    superDao.insertSuper(dr);
    superDao.insertSuper(capn);
    
    Location street = new Location();
    street.setName("The Street");
    locDao.createLocation(street);
    
    Location bridge = new Location();
    bridge.setName("Hero Memorial Bridge");
    locDao.createLocation(bridge);
    
    Sighting s = new Sighting();
    s.setLocation(street);
    s.addSuper(dr);
    s.addSuper(capn);
    
    sc.createSighting(s);
    sc.deleteSighting(s);
    
    assert(sightDao.getAllSightings().size()==0);
}

}

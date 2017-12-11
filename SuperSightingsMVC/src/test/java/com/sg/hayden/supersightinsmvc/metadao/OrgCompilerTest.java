/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.hayden.supersightinsmvc.metadao;

import com.sg.hayden.supersightingsmvc.dao.LocationDao;
import com.sg.hayden.supersightingsmvc.dao.OrgDao;
import com.sg.hayden.supersightingsmvc.dao.SuperDao;
import com.sg.hayden.supersightingsmvc.dao.SuperOrgDao;
import com.sg.hayden.supersightingsmvc.dto.Location;
import com.sg.hayden.supersightingsmvc.dto.Organization;
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
public class OrgCompilerTest {
   protected OrgCompiler oc;
   protected SuperDao superDao;
   protected LocationDao locDao;
   protected SuperOrgDao superOrgDao;
   protected OrgDao orgDao;
    
    public OrgCompilerTest() {
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

        oc = ctx.getBean("orgCompiler",OrgCompiler.class);
        superDao=ctx.getBean("superDao", SuperDao.class);
        locDao = ctx.getBean("locPartialDao",LocationDao.class);
        superOrgDao = ctx.getBean("superOrgDao",SuperOrgDao.class);
        orgDao = ctx.getBean("orgPartialDao",OrgDao.class);
        
        superOrgDao.nukeTable();
        superDao.nukeTable();
        locDao.nukeTable();        
        orgDao.nukeTable();
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void testCreateRead(){
        Location hq= new Location();
        hq.setName("Halls of Justice");
        hq.setAddress("Metropolis?");
        locDao.createLocation(hq);
        
        Super man = new Super();
        man.setName("Superman");
        man.setIdentity("Clark Kent");
        man.setSuperPower("Super Everything");
        man.setDescription("This dude super sucks");
        Super ww = new Super();
        ww.setName("Wonder Woman");
        ww.setIdentity("secret");
        ww.setDescription("wonderful");
        ww.setSuperPower("cool rope and shield also an invisble jet");
        superDao.insertSuper(ww);
       superDao.insertSuper(man);
        
        Organization org = new Organization();
        org.setHq(hq);
        org.addToRoster(ww);
        org.addToRoster(man);
        org.setName("Justice League");
        org.setDescription("They do justice stuff, I guess");
       
        oc.createOrg(org);
        int id = org.getId();
        
        Organization fromDao = oc.readOrg(id);
        
        assertEquals(fromDao,org);
        
    }   
    
    
    @Test
    public void testUpdate(){
            Location hq= new Location();
        hq.setName("Halls of Justice");
        hq.setAddress("Metropolis?");
        locDao.createLocation(hq);
        
        Super man = new Super();
        man.setName("Superman");
        man.setIdentity("Clark Kent");
        man.setSuperPower("Super Everything");
        man.setDescription("This dude super sucks");
        Super ww = new Super();
        ww.setName("Wonder Woman");
        ww.setIdentity("secret");
        ww.setDescription("wonderful");
        ww.setSuperPower("cool rope and shield also an invisble jet");
        superDao.insertSuper(ww);
       superDao.insertSuper(man);
        
        Organization org = new Organization();
        org.setHq(hq);
        org.addToRoster(ww);
        org.addToRoster(man);
        org.setName("Justice League");
        org.setDescription("They do justice stuff, I guess");
       
        oc.createOrg(org);
        int id = org.getId();
        
        Organization fromDao = oc.readOrg(id);
        
        assertEquals(fromDao,org);
        
    }   
    
    @Test
    public void testDelete(){
        Location hq= new Location();
        hq.setName("Halls of Justice");
        hq.setAddress("Metropolis?");
        locDao.createLocation(hq);
        
        Super man = new Super();
        man.setName("Superman");
        man.setIdentity("Clark Kent");
        man.setSuperPower("Super Everything");
        man.setDescription("This dude super sucks");
        Super ww = new Super();
        ww.setName("Wonder Woman");
        ww.setIdentity("secret");
        ww.setDescription("wonderful");
        ww.setSuperPower("cool rope and shield also an invisble jet");
        superDao.insertSuper(ww);
       superDao.insertSuper(man);
        
        Organization org = new Organization();
        org.setHq(hq);
        org.addToRoster(ww);
        org.addToRoster(man);
        org.setName("Justice League");
        org.setDescription("They do justice stuff, I guess");
       
        oc.createOrg(org);
        oc.deleteOrg(org);
        
        assert(orgDao.getAllOrgs().size()==0);
    }
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.hayden.supersightingsmvc.dao;

import com.sg.hayden.supersightingsmvc.dto.Location;
import com.sg.hayden.supersightingsmvc.dto.Organization;
import com.sg.hayden.supersightingsmvc.dto.Super;
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
public class SuperOrgDaoMySQLTest {
    SuperOrgDao dao;
    SuperDao superDao;
    OrgDao orgDao;
    LocationDao ldao;
    public SuperOrgDaoMySQLTest() {
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

        dao = ctx.getBean("superOrgDao", SuperOrgDao.class);       
       superDao = ctx.getBean("superDao", SuperDao.class);
       orgDao = ctx.getBean("orgPartialDao", OrgDao.class);
       ldao = ctx.getBean("locPartialDao", LocationDao.class);
        
          
       orgDao.nukeTable();
       superDao.nukeTable();    
       dao.nukeTable();
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testCreateEntry(){
        Super s = new Super();
        s.setName("Bad Horse");
        
        Location l = new Location();
        l.setName("ELE HQ");
        ldao.createLocation(l);
        
        
        superDao.insertSuper(s);
        int superID = s.getID();
        
        Organization o = new Organization();
        o.setName("Evil League of Evil");
        o.setLocationID(l.getID());
        orgDao.insertOrg(o);
        int orgID = o.getId();
        
        dao.createEntry(s,o);
        
        assert(dao.getAllEntries().size()==1);
    }
   
    @Test
    public void testGetSupers(){
         Location l = new Location();
        l.setName("ELE HQ");
        ldao.createLocation(l);
        
         Super s = new Super();
        s.setName("Bad Horse");
        Super other = new Super();
        other.setName("Dead Bowie");
        
        superDao.insertSuper(s);
        int superID = s.getID();
        superDao.insertSuper(other);
        int otherID = other.getID();
        
        Organization o = new Organization();
        o.setName("Evil League of Evil");
        o.setLocationID(l.getID());
        orgDao.insertOrg(o);
        int orgID = o.getId();
        
        dao.createEntry(s,o);
        dao.createEntry(other, o);
        
        List<Integer> supers = dao.getSupersForOrg(o);
        
        assert(supers.size()==2&&supers.contains(superID)&&supers.contains(otherID));
    }
    
    @Test
    public void testGetOrgs(){
        
         Location l = new Location();
        l.setName("ELE HQ");
        ldao.createLocation(l);
        
          Super s = new Super();
        s.setName("Bad Horse");
        Super other = new Super();
        other.setName("Dead Bowie");
        
        superDao.insertSuper(s);
        int superID = s.getID();
        superDao.insertSuper(other);
        int otherID = other.getID();
        
        Organization o = new Organization();
        o.setName("Evil League of Evil");
        o.setLocationID(l.getID());
        orgDao.insertOrg(o);
        int orgID = o.getId();
        
        Organization org = new Organization();
        org.setName("Acutal People");
        org.setLocationID(l.getID());
        orgDao.insertOrg(org);
        
        int peopleID = org.getId();
        dao.createEntry(s,o);
        dao.createEntry(other, o);
        dao.createEntry(other,org);
        
        List<Integer> orgs = dao.getOrgsForSuper(other);
        
        assert(orgs.size()==2&&orgs.contains(peopleID)&&orgs.contains(orgID));
    }
}

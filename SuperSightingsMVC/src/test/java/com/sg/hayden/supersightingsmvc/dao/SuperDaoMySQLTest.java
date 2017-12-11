/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.hayden.supersightingsmvc.dao;

import com.sg.hayden.supersightingsmvc.dto.Sighting;
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
public class SuperDaoMySQLTest {

    SuperDao dao;

    public SuperDaoMySQLTest() {
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

        dao = ctx.getBean("superDao", SuperDao.class);

        List<Super> allSupers = dao.getAllSupers();

        for (Super s : allSupers) {
            dao.deleteSuper(s.getID());
        }
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testCreateAndRead() {
        Super generated = new Super();
        generated.setName("Iron Man");
        generated.setIdentity("Tony Stark");
        generated.setSuperPower("Wealth");
        generated.setDescription("A drunk guy with a cool robot suit");

        dao.insertSuper(generated);
        int id = generated.getID();

        Super fromDao = dao.getSuperById(id);

        assertEquals(fromDao, generated);
    }

    @Test
    public void testUpdate() {
        Super generated = new Super();
        generated.setName("Iron Man");
        generated.setIdentity("Tony Stark");
        generated.setSuperPower("Wealth");
        generated.setDescription("A drunk guy with a cool robot suit");

        dao.insertSuper(generated);
        int id = generated.getID();
        generated.setName("War Machine");
        generated.setIdentity("Don Cheadle");
        dao.updateSuper(generated);

        Super updatedFromDao = dao.getSuperById(id);

        assertEquals(updatedFromDao, generated);

    }

    @Test
    public void testDelete() {
        Super generated = new Super();
        generated.setName("Iron Man");
        generated.setIdentity("Tony Stark");
        generated.setSuperPower("Wealth");
        generated.setDescription("A drunk guy with a cool robot suit");

        dao.insertSuper(generated);
        int id = generated.getID();
        dao.deleteSuper(id);

        assert (dao.getAllSupers().size() == 0);
    }

}

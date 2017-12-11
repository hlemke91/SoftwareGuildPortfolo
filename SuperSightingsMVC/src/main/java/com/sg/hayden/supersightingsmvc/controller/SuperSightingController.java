/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.hayden.supersightingsmvc.controller;

import com.sg.hayden.supersightingsmvc.dto.Location;
import com.sg.hayden.supersightingsmvc.dto.Organization;
import com.sg.hayden.supersightingsmvc.dto.Sighting;
import com.sg.hayden.supersightingsmvc.dto.Super;
import com.sg.hayden.supersightingsmvc.service.SuperService;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Hayden
 */
@Controller
public class SuperSightingController {

    protected SuperService service;

    protected List<Super> superList;
    protected List<Location> locList;
    protected List<Organization> orgList;
    protected List<Sighting> sightingList;

    protected Super superhero;
    protected Location location;
    protected Organization org;
    protected Sighting sighting;

    protected Random rng;

    @Inject
    public SuperSightingController(SuperService service) {
        this.service = service;
        rng = new Random();

//        superList = service.getAllSupers();
//        locList = service.getAllLocations();
//        orgList = service.getAllOrgs();
//        sightingList = service.getAllSightings();
    }

    @RequestMapping(value = "/displaySupersPage", method = RequestMethod.GET)
    public String displaySupersPage(Model m) {
        superList = service.getAllSupers();
        m.addAttribute(superList);
        return "displaySupersPage";
    }

    @RequestMapping(value = "/displayOrgsPage", method = RequestMethod.GET)
    public String displayOrgsPage(Model m) {
        orgList = service.getAllOrgs();
        m.addAttribute("orgs", orgList);
        return "displayOrgsPage";
    }

    @RequestMapping(value = "/displayLocsPage", method = RequestMethod.GET)
    public String displayLocsPage(Model m) {
        locList = service.getAllLocations();
        m.addAttribute(locList);
        return "displayLocsPage";
    }

    @RequestMapping(value = "/displaySightingsPage", method = RequestMethod.GET)
    public String displaySightingsPage(Model m) {
        sightingList = service.getAllSightings();
        m.addAttribute("sightings", sightingList);
        return "displaySightingsPage";
    }

    @RequestMapping(value = "index", method = RequestMethod.GET)
    public String displayHome() {
        return "index";
    }

    @RequestMapping(value = "/displayLocFull", method = RequestMethod.GET)
    public String displayLocFull(Model m, HttpServletRequest r) {
        int locID = Integer.parseInt(r.getParameter("locID"));
        Location loc = service.readLocation(locID);
        List<Sighting> sightingsHere = service.getSightings(loc);
        List<Super> supersHere = new ArrayList<>();
        for (Sighting s : sightingsHere) {
            List<Super> supers = s.getSupers();
            for (Super sh : supers) {
                supersHere.add(sh);
            }
        }

        m.addAttribute(loc);
        m.addAttribute("supersHere", supersHere);
        return "displayLocFull";
    }

    @RequestMapping(value = "/displaySuperFull", method = RequestMethod.GET)
    public String displaySuperFull(Model m, HttpServletRequest r) {
        int superID = Integer.parseInt(r.getParameter("superID"));
        Super superPerson = service.readSuper(superID);
        List<Location> placesSighted = new ArrayList<>();
        List<Sighting> sightings = service.getSightings(superPerson);
        for (Sighting s : sightings) {
            placesSighted.add(s.getLocation());
        }
        List<Organization> orgs = service.getOrgs(superPerson);
        m.addAttribute("orgs", orgs);
        m.addAttribute("locs", placesSighted);
        m.addAttribute("superhero", superPerson);

        return "displaySuperFull";
    }

    @RequestMapping(value = "/deleteLoc", method = RequestMethod.GET)
    public String deleteLocation(HttpServletRequest r) {
        int locID = Integer.parseInt(r.getParameter("locID"));

        //this is goofy and SLOOOOW, but will require a new DAO/service method
        Location l = service.readLocation(locID);
        service.removeLocation(l);

        return "redirect:displayLocsPage";
    }

    @RequestMapping(value = "/editLoc", method = RequestMethod.GET)
    public String editLocation(Model m, HttpServletRequest r) {
        int locID = Integer.parseInt(r.getParameter("locID"));
        Location l = service.readLocation(locID);
        m.addAttribute("loc", l);

        return "editLoc";
    }

    @RequestMapping(value = "/updateLoc", method = RequestMethod.POST)
    public String updateLocation(HttpServletRequest r) {
        Location l = new Location();
       // l.setID(Integer.parseInt(r.getParameter("locID")));
        l.setAddress(r.getParameter("address"));
        l.setLatitude(Double.parseDouble(r.getParameter("latitude")));
        l.setLongitude(Double.parseDouble(r.getParameter("longitude")));
        l.setName(r.getParameter("name"));
        l.setID(Integer.parseInt(r.getParameter("locID")));
        service.updateLocation(l);
        return "redirect:displayLocsPage";
    }

    @RequestMapping(value = "/createLoc", method = RequestMethod.GET)
    public String createLocation(HttpServletRequest r) {
        Location l = new Location();
        l.setAddress(r.getParameter("address"));
        l.setLatitude(Double.parseDouble(r.getParameter("latitude")));
        l.setLongitude(Double.parseDouble(r.getParameter("longitude")));
        l.setName(r.getParameter("name"));
        service.createLocation(l);
        return "redirect:displayLocsPage";
    }

    @RequestMapping(value = "/addLoc", method = RequestMethod.GET)
    public String navAddLoc() {
        return "addLoc";
    }

    @RequestMapping(value = "/addSuper", method = RequestMethod.GET)
    public String navAddSuper() {
        return "addSuper";
    }

    @RequestMapping(value = "/createSuper", method = RequestMethod.POST)
    public String createSuperhero(HttpServletRequest r) {
        Super s = new Super();
        s.setDescription(r.getParameter("description"));
        s.setName(r.getParameter("name"));
        s.setIdentity(r.getParameter("identity"));
        s.setSuperPower(r.getParameter("power"));
        service.createSuper(s);
        return "redirect:displaySupersPage";
    }

    @RequestMapping(value = "/editSuper", method = RequestMethod.GET)
    public String editSuper(HttpServletRequest r, Model m) {
        Super s = service.readSuper(Integer.parseInt(r.getParameter("superID")));
        m.addAttribute("superhero", s);
        return "editSuper";
    }

    @RequestMapping(value = "/deleteSuper", method = RequestMethod.GET)
    public String deleteSuper(HttpServletRequest r) {
        int id = Integer.parseInt(r.getParameter("superID"));
        Super s = service.readSuper(id);
        service.removeSuper(s);
        return "redirect:displaySupersPage";
    }

    @RequestMapping(value = "/updateSuper", method = RequestMethod.GET)
    public String updateSuper(HttpServletRequest r) {
        Super s = new Super();
        int id = Integer.parseInt(r.getParameter("ID"));
        s.setID(id);
        s.setName(r.getParameter("name"));
        s.setIdentity(r.getParameter("identity"));
        s.setSuperPower(r.getParameter("power"));
        s.setDescription(r.getParameter("description"));
        service.updateSuper(s);
        return "redirect:displaySupersPage";
    }

    @RequestMapping(value = "/addOrg", method = RequestMethod.GET)
    public String addOrg(Model m) {
        List<Super> supers = service.getAllSupers();
        List<Location> locs = service.getAllLocations();

        m.addAttribute("supers", supers);
        m.addAttribute("locs", locs);
        return "addOrg";
    }

    @RequestMapping(value = "/createOrg", method = RequestMethod.POST)
    public String createOrg(HttpServletRequest r) {
        Organization o = new Organization();
        o.setName(r.getParameter("name"));
        Location l = service.readLocation(Integer.parseInt(r.getParameter("hq")));
        o.setHq(l);
        o.setDescription(r.getParameter("description"));
        String[] superStrings = r.getParameterValues("sh");
if(superStrings!=null){
        for (String s : superStrings) {
            int id = Integer.parseInt(s);
            Super sh = service.readSuper(id);
            o.addToRoster(sh);
        }
}
        service.createOrg(o);
        return "redirect:displayOrgsPage";
    }

    @RequestMapping(value = "/deleteOrg", method = RequestMethod.GET)
    public String deleteOrg(HttpServletRequest r) {
        int orgid = Integer.parseInt(r.getParameter("orgID"));
        Organization o = service.readOrg(orgid);
        service.removeOrg(o);
        return "redirect:displayOrgsPage";
    }

    @RequestMapping(value = "/editOrg", method = RequestMethod.GET)
    public String editOrg(HttpServletRequest r, Model m) {
        List<Location> locs = service.getAllLocations();
        List<Super> supers = service.getAllSupers();
        int orgID = Integer.parseInt(r.getParameter("orgID"));
        Organization o = service.readOrg(orgID);
        m.addAttribute("org", o);
        m.addAttribute("supers", supers);
        m.addAttribute("locs", locs);
        return "editOrg";
    }

    @RequestMapping(value = "/updateOrg", method = RequestMethod.GET)
    public String updateOrg(HttpServletRequest r) {
        Organization o = new Organization();
        o.setName(r.getParameter("name"));
        Location l = service.readLocation(Integer.parseInt(r.getParameter("hq")));
        o.setHq(l);
        o.setDescription(r.getParameter("description"));
        String[] superStrings = r.getParameterValues("superID");
if(superStrings !=null){
        for (String superIDString : superStrings) {
            int id = Integer.parseInt(superIDString);
            Super sh = service.readSuper(id);
            o.addToRoster(sh);
        }
}
        service.updateOrg(o);
        return "redirect:displayOrgsPage";
    }

    @RequestMapping(value = "/displayOrgFull", method = RequestMethod.GET)
    public String displayOrgFull(HttpServletRequest r, Model m) {
        int orgID = Integer.parseInt(r.getParameter("orgID"));
        Organization o = service.readOrg(orgID);

        m.addAttribute("org", o);

        return "displayOrgFull";
    }

    @RequestMapping(value = "/addSighting", method = RequestMethod.GET)
    public String addSighting(Model m) {
        List<Location> locs = service.getAllLocations();
        List<Super> supers = service.getAllSupers();

        m.addAttribute("supers", supers);
        m.addAttribute("locs", locs);
        return "addSighting";
    }

    @RequestMapping(value = "/createSighting", method = RequestMethod.POST)
    public String createSighting(HttpServletRequest r) {
        Sighting s = new Sighting();
        Date date = Date.valueOf(r.getParameter("date"));
        s.setDate(date);
        int locID = Integer.parseInt(r.getParameter("locID"));
        Location l = service.readLocation(locID);
        s.setLocation(l);
        String[] superIDs = r.getParameterValues("superID");
        if(superIDs!=null){
        for (String stringID : superIDs) {
            int id = Integer.parseInt(stringID);
            Super sh = service.readSuper(id);
            s.addSuper(sh);
        }
        }

        service.createSighting(s);
        return "redirect:displaySightingsPage";
    }

    @RequestMapping(value = "/deleteSighting", method = RequestMethod.GET)
    public String deleteSighting(HttpServletRequest r) {
        int id = Integer.parseInt(r.getParameter("sightingID"));
        Sighting s = service.readSighting(id);
        service.deleteSighting(s);

        return "redirect:displaySightingsPage";
    }

    @RequestMapping(value = "/editSighting", method = RequestMethod.GET)
    public String addSighting(Model m, HttpServletRequest r) {
        List<Location> locs = service.getAllLocations();
        List<Super> supers = service.getAllSupers();
        int id = Integer.parseInt(r.getParameter("sightingID"));
        Sighting s = service.readSighting(id);
        m.addAttribute("sighting",s);
        m.addAttribute("supers", supers);
        m.addAttribute("locs", locs);
        return "editSighting";
    }
    
    @RequestMapping(value="/updateSighting",method=RequestMethod.POST)
    public String updateSighting(HttpServletRequest r){
        Sighting s = new Sighting();
        Date date = Date.valueOf(r.getParameter("date"));
        s.setDate(date);
        int sightingID = Integer.parseInt(r.getParameter("sightID"));
        s.setID(sightingID);
        int locID = Integer.parseInt(r.getParameter("locID"));
        Location l = service.readLocation(locID);
        s.setLocation(l);
        String[] superIDs = r.getParameterValues("superID");
        if(superIDs!=null){
        for (String stringID : superIDs) {
            int id = Integer.parseInt(stringID);
            Super sh = service.readSuper(id);
            s.addSuper(sh);
        }
        }
        service.updateSighting(s);
        return "redirect:displaySightingsPage";
    }

    @RequestMapping(value={"/"},method=RequestMethod.GET)
    public String addTop5(Model m){
        
        List<Sighting> topFive = service.getTop5();
        m.addAttribute("topfive",topFive);        
        return "index";
    }
    
}

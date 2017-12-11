/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.hayden.supersightingsmvc.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Hayden
 */
public class Organization {
    protected int id;
    protected String name;
    protected String description;
    protected Location hq;
    protected int locationID;
    protected List<Super> roster = new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Location getHq() {
        return hq;
    }

    public void setHq(Location hq) {
        this.hq = hq;
        this.locationID=hq.getID();
    }

   
    public String getDescription() {
        return description;
    }

    public void setDescription(String Description) {
        this.description = Description;
    }

   public void clearRoser(){
       roster.clear();
   }

   public void addToRoster(Super s){
       if(roster==null){
            roster = new ArrayList<>();
        }
       roster.add(s);
   }
    public List<Super> getRoster() {
        return roster;
    }

    public void setRoster(List<Super> roster) {
        
        this.roster = roster;
    }

    public int getLocationID() {
        return locationID;
    }

    public void setLocationID(int LocationID) {
        this.locationID = LocationID;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash + this.id;
        hash = 89 * hash + Objects.hashCode(this.name);
        hash = 89 * hash + Objects.hashCode(this.description);
        hash = 89 * hash + Objects.hashCode(this.hq);
        hash = 89 * hash + this.locationID;
        hash = 89 * hash + Objects.hashCode(this.roster);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Organization other = (Organization) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.locationID != other.locationID) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.hq, other.hq)) {
            return false;
        }
        if (!Objects.equals(this.roster, other.roster)) {
            return false;
        }
        return true;
    }
    
   

  
      
}

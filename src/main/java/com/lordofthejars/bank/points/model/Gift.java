package com.lordofthejars.bank.points.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Gift {

    @XmlElement
    private String name;
    
    @XmlElement
    private String description;
    
    @XmlElement
    private int points;
    
    public Gift() {
        super();
    }
    
    public Gift(String name, String description, int points) {
        super();
        this.name = name;
        this.description = description;
        this.points = points;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }
    public int getPoints() {
        return points;
    }
    
}

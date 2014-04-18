package com.lordofthejars.bank.points.control;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;

import com.lordofthejars.bank.points.model.Gift;

@Stateless
public class GiftCatalogService {

    List<Gift> catalog = new ArrayList(){{
        add(new Gift("podometer", "digital screen, clock, belt", 2000));
        add(new Gift("usb charger", "two slots, LED, car", 500));
    }};
    
    public Gift gift(String name) {
        
        for (Gift gift : catalog) {
            if(gift.getName().equals(name)) {
                return gift;
            }
        }
        
        return null;
    }
}

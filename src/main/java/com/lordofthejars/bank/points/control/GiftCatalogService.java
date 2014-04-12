package com.lordofthejars.bank.points.control;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;

import com.lordofthejars.bank.points.model.Gift;

@Stateless
public class GiftCatalogService {

    public List<Gift> giftCatalog() {
        
        List<Gift> catalog = new ArrayList<>();
        catalog.add(new Gift("podometer", "digital screen, clock, belt", 2000));
        catalog.add(new Gift("usb charger", "two slots, LED, car", 500));
        
        return catalog;
    }
}

package com.lordofthejars.bank.points.boundary;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.lordofthejars.bank.points.control.GiftCatalogService;
import com.lordofthejars.bank.points.model.Gift;

@Stateless
public class CatalogServiceImpl implements CatalogService {

    @EJB
    GiftCatalogService giftCatalogService;

    @Override
    public List<Gift> catalog() {
        return this.giftCatalogService.giftCatalog();
    }
    
}

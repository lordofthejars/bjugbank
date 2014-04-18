package com.lordofthejars.bank.points.boundary;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.lordofthejars.bank.points.control.GiftCatalogService;
import com.lordofthejars.bank.points.model.Gift;

@Stateless
@Path("/points/")
public class CatalogService {

    @EJB
    GiftCatalogService giftCatalogService;

    @Produces({MediaType.APPLICATION_JSON})
    @GET
    @Path("{name}")
    public Gift gift(@PathParam("name") String name) {
        return this.giftCatalogService.gift(name);
    }
    
}

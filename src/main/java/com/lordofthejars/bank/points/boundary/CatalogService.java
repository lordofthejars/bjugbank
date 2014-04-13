package com.lordofthejars.bank.points.boundary;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.lordofthejars.bank.points.model.Gift;

@Path("/points/")
public interface CatalogService {

    @Produces({MediaType.APPLICATION_JSON})
    @GET
    public List<Gift> catalog();
    
}

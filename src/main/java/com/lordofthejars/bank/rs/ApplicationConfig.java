package com.lordofthejars.bank.rs;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import com.lordofthejars.bank.points.boundary.CatalogService;

@ApplicationPath("/rest")
public class ApplicationConfig extends Application {
    public Set<Class<?>> getClasses() {
        return new HashSet<Class<?>>(Arrays.asList(CatalogService.class));
    }
}

package com.ekart.hackfest.foodmania.controller;

import com.codahale.metrics.annotation.Timed;
import io.dropwizard.hibernate.UnitOfWork;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Created by rishabh.sood on 24/02/16.
 */
@Path("/foodmania")
public class DummyController {
    @GET
    @Timed
    @UnitOfWork(value = "master")
    @Path("/get")
    @Produces(MediaType.APPLICATION_JSON)
    public String getJob() {
        return "Hello";
    }
}

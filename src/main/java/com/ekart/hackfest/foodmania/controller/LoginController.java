package com.ekart.hackfest.foodmania.controller;



import com.codahale.metrics.annotation.Timed;
import com.ekart.hackfest.foodmania.model.*;
import com.ekart.hackfest.foodmania.services.CustomerService;
import com.ekart.hackfest.foodmania.services.LoginService;
import io.dropwizard.hibernate.UnitOfWork;
import org.apache.log4j.Logger;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by vishal.bhandari on 26/02/16.
 */

@Path("/foodmania/login")
public class LoginController {

    private LoginService loginService;

    public LoginController(LoginService loginService)
    {
        this.loginService = loginService;
    }

    @POST
    @Path("/addUser")
    @Timed
    @UnitOfWork(value = "master")
    @Produces(MediaType.APPLICATION_JSON)

    public LoginDummy addUser(LoginDummy login)
    {
        return loginService.addUserLogin(login);
    }

    @GET
    @Path("/getType/{userName}/{password}")
    @Timed
    @UnitOfWork(value = "master")
    @Produces(MediaType.APPLICATION_JSON)

    public String getType(@PathParam("userName") String userName,@PathParam("password") String password)
    {
        return loginService.getType(userName,password);
    }


}

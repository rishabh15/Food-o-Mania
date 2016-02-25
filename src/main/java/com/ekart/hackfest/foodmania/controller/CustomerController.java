package com.ekart.hackfest.foodmania.controller;

import com.codahale.metrics.annotation.Timed;
import com.ekart.hackfest.foodmania.model.CustomerOrderEntity;
import com.ekart.hackfest.foodmania.model.ItemForOrderEntity;
import com.ekart.hackfest.foodmania.model.MenuEntity;
import com.ekart.hackfest.foodmania.model.Status;
import com.ekart.hackfest.foodmania.services.CustomerService;
import io.dropwizard.hibernate.UnitOfWork;
import org.apache.log4j.Logger;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by vishal.bhandari on 25/02/16.
 */
@Path("/foodmania/customer")
public class CustomerController {

    private CustomerService customerService;
    private static Logger logger = Logger.getLogger(CustomerController.class);

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }



    @POST
    @Path("/updateOrder/{orderId}")
    @Timed
    @UnitOfWork(value = "master")
    @Produces(MediaType.APPLICATION_JSON)

    public CustomerOrderEntity updateOrder(@PathParam("orderId") String orderId,Status status)
    {
           return customerService.updateOrder(orderId,status);
    }

    @GET
    @Timed
    @UnitOfWork(value = "master")
    @Path("/getOrderItem/{orderId}")
    @Produces(MediaType.APPLICATION_JSON)
    public CustomerOrderEntity getItemList(@PathParam("orderId") String orderId)
    {
        return customerService.getItemList(orderId);
    }

    @POST
    @Path("/createOrder")
    @Timed
    @UnitOfWork(value = "master")
    @Produces(MediaType.APPLICATION_JSON)

    public CustomerOrderEntity createOrder(CustomerOrderEntity customerOrderEntity)
    {
         return customerService.createOrder(customerOrderEntity);
    }



}

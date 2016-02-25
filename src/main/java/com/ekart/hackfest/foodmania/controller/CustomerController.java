package com.ekart.hackfest.foodmania.controller;

import com.codahale.metrics.annotation.Timed;
import com.ekart.hackfest.foodmania.model.CustomerOrderEntity;
import com.ekart.hackfest.foodmania.model.MenuEntity;
import com.ekart.hackfest.foodmania.services.CustomerService;
import io.dropwizard.hibernate.UnitOfWork;
import org.apache.log4j.Logger;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
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


    @GET
    @Timed
    @UnitOfWork(value = "master")
    @Path("/getOrders/{merchantId}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<CustomerOrderEntity> getOrderListByMerchant(@PathParam("merchantId") String merchantId)
    {
        List<CustomerOrderEntity> customerOrderEntities = customerService.getOrderListByMerchant(merchantId);

        return customerOrderEntities;
    }

    /*@POST
    @Path("/updateOrder/{orderId}/{statusName}")
    @Timed
    @UnitOfWork(value = "master")
    @Produces(MediaType.APPLICATION_JSON)

    public CustomerOrderEntity updateOrder()
    {

    }*/ 



}

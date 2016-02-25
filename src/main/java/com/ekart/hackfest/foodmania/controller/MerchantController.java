package com.ekart.hackfest.foodmania.controller;

import com.codahale.metrics.annotation.Timed;
import com.ekart.hackfest.foodmania.model.CustomerOrderEntity;
import com.ekart.hackfest.foodmania.model.MenuEntity;
import com.ekart.hackfest.foodmania.model.MerchantInfoEntity;
import com.ekart.hackfest.foodmania.services.MerchantService;
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
@Path("/foodmania/merchant")
public class MerchantController {

    private MerchantService merchantService;
    private static Logger logger = Logger.getLogger(MerchantController.class);

    public MerchantController(MerchantService merchantService)
    {
        this.merchantService=merchantService;
    }


    @GET
    @Timed
    @UnitOfWork(value = "master")
    @Path("/getMenu")
    @Produces(MediaType.APPLICATION_JSON)
    public List<MenuEntity> getActiveMenu()
    {
        List<MenuEntity> menuEntityList = new ArrayList<MenuEntity>();
        try
        {
            menuEntityList=merchantService.getActiveMenu();
        }catch(Exception e)
        {
            logger.error(e.getMessage(),e);
        }finally {
            return menuEntityList;
        }
    }

    @GET
    @Timed
    @UnitOfWork(value = "master")
    @Path("/getOrders/{merchantId}")
    @Produces(MediaType.APPLICATION_JSON)
    public MerchantInfoEntity getOrderListByMerchant(@PathParam("merchantId") String merchantId)
    {
        MerchantInfoEntity merchantInfoEntity = merchantService.getOrderListByMerchant(merchantId);

        return merchantInfoEntity;
    }


}

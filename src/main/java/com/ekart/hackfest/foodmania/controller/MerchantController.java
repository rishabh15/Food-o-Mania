package com.ekart.hackfest.foodmania.controller;

import com.codahale.metrics.annotation.Timed;
import com.ekart.hackfest.foodmania.model.*;
import com.ekart.hackfest.foodmania.services.MerchantService;
import io.dropwizard.hibernate.UnitOfWork;
import org.apache.log4j.Logger;

import javax.ws.rs.*;
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
    public List<MerchantRefresh> getOrderListByMerchant(@PathParam("merchantId") String merchantId)
    {
        List<MerchantRefresh> merchantInfoEntity = merchantService.getOrderListByMerchant(merchantId);

        return merchantInfoEntity;
    }

    @GET
    @Timed
    @UnitOfWork(value = "master")
    @Path("/getMenu/dummy")
    @Produces(MediaType.APPLICATION_JSON)
    public MenuEntity getOrderListByMerchant()
    {
        MenuEntity menuEntity = new MenuEntity();

        return menuEntity;
    }

    @POST
    @Path("/createMenu/{merchantId}")
    @Timed
    @UnitOfWork(value = "master")
    @Produces(MediaType.APPLICATION_JSON)

    public MerchantInfoEntity createMenu(@PathParam("merchantId") String merchantId,MenuEntity menuEntity)
    {

        return merchantService.createMenu(menuEntity,merchantId);
       /* MenuEntity menuEntity1 = new MenuEntity();
        MerchantInfoEntity merchantInfoEntity1 = new MerchantInfoEntity();
        List<ItemEntity> itemEntities = new ArrayList<ItemEntity>();
        ItemEntity itemEntity = new ItemEntity();
        itemEntities.add(itemEntity);
        menuEntity1.setMerchantInfoEntity(merchantInfoEntity1);
        menuEntity1.setItemEntities(itemEntities);
        return menuEntity1;*/
    }

}

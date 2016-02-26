package com.ekart.hackfest.foodmania.services;

import com.ekart.hackfest.foodmania.model.*;
import com.ekart.hackfest.foodmania.repository.*;
import org.apache.log4j.Logger;

import java.awt.*;
import java.util.List;

/**
 * Created by vishal.bhandari on 25/02/16.
 */
public class MerchantService {

    private static Logger logger = Logger.getLogger(MerchantService.class);
    private MerchantDao merchantDao;
    private MenuDao menuDao;
    private ReferenceCountDao referenceCountDao;


    public MerchantService(MerchantDao merchantDao,MenuDao menuDao,ReferenceCountDao referenceCountDao)
    {
        this.merchantDao = merchantDao;
        this.menuDao = menuDao;
        this.referenceCountDao = referenceCountDao;
    }
    public List<MenuEntity> getActiveMenu()
    {
        return menuDao.getActiveMenu();
    }

    public List<MerchantRefresh> getOrderListByMerchant(String merchantId)
    {
        return merchantDao.getOrderListByMerchant(merchantId);
    }


    public MerchantInfoEntity createMenu(MenuEntity menuEntity,String merchantId) {

        ReferenceCountEntity referenceCount = referenceCountDao.getReferenceCount();
        long count = referenceCount.getCount();
        String menuId = "MENU" + count;

        menuEntity.setMenuid(menuId);

        for(ItemEntity itemEntity:menuEntity.getItemEntities())
        {
            count = referenceCount.getCount();
            String itemId = "ITEM" + count;
            count++;
            referenceCount.setCount(count);
            itemEntity.setItemid(itemId);
        }

        referenceCountDao.addReferenceCount(referenceCount);
        MerchantInfoEntity merchantInfoEntity = merchantDao.getMerchant(merchantId);
        List<MenuEntity> menuEntityList = merchantInfoEntity.getMenuEntities();
        menuEntityList.add(menuEntity);
        merchantInfoEntity.setMenuEntities(menuEntityList);
        return merchantDao.createMerchant(merchantInfoEntity);

    }
}

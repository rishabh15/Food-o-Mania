package com.ekart.hackfest.foodmania.services;

import com.ekart.hackfest.foodmania.model.CustomerOrderEntity;
import com.ekart.hackfest.foodmania.model.MenuEntity;
import com.ekart.hackfest.foodmania.model.MerchantInfoEntity;
import com.ekart.hackfest.foodmania.model.MerchantRefresh;
import com.ekart.hackfest.foodmania.repository.*;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * Created by vishal.bhandari on 25/02/16.
 */
public class MerchantService {

    private static Logger logger = Logger.getLogger(MerchantService.class);
    private MerchantDao merchantDao;
    private MenuDao menuDao;


    public MerchantService(MerchantDao merchantDao,MenuDao menuDao)
    {
        this.merchantDao = merchantDao;
        this.menuDao = menuDao;
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
        MerchantInfoEntity merchantInfoEntity = merchantDao.getMerchant(merchantId);
        List<MenuEntity> menuEntityList = merchantInfoEntity.getMenuEntities();
        menuEntityList.add(menuEntity);
        merchantInfoEntity.setMenuEntities(menuEntityList);
        return merchantDao.createMerchant(merchantInfoEntity);
    }
}

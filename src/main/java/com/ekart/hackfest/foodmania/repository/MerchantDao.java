package com.ekart.hackfest.foodmania.repository;

import com.ekart.hackfest.foodmania.model.CustomerOrderEntity;
import com.ekart.hackfest.foodmania.model.MenuEntity;
import com.ekart.hackfest.foodmania.model.MerchantInfoEntity;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import java.util.List;

/**
 * Created by vishal.bhandari on 25/02/16.
 */
public class MerchantDao extends AbstractDAO<MerchantInfoEntity> {

    public MerchantDao(SessionFactory sessionFactory) {

        super(sessionFactory);
    }


}

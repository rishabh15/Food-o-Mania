package com.ekart.hackfest.foodmania.repository;

import com.ekart.hackfest.foodmania.model.CustomerOrderEntity;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vishal.bhandari on 25/02/16.
 */
public class CustomerOrderDao extends AbstractDAO<CustomerOrderEntity> {

    public CustomerOrderDao(SessionFactory sessionFactory) {

        super(sessionFactory);
    }

    public List<CustomerOrderEntity> getOrderListByMerchant(String merchantId)
    {
        Criteria c = criteria();
        System.out.println("key is " + merchantId);
        c.add(Restrictions.eq("merchantInfoEntity", merchantId));
        List<CustomerOrderEntity> output = c.list();
        return output;
    }
}

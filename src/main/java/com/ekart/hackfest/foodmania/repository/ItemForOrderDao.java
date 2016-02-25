package com.ekart.hackfest.foodmania.repository;

import com.ekart.hackfest.foodmania.model.CustomerOrderEntity;
import com.ekart.hackfest.foodmania.model.ItemForOrderEntity;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;

/**
 * Created by vishal.bhandari on 25/02/16.
 */
public class ItemForOrderDao extends AbstractDAO<ItemForOrderEntity> {

    public ItemForOrderDao(SessionFactory sessionFactory) {

        super(sessionFactory);
    }
}

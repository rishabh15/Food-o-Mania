package com.ekart.hackfest.foodmania.repository;

import com.ekart.hackfest.foodmania.model.ItemEntity;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;

/**
 * Created by vishal.bhandari on 25/02/16.
 */
public class ItemDao extends AbstractDAO<ItemEntity> {

public ItemDao(SessionFactory sessionFactory) {

        super(sessionFactory);

    }

}

package com.ekart.hackfest.foodmania.repository;

import com.ekart.hackfest.foodmania.model.CustomerEntity;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;

/**
 * Created by vishal.bhandari on 25/02/16.
 */
public class CustomerDao extends AbstractDAO<CustomerEntity> {

    public CustomerDao(SessionFactory sessionFactory) {

        super(sessionFactory);
    }

    public void addCustomer(CustomerEntity customerEntity)
    {
        persist(customerEntity);
    }
}

package com.ekart.hackfest.foodmania.repository;

import com.ekart.hackfest.foodmania.model.CustomerOrderEntity;
import com.ekart.hackfest.foodmania.model.Status;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.Criteria;
import org.hibernate.Query;
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



    public CustomerOrderEntity updateOrder(String orderId,Status status)
    {
        Criteria c = criteria();

        c.add(Restrictions.eq("orderid", orderId));
        CustomerOrderEntity output = (CustomerOrderEntity)c.uniqueResult();
        output.setStatus(status.getStatus());
        return persist(output);
    }

    public CustomerOrderEntity getItemList(String orderId)
    {
        Criteria c = criteria();

        c.add(Restrictions.eq("orderid", orderId));
        CustomerOrderEntity output = (CustomerOrderEntity)c.uniqueResult();
        return output;
    }

    public CustomerOrderEntity createOrder(CustomerOrderEntity customerOrderEntity) {

        Query query = this.currentSession().createSQLQuery("select COUNT FROM ReferenceCount");

        return persist(customerOrderEntity);
    }
}

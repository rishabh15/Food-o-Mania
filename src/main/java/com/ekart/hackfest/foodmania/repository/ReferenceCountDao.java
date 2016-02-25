package com.ekart.hackfest.foodmania.repository;

import com.ekart.hackfest.foodmania.model.CustomerOrderEntity;
import com.ekart.hackfest.foodmania.model.ItemEntity;
import com.ekart.hackfest.foodmania.model.ReferenceCountEntity;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

/**
 * Created by vishal.bhandari on 25/02/16.
 */
public class ReferenceCountDao extends AbstractDAO<ReferenceCountEntity> {

    public ReferenceCountDao(SessionFactory sessionFactory) {

        super(sessionFactory);
    }
    public ReferenceCountEntity getReferenceCount(String name)
    {
        Criteria c = criteria();
        c.add(Restrictions.eq("ORDERID", name));
        ReferenceCountEntity output = (ReferenceCountEntity)c.uniqueResult();
        return output;
    }
    public ReferenceCountEntity addReferenceCount(ReferenceCountEntity referenceCountEntity)
    {
        return persist(referenceCountEntity);
    }
}

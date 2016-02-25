package com.ekart.hackfest.foodmania.repository;

import com.ekart.hackfest.foodmania.model.CustomerOrderEntity;
import com.ekart.hackfest.foodmania.model.MenuEntity;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import java.util.List;

/**
 * Created by vishal.bhandari on 25/02/16.
 */
public class MenuDao extends AbstractDAO<MenuEntity> {

    public MenuDao(SessionFactory sessionFactory) {

        super(sessionFactory);
    }

    public List<MenuEntity> getActiveMenu()
    {
        Criteria c = criteria();
        c.add(Restrictions.eq("active", "TRUE"));
        List<MenuEntity> output = c.list();
        return output;
    }
}

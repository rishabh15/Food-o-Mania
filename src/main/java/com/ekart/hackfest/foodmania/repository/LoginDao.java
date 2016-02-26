package com.ekart.hackfest.foodmania.repository;

import com.ekart.hackfest.foodmania.model.CustomerOrderEntity;
import com.ekart.hackfest.foodmania.model.LoginEntity;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

/**
 * Created by vishal.bhandari on 26/02/16.
 */
public class LoginDao extends AbstractDAO<LoginEntity> {

    public LoginDao(SessionFactory sessionFactory) {

        super(sessionFactory);
    }

    public void addUserLogin(LoginEntity loginEntity)
    {
        persist(loginEntity);
    }

    public LoginEntity getType(String username,String password)
    {
        Criteria c = criteria();
        c.add(Restrictions.eq("username", username));
        c.add(Restrictions.eq("password",password));
        LoginEntity output = (LoginEntity)c.uniqueResult();
        return output;
    }
}

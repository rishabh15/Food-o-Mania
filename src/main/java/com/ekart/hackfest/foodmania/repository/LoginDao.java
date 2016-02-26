package com.ekart.hackfest.foodmania.repository;

import com.ekart.hackfest.foodmania.model.LoginEntity;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;

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

}

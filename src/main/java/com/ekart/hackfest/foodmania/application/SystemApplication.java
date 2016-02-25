package com.ekart.hackfest.foodmania.application;

import com.ekart.hackfest.foodmania.controller.CustomerController;
import com.ekart.hackfest.foodmania.controller.DummyController;
import com.ekart.hackfest.foodmania.controller.MerchantController;
import com.ekart.hackfest.foodmania.model.*;
import com.ekart.hackfest.foodmania.repository.CustomerOrderDao;
import com.ekart.hackfest.foodmania.repository.MerchantDao;
import com.ekart.hackfest.foodmania.services.CustomerService;
import com.ekart.hackfest.foodmania.services.MerchantService;
import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

/**
 * Created by rishabh.sood on 23/02/16.
 */
public class SystemApplication extends Application<SystemConfiguration>{
    public static final HibernateBundle<SystemConfiguration> bundle = new HibernateBundle<SystemConfiguration>(CustomerEntity.class, CustomerOrderEntity.class, MerchantInfoEntity.class, MenuEntity.class, ItemEntity.class, ItemForOrderEntity.class) {
        @Override
        protected String name() {
            return "master";
        }

        @Override
        public DataSourceFactory getDataSourceFactory(SystemConfiguration systemConfiguration) {
            return systemConfiguration.getDataSourceFactory();
        }
    };

    public static void main(String[] args) throws Exception {
        new SystemApplication().run(args);
    }

    @Override
    public void initialize(Bootstrap<SystemConfiguration> bootstrap) {
        bootstrap.addBundle(bundle);
        bootstrap.addBundle(new AssetsBundle("/assets", "/", "index.html", "html"));
    }

    @Override
    public void run(SystemConfiguration configuration,
                    Environment environment) {
        final DummyController controller1 = new DummyController();
        environment.jersey().register(controller1);
        environment.jersey().register(new CustomerController(new CustomerService(new CustomerOrderDao(bundle.getSessionFactory()))));
        environment.jersey().register(new MerchantController(new MerchantService(new MerchantDao(bundle.getSessionFactory()))));

    }
    
}

package com.ekart.hackfest.foodmania.repository;

import com.ekart.hackfest.foodmania.model.CustomerOrderEntity;
import com.ekart.hackfest.foodmania.model.MenuEntity;
import com.ekart.hackfest.foodmania.model.MerchantInfoEntity;
import com.ekart.hackfest.foodmania.model.MerchantRefresh;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vishal.bhandari on 25/02/16.
 */
public class MerchantDao extends AbstractDAO<MerchantInfoEntity> {

    public MerchantDao(SessionFactory sessionFactory) {

        super(sessionFactory);
    }

    public List<MerchantRefresh> getOrderListByMerchant(String merchantId)
    {
        /*Criteria c = criteria();
        System.out.println("key is " + merchantId);
        c.add(Restrictions.eq("merchantid", merchantId));
        MerchantInfoEntity output =(MerchantInfoEntity) c.uniqueResult();
        return output;*/
        Query sqlQuery = this.currentSession().createSQLQuery("Select O.ORDERID as orderId, O.STATUS as status, O.PRICE as price, C.NAME as name, C.PHONE as phone \n" +
                "FROM CustomerOrder O INNER JOIN Customer C \n" +
                "WHERE C.CUSTOMERID = O.CUSTOMERID AND O.MERCHANTID=\""+merchantId+"\"").setResultTransformer(Transformers.aliasToBean(MerchantRefresh.class));
        List<MerchantRefresh> list = sqlQuery.list();
        return list;
    }

    public MerchantInfoEntity getMerchant(String merchantId)
    {
        Criteria c = criteria();
        System.out.println("key is " + merchantId);
        c.add(Restrictions.eq("merchantid", merchantId));
        MerchantInfoEntity output =(MerchantInfoEntity) c.uniqueResult();
        return output;
    }

    public MerchantInfoEntity createMerchant(MerchantInfoEntity merchantInfoEntity) {

        return persist(merchantInfoEntity);
    }
}

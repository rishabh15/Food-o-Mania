package com.ekart.hackfest.foodmania.services;

import com.ekart.hackfest.foodmania.model.CustomerEntity;
import com.ekart.hackfest.foodmania.model.LoginDummy;
import com.ekart.hackfest.foodmania.model.LoginEntity;
import com.ekart.hackfest.foodmania.model.MerchantInfoEntity;
import com.ekart.hackfest.foodmania.repository.CustomerDao;
import com.ekart.hackfest.foodmania.repository.LoginDao;
import com.ekart.hackfest.foodmania.repository.MerchantDao;

/**
 * Created by vishal.bhandari on 26/02/16.
 */
public class LoginService {

    private LoginDao loginDao;
    private MerchantDao merchantDao;
    private CustomerDao customerDao;

    public LoginService(MerchantDao merchantDao,CustomerDao customerDao,LoginDao loginDao)
    {
        this.loginDao=loginDao;
        this.merchantDao=merchantDao;
        this.customerDao=customerDao;
    }


    public  LoginDummy addUserLogin(LoginDummy login) {
        LoginEntity loginEntity = new LoginEntity();
        loginEntity.setUsername(login.getUsername());
        loginEntity.setPassword(login.getPassword());
        loginEntity.setType(login.getType());

        loginDao.addUserLogin(loginEntity);

        if(login.getType()=="CUSTOMER")
        {
            CustomerEntity customerEntity = new CustomerEntity();
            customerEntity.setCustomerid(login.getUsername());
            customerEntity.setEmail(login.getEmail());
            customerEntity.setName(login.getName());
            customerEntity.setPhone(login.getPhone());
            customerDao.addCustomer(customerEntity);
        }
        else
        {
            MerchantInfoEntity merchantInfoEntity = new MerchantInfoEntity();
            merchantInfoEntity.setMerchantid(login.getUsername());
            merchantInfoEntity.setPhone(login.getPhone());
            merchantInfoEntity.setName(login.getName());
            merchantInfoEntity.setEmail(login.getEmail());
            merchantInfoEntity.setMenuEntities(null);
            merchantInfoEntity.setCustomerOrderEntities(null);
            merchantDao.createMerchant(merchantInfoEntity);
        }


        return login;

    }
}

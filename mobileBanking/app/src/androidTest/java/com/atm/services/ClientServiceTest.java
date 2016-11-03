package com.atm.services;

import android.app.Instrumentation;
import android.content.Context;
import android.content.Intent;
import android.test.AndroidTestCase;

import com.atm.conf.util.App;
import com.atm.domain.client.impl.Business;
import com.atm.repository.client.BusinessRepository;
import com.atm.repository.client.impl.BusinessRepositoryImpl;
import com.atm.services.client.ClientService;
import com.atm.services.client.impl.ClientServiceImpl;

import junit.framework.Assert;

import java.io.Serializable;
import java.util.Set;



public class ClientServiceTest extends AndroidTestCase {

    public void testInsert() throws Exception {

        ClientServiceImpl clientService = ClientServiceImpl.getInstance();
        Context context = getContext();
        BusinessRepository businessRepository = new BusinessRepositoryImpl(context);

        Business business = new Business.Builder()
                .membershipType("1")
                .name("1")
                .idNumber("1")
                .emailAddress("1")
                .build();

        clientService.addClient(this.mContext, business);

        Thread.sleep(5000);
        // READ ALL
        Set<Business> businessSet1 = businessRepository.findAll();
        Assert.assertTrue(businessSet1.size() > 0);
    }

}

package com.atm.services;


import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.test.AndroidTestCase;

import com.atm.domain.client.impl.Business;
import com.atm.factories.client.BusinessFactory;
import com.atm.repository.client.BusinessRepository;
import com.atm.repository.client.impl.BusinessRepositoryImpl;
import com.atm.services.client.RetrieveClientInfo;
import com.atm.services.client.impl.RetrieveClientInfoImpl;

import junit.framework.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class RetrieveClientInfoTest extends AndroidTestCase{
    private RetrieveClientInfo retrieveClientInfo;
    private boolean isBound;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        Intent intent = new Intent(this.getContext(), RetrieveClientInfoImpl.class);
        this.getContext().bindService(intent, connection, Context.BIND_AUTO_CREATE);

    }

    public ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            RetrieveClientInfoImpl.RetrieveAccountInfoLocalBinder binder
                    = (RetrieveClientInfoImpl.RetrieveAccountInfoLocalBinder) service;
            retrieveClientInfo = binder.getService();

            isBound = true;

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

            isBound = false;

        }
    };

    public void testName() throws Exception {
        Business clientBusiness = BusinessFactory.createBusinessClient("9403300", "Ferin", "FerinTaylor@gmail.com");
        BusinessRepository businessRepository = new BusinessRepositoryImpl(this.getContext());
        businessRepository.save(clientBusiness);
        Set<Business> businessSet = retrieveClientInfo.getBusinessClient();

        //Assert.assertEquals(businessSet.size(), 1);

        List<Business> list = new ArrayList<Business>(businessSet);
        Assert.assertEquals(list.get(0).getName(), "Ferin");

    }
}

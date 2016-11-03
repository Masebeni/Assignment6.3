package com.atm.services;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.test.AndroidTestCase;

import com.atm.domain.account.impl.Credit;
import com.atm.domain.client.Client;
import com.atm.domain.client.impl.Business;
import com.atm.factories.client.BusinessFactory;
import com.atm.repository.account.CreditRepository;
import com.atm.repository.account.impl.CreditRepositoryImpl;
import com.atm.services.account.RetrieveAccountInfo;
import com.atm.services.account.impl.RetrieveAccountInfoImpl;

import junit.framework.Assert;

/**
 * Created by Axe on 2016-05-13.
 */
public class RetrieveAccountInfoTest extends AndroidTestCase{
    private RetrieveAccountInfo retrieveAccountInfo;
    private boolean isBound;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        Intent intent = new Intent(this.getContext(), RetrieveAccountInfoImpl.class);
        this.getContext().bindService(intent, connection, Context.BIND_AUTO_CREATE);

    }

    public ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            RetrieveAccountInfoImpl.RetrieveAccountInfoLocalBinder binder
                    = (RetrieveAccountInfoImpl.RetrieveAccountInfoLocalBinder) service;
            retrieveAccountInfo = binder.getService();

            isBound = true;

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

            isBound = false;

        }
    };

    public void testRetrieveAccountInfo() throws Exception {
        Long id;
        Business client = BusinessFactory.createBusinessClient("456", "ferin", "ferin@abc");

        CreditRepository creditRepository = new CreditRepositoryImpl(this.getContext());

        // CREATE
        Credit credit = new Credit.Builder()
                .accountNumber("1234")
                .balance(300)
                .limit(100)
                .pin("123")
                .client(client)
                .build();
        Credit insertedEntity = creditRepository.save(credit);
        id = insertedEntity.getId();
        Assert.assertNotNull(insertedEntity);
        Credit newEntity = new Credit.Builder()
                .copy(credit)
                .id(id)
                .build();

        Credit credit1 = retrieveAccountInfo.getAccountInfo(newEntity);
        Assert.assertNotNull(credit1);
    }
}

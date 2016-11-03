package com.atm.services;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.test.AndroidTestCase;

import com.atm.services.client.ValidateAccountNumberService;
import com.atm.services.client.impl.ValidateAccountNumberServiceImpl;

import junit.framework.Assert;

/**
 * Created by Axe on 2016-05-13.
 */
public class ValidateAccountNumberServiceTest extends AndroidTestCase {
    private ValidateAccountNumberService validateAccountNumberService;
    private boolean isBound;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        Intent intent = new Intent(this.getContext(), ValidateAccountNumberServiceImpl.class);
        this.getContext().bindService(intent, connection, Context.BIND_AUTO_CREATE);

    }

    public ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            ValidateAccountNumberServiceImpl.RetrieveAccountInfoLocalBinder binder
                    = (ValidateAccountNumberServiceImpl.RetrieveAccountInfoLocalBinder) service;
            validateAccountNumberService = binder.getService();

            isBound = true;

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

            isBound = false;

        }
    };

    public void testName() throws Exception {
        boolean isVaild = validateAccountNumberService.isValidAccountNumber("1267893492");
        Assert.assertTrue(isVaild);

    }
}

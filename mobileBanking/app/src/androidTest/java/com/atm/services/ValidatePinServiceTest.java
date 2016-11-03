package com.atm.services;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.test.AndroidTestCase;

import com.atm.services.account.ValidatePinService;
import com.atm.services.account.impl.ValidatePinServiceImpl;

import junit.framework.Assert;

/**
 * Created by Axe on 2016-05-13.
 */
public class ValidatePinServiceTest extends AndroidTestCase {

    private ValidatePinService validatePinService;
    private boolean isBound;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        Intent intent = new Intent(this.getContext(), ValidatePinServiceImpl.class);
        this.getContext().bindService(intent, connection, Context.BIND_AUTO_CREATE);

    }

    public ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            ValidatePinServiceImpl.RetrieveAccountInfoLocalBinder binder
                    = (ValidatePinServiceImpl.RetrieveAccountInfoLocalBinder) service;
            validatePinService = binder.getService();

            isBound = true;

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

            isBound = false;

        }
    };

    public void testPin() throws Exception {
        boolean isValid = validatePinService.isValidPin("12345");
        Assert.assertTrue(isValid);

    }
}

package com.atm.services;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.test.AndroidTestCase;

import com.atm.domain.transaction.Transaction;
import com.atm.domain.transaction.impl.Withdraw;
import com.atm.services.transaction.TransactionService;
import com.atm.services.transaction.impl.TransactionServiceImpl;

import junit.framework.Assert;

/**
 * Created by Axe on 2016-05-13.
 */
public class TransactionServiceTest extends AndroidTestCase {
    private TransactionService transactionService;
    private boolean isBound;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        Intent intent = new Intent(this.getContext(), TransactionServiceImpl.class);
        this.getContext().bindService(intent, connection, Context.BIND_AUTO_CREATE);

    }

    public ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            TransactionServiceImpl.RetrieveAccountInfoLocalBinder binder
                    = (TransactionServiceImpl.RetrieveAccountInfoLocalBinder) service;
            transactionService = binder.getService();

            isBound = true;

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

            isBound = false;

        }
    };

    public void testTrasactionchain() throws Exception {
        Transaction transaction = transactionService.getTransaction("Withdraw");

        Assert.assertEquals(transaction.getClass(), new Withdraw().getClass());

    }
}

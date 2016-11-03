package com.example.ferin.atm.services.transaction.impl;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.example.ferin.atm.domain.transaction.Transaction;
import com.example.ferin.atm.factories.tranaction.TransactionFactory;
import com.example.ferin.atm.services.transaction.TransactionService;

public class TransactionServiceImpl extends Service implements TransactionService{
    private IBinder localBinder = new RetrieveAccountInfoLocalBinder();
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return localBinder;
    }
    public class RetrieveAccountInfoLocalBinder extends Binder {
        public TransactionServiceImpl getService()
        {
            return  TransactionServiceImpl.this;
        }
    }

    public TransactionServiceImpl() {
    }

    @Override
    public Transaction getTransaction(String transaction) {
        return TransactionFactory.getTransaction(transaction);
    }
}

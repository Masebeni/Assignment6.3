package com.example.ferin.atm.services.client.impl;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.example.ferin.atm.services.client.ValidateAccountNumberService;

public class ValidateAccountNumberServiceImpl extends Service implements ValidateAccountNumberService{
    private IBinder localBinder = new RetrieveAccountInfoLocalBinder();
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return localBinder;
    }


    public class RetrieveAccountInfoLocalBinder extends Binder {
        public ValidateAccountNumberServiceImpl getService()
        {
            return  ValidateAccountNumberServiceImpl.this;
        }
    }


    public ValidateAccountNumberServiceImpl() {
    }

    @Override
    public boolean isValidAccountNumber(String accountNumber) {
       if(accountNumber.length() == 10)
           return true;
        else
           return false;
    }
}

package com.example.ferin.atm.services.account.impl;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.example.ferin.atm.domain.client.impl.Standard;
import com.example.ferin.atm.services.account.ValidatePinService;

public class ValidatePinServiceImpl extends Service implements ValidatePinService{
    private IBinder localBinder = new RetrieveAccountInfoLocalBinder();
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return localBinder;
    }

    public class RetrieveAccountInfoLocalBinder extends Binder {
        public ValidatePinServiceImpl getService()
        {
            return  ValidatePinServiceImpl.this;
        }
    }

    public ValidatePinServiceImpl() {
    }

    @Override
    public boolean isValidPin(String pin) {

        if(pin.length() > 4)
            return true;
        else
            return  false;
    }
}

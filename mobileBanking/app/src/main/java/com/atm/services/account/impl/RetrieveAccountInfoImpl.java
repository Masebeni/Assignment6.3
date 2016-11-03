package com.example.ferin.atm.services.account.impl;

import android.app.Service;
import android.content.Intent;
import android.net.Credentials;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.example.ferin.atm.domain.account.Account;
import com.example.ferin.atm.domain.account.impl.Credit;
import com.example.ferin.atm.repository.account.CreditRepository;
import com.example.ferin.atm.repository.account.impl.CreditRepositoryImpl;
import com.example.ferin.atm.services.account.RetrieveAccountInfo;

import java.util.Set;

public class RetrieveAccountInfoImpl extends Service implements RetrieveAccountInfo{

    private IBinder localBinder = new RetrieveAccountInfoLocalBinder();
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return localBinder;
    }

    public RetrieveAccountInfoImpl() {
    }

    @Override
    public Credit getAccountInfo(Credit account) {
        CreditRepository creditRepository= new CreditRepositoryImpl(getBaseContext());

        if(true)
        {
            Credit credit = creditRepository.findById(account.getId());
            return credit;
        }
        else
            return null;
    }

    public class RetrieveAccountInfoLocalBinder extends Binder {
        public RetrieveAccountInfoImpl getService()
        {
            return  RetrieveAccountInfoImpl.this;
        }
    }

}

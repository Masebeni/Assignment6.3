package com.example.ferin.atm.services.client.impl;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.example.ferin.atm.conf.util.App;
import com.example.ferin.atm.domain.client.impl.Business;
import com.example.ferin.atm.domain.client.impl.Premium;
import com.example.ferin.atm.domain.client.impl.Standard;
import com.example.ferin.atm.repository.client.BusinessRepository;
import com.example.ferin.atm.repository.client.PremiumRepository;
import com.example.ferin.atm.repository.client.StandardRepository;
import com.example.ferin.atm.repository.client.impl.BusinessRepositoryImpl;
import com.example.ferin.atm.repository.client.impl.PremiumRepositoryImpl;
import com.example.ferin.atm.repository.client.impl.StandardRepositoryImpl;
import com.example.ferin.atm.services.client.RetrieveClientInfo;

import java.util.Set;

public class RetrieveClientInfoImpl extends Service implements RetrieveClientInfo {

    private IBinder localBinder = new MyLocalBinder();

    @Override
    public IBinder onBind(Intent intent) {
        return localBinder;
    }


    public RetrieveClientInfoImpl() {
    }

    @Override
    public Set<Premium> getPremiumClient() {

        PremiumRepository premiumRepository = new PremiumRepositoryImpl(getBaseContext());

        return premiumRepository.findAll();
    }

    @Override
    public Set<Business> getBusinessClient() {
        BusinessRepository businessRepository = new BusinessRepositoryImpl(getBaseContext());

        return businessRepository.findAll();
    }

    @Override
    public Set<Standard> getStandardClient() {
        StandardRepository standardRepository = new StandardRepositoryImpl(getBaseContext());

        return standardRepository.findAll();
    }

    public class MyLocalBinder extends Binder {
        public RetrieveClientInfoImpl getService() {
            return RetrieveClientInfoImpl.this;
        }
    }
}

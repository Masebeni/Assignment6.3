package com.example.ferin.atm.services.account.impl;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.example.ferin.atm.domain.account.impl.Credit;
import com.example.ferin.atm.domain.account.impl.Savings;
import com.example.ferin.atm.domain.account.impl.Student;
import com.example.ferin.atm.repository.account.CreditRepository;
import com.example.ferin.atm.repository.account.SavingsRepository;
import com.example.ferin.atm.repository.account.StudentRepository;
import com.example.ferin.atm.repository.account.impl.CreditRepositoryImpl;
import com.example.ferin.atm.repository.account.impl.SavingsRepositoryImpl;
import com.example.ferin.atm.repository.account.impl.StudentRepositoryImpl;
import com.example.ferin.atm.services.account.GetAllAccountInfo;

import java.util.Set;

public class GetAllAccountInfoImpl extends Service implements GetAllAccountInfo {
    private IBinder localBinder = new RetrieveAccountInfoLocalBinder();
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return localBinder;
    }

    public GetAllAccountInfoImpl() {
    }

    @Override
    public Set<Credit> getAllCredit() {
        CreditRepository creditRepository = new CreditRepositoryImpl(getBaseContext());

        return  creditRepository.findAll();
    }

    @Override
    public Set<Savings> getAllSavings() {
        SavingsRepository savingsRepository = new SavingsRepositoryImpl(getBaseContext());
        return  savingsRepository.findAll();
    }

    @Override
    public Set<Student> getAllStudent() {
        StudentRepository studentRepository = new StudentRepositoryImpl(getBaseContext());

        return  studentRepository.findAll();
    }

    public class RetrieveAccountInfoLocalBinder extends Binder {
        public GetAllAccountInfoImpl getService()
        {
            return  GetAllAccountInfoImpl.this;
        }
    }
}

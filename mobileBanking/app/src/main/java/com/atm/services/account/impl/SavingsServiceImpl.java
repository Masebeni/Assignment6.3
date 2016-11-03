package com.example.ferin.atm.services.account.impl;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;

import com.example.ferin.atm.domain.account.impl.Credit;
import com.example.ferin.atm.domain.account.impl.Savings;
import com.example.ferin.atm.repository.account.CreditRepository;
import com.example.ferin.atm.repository.account.impl.CreditRepositoryImpl;
import com.example.ferin.atm.services.account.SavingsService;

public class SavingsServiceImpl extends IntentService implements SavingsService{

    private static final String ACTION_ADD = "com.example.ferin.atm.services.account.impl.action.ADD";
    private static final String ACTION_BAZ = "com.example.ferin.atm.services.account.impl.action.BAZ";

    // TODO: Rename parameters
    private static final String EXTRA_ADD = "com.example.ferin.atm.services.account.impl.extra.ADD";
    private static final String EXTRA_PARAM2 = "com.example.ferin.atm.services.account.impl.extra.PARAM2";

    public SavingsServiceImpl() {
        super("SavingsServiceImpl");
    }


    private static SavingsServiceImpl service = null;

    public static SavingsServiceImpl getInstance() {
        if (service == null)
            service = new SavingsServiceImpl();
        return service;
    }

    private void saveAccount(Credit credit) {
        CreditRepository personRepository= new CreditRepositoryImpl(getBaseContext());
        //POST and Save Local
        personRepository.save(credit);
    }

    @Override
    public void addAccount(Context context, Credit credit) {
        Intent intent = new Intent(context, SavingsServiceImpl.class);
        intent.setAction(ACTION_ADD);
        intent.putExtra(EXTRA_ADD, credit);
        context.startService(intent);
    }

    @Override
    public void updateAcount(Context context, Savings savings) {

    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_ADD.equals(action)) {
                final Credit param1 = (Credit)intent.getSerializableExtra(EXTRA_ADD);
                saveAccount(param1);
            }
        }
    }


}

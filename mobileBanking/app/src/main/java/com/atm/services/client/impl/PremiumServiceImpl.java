package com.example.ferin.atm.services.client.impl;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;

import com.example.ferin.atm.domain.client.impl.Premium;
import com.example.ferin.atm.repository.client.PremiumRepository;
import com.example.ferin.atm.repository.client.impl.PremiumRepositoryImpl;

public class PremiumServiceImpl extends IntentService {

    private static final String ACTION_ADD = "com.example.ferin.atm.services.client.impl.action.ADD";
    private static final String ACTION_DELETE = "com.example.ferin.atm.services.client.impl.action.DELETE";

    // TODO: Rename parameters
    private static final String EXTRA_ADD = "com.example.ferin.atm.services.client.impl.extra.ADD";
    private static final String EXTRA_DELETE = "com.example.ferin.atm.services.client.impl.extra.DELETE";

    public PremiumServiceImpl() {
        super("PremiumServiceImpl");
    }


    private static PremiumServiceImpl service = null;

    public static PremiumServiceImpl getInstance() {
        if (service == null)
            service = new PremiumServiceImpl();
        return service;
    }

    public static void startActionInsert(Context context, Premium premium) {
        Intent intent = new Intent(context, PremiumServiceImpl.class);
        intent.setAction(ACTION_ADD);
        intent.putExtra(EXTRA_ADD, premium);
        context.startService(intent);
    }


    public static void startActionDelete(Context context, Premium premium) {
        Intent intent = new Intent(context, PremiumServiceImpl.class);
        intent.setAction(ACTION_DELETE);
        intent.putExtra(EXTRA_DELETE, premium);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_ADD.equals(action)) {
                final Premium premium = (Premium)intent.getSerializableExtra(EXTRA_ADD);
                insert(premium);
            } else if (ACTION_DELETE.equals(action)) {
                final Premium premium = (Premium)intent.getSerializableExtra(EXTRA_DELETE);
                delete(premium);
            }
        }
    }

    private void insert(Premium premium) {
        PremiumRepository premiumRepository = new PremiumRepositoryImpl(getBaseContext());
        premiumRepository.save(premium);
    }


    private void delete(Premium premium) {
        PremiumRepository premiumRepository = new PremiumRepositoryImpl(getBaseContext());
        premiumRepository.delete(premium);
    }
}

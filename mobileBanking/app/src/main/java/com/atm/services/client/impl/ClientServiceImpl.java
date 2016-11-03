package com.example.ferin.atm.services.client.impl;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;

import com.example.ferin.atm.conf.util.App;
import com.example.ferin.atm.domain.client.Client;
import com.example.ferin.atm.domain.client.impl.Business;
import com.example.ferin.atm.repository.client.BusinessRepository;
import com.example.ferin.atm.repository.client.impl.BusinessRepositoryImpl;
import com.example.ferin.atm.services.client.ClientService;

import java.util.List;
import java.util.Set;


public class ClientServiceImpl extends IntentService implements ClientService{

    BusinessRepository businessRepository = new BusinessRepositoryImpl(App.getAppContext());
    private static final String ACTION_ADD = "com.example.ferin.atm.services.client.impl.action.ADD";
    private static final String ACTION_UPDATE = "com.example.ferin.atm.services.client.impl.action.UPDATE";

    // TODO: Rename parameters
    private static final String EXTRA_ADD = "com.example.ferin.atm.services.client.impl.extra.ADD";
    private static final String EXTRA_UPDATE = "com.example.ferin.atm.services.client.impl.extra.UPDATE";


    private static ClientServiceImpl service = null;

    public static ClientServiceImpl getInstance() {
        if (service == null)
            service = new ClientServiceImpl();
        return service;
    }

    public ClientServiceImpl() {
        super("ClientServiceImpl");
    }



    @Override
    protected void onHandleIntent(Intent intent) {
        try {
            if (intent != null) {
                final String action = intent.getAction();
                if (ACTION_ADD.equals(action)) {
                    final Business param1 = (Business)intent.getSerializableExtra(EXTRA_ADD);
                    saveClient(param1);
                }
                else if(ACTION_UPDATE.equals(action)) {
                    final Business param1 = (Business)intent.getSerializableExtra(EXTRA_ADD);
                    clientUpdate(param1);
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void addClient(Context context, Business client) {
        Intent intent = new Intent(context, ClientServiceImpl.class);
        intent.setAction(ACTION_ADD);
        intent.putExtra(EXTRA_ADD, client);
        context.startService(intent);
    }

    @Override
    public void updateClient(Context context, Business client) {

        Intent intent = new Intent(context, ClientServiceImpl.class);
        intent.setAction(ACTION_UPDATE);
        intent.putExtra(EXTRA_UPDATE, client);
        context.startService(intent);

    }

    @Override
    public void deleteClient(Context context, Business client) {

    }

    private void clientUpdate(Business client)
    {
        BusinessRepository personRepository= new BusinessRepositoryImpl(getBaseContext());
        personRepository.update(client);
    }


    private void saveClient(Business client) {
        BusinessRepository personRepository= new BusinessRepositoryImpl(getBaseContext());
        //POST and Save Local
        personRepository.save(client);
    }


}

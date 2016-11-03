package com.example.ferin.atm.services.client;

import android.content.Context;

import com.example.ferin.atm.domain.client.Client;
import com.example.ferin.atm.domain.client.impl.Business;

/**
 * Created by Ferin on 2016-05-10.
 */
public interface ClientService {
    void addClient(Context context, Business client);
    void updateClient(Context context, Business client);
    void deleteClient(Context context, Business client);
}

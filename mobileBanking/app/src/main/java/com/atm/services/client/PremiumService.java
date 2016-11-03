package com.example.ferin.atm.services.client;

import android.content.Context;

import com.example.ferin.atm.domain.client.impl.Premium;

import java.util.Set;

/**
 * Created by Ferin on 2016-05-13.
 */
public interface PremiumService {
    void addClient(Context context, Premium client);
    void updateClient(Context context, Premium client);
    void deleteClient(Context context, Premium client);
    Set<Premium>getClientSet(Context context);
}

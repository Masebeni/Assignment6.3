package com.example.ferin.atm.factories.client;

import com.example.ferin.atm.domain.client.impl.Business;

/**
 * Created by Ferin on 2016-05-31.
 */
public abstract class BusinessFactory {

    public static Business createBusinessClient(String idNumber, String name, String email)
    {
        return new Business.Builder()
                .idNumber(idNumber)
                .name(name)
                .emailAddress(email)
                .build();
    }

}

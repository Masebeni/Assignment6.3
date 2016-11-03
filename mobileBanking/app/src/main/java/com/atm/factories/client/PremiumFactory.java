package com.example.ferin.atm.factories.client;

import com.example.ferin.atm.domain.client.impl.Premium;

/**
 * Created by Ferin on 2016-05-31.
 */
public abstract class PremiumFactory {

    public static Premium createBusinessClient(String idNumber, String name, String email)
    {
        return new Premium.Builder()
                .idNumber(idNumber)
                .name(name)
                .emailAddress(email)
                .build();
    }

}

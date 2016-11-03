package com.example.ferin.atm.factories.account;

import com.example.ferin.atm.domain.account.impl.Credit;
import com.example.ferin.atm.domain.client.Client;

/**
 * Created by Ferin on 2016-05-31.
 */
public abstract class CreditFactory {

    public static Credit createCredit(String accountNumber, double balance, double limit, String pin, Client client)
    {
        return new Credit.Builder()
                .balance(balance)
                .accountNumber(accountNumber)
                .limit(limit)
                .pin(pin)
                .client(client)
                .build();
    }
}

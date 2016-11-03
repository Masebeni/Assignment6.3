package com.example.ferin.atm.domain.account;

/**
 * Created by Ferin on 2016-05-08.
 */
public interface Account<E>{

    E debit(double amount);

    E credit(double amount);

    E display();

}

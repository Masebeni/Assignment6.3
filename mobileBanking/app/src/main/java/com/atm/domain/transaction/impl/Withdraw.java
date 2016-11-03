package com.example.ferin.atm.domain.transaction.impl;


import com.example.ferin.atm.domain.transaction.Transaction;

public class Withdraw extends Transaction {

    public Withdraw(){}

    @Override
    public Transaction handleRequest(String request) {
        if(request.equalsIgnoreCase("withdraw")) {
             return new Withdraw();//withdraw();
        }
        else{
            if (nextTransaction != null) {
                return nextTransaction.handleRequest(request);
            }
        }
        return null;
    }

    public String withdraw()
    {
        return "withdraw";
    }
}

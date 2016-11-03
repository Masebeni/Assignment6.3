package com.example.ferin.atm.domain.transaction.impl;


import com.example.ferin.atm.domain.transaction.Transaction;

public class BalanceEnquiry extends Transaction {

    @Override
    public Transaction handleRequest(String request) {
        if(request.equalsIgnoreCase("balanceEnquiry")) {
            return new BalanceEnquiry();
                    //displayBalance();
        }
        else{
            if (nextTransaction != null) {
                return nextTransaction.handleRequest(request);
            }
        }

        return null;
    }

    public String displayBalance()
    {
        return "balanceEnquiry";
    }

}

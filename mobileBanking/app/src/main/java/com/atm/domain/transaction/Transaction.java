package com.atm.domain.transaction;


/* Chain of responsibility implementation */

public abstract class Transaction {

    public Transaction nextTransaction;

    public void setNextTransaction(Transaction nextTransaction) {
        this.nextTransaction = nextTransaction;
    }
    public abstract Transaction handleRequest(String request);
}

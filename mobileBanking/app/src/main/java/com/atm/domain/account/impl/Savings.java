package com.atm.domain.account.impl;


import com.atm.domain.account.Account;
import com.atm.domain.client.Client;
import com.atm.domain.client.impl.Business;

import java.io.Serializable;

public class Savings  implements Account<Savings>, Serializable {

    private String accountNumber;
    private double balance;
    private double limit;
    private String pin;
    private Client client;
    private Long id;

    private Savings(Builder builder)
    {
        this.accountNumber = builder.accountNumber;
        this.balance = builder.balance;
        this.limit = builder.limit;
        this.client = builder.client;
        this.pin = builder.pin;
        this.id = builder.id;
    }

    public Long getId() {
        return id;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public double getLimit() {
        return limit;
    }

    public String getPin() {
        return pin;
    }

    public Client getClient() {
        return client;
    }

    @Override
    public Savings debit(double amount) {
        this.balance = this.balance - amount;
        // call update db method, use bound service
        return null;
    }

    @Override
    public Savings credit(double amount) {
        this.balance = this.balance + amount;
        return null;
    }

    @Override
    public Savings display() {
        // call select by id db method, use bound service
        return  null;
    }

    public static class Builder
    {
        private String accountNumber;
        private double balance;
        private double limit;
        private String pin;
        private Client client;
        private Long id;

        public Builder id(Long id)
        {
            this.id = id;
            return this;
        }

        public Builder pin(String value)
        {
            this.pin = value;
            return this;
        }

        public Builder client(Client value)
        {
            this.client = value;
            return this;
        }

        public Builder accountNumber(String accountNumber)
        {
            this.accountNumber = accountNumber;
            return this;
        }

        public Builder balance(double balance)
        {
            this.balance = balance;
            return this;
        }

        public Builder limit(double limit)
        {
            this.limit = limit;
            return this;
        }

        public Builder copy(Savings account)
        {
            this.accountNumber = account.accountNumber;
            this.balance = account.balance;
            this.limit = account.limit;
            this.client = account.client;
            this.pin = account.pin;
            this.id = account.id;

            return this;
        }

        public Savings build() {
            return new Savings(this);
        }
    }

}

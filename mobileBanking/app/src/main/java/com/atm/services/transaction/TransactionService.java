package com.example.ferin.atm.services.transaction;

import com.example.ferin.atm.domain.transaction.Transaction;

/**
 * Created by Ferin on 2016-05-13.
 */
public interface TransactionService {
    Transaction getTransaction(String transaction);
}

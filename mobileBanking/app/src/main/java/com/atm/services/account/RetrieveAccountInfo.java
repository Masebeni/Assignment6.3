package com.example.ferin.atm.services.account;

import com.example.ferin.atm.domain.account.Account;
import com.example.ferin.atm.domain.account.impl.Credit;

import java.util.Set;

/**
 * Created by Ferin on 2016-05-13.
 */
public interface RetrieveAccountInfo{
    Credit getAccountInfo(Credit account);
}

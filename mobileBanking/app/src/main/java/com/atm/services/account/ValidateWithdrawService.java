package com.example.ferin.atm.services.account;

import com.example.ferin.atm.domain.account.impl.Student;

/**
 * Created by Ferin on 2016-05-13.
 */
public interface ValidateWithdrawService {

    boolean isValidWithdraw(Student account, double withdrawRequest);
}

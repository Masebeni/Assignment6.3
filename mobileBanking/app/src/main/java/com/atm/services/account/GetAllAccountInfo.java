package com.example.ferin.atm.services.account;

import com.example.ferin.atm.domain.account.impl.Credit;
import com.example.ferin.atm.domain.account.impl.Savings;
import com.example.ferin.atm.domain.account.impl.Student;

import java.util.Set;

/**
 * Created by Ferin on 2016-05-31.
 */
public interface GetAllAccountInfo {

    Set<Credit> getAllCredit();
    Set<Savings> getAllSavings();
    Set<Student> getAllStudent();

}

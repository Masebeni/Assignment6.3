package com.example.ferin.atm.services.client;

import android.content.Context;

import com.example.ferin.atm.domain.account.impl.Student;

/**
 * Created by Ferin on 2016-05-13.
 */
public interface StudentService {
    void addClient(Context context, Student client);
    void deleteClient(Context context, Student client);
}

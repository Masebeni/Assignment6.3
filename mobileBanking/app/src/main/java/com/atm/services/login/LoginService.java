package com.example.ferin.atm.services.login;

import com.example.ferin.atm.domain.login.Login;

/**
 * Created by Ferin on 2016-05-13.
 */
public interface LoginService {
    boolean isValiduser(Login login);
}

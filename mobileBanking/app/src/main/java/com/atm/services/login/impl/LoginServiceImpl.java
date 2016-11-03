package com.example.ferin.atm.services.login.impl;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.example.ferin.atm.domain.login.Login;
import com.example.ferin.atm.repository.login.LoginRepository;
import com.example.ferin.atm.repository.login.impl.LoginRepositoryImpl;
import com.example.ferin.atm.services.login.LoginService;

public class LoginServiceImpl extends Service implements LoginService {

    private IBinder localBinder = new RetrieveAccountInfoLocalBinder();
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return localBinder;
    }

    @Override
    public boolean isValiduser(Login login) {
        LoginRepository loginRepository = new LoginRepositoryImpl(getBaseContext());
        Login login1 = null;
        if(login1 == null)
            return false;
        else
            return true;
    }


    public class RetrieveAccountInfoLocalBinder extends Binder {
        public LoginServiceImpl getService()
        {
            return  LoginServiceImpl.this;
        }
    }
}

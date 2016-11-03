package com.example.ferin.atm.services.account;

import com.example.ferin.atm.domain.client.impl.Standard;

/**
 * Created by Ferin on 2016-05-13.
 */
public interface ValidatePinService {
    boolean isValidPin(String pin);
}

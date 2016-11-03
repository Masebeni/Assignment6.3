package com.example.ferin.atm.services.client;

import com.example.ferin.atm.domain.client.impl.Business;
import com.example.ferin.atm.domain.client.impl.Premium;
import com.example.ferin.atm.domain.client.impl.Standard;

import java.util.Set;

/**
 * Created by Ferin on 2016-05-31.
 */
public interface RetrieveClientInfo {
    Set<Premium> getPremiumClient();
    Set<Business> getBusinessClient();
    Set<Standard> getStandardClient();

}

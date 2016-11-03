package com..atm.services;

import android.content.Context;
import android.test.AndroidTestCase;

import com.atm.domain.client.impl.Business;
import com.atm.factories.client.BusinessFactory;
import com.atm.repository.account.SavingsRepository;
import com.atm.repository.account.impl.SavingsRepositoryImpl;


public class SavingsServiceTest extends AndroidTestCase {

    public void testInsertDelete() throws Exception {

        Business client = BusinessFactory.createBusinessClient("456", "Axe", "Axe@abc");
       // SavingsServiceImpl savingsService = SavingsServiceImpl.getInstance();
        Context context = getContext();

        SavingsRepository savingsRepository = new SavingsRepositoryImpl(context);

      /*  Savings savings = new Savings.Builder()
                .accountNumber("123")
                .balance(new Double(900))
                .limit(new Double(500))
                .pin("123")
                .client(client)
                .build();

        savingsService.startActionInsert(context, savings);

        Thread.sleep(5000);
        // READ ALL
        Set<Savings> savingsSet = savingsRepository.findAll();
        Assert.assertTrue(savingsSet.size() > 0);

        List<Savings> savingsList = new ArrayList<>(savingsSet);

        savingsService.startActionDelete(context, savingsList.get(0));

        Thread.sleep(5000);
        // READ ALL
        Set<Savings> savingsSet1 = savingsRepository.findAll();
        Assert.assertTrue(savingsSet1.size() == 0);*/
    }

}

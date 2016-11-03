package com.atm.account;

import android.test.AndroidTestCase;

import com.atm.domain.account.impl.Savings;
import com.atm.domain.client.Client;
import com.atm.domain.client.impl.Business;
import com.atm.factories.client.BusinessFactory;
import com.atm.repository.account.SavingsRepository;
import com.atm.repository.account.impl.SavingsRepositoryImpl;

import junit.framework.Assert;

import java.util.Set;

/**
 * Created by Axe on 2016-05-09.
 */
public class SavingsRepositoryTest extends AndroidTestCase {

    private Long id;

    public void testCreateReadUpdateDelete() throws Exception {
        Business client = BusinessFactory.createBusinessClient("456", "ferin", "ferin@abc");
        SavingsRepository creditRepository = new SavingsRepositoryImpl(this.getContext());

        // CREATE
        Savings credit = new Savings.Builder()
                .accountNumber("1234")
                .balance(300)
                .limit(100)
                .pin("123")
                .client(client)
                .build();
        Savings insertedEntity = creditRepository.save(credit);
        id = insertedEntity.getId();
        Assert.assertNotNull(insertedEntity);

        // READ ALL
        Set<Savings> businessSet = creditRepository.findAll();
        Assert.assertTrue(businessSet.size() > 0);

        // READ ENTITY
        Savings entity = creditRepository.findById(id);
        Assert.assertNotNull(entity);

        // UPDATE ENTITY
        Savings updateEntity = new Savings.Builder()
                .copy(entity)
                .balance(700)
                .client(client)
                .build();
        creditRepository.update(updateEntity);
        Savings newEntity = creditRepository.findById(id);
        Assert.assertEquals(Double.parseDouble("700"), newEntity.getBalance());

        // DELETE ENTITY
        creditRepository.delete(updateEntity);
        Savings deletedEntity = creditRepository.findById(id);
        Assert.assertNull(deletedEntity);


        // DELETE ALL
        creditRepository.deleteAll();
        Set<Savings> deletedUsers = creditRepository.findAll();
        Assert.assertTrue(deletedUsers.size() == 0);

    }

}

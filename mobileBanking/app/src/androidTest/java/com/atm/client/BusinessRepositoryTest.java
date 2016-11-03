package com.atm.client;

import android.content.Context;
import android.content.Intent;
import android.test.AndroidTestCase;

import com.atm.conf.util.App;
import com.atm.domain.client.Client;
import com.atm.domain.client.impl.Business;
import com.atm.repository.client.BusinessRepository;
import com.atm.repository.client.impl.BusinessRepositoryImpl;
import com.atm.services.client.ClientService;
import com.atm.services.client.impl.ClientServiceImpl;

import junit.framework.Assert;

import java.util.Set;

/**
 * Created by Axe on 2016-05-04.
 */
public class BusinessRepositoryTest extends AndroidTestCase {

    private Long id;

    public void testCreateReadUpdateDelete() throws Exception {
        Context context = getContext();
        BusinessRepository businessRepository = new BusinessRepositoryImpl(context);

        // CREATE
        Business client = new Business.Builder()
                .name("Axen")
                .emailAddress("123@gmail")
                .idNumber("456")
                .membershipType("client")
                .build();

        Business insertedEntity = businessRepository.save(client);
        id = insertedEntity.getId();
        Assert.assertNotNull(insertedEntity);

        // READ ALL
        Set<Business> businessSet = businessRepository.findAll();
        Assert.assertTrue(businessSet.size() > 0);

        // READ ENTITY
        Business entity = businessRepository.findById(id);
        Assert.assertNotNull(entity);

        // UPDATE ENTITY
        Business updateEntity = new Business.Builder()
                .copy(entity)
                .name("Axe")
                .build();
        businessRepository.update(updateEntity);
        Business newEntity = businessRepository.findById(id);
        Assert.assertEquals("Axe", newEntity.getName());

        // DELETE ENTITY
        businessRepository.delete(updateEntity);
        Business deletedEntity = businessRepository.findById(id);
        Assert.assertNull(deletedEntity);


        // DELETE ALL
        businessRepository.deleteAll();
        Set<Business> deletedUsers = businessRepository.findAll();
        Assert.assertTrue(deletedUsers.size() == 0);


    }

    }


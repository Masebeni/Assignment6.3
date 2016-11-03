package com.atm.client;

import android.test.AndroidTestCase;

import com.atm.domain.client.impl.Premium;
import com.atm.repository.client.PremiumRepository;
import com.atm.repository.client.impl.PremiumRepositoryImpl;

import junit.framework.Assert;

import java.util.Set;

/**
 * Created by Axe on 2016-05-08.
 */
public class PremiumRepositoryTest extends AndroidTestCase {

    private Long id;
    public void testCreateReadUpdateDelete() throws Exception {
        PremiumRepository standardRepository = new PremiumRepositoryImpl(this.getContext());

        // CREATE
        Premium client = new Premium.Builder()
                .name("Axe")
                .emailAddress("123@gmail")
                .idNumber("456")
                .membershipType("client")
                .build();

        Premium insertedEntity = standardRepository.save(client);
        id = insertedEntity.getId();
        Assert.assertNotNull(insertedEntity);

        // READ ALL
        Set<Premium> businessSet = standardRepository.findAll();
        Assert.assertTrue(businessSet.size() > 0);

        // READ ENTITY
        Premium entity = standardRepository.findById(id);
        Assert.assertNotNull(entity);

        // UPDATE ENTITY
        Premium updateEntity = new Premium.Builder()
                .copy(entity)
                .name("Axe")
                .build();
        standardRepository.update(updateEntity);
        Premium newEntity = standardRepository.findById(id);
        Assert.assertEquals("Axe", newEntity.getName());

        // DELETE ENTITY
        standardRepository.delete(updateEntity);
        Premium deletedEntity = standardRepository.findById(id);
        Assert.assertNull(deletedEntity);


        // DELETE ALL
        standardRepository.deleteAll();
        Set<Premium> deletedUsers = standardRepository.findAll();
        Assert.assertTrue(deletedUsers.size() == 0);
    }
}

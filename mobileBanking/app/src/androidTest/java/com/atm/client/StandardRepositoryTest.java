package com.atm.client;

import android.test.AndroidTestCase;

import com.atm.domain.client.impl.Standard;
import com.atm.repository.client.StandardRepository;
import com.atm.repository.client.impl.StandardRepositoryImpl;

import junit.framework.Assert;

import java.util.Set;

/**
 * Created by Axe on 2016-05-08.
 */
public class StandardRepositoryTest extends AndroidTestCase {

    private Long id;
    public void testCreateReadUpdateDelete() throws Exception {
        StandardRepository standardRepository = new StandardRepositoryImpl(this.getContext());

        // CREATE
        Standard client = new Standard.Builder()
                .name("Axe")
                .emailAddress("123@gmail")
                .idNumber("456")
                .membershipType("client")
                .build();

        Standard insertedEntity = standardRepository.save(client);
        id = insertedEntity.getId();
        Assert.assertNotNull(insertedEntity);

        // READ ALL
        Set<Standard> businessSet = standardRepository.findAll();
        Assert.assertTrue(businessSet.size() > 0);

        // READ ENTITY
        Standard entity = standardRepository.findById(id);
        Assert.assertNotNull(entity);

        // UPDATE ENTITY
        Standard updateEntity = new Standard.Builder()
                .copy(entity)
                .name("Axe")
                .build();
        standardRepository.update(updateEntity);
        Standard newEntity = standardRepository.findById(id);
        Assert.assertEquals("Axe", newEntity.getName());

        // DELETE ENTITY
        standardRepository.delete(updateEntity);
        Standard deletedEntity = standardRepository.findById(id);
        Assert.assertNull(deletedEntity);


        // DELETE ALL
        standardRepository.deleteAll();
        Set<Standard> deletedUsers = standardRepository.findAll();
        Assert.assertTrue(deletedUsers.size() == 0);
    }
}

package com.atm.account;

import android.test.AndroidTestCase;

import com.atm.domain.account.impl.Student;
import com.atm.domain.client.Client;
import com.atm.domain.client.impl.Business;
import com.atm.factories.client.BusinessFactory;
import com.atm.repository.account.StudentRepository;
import com.atm.repository.account.impl.StudentRepositoryImpl;

import junit.framework.Assert;

import java.util.Set;

/**
 * Created by Axe on 2016-05-09.
 */
public class StudentTestRepository  extends AndroidTestCase {

    private Long id;

    public void testCreateReadUpdateDelete() throws Exception {
        Business client = BusinessFactory.createBusinessClient("456", "ferin", "ferin@abc");
        StudentRepository creditRepository = new StudentRepositoryImpl(this.getContext());

        // CREATE
        Student credit = new Student.Builder()
                .accountNumber("1234")
                .balance(300)
                .limit(100)
                .pin("123")
                .client(client)
                .build();
        Student insertedEntity = creditRepository.save(credit);
        id = insertedEntity.getId();
        Assert.assertNotNull(insertedEntity);

        // READ ALL
        Set<Student> businessSet = creditRepository.findAll();
        Assert.assertTrue(businessSet.size() > 0);

        // READ ENTITY
        Student entity = creditRepository.findById(id);
        Assert.assertNotNull(entity);

        // UPDATE ENTITY
        Student updateEntity = new Student.Builder()
                .copy(entity)
                .balance(700)
                .client(client)
                .build();
        creditRepository.update(updateEntity);
        Student newEntity = creditRepository.findById(id);
        Assert.assertEquals(Double.parseDouble("700"), newEntity.getBalance());

        // DELETE ENTITY
        creditRepository.delete(updateEntity);
        Student deletedEntity = creditRepository.findById(id);
        Assert.assertNull(deletedEntity);


        // DELETE ALL
        creditRepository.deleteAll();
        Set<Student> deletedUsers = creditRepository.findAll();
        Assert.assertTrue(deletedUsers.size() == 0);

    }
}

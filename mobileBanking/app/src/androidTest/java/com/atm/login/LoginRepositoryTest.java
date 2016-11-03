package com.atm.login;

import android.test.AndroidTestCase;

import com.atm.domain.login.Login;
import com.atm.repository.login.LoginRepository;
import com.atm.repository.login.impl.LoginRepositoryImpl;

import junit.framework.Assert;

import java.util.Set;

/**
 * Created by Axe on 2016-05-09.
 */
public class LoginRepositoryTest extends AndroidTestCase {

    private Long id;

    public void testCreateReadUpdateDelete() throws Exception {
        LoginRepository loginRepository = new LoginRepositoryImpl(this.getContext());

        // CREATE
        Login login = new Login.Builder()
                .idNumber("456")
                .pin("123")
                .build();

        Login insertedEntity = loginRepository.save(login);
        id = insertedEntity.getId();
        Assert.assertNotNull(insertedEntity);

        // READ ALL
        Set<Login> businessSet = loginRepository.findAll();
        Assert.assertTrue(businessSet.size() > 0);

        // READ ENTITY
        Login entity = loginRepository.findById(id);
        Assert.assertNotNull(entity);

        // UPDATE ENTITY
        Login updateEntity = new Login.Builder()
                .copy(entity)
                .pin("pin")
                .build();
        loginRepository.update(updateEntity);
        Login newEntity = loginRepository.findById(id);
        Assert.assertEquals("pin", newEntity.getPin());

        // DELETE ENTITY
        loginRepository.delete(updateEntity);
        Login deletedEntity = loginRepository.findById(id);
        Assert.assertNull(deletedEntity);


        // DELETE ALL
        loginRepository.deleteAll();
        Set<Login> deletedUsers = loginRepository.findAll();
        Assert.assertTrue(deletedUsers.size() == 0);


    }
}

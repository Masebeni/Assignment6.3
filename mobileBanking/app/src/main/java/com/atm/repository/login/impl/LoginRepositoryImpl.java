package com.example.ferin.atm.repository.login.impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.ferin.atm.conf.database.DBConstants;
import com.example.ferin.atm.conf.database.ManageDatabase;
import com.example.ferin.atm.conf.util.App;
import com.example.ferin.atm.domain.login.Login;
import com.example.ferin.atm.repository.login.LoginRepository;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Ferin on 2016-05-09.
 */
public class LoginRepositoryImpl extends SQLiteOpenHelper implements LoginRepository {

    public static final String TABLE_NAME = "login";
    private SQLiteDatabase db;

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_IDNUMBER = "idNumber";
    public static final String COLUMN_PIN = "pin";

    public LoginRepositoryImpl(Context context)
    {
        super(context, DBConstants.DATABASE_NAME, null, DBConstants.DATABASE_VERSION);
    }

    public void open() throws SQLException {
        db = this.getWritableDatabase();
    }

    public LoginRepositoryImpl(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public LoginRepositoryImpl(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    @Override
    public Login findById(Long id) {
        Cursor cursor = db.query(
                TABLE_NAME,
                new String[]{
                        COLUMN_ID,
                        COLUMN_IDNUMBER,
                        COLUMN_PIN},
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(id)},
                null,
                null,
                null);
        if (cursor.moveToFirst()) {
            String pin = cursor.getString(cursor.getColumnIndex(COLUMN_PIN));
            String idNumber = cursor.getString(cursor.getColumnIndex(COLUMN_IDNUMBER));
            Long columnId = cursor.getLong(cursor.getColumnIndex(COLUMN_ID));

            /* dont need this */
            final Login login = new Login.Builder()
                    .id(columnId)
                    .pin(pin)
                    .idNumber(idNumber)
                    .build();

            return login;
        }
        return null;
    }

    @Override
    public Login save(Login entity) {
        open();
        ContentValues values = new ContentValues();

        values.put(COLUMN_IDNUMBER, entity.getIdNumber());
        values.put(COLUMN_PIN, entity.getPin());

        Long id = db.insertOrThrow(TABLE_NAME, null,values);

        Login standard = new Login.Builder()
                .copy(entity)
                .id(new Long(id))
                .build();

        return standard;
    }

    @Override
    public Login update(Login entity) {
        open();

        ContentValues values = new ContentValues();

        values.put(COLUMN_ID, entity.getId());
        values.put(COLUMN_IDNUMBER, entity.getIdNumber());
        values.put(COLUMN_PIN, entity.getPin());

        db.update(
                TABLE_NAME,
                values,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf((entity.getId()))}
        );
        return entity;
    }

    @Override
    public Login delete(Login entity) {
        open();
        db.delete(
                TABLE_NAME,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getId())});
        return entity;
    }

    @Override
    public Set<Login> findAll() {
        Set<Login> standardSet = new HashSet<>();
        open();
        Cursor cursor = db.query(TABLE_NAME, null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                String pin = cursor.getString(cursor.getColumnIndex(COLUMN_PIN));
                String idNumber = cursor.getString(cursor.getColumnIndex(COLUMN_IDNUMBER));
                Long columnId = cursor.getLong(cursor.getColumnIndex(COLUMN_ID));


                Login business = new Login.Builder()
                        .id(columnId)
                        .pin(pin)
                        .idNumber(idNumber)
                        .build();
                standardSet.add(business);
            }
            while (cursor.moveToNext());
        }
        return standardSet;
    }

    @Override
    public int deleteAll() {
        open();
        int rowsDeleted = db.delete(TABLE_NAME,null,null);
        //close();
        return rowsDeleted;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        ManageDatabase manageDatabase = new ManageDatabase(App.getInstance());
        manageDatabase.onCreate(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

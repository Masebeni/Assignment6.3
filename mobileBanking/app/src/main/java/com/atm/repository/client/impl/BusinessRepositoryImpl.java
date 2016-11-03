package com.example.ferin.atm.repository.client.impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.ferin.atm.conf.database.DBConstants;
import com.example.ferin.atm.conf.database.ManageDatabase;
import com.example.ferin.atm.conf.util.App;
import com.example.ferin.atm.domain.client.Client;
import com.example.ferin.atm.domain.client.impl.Business;
import com.example.ferin.atm.repository.client.BusinessRepository;

import java.util.HashSet;
import java.util.Set;


/* Call this class for business, premium and standard client type, just change table name, create all 3 table in conf datamanager */

public class BusinessRepositoryImpl extends SQLiteOpenHelper implements BusinessRepository {

    public static final String TABLE_NAME = "business";
    private SQLiteDatabase db;

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_IDNUMBER = "idNumber";
    public static final String COLUMN_EMAIL = "email";


    public BusinessRepositoryImpl(Context context)
    {
        super(context, DBConstants.DATABASE_NAME, null, DBConstants.DATABASE_VERSION);
    }

    public void open() throws SQLException {
        db = this.getWritableDatabase();
    }

    public BusinessRepositoryImpl(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public BusinessRepositoryImpl(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        ManageDatabase manageDatabase = new ManageDatabase(App.getInstance());
        manageDatabase.onCreate(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(this.getClass().getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);

    }

    @Override
    public Business findById(Long id) {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_NAME,
                new String[]{
                        COLUMN_ID,
                        COLUMN_EMAIL,
                        COLUMN_IDNUMBER,
                        COLUMN_NAME},
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(id)},
                null,
                null,
                null);
        if(cursor.moveToFirst())
        {
            String email = cursor.getString(cursor.getColumnIndex(COLUMN_EMAIL));
            String idNumber = cursor.getString(cursor.getColumnIndex(COLUMN_IDNUMBER));
            String name = cursor.getString(cursor.getColumnIndex(COLUMN_NAME));
            Long columnId = cursor.getLong(cursor.getColumnIndex(COLUMN_ID));

            /* dont need this */
            final Business client = new Business.Builder()
                .id(columnId)
                .emailAddress(email)
                .idNumber(idNumber)
                .membershipType("business")
                .name(name)
                .build();

                return client;
               }
        else
        return null;
    }

    @Override
    public Business save(Business entity) {

        open();
        ContentValues values = new ContentValues();

        values.put(COLUMN_IDNUMBER, entity.getIdNumber());
        values.put(COLUMN_EMAIL, entity.getEmailAddress());
        values.put(COLUMN_NAME, entity.getName());

        Long id = db.insertOrThrow(TABLE_NAME, null,values);

        Business business = new Business.Builder()
                .copy(entity)
                .id(new Long(id))
                .build();

        return business;
    }

    @Override
    public Business update(Business entity) {
        open();

        ContentValues values = new ContentValues();

        values.put(COLUMN_ID, entity.getId());
        values.put(COLUMN_EMAIL, entity.getEmailAddress());
        values.put(COLUMN_IDNUMBER, entity.getIdNumber());
        values.put(COLUMN_NAME, entity.getName());

        db.update(
                TABLE_NAME,
                values,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf((entity.getId()))}
        );
        return entity;
    }

    @Override
    public Business delete(Business entity) {
        open();
        db.delete(
                TABLE_NAME,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getId())});
        return entity;
    }

    @Override
    public Set<Business> findAll() {
        SQLiteDatabase db = this.getReadableDatabase();
        Set<Business> businessSet = new HashSet<>();
        open();
        Cursor cursor = db.query(TABLE_NAME, null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                String email = cursor.getString(cursor.getColumnIndex(COLUMN_EMAIL));
                String idNumber = cursor.getString(cursor.getColumnIndex(COLUMN_IDNUMBER));
                String name = cursor.getString(cursor.getColumnIndex(COLUMN_NAME));
                Long columnId = cursor.getLong(cursor.getColumnIndex(COLUMN_ID));

                Business business = new Business.Builder()
                        .emailAddress(email)
                        .id(columnId)
                        .idNumber(idNumber)
                        .name(name)
                        .membershipType("business")
                        .build();
                businessSet.add(business);
            }
            while (cursor.moveToNext());
        }
        return businessSet;
    }

    @Override
    public int deleteAll() {
        open();
        int rowsDeleted = db.delete(TABLE_NAME,null,null);
        //close();
        return rowsDeleted;
    }
}

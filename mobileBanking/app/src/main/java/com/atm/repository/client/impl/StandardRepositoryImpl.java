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
import com.example.ferin.atm.domain.client.impl.Standard;
import com.example.ferin.atm.repository.client.StandardRepository;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Ferin on 2016-05-08.
 */
public class StandardRepositoryImpl extends SQLiteOpenHelper implements StandardRepository {


    public static final String TABLE_NAME = "standard";
    private SQLiteDatabase db;

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_IDNUMBER = "idNumber";
    public static final String COLUMN_EMAIL = "email";


    public StandardRepositoryImpl(Context context)
    {
        super(context, DBConstants.DATABASE_NAME, null, DBConstants.DATABASE_VERSION);
    }

    public void open() throws SQLException {
        db = this.getWritableDatabase();
    }


    public StandardRepositoryImpl(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public StandardRepositoryImpl(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    @Override
    public Standard findById(Long id) {  SQLiteDatabase db = this.getReadableDatabase();
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
            final Standard client = new Standard.Builder()
                    .id(columnId)
                    .emailAddress(email)
                    .idNumber(idNumber)
                    .membershipType("standard")
                    .name(name)
                    .build();

            return client;
        }
        else
            return null;
    }

    @Override
    public Standard save(Standard entity) {
        open();
        ContentValues values = new ContentValues();

        values.put(COLUMN_IDNUMBER, entity.getIdNumber());
        values.put(COLUMN_EMAIL, entity.getEmailAddress());
        values.put(COLUMN_NAME, entity.getName());

        Long id = db.insertOrThrow(TABLE_NAME, null,values);

        Standard standard = new Standard.Builder()
                .copy(entity)
                .id(new Long(id))
                .build();

        return standard;
    }

    @Override
    public Standard update(Standard entity) {
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
    public Standard delete(Standard entity) {
        open();
        db.delete(
                TABLE_NAME,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getId())});
        return entity;
    }

    @Override
    public Set<Standard> findAll() {
        Set<Standard> standardSet = new HashSet<>();
        open();
        Cursor cursor = db.query(TABLE_NAME, null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                String email = cursor.getString(cursor.getColumnIndex(COLUMN_EMAIL));
                String idNumber = cursor.getString(cursor.getColumnIndex(COLUMN_IDNUMBER));
                String name = cursor.getString(cursor.getColumnIndex(COLUMN_NAME));
                Long columnId = cursor.getLong(cursor.getColumnIndex(COLUMN_ID));

                Standard business = new Standard.Builder()
                        .emailAddress(email)
                        .id(columnId)
                        .idNumber(idNumber)
                        .name(name)
                        .membershipType("standard")
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
        Log.w(this.getClass().getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}

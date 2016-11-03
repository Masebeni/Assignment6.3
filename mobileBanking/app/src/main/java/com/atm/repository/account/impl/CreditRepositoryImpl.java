package com.example.ferin.atm.repository.account.impl;

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
import com.example.ferin.atm.domain.account.impl.Credit;
import com.example.ferin.atm.repository.account.CreditRepository;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Ferin on 2016-05-08.
 */
public class CreditRepositoryImpl extends SQLiteOpenHelper implements CreditRepository {

    public static final String TABLE_NAME = "credit";
    private SQLiteDatabase db;

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_ACCOUNTNUMBER = "accountNumber";
    public static final String COLUMN_BALANCE = "balance";
    public static final String COLUMN_LIMIT = "creditLimit";
    public static final String COLUMN_PIN = "pin";
    public static final String COLUMN_CLIENTID = "clientId";


    public CreditRepositoryImpl(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public CreditRepositoryImpl(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    public CreditRepositoryImpl(Context context)
    {
        super(context, DBConstants.DATABASE_NAME, null, DBConstants.DATABASE_VERSION);
    }

    public void open() throws SQLException {
        db = this.getWritableDatabase();
    }


    @Override
    public Credit findById(Long id) {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_NAME,
                new String[]{
                        COLUMN_ID,
                        COLUMN_ACCOUNTNUMBER,
                        COLUMN_BALANCE,
                        COLUMN_LIMIT,
                        COLUMN_PIN,
                        COLUMN_CLIENTID},
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(id)},
                null,
                null,
                null);
        if(cursor.moveToFirst())
        {
            String accNumber = cursor.getString(cursor.getColumnIndex(COLUMN_ACCOUNTNUMBER));
            double balance = cursor.getDouble(cursor.getColumnIndex(COLUMN_BALANCE));
            double limit = cursor.getDouble(cursor.getColumnIndex(COLUMN_LIMIT));
            String pin = cursor.getString(cursor.getColumnIndex(COLUMN_PIN));
            Long columnId = cursor.getLong(cursor.getColumnIndex(COLUMN_ID));
            Long clientID = cursor.getLong(cursor.getColumnIndex(COLUMN_CLIENTID));

            final Credit client = new Credit.Builder()
                    .id(columnId)
                    .accountNumber(accNumber)
                    .balance(balance)
                    .limit(limit)
                    .pin(pin)
                    .build();

            return client;
        }
        else
            return null;
    }

    @Override
    public Credit save(Credit entity) {
        open();
        ContentValues values = new ContentValues();

        values.put(COLUMN_ACCOUNTNUMBER, entity.getAccountNumber());
        values.put(COLUMN_BALANCE, entity.getBalance());
        values.put(COLUMN_LIMIT, entity.getLimit());
        values.put(COLUMN_PIN, entity.getPin());
        values.put(COLUMN_CLIENTID, entity.getClient().getIdNumber()); // acts as FK

        Long id = db.insertOrThrow(TABLE_NAME, null,values);

        Credit credit = new Credit.Builder()
                .copy(entity)
                .id(new Long(id))
                .build();

        return credit;
    }

    @Override
    public Credit update(Credit entity) {
        open();

        ContentValues values = new ContentValues();

        values.put(COLUMN_ID, entity.getId());
        values.put(COLUMN_PIN, entity.getPin());
        values.put(COLUMN_ACCOUNTNUMBER, entity.getAccountNumber());
        values.put(COLUMN_BALANCE, entity.getBalance());
        values.put(COLUMN_LIMIT, entity.getLimit());
        values.put(COLUMN_CLIENTID, entity.getClient().getIdNumber());

        db.update(
                TABLE_NAME,
                values,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf((entity.getId()))}
        );
        return entity;
    }

    @Override
    public Credit delete(Credit entity) {
        open();
        db.delete(
                TABLE_NAME,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getId())});
        return entity;
    }

    @Override
    public Set<Credit> findAll() {
        SQLiteDatabase db = this.getReadableDatabase();
        Set<Credit> creditSet = new HashSet<>();
        open();
        Cursor cursor = db.query(TABLE_NAME, null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                String accNumber = cursor.getString(cursor.getColumnIndex(COLUMN_ACCOUNTNUMBER));
                double balance = cursor.getDouble(cursor.getColumnIndex(COLUMN_BALANCE));
                double limit = cursor.getDouble(cursor.getColumnIndex(COLUMN_LIMIT));
                String pin = cursor.getString(cursor.getColumnIndex(COLUMN_PIN));
                Long columnId = cursor.getLong(cursor.getColumnIndex(COLUMN_ID));
                Long clientID = cursor.getLong(cursor.getColumnIndex(COLUMN_CLIENTID));

                Credit credit = new Credit.Builder()
                        .id(columnId)
                        .pin(pin)
                        .balance(balance)
                        .limit(limit)
                        .accountNumber(accNumber)
                        .build();
                creditSet.add(credit);
            }
            while (cursor.moveToNext());
        }
        return creditSet;
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

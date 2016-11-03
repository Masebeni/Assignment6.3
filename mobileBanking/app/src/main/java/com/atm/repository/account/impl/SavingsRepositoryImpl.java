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
import com.example.ferin.atm.domain.account.impl.Savings;
import com.example.ferin.atm.repository.account.SavingsRepository;

import java.util.HashSet;
import java.util.Set;

public class SavingsRepositoryImpl extends SQLiteOpenHelper implements SavingsRepository {

    public static final String TABLE_NAME = "savings";
    private SQLiteDatabase db;

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_ACCOUNTNUMBER = "accountNumber";
    public static final String COLUMN_BALANCE = "balance";
    public static final String COLUMN_LIMIT = "creditLimit";
    public static final String COLUMN_PIN = "pin";
    public static final String COLUMN_CLIENTID = "clientId";

    public SavingsRepositoryImpl(Context context)
    {
        super(context, DBConstants.DATABASE_NAME, null, DBConstants.DATABASE_VERSION);
    }

    public SavingsRepositoryImpl(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public SavingsRepositoryImpl(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    public void open() throws SQLException {
        db = this.getWritableDatabase();
    }

    @Override
    public Savings findById(Long id) {

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

            final Savings client = new Savings.Builder()
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
    public Savings save(Savings entity) {
        open();
        ContentValues values = new ContentValues();

        values.put(COLUMN_ACCOUNTNUMBER, entity.getAccountNumber());
        values.put(COLUMN_BALANCE, entity.getBalance());
        values.put(COLUMN_LIMIT, entity.getLimit());
        values.put(COLUMN_PIN, entity.getPin());
        values.put(COLUMN_CLIENTID, entity.getClient().getIdNumber()); // acts as FK

        Long id = db.insertOrThrow(TABLE_NAME, null,values);

        Savings savings = new Savings.Builder()
                .copy(entity)
                .id(new Long(id))
                .build();

        return savings;
    }

    @Override
    public Savings update(Savings entity) {
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
    public Savings delete(Savings entity) {
        open();
        db.delete(
                TABLE_NAME,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getId())});
        return entity;
    }

    @Override
    public Set<Savings> findAll() {
        SQLiteDatabase db = this.getReadableDatabase();
        Set<Savings> savingsSet = new HashSet<>();
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

                Savings credit = new Savings.Builder()
                        .id(columnId)
                        .pin(pin)
                        .balance(balance)
                        .limit(limit)
                        .accountNumber(accNumber)
                        .build();
                savingsSet.add(credit);
            }
            while (cursor.moveToNext());
        }
        return savingsSet;
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

package com.atm.conf.database;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by Axe on 2016-04-24.
 */

/* Ensures database is created with all tables */

public class ManageDatabase extends SQLiteOpenHelper {

    public static final String TABLE_NAME_BUSINESS = "business";
    private SQLiteDatabase db;

    public static final String COLUMN_ID_BUSINESS = "id";
    public static final String COLUMN_NAME_BUSINESS = "name";
    public static final String COLUMN_IDNUMBER_BUSINESS = "idNumber";
    public static final String COLUMN_EMAIL_BUSINESS = "email";

    private static final String DATABASE_CREATE_BUSINESS = " CREATE TABLE "
            + TABLE_NAME_BUSINESS + "("
            + COLUMN_ID_BUSINESS + " INTEGER  PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_NAME_BUSINESS + " TEXT  NOT NULL , "
            + COLUMN_IDNUMBER_BUSINESS + " TEXT  NOT NULL , "
            + COLUMN_EMAIL_BUSINESS + " TEXT  NOT NULL );";



    public static String TABLE_NAME_PREMIUM = "premium";

    public static final String COLUMN_ID_PREMIUM = "id";
    public static final String COLUMN_NAME_PREMIUM = "name";
    public static final String COLUMN_IDNUMBER_PREMIUM = "idNumber";
    public static final String COLUMN_EMAIL_PREMIUM = "email";

    private static  String DATABASE_CREATE_PREMIUM = " CREATE TABLE "
            + TABLE_NAME_PREMIUM + "("
            + COLUMN_ID_PREMIUM + " INTEGER  PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_NAME_PREMIUM + " TEXT  NOT NULL , "
            + COLUMN_IDNUMBER_PREMIUM + " TEXT  NOT NULL , "
            + COLUMN_EMAIL_PREMIUM + " TEXT  NOT NULL );";



    public static final String TABLE_NAME_STANDARD = "standard";

    public static final String COLUMN_ID_STANDARD = "id";
    public static final String COLUMN_NAME_STANDARD = "name";
    public static final String COLUMN_IDNUMBER_STANDARD = "idNumber";
    public static final String COLUMN_EMAIL_STANDARD = "email";

    private static final String DATABASE_CREATE_STANDARD = " CREATE TABLE "
            + TABLE_NAME_STANDARD + "("
            + COLUMN_ID_STANDARD + " INTEGER  PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_NAME_STANDARD + " TEXT  NOT NULL , "
            + COLUMN_IDNUMBER_STANDARD + " TEXT  NOT NULL , "
            + COLUMN_EMAIL_STANDARD + " TEXT  NOT NULL );";



    public static final String TABLE_NAME_CREDIT = "credit";

    public static final String COLUMN_ID_CREDIT = "id";
    public static final String COLUMN_ACCOUNTNUMBER_CREDIT = "accountNumber";
    public static final String COLUMN_BALANCE_CREDIT = "balance";
    public static final String COLUMN_LIMIT_CREDIT = "creditLimit";
    public static final String COLUMN_PIN_CREDIT = "pin";
    public static final String COLUMN_CLIENTID_CREDIT = "clientId";

    private static final String DATABASE_CREATE_CREDIT = " CREATE TABLE "
            + TABLE_NAME_CREDIT + "("
            + COLUMN_ID_CREDIT + " INTEGER  PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_ACCOUNTNUMBER_CREDIT + " TEXT  NOT NULL , "
            + COLUMN_BALANCE_CREDIT + " REAL  NOT NULL , "
            + COLUMN_LIMIT_CREDIT + " REAL  NOT NULL , "
            + COLUMN_PIN_CREDIT + " TEXT  NOT NULL , "
            + COLUMN_CLIENTID_CREDIT + " TEXT  NOT NULL );";

    public static final String TABLE_NAME_SAVINGS = "savings";

    public static final String COLUMN_ID_SAVINGS = "id";
    public static final String COLUMN_ACCOUNTNUMBER_SAVINGS = "accountNumber";
    public static final String COLUMN_BALANCE_SAVINGS = "balance";
    public static final String COLUMN_LIMIT_SAVINGS = "creditLimit";
    public static final String COLUMN_PIN_SAVINGS = "pin";
    public static final String COLUMN_CLIENTID_SAVINGS = "clientId";

    private static final String DATABASE_CREATE_SAVINGS = " CREATE TABLE "
            + TABLE_NAME_SAVINGS + "("
            + COLUMN_ID_SAVINGS + " INTEGER  PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_ACCOUNTNUMBER_SAVINGS + " TEXT  NOT NULL , "
            + COLUMN_BALANCE_SAVINGS + " REAL  NOT NULL , "
            + COLUMN_LIMIT_SAVINGS + " REAL  NOT NULL , "
            + COLUMN_PIN_SAVINGS + " TEXT  NOT NULL , "
            + COLUMN_CLIENTID_SAVINGS + " TEXT  NOT NULL );";


    public static final String TABLE_NAME_STUDENT = "student";

    public static final String COLUMN_ID_STUDENT = "id";
    public static final String COLUMN_ACCOUNTNUMBER_STUDENT = "accountNumber";
    public static final String COLUMN_BALANCE_STUDENT = "balance";
    public static final String COLUMN_LIMIT_STUDENT = "creditLimit";
    public static final String COLUMN_PIN_STUDENT = "pin";
    public static final String COLUMN_CLIENTID_STUDENT = "clientId";

    private static final String DATABASE_CREATE_STUDENT = " CREATE TABLE "
            + TABLE_NAME_STUDENT + "("
            + COLUMN_ID_STUDENT + " INTEGER  PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_ACCOUNTNUMBER_STUDENT + " TEXT  NOT NULL , "
            + COLUMN_BALANCE_STUDENT + " REAL  NOT NULL , "
            + COLUMN_LIMIT_STUDENT + " REAL  NOT NULL , "
            + COLUMN_PIN_STUDENT + " TEXT  NOT NULL , "
            + COLUMN_CLIENTID_STUDENT + " TEXT  NOT NULL );";

    public static final String TABLE_NAME_LOGIN = "login";

    public static final String COLUMN_ID_LOGIN = "id";
    public static final String COLUMN_IDNUMBER_LOGIN = "idNumber";
    public static final String COLUMN_PIN_LOGIN = "pin";

    private static final String DATABASE_CREATE_LOGIN = " CREATE TABLE "
            + TABLE_NAME_LOGIN + "("
            + COLUMN_ID_LOGIN + " INTEGER  PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_IDNUMBER_LOGIN + " TEXT  NOT NULL , "
            + COLUMN_PIN_LOGIN + " TEXT  NOT NULL );";


    public ManageDatabase(Context context)
    {
        super(context, DBConstants.DATABASE_NAME, null, DBConstants.DATABASE_VERSION);

    }


    public ManageDatabase(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, SQLiteDatabase db) {
        super(context, name, factory, version);
        this.db = db;
    }

    public ManageDatabase(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler, SQLiteDatabase db) {
        super(context, name, factory, version, errorHandler);
        this.db = db;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(DATABASE_CREATE_BUSINESS);
        db.execSQL(DATABASE_CREATE_PREMIUM);
        db.execSQL(DATABASE_CREATE_STANDARD);
        db.execSQL(DATABASE_CREATE_CREDIT);
        db.execSQL(DATABASE_CREATE_SAVINGS);
        db.execSQL(DATABASE_CREATE_STUDENT);
        db.execSQL(DATABASE_CREATE_LOGIN);

    }

    public void deleteTable(String tablename){
        db.execSQL("DROP TABLE IF EXISTS "+tablename+";");
    }

    public boolean deleteDatabase(Context context) {
        return context.deleteDatabase(DBConstants.DATABASE_NAME);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

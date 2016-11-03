package com.example.ferin.atm.services.client.impl;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;

import com.example.ferin.atm.domain.account.impl.Student;
import com.example.ferin.atm.repository.account.StudentRepository;
import com.example.ferin.atm.repository.account.impl.StudentRepositoryImpl;
import com.example.ferin.atm.services.client.StudentService;


public class StudentServiceImpl extends IntentService {

    private static final String ACTION_ADD = "com.example.ferin.atm.services.client.impl.action.ADD";
    private static final String ACTION_DELETE = "com.example.ferin.atm.services.client.impl.action.DELETE";

    // TODO: Rename parameters
    private static final String EXTRA_ADD = "com.example.ferin.atm.services.client.impl.extra.ADD";
    private static final String EXTRA_DELETE = "com.example.ferin.atm.services.client.impl.extra.DELETE";

    public StudentServiceImpl() {
        super("StudentServiceImpl");
    }

    private static StudentServiceImpl service = null;

    public static StudentServiceImpl getInstance() {
        if (service == null)
            service = new StudentServiceImpl();
        return service;
    }
    public static void startActionAdd(Context context,Student student) {
        Intent intent = new Intent(context, StudentServiceImpl.class);
        intent.setAction(ACTION_ADD);
        intent.putExtra(EXTRA_ADD, student);
        context.startService(intent);
    }

    public static void startActionDelete(Context context, Student student) {
        Intent intent = new Intent(context, StudentServiceImpl.class);
        intent.setAction(ACTION_DELETE);
        intent.putExtra(EXTRA_DELETE, student);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_ADD.equals(action)) {
                final Student student = (Student)intent.getSerializableExtra(EXTRA_ADD);
                insert(student);
            } else if (ACTION_DELETE.equals(action)) {
                final Student student = (Student)intent.getSerializableExtra(EXTRA_ADD);
                delete(student);
            }
        }
    }

    private void insert(Student student) {
        StudentRepository studentRepository = new StudentRepositoryImpl(getBaseContext());
        studentRepository.save(student);
    }

    private void delete(Student student) {
        StudentRepository studentRepository = new StudentRepositoryImpl(getBaseContext());
        studentRepository.delete(student);
    }
}

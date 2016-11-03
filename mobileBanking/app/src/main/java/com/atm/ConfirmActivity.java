package com.atm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.atm.domain.account.impl.Credit;
import com.atm.domain.client.impl.Business;
import com.atm.services.account.impl.SavingsServiceImpl;
import com..atm.services.client.impl.ClientServiceImpl;

public class ConfirmActivity extends AppCompatActivity {

    Business business;
    Credit credit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm);

            Bundle extras = getIntent().getExtras();
            business = (Business)extras.getSerializable("CLIENT");
            credit = (Credit)extras.getSerializable("ACCOUNT");

        ((TextView)findViewById(R.id.txtName)).setText(business.getName());
        ((TextView)findViewById(R.id.txtEmail)).setText(business.getEmailAddress());
        ((TextView)findViewById(R.id.txtIdNumber)).setText(business.getIdNumber());
        ((TextView)findViewById(R.id.txtAccountNumber)).setText(credit.getAccountNumber());
        ((TextView)findViewById(R.id.txtBalance)).setText((Double.toString(credit.getBalance())));
        ((TextView)findViewById(R.id.txtLimit)).setText((Double.toString(credit.getLimit())));
        ((TextView)findViewById(R.id.txtAccountNumber)).setText(credit.getPin());


    }

    public void saveToDB(View v)
    {
        ClientServiceImpl clientService = ClientServiceImpl.getInstance();
        clientService.addClient(getBaseContext(), business);

        SavingsServiceImpl savingsService = SavingsServiceImpl.getInstance();
        savingsService.addAccount(getBaseContext(), credit);

        Intent intent = new Intent(this, DisplayAllActivity.class);
        startActivity(intent);
    }
}

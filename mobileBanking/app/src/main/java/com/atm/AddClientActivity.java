package com.atm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.ferin.atm.domain.account.impl.Credit;
import com.example.ferin.atm.domain.client.impl.Business;
import com.example.ferin.atm.factories.account.CreditFactory;
import com.example.ferin.atm.factories.client.BusinessFactory;

public class AddClientActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_withdraw);
    }

    public void nextActivity(View v)
    {
        Intent intent = new Intent(this, ConfirmActivity.class);

        String name = ((EditText)findViewById(R.id.txtName)).getText().toString();
        String idNumber = ((EditText)findViewById(R.id.txtIdNumber)).getText().toString();
        String email = ((EditText)findViewById(R.id.txtEmail)).getText().toString();

        String accNr = ((EditText)findViewById(R.id.txtAccountNumber)).getText().toString();
        String balance = ((EditText)findViewById(R.id.txtBalance)).getText().toString();
        String limit = ((EditText)findViewById(R.id.txtLimit)).getText().toString();
        String pin = ((EditText)findViewById(R.id.txtPin)).getText().toString();

        Business business = BusinessFactory.createBusinessClient(idNumber, name, email);

        Credit credit = CreditFactory.createCredit(accNr,Double.parseDouble(balance),Double.parseDouble(limit),pin,business);

        intent.putExtra("CLIENT", business);
        intent.putExtra("ACCOUNT", credit);

        startActivity(intent);
    }
}

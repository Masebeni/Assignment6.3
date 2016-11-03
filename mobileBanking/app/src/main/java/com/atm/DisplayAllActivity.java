package com.atm;
import com.example.ferin.atm.services.client.RetrieveClientInfo.*;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.atm.domain.account.impl.Credit;
import com..atm.domain.client.impl.Business;
import com.atm.repository.account.CreditRepository;
import com.atm.repository.account.impl.CreditRepositoryImpl;
import com.atm.repository.client.BusinessRepository;
import com.atm.repository.client.impl.BusinessRepositoryImpl;
import com.atm.services.account.GetAllAccountInfo;
import com.atm.services.account.impl.GetAllAccountInfoImpl;

import com.atm.services.client.RetrieveClientInfo;
import com.atm.services.client.impl.RetrieveClientInfoImpl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DisplayAllActivity extends AppCompatActivity {

    RetrieveClientInfoImpl clientService;
    boolean isClientbound = false;
    Set<Business> businessSet = new HashSet<>();
    List<Business> businessList;

    GetAllAccountInfoImpl accountService;
    boolean isAccountBound = false;
    Set<Credit> creditSet = new HashSet<>();
    List<Credit> creditList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_all);

        Intent intent = new Intent(this, RetrieveClientInfoImpl.class);
        bindService(intent, myConnection, Context.BIND_AUTO_CREATE);

       /* Intent intent1 = new Intent(this, GetAllAccountInfoImpl.class);
        bindService(intent1, myCreditConnect, Context.BIND_AUTO_CREATE);*/

    }

    public void backToMenu(View v)
    {
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    }

    private ServiceConnection myConnection = new ServiceConnection()
    {

        public void onServiceConnected(ComponentName className,
                                       IBinder service) {
            RetrieveClientInfoImpl.MyLocalBinder binder = (RetrieveClientInfoImpl.MyLocalBinder) service;
            clientService = binder.getService();
            isClientbound = true;

            businessSet = clientService.getBusinessClient();
            businessList = new ArrayList<Business>(businessSet);


            TableLayout table = (TableLayout) findViewById(R.id.tblData);
            TableRow row = new TableRow(getBaseContext());
            //row.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT, TableRow.LayoutParams.WRAP_CONTENT));

            TextView t = new TextView(getBaseContext());
            t.setText("Name         ");
            row.addView(t);

            TextView idNum = new TextView(getBaseContext());
            idNum.setText("Id           ");
            row.addView(idNum);

            TextView email = new TextView(getBaseContext());
            email.setText("Email            ");
            row.addView(email);

            table.addView(row, 0);


        for(int i = 0; i < businessList.size(); i++) {
            TableRow row2 = new TableRow(getBaseContext());
            //row2.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT, TableRow.LayoutParams.WRAP_CONTENT));

            TextView t2 = new TextView(getBaseContext());
            t2.setText(businessList.get(i).getName() + "            ");
            row2.addView(t2);

            TextView idNum2 = new TextView(getBaseContext());
            idNum2.setText(businessList.get(i).getIdNumber() + "            ");
            row2.addView(idNum2);

            TextView email2 = new TextView(getBaseContext());
            email2.setText(businessList.get(i).getEmailAddress() + "            ");
            row2.addView(email2);

            table.addView(row2, (i + 1));
        }

        }

        public void onServiceDisconnected(ComponentName arg0) {
            isClientbound = false;
        }
    };

    private ServiceConnection myCreditConnect = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            GetAllAccountInfoImpl.RetrieveAccountInfoLocalBinder binder = (GetAllAccountInfoImpl.RetrieveAccountInfoLocalBinder) service;

            accountService = binder.getService();
            isAccountBound = true;

            creditSet = accountService.getAllCredit();
            creditList = new ArrayList<Credit>(creditSet);

            TableLayout table = (TableLayout) findViewById(R.id.tblData);
            TableRow row = new TableRow(getBaseContext());
            //row.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT, TableRow.LayoutParams.WRAP_CONTENT));

            TextView t = new TextView(getBaseContext());
            t.setText("Account Number     ");
            row.addView(t);

            TextView idNum = new TextView(getBaseContext());
            idNum.setText("Balance       ");
            row.addView(idNum);

            TextView email = new TextView(getBaseContext());
            email.setText("Limit        ");
            row.addView(email);

            table.addView(row, 0);


            for(int i = 0; i < businessList.size(); i++) {
                TableRow row2 = new TableRow(getBaseContext());
                //row2.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT, TableRow.LayoutParams.WRAP_CONTENT));

                TextView t2 = new TextView(getBaseContext());
                t2.setText(creditList.get(i).getAccountNumber() + "        ");
                row2.addView(t2);

                TextView idNum2 = new TextView(getBaseContext());
                idNum2.setText(creditList.get(i).getBalance() + "        ");
                row2.addView(idNum2);

                TextView email2 = new TextView(getBaseContext());
                email2.setText(creditList.get(i).getLimit() + "        ");
                row2.addView(email2);

                table.addView(row2, (i + 1));
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            isAccountBound = false;
        }
    };

}

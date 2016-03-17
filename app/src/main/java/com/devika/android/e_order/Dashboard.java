package com.devika.android.e_order;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.devika.android.e_order.database.OrderDAO;
import com.devika.android.e_order.entity.Bestelling;


public class Dashboard extends AppCompatActivity {

    private OrderDAO db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        db = new OrderDAO(this);

        Intent intent = getIntent();
        String message = intent.getStringExtra("username");
        TextView welcomeMessage = (TextView) findViewById(R.id.welcomeMessage);
        // welcomeMessage.setText(message);
        onClickBestelling(message);
        getBestelling(message);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_dashboard, menu);
        return true;
    }

    public void getBestelling(String username) {
        Button button = (Button) findViewById(R.id.button);
        final String usernameValue = username;
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Bestelling user = db.findBest(usernameValue);
                TextView order = (TextView) findViewById(R.id.order);
                String bestelling = String.format("Uw  bestelling is een %s", Bestelling.getBestelling());
                order.setText(bestelling);
            }
        });
    }


    public void onClickBestelling(String username) {
        //reference to textview
        final TextView order = (TextView) findViewById(R.id.order);
        Button bestelling = (Button) findViewById(R.id.bestelling);

        //reference to spinners
        Spinner drank = (Spinner) findViewById(R.id.drank);
        Spinner eten = (Spinner) findViewById(R.id.eten);
        //get selected item in spinner
        final String drankOrder = drank.getSelectedItem().toString();
        final String etenOrder = eten.getSelectedItem().toString();

        final String usernameValue = username;

        bestelling.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ContentValues etenInsert = new ContentValues();
                etenInsert.put(OrderDAO.BESTELLING_USERNAME, usernameValue);
                etenInsert.put(OrderDAO.BESTELLING_DRANK, drankOrder);
                etenInsert.put(OrderDAO.BESTELLING_ETEN, etenOrder);
                long recordId = db.insertBestelling(OrderDAO.BESTELLING_TABLE, etenInsert);

                if (recordId > 0) {
                    order.setText("U heeft een " + drankOrder + " en een " + etenOrder + " besteld");
                    String notificationUser = "Wel besteld";
                    Toast.makeText(Dashboard.this, notificationUser, Toast.LENGTH_LONG).show();
                } else {
                    String notificationUser = " niet besteld";
                    Toast.makeText(Dashboard.this, notificationUser, Toast.LENGTH_LONG).show();
                }
            }
        });
    }


}

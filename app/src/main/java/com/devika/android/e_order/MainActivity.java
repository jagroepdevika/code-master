package com.devika.android.e_order;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.devika.android.e_order.database.OrderDAO;
import com.devika.android.e_order.entity.User;

public class MainActivity extends AppCompatActivity {

    private OrderDAO db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        db = new OrderDAO(this);
    }

    public void onClickLogin(View view) {

        EditText username = (EditText) findViewById(R.id.usernameField);
        String usernameValue = String.valueOf(username.getText());

        EditText password = (EditText) findViewById(R.id.passwordField);
        String passwordvalue = String.valueOf(password.getText());

        String notification = "Verkeerd ingelogd";

        if (!usernameValue.isEmpty() && !passwordvalue.isEmpty()) {
            //search user cred
            User user = null;
            db.login(usernameValue, passwordvalue);

            Intent intent = new Intent(this, Dashboard.class);
            intent.putExtra("username", User.getUsername());
            startActivity(intent);

        } else {
            Toast.makeText(this, notification, Toast.LENGTH_LONG).show();
        }
    }
}


package com.walangare.regina.totabuantaxi;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;



import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {
    private Toolbar toolbar;

    private EditText firstnameText;
    private EditText lastnameText;
    private EditText emailText;
    private EditText passwordText;

    private Button btnRegister;



    public static void start(Context context) {
        Intent intent = new Intent(context, RegisterActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        toolbar = (Toolbar) findViewById(R.id.toolbar);

        firstnameText = (EditText) findViewById(R.id.firstname);
        lastnameText = (EditText) findViewById(R.id.lastname);
        emailText = (EditText) findViewById(R.id.email);
        passwordText = (EditText) findViewById(R.id.password);
        btnRegister = (Button) findViewById(R.id.btn_register);

        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerAct();
            }
        });
    }

    void registerAct() {
        String firstname = firstnameText.getText().toString();
        String lastname = lastnameText.getText().toString();
        String email = emailText.getText().toString();
        String password = passwordText.getText().toString();

        if (TextUtils.isEmpty(firstname)) {
            firstnameText.setError("Firstname cannot be empty !");
            return;
        }

        if (TextUtils.isEmpty(lastname)) {
            firstnameText.setError("Lastname cannot be empty !");
            return;
        }

        if (TextUtils.isEmpty(email)) {
            firstnameText.setError("Email cannot be empty !");
            return;
        }

        if (TextUtils.isEmpty(password)) {
            firstnameText.setError("Password cannot be empty !");
            return;
        }
    }

}
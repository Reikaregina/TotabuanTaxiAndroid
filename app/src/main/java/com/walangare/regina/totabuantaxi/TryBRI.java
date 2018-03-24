package com.walangare.regina.totabuantaxi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.app.Activity;

public class TryBRI extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_try_bri);

        Button mTwo = findViewById(R.id.btn_apload);
        mTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(TryBRI.this,
                        "",
                        Toast.LENGTH_LONG).show();
            }
        });
    }
}

package com.walangare.regina.totabuantaxi;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    private TextView greeting;
    private TextView email;
    private Button btnLogout;
    private Button keKM;
    private Button keMK;

    public static void start(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        keKM = (Button) findViewById(R.id.btkeKM);

        keKM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent KM = new Intent(MainActivity.this, PesanKM.class);
                startActivity(KM);
            }
        });

        keMK = (Button) findViewById(R.id.btkeMK);

        keMK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent MK = new Intent(MainActivity.this, PesanKM.class);
                startActivity(MK);
            }
        });

    }

}

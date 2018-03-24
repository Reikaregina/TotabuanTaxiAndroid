package com.walangare.regina.totabuantaxi;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.view.ViewGroup.LayoutParams;
import android.widget.Toast;

import org.xmlpull.v1.XmlPullParser;


public class Hasilpesanmk extends AppCompatActivity {
    //"inisialisasi" menampilkan hasil dari textview layout sebelumnya
    public static final String DATE = "defaultDate";
    public static final String TIME = "DetaultTime";
    public static final String PRIZE = "DefaultPrize";
    public static final String NOMOR = "DefaultNomor";
    private String dateFromPesanClass, timeFromPesanClass, priceFromPesanClass, nomorFromPesanClass;

    //untuk popup
    private  Context briContext, bniContext;
    private  Activity briActivity, bniActivity;
    private  RelativeLayout briRelativeLayout, bniRelativeLayout;
    private  PopupWindow bripopup, bnipopup;
    private  ImageView BRI, BNI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hasilpesanmk);

        //Bundle b = getIntent().getExtras();

        //membuat obyek dari widget textview

        TextView tanggal = (TextView)  findViewById(R.id.tv_tgl);
        TextView jam = (TextView) findViewById(R.id.tv_jam);
        TextView Harga = (TextView) findViewById(R.id.tv_harga);
        TextView Nomor = (TextView) findViewById(R.id.tv_kursi);

        //set data untuk widget

        //tanggal.setText(b.getCharSequence("txtDate"));
        //jam.setText(b.getCharSequence("txtTime"));

        dateFromPesanClass = getIntent().getStringExtra(DATE);
        timeFromPesanClass = getIntent().getStringExtra(TIME);
        priceFromPesanClass = getIntent().getStringExtra(PRIZE);
        nomorFromPesanClass = getIntent().getStringExtra(NOMOR);

        if (dateFromPesanClass == null && timeFromPesanClass == null) {
            throw new IllegalArgumentException("Must pass EXTRA_POST_KEY");
        }

        tanggal.setText(dateFromPesanClass);
        jam.setText(timeFromPesanClass);
        Harga.setText(priceFromPesanClass);
        Nomor.setText(nomorFromPesanClass);

        ImageView mPressAlert = findViewById(R.id.BRI);
        mPressAlert.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("RestrictedApi")
            @Override
            public void onClick(View v) {
                final android.support.v7.app.AlertDialog.Builder alertDialogBuilder =
                        new android.support.v7.app.AlertDialog.Builder(Hasilpesanmk.this);
                alertDialogBuilder.setTitle("BRI");
                alertDialogBuilder.setIcon(R.mipmap.bri_new);
                alertDialogBuilder.setCancelable(false);
                LinearLayout layout = new LinearLayout(Hasilpesanmk.this);
                layout.setOrientation(LinearLayout.VERTICAL);

                final TextView testEd = new TextView(Hasilpesanmk.this);

                final ImageView icon = new ImageView(Hasilpesanmk.this);
                final TextView norek = new TextView(Hasilpesanmk.this);
                final TextView narek = new TextView(Hasilpesanmk.this);
                final TextView kota = new TextView(Hasilpesanmk.this);

                norek.setText("Nomor Rekening : 928409284128442");
                narek.setText("Nama Rekening  : Totabuan Indah");
                kota.setText("Kotamobagu, Sulawesi Utara Indonesia");

                layout.addView(icon);
                layout.addView(norek);
                layout.addView(narek);
                layout.addView(kota);


                alertDialogBuilder.setView(layout, 40, 0, 40, 0);

                alertDialogBuilder.setNegativeButton("Apload Bukti Pembayaran", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(Hasilpesanmk.this,
                                "Gambar Tersimpan",
                                Toast.LENGTH_LONG).show();
                    }
                });

                // membuat alert dialog dari builder
                android.support.v7.app.AlertDialog alertDialog = alertDialogBuilder.create();

                // menampilkan alert dialog
                alertDialog.show();
            }
        });



    }



}

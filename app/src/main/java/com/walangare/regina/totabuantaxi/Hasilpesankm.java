package com.walangare.regina.totabuantaxi;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.walangare.regina.totabuantaxi.Api.Retroserver;

import java.io.FileNotFoundException;
import java.io.InputStream;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Hasilpesankm extends AppCompatActivity {

    private Button btInsert;
    ImageView imgHolder;
    final int REQUEST_GALLERY = 9544;

    TextView mNamaPenumpang, mNotelPenumpang, mTanggal, mWaktu, mNamaPengemudi, mKodeKendaraan, mJenisKendaraan, mNomor , mHarga;

    //"inisialisasi" menampilkan hasil dari textview layout sebelumnya
    public static final String DATE = "defaultDate";
    public static final String TIME = "DetaultTime";
    public static final String PRIZE = "DefaultPrize";
    public static final String NOMOR = "DefaultNomor";
    private String dateFromPesanClass, timeFromPesanClass, priceFromPesanClass, nomorFromPesanClass;

    Retroserver mClient = new Retroserver();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hasilpesankm);

        //membuat obyek dari widget textview
        mTanggal = (TextView)  findViewById(R.id.tv_tgl);
        mWaktu = (TextView) findViewById(R.id.tv_jam);
        mHarga = (TextView) findViewById(R.id.tv_harga);
        mNomor = (TextView) findViewById(R.id.tv_kursi);
        mNamaPengemudi = findViewById(R.id.tv_pengemudiKM);
        mKodeKendaraan = findViewById(R.id.tv_kodeKM);
        mJenisKendaraan = findViewById(R.id.tv_jeniskendraanKM);

        imgHolder = (ImageView) findViewById(R.id.imgHolder);
        btInsert = (Button) findViewById(R.id.btPesan);

                dateFromPesanClass = getIntent().getStringExtra(DATE);
                timeFromPesanClass = getIntent().getStringExtra(TIME);
                priceFromPesanClass = getIntent().getStringExtra(PRIZE);
                nomorFromPesanClass = getIntent().getStringExtra(NOMOR);

        if (dateFromPesanClass == null && timeFromPesanClass == null) {
            throw new IllegalArgumentException("Must pass EXTRA_POST_KEY");
        }

                mTanggal.setText(dateFromPesanClass);
                mWaktu.setText(timeFromPesanClass);
                mHarga.setText(priceFromPesanClass);
                mNomor.setText(nomorFromPesanClass);


       // progressDialog = new ProgressDialog(this);

        //progressDialog.setMessage("loading...");
       // progressDialog.setCancelable(false);
        //progressDialog.show();

        //menampilkan data dari database
        //ApiRequestKM api = Retroserver.getClient().create(ApiRequestKM.class );
        //Call<ResponsModelkm> getData = api.getKeberangkatankm();
       // getData.enqueue(new Callback<ResponsModelkm>() {
            //@Override
           // public void onResponse(Call<ResponsModelkm> call, Response<ResponsModelkm> response) {
               // progressDialog.hide();
               // Log.d("Retro","Response : " + response.body().toString());

               // kmList = response.body().getResult();

               // mAdapter = new AdapterdataKM(Hasilpesankm.this,kmList);
               // mRecycler.setAdapter(mAdapter);
               // mAdapter.notifyDataSetChanged();

            //}

            //@Override
           // public void onFailure(Call<ResponsModelkm> call, Throwable t) {
      //          progressDialog.hide();
         //   }
       // });



        ImageView mPressAlert = findViewById(R.id.BRI);
        mPressAlert.setOnClickListener(new View.OnClickListener() {
           @SuppressLint("RestrictedApi")
            @Override
            public void onClick(View v) {
               final AlertDialog.Builder alertDialogBuilder =
                       new AlertDialog.Builder(Hasilpesankm.this);
               alertDialogBuilder.setTitle("BRI");
               alertDialogBuilder.setIcon(R.mipmap.bri_new);
               alertDialogBuilder.setCancelable(false);
               LinearLayout layout = new LinearLayout(Hasilpesankm.this);
               layout.setOrientation(LinearLayout.VERTICAL);

               final TextView testEd = new TextView(Hasilpesankm.this);

               final ImageView icon = new ImageView(Hasilpesankm.this);
               final TextView norek = new TextView(Hasilpesankm.this);
               final TextView narek = new TextView(Hasilpesankm.this);
               final TextView kota = new TextView(Hasilpesankm.this);

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
                       Intent intent = new Intent();
                        intent.setType("image/*");
                        intent.setAction(Intent.ACTION_GET_CONTENT);
                        startActivityForResult(Intent.createChooser(intent, "Open Galeri"), REQUEST_GALLERY);

                   }
               });

               // membuat alert dialog dari builder
               AlertDialog alertDialog = alertDialogBuilder.create();

               // menampilkan alert dialog
               alertDialog.show();


           }
        });

        btInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                storeData();

            }
        });


        //menyimpan data


        getData();
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);

//        if (resultCode == RESULT_OK)
//        {
//            if(requestCode ==  REQUEST_GALLERY)
//            {
//                Uri dataimage = data.getData();
//                String[] imageprojection = {MediaStore.Images.Media.DATA};
//                Cursor cursor = getContentResolver().query(dataimage,imageprojection,null,null,null);
//
//                if (cursor != null)
//                {
//                    cursor.moveToFirst();
//                    int indexImage = cursor.getColumnIndex(imageprojection[0]);
//                    part_image = cursor.getString(indexImage);
//                    if (part_image != null)
//                    {
//                        File image = new File(part_image);
//                        imgHolder.setImageBitmap(BitmapFactory.decodeFile(image.getAbsolutePath()));
//                        Log.d("testData", image.getName());
//                    } else  {
//
//                        Log.e("thisIsError", "thisIs error message");
//                    }
//
//                    File image = new File(part_image);
//                    imgHolder.setImageBitmap(BitmapFactory.decodeFile(image.getAbsolutePath()));
//                    Log.d("testData", image.getName());
//
//                }
//
//            }
//
//        }

        if (resultCode == RESULT_OK) {
            try {
                final Uri imageUri = data.getData();
                final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                imgHolder.setImageBitmap(selectedImage);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                Toast.makeText(Hasilpesankm.this, "Something went wrong", Toast.LENGTH_LONG).show();
            }

        }else {
            Toast.makeText(Hasilpesankm.this, "You haven't picked Image",Toast.LENGTH_LONG).show();
        }
    }


    private void storeData() {
        String sTanggal = mTanggal.getText().toString();
        String sJam = mWaktu.getText().toString();
        String sKodeKend = mKodeKendaraan.getText().toString();
        String sJenisKend = mJenisKendaraan.getText().toString();
        String sNamaSupir = mNamaPengemudi.getText().toString();
        String sKuris = mNomor.getText().toString();
        String sHarga = mHarga.getText().toString();

        Log.d("thisIsData", sTanggal + sJam  + sKodeKend + sJenisKend + sNamaSupir + sKuris + sHarga);
        //mClient.ApiService().saveData()
        mClient.ApiService().saveData(sTanggal, sJam, sKuris, sHarga,
                sNamaSupir, sKodeKend, sJenisKend)
                .enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(Hasilpesankm.this, "Pesan berhasil", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    private void getData() {
        mClient.ApiService().getDetail(mWaktu.getText().toString()+":00", mTanggal.getText().toString())
                .enqueue(new Callback<JsonObject>() {
                    @Override
                    public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                        if (response.isSuccessful()) {

                            JsonObject mData = response.body();
                            JsonObject responseData = mData.getAsJsonObject("responsedata");
                            String name = responseData.get("nama_pengemudi").toString();
                            String kode = responseData.get("kode_kendaraan").toString();
                            String jenis = responseData.get("jenis_kendaraan").toString();

                            mNamaPengemudi.setText(name);
                            mJenisKendaraan.setText(jenis);
                            mKodeKendaraan.setText(kode);

                        } else {
                            ResponseBody mData = response.errorBody();
                            Log.d("thisErrorBody", mData.toString());
                        }
                    }

                    @Override
                    public void onFailure(Call<JsonObject> call, Throwable t) {
                        t.printStackTrace();
                    }
                });
    }
}






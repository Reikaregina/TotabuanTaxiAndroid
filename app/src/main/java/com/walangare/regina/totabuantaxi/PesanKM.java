package com.walangare.regina.totabuantaxi;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

public class PesanKM extends AppCompatActivity implements View.OnClickListener {

    private ImageView Aa1, steering, Bb1, Bb2, Bb3, Cc1, Cc2, Cc3;
    private Button btnOrder;

    private ArrayList<String> choosenSeats;


    private boolean a1, b1, b2, b3, c1, c2, c3;

    TextView btnDatePicker, btnTimePicker;
    TextView txtDate, txtTime;
    private int mYear, mMonth, mDay, mHour, mMinute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesan_km);

        btnDatePicker = (TextView) findViewById(R.id.tv_dateresult);
        btnTimePicker = (TextView) findViewById(R.id.tv_Timeresult);
        txtDate = (TextView) findViewById(R.id.tv_dateresult);
        txtTime = (TextView) findViewById(R.id.tv_Timeresult);

        btnDatePicker.setOnClickListener(this);
        btnTimePicker.setOnClickListener(this);


        //code for pilih kursi

        Aa1 = findViewById(R.id.A1);
        Bb1 = findViewById(R.id.B1);
        Bb2 = findViewById(R.id.B2);
        Bb3 = findViewById(R.id.B3);
        Cc1 = findViewById(R.id.C1);
        Cc2 = findViewById(R.id.C2);
        Cc3 = findViewById(R.id.C3);
        steering = findViewById(R.id.steering);

        btnOrder = findViewById(R.id.btPesan);


        Aa1.setOnClickListener(this);
        Bb1.setOnClickListener(this);
        Bb2.setOnClickListener(this);
        Bb3.setOnClickListener(this);
        Cc1.setOnClickListener(this);
        Cc2.setOnClickListener(this);
        Cc3.setOnClickListener(this);

        btnOrder.setOnClickListener(this);

        choosenSeats = new ArrayList<>();


    }

    @Override
    public void onClick(View v) {

        if (v == btnDatePicker) {

            // Get Current Date
            final Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);


            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {

                            txtDate.setText(year + "-" + "0"+(monthOfYear + 1) + "-" + dayOfMonth);

                        }
                    }, mYear, mMonth, mDay);
            datePickerDialog.show();
        }
        if (v == btnTimePicker) {

            // Get Current Time

            final Calendar c = Calendar.getInstance();
            mHour = c.get(Calendar.HOUR_OF_DAY);
            mMinute = c.get(Calendar.MINUTE);

            // Launch Time Picker Di
            // alog
            TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                    new TimePickerDialog.OnTimeSetListener() {

                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay,
                                              int minute) {

                            txtTime.setText(String.format("%02d:%02d", hourOfDay, minute));
                        }
                    }, mHour, mMinute, false);
            timePickerDialog.show();
        }

        switch (v.getId()) {
            case R.id.A1:
                isChoosed(Aa1, "a1", a1);
                a1 = !a1;
                break;
            case R.id.B1:
                isChoosed(Bb1, "b1", b1);
                b1 = !b1;
                break;
            case R.id.B2:
                isChoosed(Bb2, "b2", b2);
                b2 = !b2;
                break;
            case R.id.B3:
                isChoosed(Bb3, "b3", b3);
                b3 = !b3;
                break;
            case R.id.C1:
                isChoosed(Cc1, "c1", c1);
                c1 = !c1;
                break;
            case R.id.C2:
                isChoosed(Cc2, "c2", c2);
                c2 = !c2;
                break;
            case R.id.C3:
                isChoosed(Cc3, "c3", c3);
                c3 = !c3;
                break;
            case R.id.steering:
                reset();
                break;
            case R.id.btPesan:
                countPrize();
                numbOfSeat();

                // Intent intent = new Intent(PesanKM.this,Hasilpesankm.class);

                // Bundle b = new Bundle();

                // b.putString("txtDate",txtDate.getText().toString());
                // b.putString("txtTime", txtTime.getText().toString());

                // intent.putExtras(b);
                // startActivity(intent);

                String thisDate = txtDate.getText().toString();
                String thisTime = txtTime.getText().toString();

                if (thisDate.isEmpty()) Toast.makeText(PesanKM.this,
                        "Tanggal keberangkatan tidak boleh kosong",
                        Toast.LENGTH_SHORT).show();

                if(thisTime.isEmpty()) Toast.makeText(PesanKM.this,
                        "Waktu Keberangkatan tidak boleh kosong",
                        Toast.LENGTH_SHORT).show();

                if (!thisDate.isEmpty() && !thisTime.isEmpty() && !numbOfSeat().equals("seat id not selected")) {
                    Intent intent = new Intent(PesanKM.this, Hasilpesankm.class);
                    intent.putExtra(Hasilpesankm.DATE, txtDate.getText().toString());
                    intent.putExtra(Hasilpesankm.TIME, txtTime.getText().toString());
                    intent.putExtra(Hasilpesankm.PRIZE, String.valueOf(countPrize()));
                    intent.putExtra(Hasilpesankm.NOMOR, String.valueOf(numbOfSeat()));
                    //intent.putExtra(Hasilpesankm.KURSI, String.valueOf()
                    startActivity(intent);
                }

                break;

        }


    }



    private void reset() {

    }

    private String numbOfSeat() {
        String seatNumb = "";
        for (String seat : choosenSeats) {
            if (seat.equals("a1")) {
                seatNumb += "A1";
            } else if (seat.equals("b1")){
                seatNumb += "B1";
            } else if (seat.equals("b2")){
                seatNumb += "B2";
            } else if (seat.equals("b3")){
                seatNumb += "B3";
            } else if (seat.equals("c1")){
                seatNumb += "C1";
            } else if (seat.equals("c2")){
                seatNumb += "C2";
            } else if (seat.equals("c3")){
                seatNumb += "C3";
            }

        }
        if (seatNumb != "") {
            return seatNumb;
        } else {
            Toast.makeText(PesanKM.this,
                    "Mohon pilih kursi yang tersedia",
                    Toast.LENGTH_SHORT).show();
            return( "seat id not selected");
        }

    }

    //p//rivate String NomorKursi() {
       // String nomor = "";
       // for (String seat : choosenSeats) {
           // String s = seat.substring(0, 1);
           // nomor += s.equalsIgnoreCase("a1");
           // {
             // return String.valueOf(nomor);
           // }
           // nomor += s.equalsIgnoreCase("b1");
            //nomor += seat.equalsIgnoreCase("b2");
           // nomor += seat.equalsIgnoreCase("b3");
          //  nomor += seat.equalsIgnoreCase("c1");
           // nomor += seat.equalsIgnoreCase("c2");
          //  nomor += seat.equalsIgnoreCase("c3");
       // }
       // return String.valueOf(nomor);
  //  }


    private String countPrize() {
        int prize = 0;
        for (String seat : choosenSeats) {
            String s = seat.substring(0, 1);
            if (s.equalsIgnoreCase("a")) {
                prize += 100000;
            } else if (s.equalsIgnoreCase("b")) {
                prize += 90000;
            } else {
                prize += 80000;
            }
        }
        if (choosenSeats.size() > 0) {
            return String.valueOf(prize);// + String.valueOf(choosenSeats);
        } else {
            return "Anda Belum Memilih Kursi";
        }
    }


    private void isChoosed(ImageView iv, String choosenCode, boolean isChecked) {
        if (isChecked) {
            choosenSeats.remove(choosenCode);
            iv.setImageResource(R.drawable.seat_normal);
        } else {
            choosenSeats.add(choosenCode);
            iv.setImageResource(R.drawable.seat_normal_booking);
        }
    }

    // public void showToast(String message){
    //  Toast.makeText(getApplicationContext(),message,Toast.LENGTH_SHORT).show();
    //}

}

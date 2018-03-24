package com.walangare.regina.totabuantaxi.Api;

import com.google.gson.JsonObject;
import com.walangare.regina.totabuantaxi.Model.ResponsModelkm;

import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

/**
 * Created by REGINA on 3/4/2018.
 */

public interface ApiRequestKM {


    @Multipart
    @POST("aploadimage.php")
    Call<ResponsModelkm> aploadImage (@Part MultipartBody.Part image);

    @GET("data/detail")
    Call<JsonObject> getDetail(@Query("jam") String mJam,
                               @Query("tgl") String mTgl);

    @FormUrlEncoded
    @POST("data/store")
    Call<ResponseBody> saveData(@Field("tgl") String mTgl,
                                @Field("jam") String mJam,
                                @Field("kursi") String mKursi,
                                @Field("harga") String mHarga,
                                @Field("supir") String mSupir,
                                @Field("platno") String mPlat,
                                @Field("jenisoto") String mJenisKendaraan);
   // @FormUrlEncoded
  //  @POST("PostAndroid.php")
   // Call<ResponsModelkm> sendPenumpangKM(@Field("nama_penumpang") String nama_penumpang,
                                        // @Field("jml_bayar") String jml_bayar,
                                       //  @Field("tgl_keberangkatan") String tgl_keberangkatan,
                                       //  @Field("jam_keberangkatan") String jam_keberangkatan
   // );

   // @GET("Api_Android.php")
   // Call<ResponsModelkm> getKeberangkatankm();
}


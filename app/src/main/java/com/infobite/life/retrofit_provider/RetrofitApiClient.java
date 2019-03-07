package com.infobite.life.retrofit_provider;

import com.infobite.life.constant.Constant;
import com.infobite.life.modal.main_categry_products.CategeryMainModal;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface RetrofitApiClient {

    @GET(Constant.PRODUCTS_API)
    Call<CategeryMainModal> productData();

    @FormUrlEncoded
    @POST(Constant.SIGNUP_API)
    Call<ResponseBody> signUp(@Field("fullname") String fullname, @Field("password") String password, @Field("email") String email);

    @FormUrlEncoded
    @POST(Constant.LOGIN_API)
    Call<ResponseBody> signIn(@Field("email") String email, @Field("password") String password);

}
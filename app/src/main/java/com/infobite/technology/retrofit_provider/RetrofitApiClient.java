package com.infobite.technology.retrofit_provider;

import com.infobite.technology.constant.Constant;
import com.infobite.technology.modal.main_categry_products.CategeryMainModal;

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
    @POST(Constant.LOGIN_API)
    Call<ResponseBody> signIn(@Field("email") String email, @Field("password") String password);

}
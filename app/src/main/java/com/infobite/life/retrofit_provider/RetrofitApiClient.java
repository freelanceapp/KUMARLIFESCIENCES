package com.infobite.life.retrofit_provider;

import com.infobite.life.constant.Constant;
import com.infobite.life.modal.banner_modal.BannerMainModal;
import com.infobite.life.modal.gallery_modal.GalleryMainModal;
import com.infobite.life.modal.main_categry_products.CategeryMainModal;
import com.infobite.life.modal.order_history_modal.OrderHistoryMainModal;
import com.infobite.life.modal.products_modal.ProductsMainModal;
import com.infobite.life.modal.search_model.SearchModel;
import com.infobite.life.modal.subcategory_modal.SubcategoryMainModal;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface RetrofitApiClient {

    @GET(Constant.PRODUCTS_API)
    Call<ProductsMainModal> productData();

    @GET(Constant.SEARCH_API)
    Call<SearchModel> searchData();

    @GET(Constant.SUBCATEGORY_API)
    Call<SubcategoryMainModal> subCategoryData(@Query("category_id") String categoryId);

    @GET(Constant.ORDER_HOSTORY_API)
    Call<OrderHistoryMainModal> orderHistoryData(@Query("user_id") String userId);

    @GET(Constant.GALLERY_API)
    Call<GalleryMainModal> galleryData();

    @GET(Constant.BANNER_API)
    Call<BannerMainModal> bannerData();

    @FormUrlEncoded
    @POST(Constant.SIGNUP_API)
    Call<ResponseBody> signUp(@Field("fullname") String fullname, @Field("password") String password, @Field("email") String email);

    @FormUrlEncoded
    @POST(Constant.LOGIN_API)
    Call<ResponseBody> loginData(@Field("email") String email, @Field("password") String password);


    @FormUrlEncoded
    @POST(Constant.CONTACT_US)
    Call<ResponseBody> contactUs(@Field("name") String name, @Field("email") String email,
                                 @Field("mobile_no") String mobile_no, @Field("subject") String subject
                                 ,@Field("message") String message);



    @FormUrlEncoded
    @POST(Constant.ORDER_API)
    Call<ResponseBody> order(@Field("first_name") String first_name,
                             @Field("user_id") String user_id,
                             @Field("company_name") String company_name,
                             @Field("user_email") String user_email,
                             @Field("address") String address,
                             @Field("phone_number") String phone_number,
                             @Field("state") String state,
                             @Field("city") String city,
                             @Field("zip_code") String zip_code,
                             @Field("product_id") String product_id,
                             @Field("product_name") String product_name,
                             @Field("product_category") String product_category,
                             @Field("product_sub_category") String product_sub_category,
                             @Field("product_image") String product_image,
                             @Field("product_price") String product_price,
                             @Field("product_qty") String product_qty,
                             @Field("total_price") String total_price);


}
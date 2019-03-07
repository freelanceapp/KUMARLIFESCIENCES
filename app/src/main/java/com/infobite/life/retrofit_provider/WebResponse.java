package com.infobite.life.retrofit_provider;

import retrofit2.Response;


public interface WebResponse {

    void onResponseSuccess(Response<?> result);

    void onResponseFailed(String error);
}
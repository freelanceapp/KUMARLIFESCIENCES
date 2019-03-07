package com.infobite.technology.retrofit_provider;

import retrofit2.Response;


public interface WebResponse {

    void onResponseSuccess(Response<?> result);

    void onResponseFailed(String error);
}
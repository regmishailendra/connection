package com.example.shailendraconnection;

import com.google.gson.JsonObject;

import org.json.JSONObject;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;

public interface Apiinterface {


    public interface GoogleSignIn{

        @GET("gmail/")
        Call<ResponseBody> signInToGoogle();
    }
}

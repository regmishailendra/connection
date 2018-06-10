package com.example.shailendraconnection;

import android.accounts.NetworkErrorException;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public class Apicall<ClientClass> {
    Class<ClientClass> clientClass;
    ClientClass client;

    public Apicall(Class<ClientClass> clientClass) {
        this.clientClass = clientClass;
        Retrofit retrofit = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl("https://www.google.com/").build();
        client = (ClientClass) retrofit.create(clientClass);
    }
    public ClientClass operate() {
        return client;
    }

    public void getResponse(final Context context, Call<ResponseBody> caller, final responseListener listener) {
        caller.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    try {
                        listener.onSuccessResponse(response.body().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                        Log.e("connectionerrormessage", "exception-"+e.getLocalizedMessage());
                        Log.v("connectionerrormessage", "exception-in-success"+e.getLocalizedMessage());

                    }
                }
                    else {
                    try {
                        Log.e("connectionerrormessage", "connection is not successful-" + response.errorBody().string());
                        Log.v("connectionerrormessage", "connection is not successful-" + response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                        Log.e("connectionerrormessage", "exception-in-failure" + e.getLocalizedMessage());
                        } } }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

                if (t.getCause() instanceof SocketTimeoutException) {
                    Toast.makeText(context, "Connection timed out", Toast.LENGTH_SHORT).show();
                }
//make a utils for connection
                else {
                    Toast.makeText(context, "Some error", Toast.LENGTH_SHORT).show(); }
                Log.v("connectionerrormessage", "connection error" + t.getLocalizedMessage()); }}); }

    public interface responseListener {
        public void onSuccessResponse(String successData);
    }
}







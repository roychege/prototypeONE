package com.example.prototypeone;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.net.HttpRetryException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

//Class created so it'll be easier for using POST and GET requests. Maybe removed at later date.

public class HttpRequest {
    public String responseString;
    private OkHttpClient client = new OkHttpClient();
    public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");
    HttpRequest(){
        System.out.println("Http Request Class Created");
    }

    public String GET(String url) throws IOException{

        Request request = new Request.Builder()
                .url(url)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                responseString = response.body().string();
                System.out.println("Runing");
            }
        });
        return responseString;
    }
    public String POST(String url, String json) throws IOException{
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        try(Response response = client.newCall(request).execute()){
            return request.body().toString();
        }
    }




}

package com.crud.hotels.service;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class RequestsExecutorService {
    OkHttpClient client = new OkHttpClient();

    public String execute(Request request) throws IOException {
        return client.newCall(request).execute().body().string();
    }

}

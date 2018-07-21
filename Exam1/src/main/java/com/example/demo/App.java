package com.example.demo;

import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Hello world!
 */
public class App {

    private static final OkHttpClient CLIENT = new OkHttpClient();
    private static final String URL = "http://192.168.11.205:18080/trainning/SampleChapter1.pdf";

    public static void main(String[] args) {
        Request request = new Request.Builder().url(URL).build();

    }
}

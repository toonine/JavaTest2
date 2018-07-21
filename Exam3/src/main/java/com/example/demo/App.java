package com.example.demo;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.ResponseBody;

import java.io.*;
import java.util.Objects;

/**
 * Hello world!
 */
public class App {
    private static final String url_prefix = "http://hq.sinajs.cn/list=";
    private static final OkHttpClient CLIENT = new OkHttpClient();

    public static void main(String[] args) throws Exception {
        String code = args[0];
        System.out.println("股票编码: " + code);
        String url = url_prefix + code;
        System.out.println("开始获取数据。。。。。。 ");
        Request request = new Request.Builder().url(url).build();
        ResponseBody body = CLIENT.newCall(request).execute().body();
        System.out.println("获取数据成功！");
        String dataText = Objects.requireNonNull(body).string();
        new Thread(() -> {
            try {
                String xml = Data.toXML(dataText);
                transferTo(xml, "tmp/股票编码.xml");
                System.out.println("解析为xml成功！");
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("解析为xml失败！");
            }

        }).start();

        new Thread(() -> {
            try {
                String json = Data.toJson(dataText);
                transferTo(json, "tmp/股票编码.json");
                System.out.println("解析为json成功！");
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("解析为json失败！");
            }
        }).start();
    }

    private static void transferTo(String string, String filename) throws IOException {
        File file = new File(filename);
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdir();
        }
        try (BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(
                        new FileOutputStream(file), "UTF-8"))) {
            writer.write(string);
        }
    }
}

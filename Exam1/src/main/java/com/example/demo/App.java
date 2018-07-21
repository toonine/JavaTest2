package com.example.demo;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.*;
import java.util.Objects;

/**
 * Hello world!
 */
public class App {

    private static final OkHttpClient CLIENT = new OkHttpClient();
    private static final String URL = "http://192.168.11.205:18080/trainning/SampleChapter1.pdf";

    public static void main(String[] args) {
        Request request = new Request.Builder().url(URL).build();
        try {
            Response response = CLIENT.newCall(request).execute();
            File file = new File("tmp/SampleChapter1.pdf");
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdir();
            }
            try (BufferedInputStream in = new BufferedInputStream(Objects.requireNonNull(response.body()).byteStream());
                 BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(file))) {
                int len;
                byte[] buff = new byte[2048];
                while ((len = in.read(buff)) != -1) {
                    out.write(buff, 0, len);
                }
            } catch (IOException e) {
                throw new IOException(e);
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("文件下载失败");
            return;
        }
        System.out.println("文件下载完成");
    }
}

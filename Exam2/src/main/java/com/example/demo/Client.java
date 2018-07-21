package com.example.demo;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
    public static void main(String[] args) {
        File file = new File("tmp/SampleChapter1.pdf");
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdir();
        }
        try (Socket socket = new Socket("127.0.0.1", 5342);
             InputStream in = socket.getInputStream();
             OutputStream out = new FileOutputStream(file)
        ) {
            int len;
            byte[] buff = new byte[2048];
            while ((len = in.read(buff)) != -1) {
                out.write(buff, 0, len);
            }
            System.out.println("文件传输结束");
            System.out.println("客户端断开连接");
            ;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("客户端异常退出");
        }
    }
}

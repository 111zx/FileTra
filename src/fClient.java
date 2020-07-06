
import java.io.*;

import java.net.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

import java.util.concurrent.ExecutorService;

import java.util.concurrent.Executors;

import java.util.concurrent.ThreadPoolExecutor;


public class fClient {
    /**
     * 实现接收从服务器下载文件的功能
     *
     * @param filepath 下载文件的默认存放绝对路径
     * @throws UnknownHostException
     * @throws IOException
     */
    public void receive(String filepath) {
        Socket s = null;
        DataInputStream dis = null;
        DataOutputStream fos = null;
        try {

            //建立连接
            s = new Socket("127.0.0.1", 6788);

            //创建数据输入输入流
            dis = new DataInputStream(s.getInputStream());
            fos = new DataOutputStream(new FileOutputStream(filepath));
            byte[] b = new byte[1024 * 9];
            int len = 0;

            //接收服务器发来的文件（下载功能）
            while ((len = dis.read(b)) != -1) {
                fos.write(b, 0, len);
            }
            System.out.println("下载完成");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (dis != null) {
                try {
                    dis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (s != null) {
                    try {
                        s.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }
        }
    }
}
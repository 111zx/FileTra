
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

	public  void receive(String filepath) throws UnknownHostException, IOException {
		
		//建立连接
		Socket s = new Socket("127.0.0.1",6788);
//		//获取socket的输入流
//		InputStream is = s.getInputStream();
//		DataInputStream dis = new DataInputStream(is);
//
//		//准备file对象接受socket中的数据
//		File f = new File(filepath);
//		FileOutputStream fos = new FileOutputStream(f);
		DataInputStream dis = new DataInputStream(s.getInputStream());
		DataOutputStream fos = new DataOutputStream(new FileOutputStream(filepath));
	
		byte[] b = new byte[1024*9];
		int len = 0;
		while((len = dis.read(b)) != -1){
			System.out.println("aaa");
			fos.write(b, 0, len);
		}
		System.out.println("下载完成");
		fos.close();
		dis.close();
		s.close();
	}
	 public static void main(String[] args) throws IOException {
	        new fClient().receive("D:\\lok.mp4");
	    }
}

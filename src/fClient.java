
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
		
		//��������
		Socket s = new Socket("127.0.0.1",6788);
		//��ȡsocket��������
		InputStream is = s.getInputStream();
		DataInputStream dis = new DataInputStream(is);
		
		//׼��file�������socket�е�����
		File f = new File(filepath);
		FileOutputStream fos = new FileOutputStream(f);
	
		byte[] b = new byte[1027*9];
		int len = 0;
		while((len = dis.read(b)) != -1){
			fos.write(b, 0, len);
		}
		System.out.println("�������");
		fos.close();
		dis.close();
		s.close();
	}
	 public static void main(String[] args) throws IOException {
	        new fClient().receive("D:\\lok.mp4");
	    }
}

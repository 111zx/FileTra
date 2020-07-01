
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





public class FileServer2 extends Thread{

	private static Socket s;
	private static ServerSocket serverSocket;
	private File source;
	public FileServer2(Socket s, File source) {
		super();
		this.s = s;
		this.source = source;
	}

	@Override
	public void run() {
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		try {
			//��ȡԴ�ļ���������
			bis = new BufferedInputStream(new FileInputStream(source));
			//��ȡsocket�����������װ
			bos = new BufferedOutputStream(s.getOutputStream());

			byte[] b = new byte[1024];
			int len = 0;
			System.out.println("�� "+s.getInetAddress().getHostAddress()+"��ʼ����....");
			while((len = bis.read(b)) != -1){
				bos.write(b, 0, len);
			}
			System.out.println("�� "+s.getInetAddress().getHostAddress()+"�������!");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				if(bos != null)bos.close();
				if(bis != null)bis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	private static void receieveFile(String filePath) {
		while (true) {
			try {
				Socket socket = null;

				socket = serverSocket.accept();

				DataInputStream dis = new DataInputStream(socket.getInputStream());
				DataOutputStream dos = new DataOutputStream(new FileOutputStream(filePath));

				byte[] buf = new byte[1027 * 9];
				int len = 0;

				while ((len = dis.read(buf)) != -1) {
					dos.write(buf, 0, len);
				}
				dos.flush();
				dis.close();
				dos.close();
				System.out.println("�������");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	public  static void main(String[] args) throws IOException {
		ServerSocket server = new ServerSocket(6788);
		System.out.println("SOFEEM�ļ�������������,�ȴ�����...");
		//׼����Ҫ������ļ�����
		String filepath = null;
		//ѭ������
		new FileTransferServer().start();
		while(true){
			File source = new File("D:\\Ĭ��.txt");
			Socket s = server.accept();
			System.out.println(s.getInetAddress().getHostAddress()+"���������,׼������...");
			//����ÿһ�����ӵĿͻ�������һ�����߳�
			new FileServer2(s, source).start();
		}
	}

}

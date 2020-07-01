
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
			//获取源文件的输入流
			bis = new BufferedInputStream(new FileInputStream(source));
			//获取socket的输出流并包装
			bos = new BufferedOutputStream(s.getOutputStream());

			byte[] b = new byte[1024];
			int len = 0;
			System.out.println("向 "+s.getInetAddress().getHostAddress()+"开始传输....");
			while((len = bis.read(b)) != -1){
				bos.write(b, 0, len);
			}
			System.out.println("向 "+s.getInetAddress().getHostAddress()+"传输完成!");
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
				System.out.println("传输完成");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	public  static void main(String[] args) throws IOException {
		ServerSocket server = new ServerSocket(6788);
		System.out.println("SOFEEM文件服务器已启动,等待连接...");
		//准备需要传输的文件对象
		String filepath = null;
		//循环监听
		new FileTransferServer().start();
		while(true){
			File source = new File("D:\\默认.txt");
			Socket s = server.accept();
			System.out.println(s.getInetAddress().getHostAddress()+"进入服务器,准备传输...");
			//根据每一个连接的客户端启动一条子线程
			new FileServer2(s, source).start();
		}
	}

}

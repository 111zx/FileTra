
import javax.xml.crypto.Data;
import java.io.*;

import java.net.*;


public class FileServer extends Thread{

	private static Socket s;
	private static ServerSocket serverSocket;
	private File source = new File("C:\\Users\\14914\\Desktop\\test\\moren55.txt");
	public FileServer(Socket s, File source) {
		super();
		this.s = s;
		this.source = source;
	}

	@Override
	public void run() {
		DataInputStream bis = null;
		DataOutputStream bos = null;

			source = new File(new FileTree().serverfilename);
			try {
				//��ȡԴ�ļ���������
				System.out.println(source.getAbsolutePath());
				bis = new DataInputStream(new FileInputStream(source));
				//��ȡsocket�����������װ
				bos = new DataOutputStream(s.getOutputStream());

				byte[] b = new byte[1024 * 9];
				int len = 0;
				System.out.println("�� " + s.getInetAddress().getHostAddress() + "��ʼ����....");
				while ((len = bis.read(b)) != -1) {
					bos.write(b, 0, len);
				}
				System.out.println("�� " + s.getInetAddress().getHostAddress() + "�������!");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					if (bos != null) bos.close();
					if (bis != null) bis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
	}
//	private static void receieveFile(String filePath) {
//		while (true) {
//			try {
//				Socket socket = null;
//
//				socket = serverSocket.accept();
//
//				DataInputStream dis = new DataInputStream(socket.getInputStream());
//				DataOutputStream dos = new DataOutputStream(new FileOutputStream(filePath));
//
//				byte[] buf = new byte[1027 * 9];
//				int len = 0;
//
//				while ((len = dis.read(buf)) != -1) {
//					dos.write(buf, 0, len);
//				}
//				dos.flush();
//				dis.close();
//				dos.close();
//				System.out.println("�������");
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//
//		}
//
//	}

	public  static void main(String[] args) throws IOException {
		ServerSocket server = new ServerSocket(6788);
		System.out.println("SOFEEM�ļ�������������,�ȴ�����...");
		//ѭ������
		new FileTransferServer().start();
		while(true){
			File source = new File(new FileTree().serverfilename);
			Socket s = server.accept();
			System.out.println(s.getInetAddress().getHostAddress()+"���������,׼������...");
			//����ÿһ�����ӵĿͻ�������һ�����߳�
			new FileServer(s, source).start();
		}
	}

}

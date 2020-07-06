
import javax.xml.crypto.Data;
import java.io.*;

import java.net.*;


public class FileServer extends Thread{

	private static Socket s;
	private static ServerSocket serverSocket;
	private File source = new File("C:\\Users\\14914\\Desktop\\test\\moren55.txt");//�������ʾ�ļ��Ĵ��λ��
	static ServerSocket server;
	//6788�˿ڸ�����ͻ��˷����ļ�
	static {
		try {
			server = new ServerSocket(6788);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public FileServer(Socket s, File source) throws IOException {
		super();
		this.s = s;
		this.source = source;
	}

	/**
	 * ��ȡ��Ҫ�ͻ������ص�Ŀ���ļ����ļ���
	 * @param s
	 * @return
	 * @throws IOException
	 */
	public static String getfilepath(Socket s) throws IOException {
		String name = null;
		InputStream is = null;
		try {
			//��ȡ�ļ���
			is = s.getInputStream();
			int len1;
			byte[] bs = new byte[1024];
			len1 = is.read(bs);
			name = new String(bs, 0, len1);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return name;
	}

	@Override
	public void run() {
		DataInputStream bis = null;
		DataOutputStream bos = null;
			try {
//				//��ȡԴ�ļ���������
				System.out.println(source.getAbsolutePath());
				bis = new DataInputStream(new FileInputStream(source));
				//��ȡsocket�����������װ
				Socket s = server.accept();
				bos = new DataOutputStream(s.getOutputStream());
				byte[] b = new byte[1027 * 9];
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
					if (bos != null) bos.flush();bos.close();
					if (bis != null) bis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
	}

	public  static void main(String[] args) throws IOException {
		System.out.println("SOFEEM�ļ�������������,�ȴ�����...");
		//ѭ�������Ƿ��пͻ����ϴ����������ļ�
		new FileTransferServer().start();
		while(true){
			File source = new File(new FileTree().serverfilename);
			Socket s = server.accept();
			System.out.println(s.getInetAddress().getHostAddress()+"���������,׼������...");
			source = new File(getfilepath(s));
			//����ÿһ�����ӵĿͻ�������һ�����߳�
			new FileServer(s, source).start();
		}
	}

}

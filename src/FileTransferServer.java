import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * ���ܿͻ��˷������ļ���Ȼ�󱣴��ڱ���
 * 
 */
public class FileTransferServer extends Thread{

    private int port = 6787;
    private static ServerSocket serverSocket;
    private static String fileName="C:\\Users\\14914\\Desktop\\test";
    private static String fname="Ĭ��.txt";

    public FileTransferServer() throws IOException {
        serverSocket = new ServerSocket(port);
    }

	public void run()
	{
		while (true) {
            try {
                Socket socket = null;

                socket = serverSocket.accept();
                //�ȶ��ļ���
                InputStream is = socket.getInputStream();
                int len1;
                byte[] bs = new byte[1024];
                //�ȶ��ļ���
                len1 = is.read(bs);
                String name = new String(bs, 0, len1);
                System.out.println(name+"---");
                DataInputStream dis = new DataInputStream(socket.getInputStream());
                DataOutputStream dos = new DataOutputStream(new FileOutputStream(fileName+"\\"+name));
                byte[] buf = new byte[1024 * 9];
                int len = 0;

                while ((len = dis.read(buf)) != -1) {
                    dos.write(buf, 0, len);
                }
                dos.flush();
                dis.close();
                dos.close();
                System.out.println("�������");
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
	}

    public static void main(String[] args) throws IOException {
        new FileTransferServer().start();
    }


}


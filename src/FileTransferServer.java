import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * ���ܿͻ��˷������ļ���Ȼ�󱣴��ڱ���
 * 
 */
public class FileTransferServer extends Thread{

    private int port = 6787;
    private static ServerSocket serverSocket;
    private static String fileName="D:\\Ĭ��.txt";

    public FileTransferServer() throws IOException {
        serverSocket = new ServerSocket(port);
    }

	public void run()
	{
		while (true) {
            try {
                Socket socket = null;

                socket = serverSocket.accept();

                DataInputStream dis = new DataInputStream(socket.getInputStream());
                DataOutputStream dos = new DataOutputStream(new FileOutputStream(fileName));

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
                e.printStackTrace();
            }

        }
	}

    public static void main(String[] args) throws IOException {
        new FileTransferServer().start();
    }


}


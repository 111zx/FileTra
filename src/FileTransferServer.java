import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * ���ܿͻ��˷������ļ���Ȼ�󱣴��ڱ���
 */
public class FileTransferServer extends Thread {

    private int port = 6787;
    private static ServerSocket serverSocket;
    private static String fileName = "C:\\Users\\14914\\Desktop\\test";//���������ϴ������ļ�Ĭ��Ŀ¼

    public FileTransferServer() throws IOException {
        serverSocket = new ServerSocket(port);
    }

    public void run() {
        while (true) {
            try {
                Socket socket = null;
                socket = serverSocket.accept();
                //��ȡ�ļ�����������
                InputStream is = socket.getInputStream();
                int len1;
                byte[] bs = new byte[1024];
                //��ȡ�ļ���
                len1 = is.read(bs);
                String name = new String(bs, 0, len1);
                System.out.println(name + "---");

                //��ȡ�ļ����ݵ����������
                DataInputStream dis = new DataInputStream(socket.getInputStream());
                DataOutputStream dos = new DataOutputStream(new FileOutputStream(fileName + "\\" + name));
                byte[] buf = new byte[1024 * 9];
                int len = 0;
                //��ȡ�ļ�
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

}


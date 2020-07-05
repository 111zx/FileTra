import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 接受客户端发来的文件，然后保存在本地
 * 
 */
public class FileTransferServer extends Thread{

    private int port = 6787;
    private static ServerSocket serverSocket;
    private static String fileName="C:\\Users\\14914\\Desktop\\test";
    private static String fname="默认.txt";

    public FileTransferServer() throws IOException {
        serverSocket = new ServerSocket(port);
    }

	public void run()
	{
		while (true) {
            try {
                Socket socket = null;

                socket = serverSocket.accept();
                //先读文件名
                InputStream is = socket.getInputStream();
                int len1;
                byte[] bs = new byte[1024];
                //先读文件名
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
                System.out.println("传输完成");
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
	}

    public static void main(String[] args) throws IOException {
        new FileTransferServer().start();
    }


}


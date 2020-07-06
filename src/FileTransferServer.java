import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 接受客户端发来的文件，然后保存在本地
 */
public class FileTransferServer extends Thread {

    private int port = 6787;
    private static ServerSocket serverSocket;
    private static String fileName = "C:\\Users\\14914\\Desktop\\test";//服务器端上传下载文件默认目录

    public FileTransferServer() throws IOException {
        serverSocket = new ServerSocket(port);
    }

    public void run() {
        while (true) {
            try {
                Socket socket = null;
                socket = serverSocket.accept();
                //读取文件名的输入流
                InputStream is = socket.getInputStream();
                int len1;
                byte[] bs = new byte[1024];
                //读取文件名
                len1 = is.read(bs);
                String name = new String(bs, 0, len1);
                System.out.println(name + "---");

                //获取文件内容的输入输出流
                DataInputStream dis = new DataInputStream(socket.getInputStream());
                DataOutputStream dos = new DataOutputStream(new FileOutputStream(fileName + "\\" + name));
                byte[] buf = new byte[1024 * 9];
                int len = 0;
                //获取文件
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

}


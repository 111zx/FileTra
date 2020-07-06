

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * 从本地读取文件，然后向服务器上传文件
 *
 */
public class FileTransferClient {
    private String host = "127.0.0.1";
    private int port =6787;
    private int port1 =6788;
    private Socket clientSocket;

    /**
     * 对应6787端口的构造器：完成上传文件功能
     * @throws IOException
     */
    public FileTransferClient() throws IOException {
    	clientSocket = new Socket(host, port);
    }

    /**
     * 对应6788端口的构造器：完成下载文件功能
     * @param temp
     * @throws IOException
     */
    public FileTransferClient(String temp) throws IOException {
        clientSocket = new Socket(host, port1);
    }

    /**
     * 向服务器端发送想要下载的目标文件的文件名
     * @param sfilePath
     */
    void sendM(String sfilePath){
        DataOutputStream sdos = null;
        try {
            System.out.println(sfilePath);
            sdos = new DataOutputStream(clientSocket.getOutputStream());
            sdos.write(sfilePath.getBytes());
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally {
            if(sdos != null)
                try {
                    sdos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }

    }

    /**
     * 向服务器端上传文件
     * @param sfilePath：对应本地文件路径
     */
    void sendFile(String sfilePath) {


                try {
                    File sfile = new File(sfilePath);
                    System.out.println(sfilePath);
                    DataInputStream sdis = new DataInputStream(new FileInputStream(sfilePath));
                    DataOutputStream sdos = new DataOutputStream(clientSocket.getOutputStream());
                    byte[] sbuf = new byte[1024 * 9];
                    int slen = 0;
                    sdos.write(sfile.getName().getBytes());
                    while ((slen = sdis.read(sbuf)) != -1) {
                        sdos.write(sbuf, 0, slen);
                    }
                    sdos.flush();
                    sdis.close();
                    sdos.close();
                } catch (FileNotFoundException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

            }
}

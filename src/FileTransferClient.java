

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
    private Socket clientSocket;;

    public FileTransferClient() throws IOException {
    	clientSocket = new Socket(host, port);
    }

    void sendFile(String sfilePath) {


                try {
                    File sfile = new File(sfilePath);
                    DataInputStream sdis = new DataInputStream(new FileInputStream(sfilePath));
                    DataOutputStream sdos = new DataOutputStream(clientSocket.getOutputStream());
                    byte[] sbuf = new byte[1024 * 9];
                    int slen = 0;
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
    public static void main(String[] args) throws IOException {
        new FileTransferClient().sendFile("D:\\四季.mp3");
    }

}

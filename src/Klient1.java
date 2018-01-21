import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.Socket;

public class Klient1 {

    private Socket s;

    public Klient1(String host, int port, String plik) {
        try {
            s = new Socket(host, port);
            wyslijPlikNaSerwer(plik);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void wyslijPlikNaSerwer(String plik) throws IOException {
        DataOutputStream dataOutputStream = new DataOutputStream(s.getOutputStream());
        FileInputStream fileInputStream = new FileInputStream(plik);
        byte[] buffer = new byte[4096];

        while (fileInputStream.read(buffer) > 0) {
            dataOutputStream.write(buffer);
        }

        fileInputStream.close();
        dataOutputStream.close();
    }

    public static void main(String[] args) {
        Klient1 klient1 = new Klient1("localhost", 11000, "pliktestowy.txt");
    }

}
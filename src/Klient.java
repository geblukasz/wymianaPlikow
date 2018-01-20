import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.Socket;

public class Klient {

    private Socket s;

    public Klient(String host, int port, String plik) {
        try {
            s = new Socket(host, port);
            wyslijPlikNaSerwer(plik);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void wyslijPlikNaSerwer(String plik) throws IOException {
        DataOutputStream dos = new DataOutputStream(s.getOutputStream());
        FileInputStream fis = new FileInputStream(plik);
        byte[] buffer = new byte[4096];

        while (fis.read(buffer) > 0) {
            dos.write(buffer);
        }

        fis.close();
        dos.close();
    }

    public static void main(String[] args) {
        Klient fc = new Klient("localhost", 1988, "pliktestowy.txt");
    }

}
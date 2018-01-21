import java.io.DataInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Serwer extends Thread {

    private ServerSocket ss;
    private int port = 1988;

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public Serwer(int port) {
        try {
            ss = new ServerSocket(port);
        } catch (IOException e) {
            System.out.println("Port: " + port + " jest zajęty");
            e.printStackTrace();
        }
    }

    public void run() {
        while (true) {
            try {
                Socket clientSock = ss.accept();
                zapiszPlikOdKlienta(clientSock);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void zapiszPlikOdKlienta(Socket clientSock) throws IOException {
        DataInputStream streamPrzyjmujacyPlikOdKlienta = new DataInputStream(clientSock.getInputStream());
        FileOutputStream streamZapisujacyPlikOdKlienta = new FileOutputStream("PrzykladowyPlikDoTestow.txt");
        byte[] buffer = new byte[4096];

        int rozmiarPliku = 15123;
        int licznikPrzeczytanychBajtow = 0;
        int koncowaLiczbaPrzeczytanychBajtow = 0;
        int liczbaBajtowPozostalhcDoPrzeczytania = rozmiarPliku;
        while((licznikPrzeczytanychBajtow = streamPrzyjmujacyPlikOdKlienta.read(buffer, 0, Math.min(buffer.length, liczbaBajtowPozostalhcDoPrzeczytania))) > 0) {
            koncowaLiczbaPrzeczytanychBajtow += licznikPrzeczytanychBajtow;
            liczbaBajtowPozostalhcDoPrzeczytania -= licznikPrzeczytanychBajtow;
            System.out.println("Zapisano plik na serwerze, wielkosc transferu " + koncowaLiczbaPrzeczytanychBajtow + " bajtow.");
            streamZapisujacyPlikOdKlienta.write(buffer, 0, licznikPrzeczytanychBajtow);
        }

        //zamykanie streamow
        streamZapisujacyPlikOdKlienta.close();
        streamPrzyjmujacyPlikOdKlienta.close();
    }

    public static void main(String[] args) {
        Serwer fs = new Serwer(11000);
        fs.start();
    }

}
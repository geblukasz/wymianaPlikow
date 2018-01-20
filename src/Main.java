import java.io.File;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Main {

    public static void main(String[] args) {

        String checksum = null;

        //Plik do obliczenia sumy kontrolnej
        File plik = new File("C:\\Users\\Lukasz\\Desktop\\ProjektKlientSerwer\\WymianaPlikow\\PrzykladowyPlikDoTestow.txt");


        //Algorytm MD5
        MessageDigest md5Digest = null;
        try {
            md5Digest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            System.out.println("Błąd podczas pracy algorytmu");
            e.printStackTrace();
        }

        //Obliczanie sumy kontrolnej
        checksum = GeneratorSumyMD5.getFileChecksum(md5Digest, plik);

        System.out.println("Suma kontrolna dla pliku " + plik + " wynosi: " + checksum);

    }
}

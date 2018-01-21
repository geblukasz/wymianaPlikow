import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.MessageDigest;

public class GeneratorSumyMD5 {

    static String getFileChecksum(MessageDigest digest, File plik)
    {
        //Przeczytaj plik
        FileInputStream streamCzytaniaPliku = null;
        try {
            streamCzytaniaPliku = new FileInputStream(plik);
        } catch (FileNotFoundException e) {
            System.out.println("Nie znaleziono pliku" + plik);
            e.printStackTrace();
        }

        byte[] tablicaBajtow = new byte[1024];
        int licznikBajtow = 0;

        try {
            while ((licznikBajtow = streamCzytaniaPliku.read(tablicaBajtow)) != -1) {
                digest.update(tablicaBajtow, 0, licznikBajtow);
            }
        } catch (IOException e) {
            System.out.println();
            e.printStackTrace();
        }

        try {
            streamCzytaniaPliku.close();
        } catch (IOException e) {
            System.out.println("Błąd podczas czytania pliku");
            e.printStackTrace();
        }

        byte[] bytes = digest.digest();

        //Przekonwertowanie z formatu decimal do hexDecimal
        StringBuilder sb = new StringBuilder();
        for(int i=0; i< bytes.length ;i++)
        {
            sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
        }

        //zwrocenie hash'a
        return sb.toString();
    }

}

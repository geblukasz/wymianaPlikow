import java.io.File;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        String sumaKontrolnaPliku = null;
        String przykladowaSciezkaDoPliku = "C:\\Users\\Lukasz\\Desktop\\ProjektKlientSerwer\\WymianaPlikow\\PrzykladowyPlikDoTestow.txt";

        //Algorytm MD5
        MessageDigest md5Digest = null;
        try {
            md5Digest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            System.out.println("Błąd podczas pracy algorytmu");
            e.printStackTrace();
        }

        String nazwaPlikuDoWyslania;
        Scanner scanner = new Scanner(System.in);
        String informacjeOgolne = "Wybierz odpowiednia opcję: \n 1: Wyslij plik na serwer " +
                "\n 2: Oblicz sume kontrolną dla pliku " +
                "\n 0: Zakończ działanie programu";
        String odpowiedz;
        int port = 11000;

        do {
            System.out.println(informacjeOgolne);
            odpowiedz = scanner.nextLine();

            if (odpowiedz.equals("1")) {
                Klient1 klient1 = new Klient1("localhost", 11000, "pliktestowy.txt");
                System.out.println("Wysłano plik na serwer");

            } else if (odpowiedz.equals("2")) {
                System.out.println("Wprowadź ścieżkę do pliku dla którego ma być obliczona suma kontrolna");
                String sciezkaDoPliku = scanner.nextLine();
                File plik = new File(sciezkaDoPliku);
                sumaKontrolnaPliku = GeneratorSumyMD5.getFileChecksum(md5Digest, plik);
                System.out.println("\n Suma kontrolna dla pliku " + plik + " wynosi: " + sumaKontrolnaPliku + " \n\n");
            } else if (odpowiedz.equals("0")) {
                break;
            } else {
                System.out.println("Niepoprawna odpowiedz");
            }
        } while (!odpowiedz.equals("0"));
        System.out.println("Koniec działania programu");


    }
}
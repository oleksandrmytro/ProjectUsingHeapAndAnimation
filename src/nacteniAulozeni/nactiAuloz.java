package nacteniAulozeni;

import abstrTable.Obec;
import enumsTypy.eProchazeni;
import agendaKraj.AgendaKraj;
import javafx.scene.control.Alert;
import javafx.stage.FileChooser;

import java.io.*;
import java.util.Iterator;
import java.util.Objects;
import java.util.Scanner;

public class nactiAuloz {

    public static <T> void uloz(String nazevSouboru, AgendaKraj list, eProchazeni value)
            throws IOException {
        Objects.requireNonNull(list);
        try (PrintWriter uloz = new PrintWriter(new BufferedWriter(new FileWriter(nazevSouboru)))) {
            Iterator<T> iterator = (Iterator<T>) list.vytvorIterator(value);
            while (iterator.hasNext()) {
                uloz.println(iterator.next());
            }
        } catch (Exception ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Zadejte typ prohlidky");
            alert.showAndWait();
        }
    }

    public static AgendaKraj nacti(AgendaKraj list, eProchazeni value) throws IOException {
        Objects.requireNonNull(list, "Agenda list cannot be null");

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Text Files", "*.txt"));

        File file = fileChooser.showOpenDialog(null); // null can be replaced with a Stage object

        if (file == null) {
            throw new FileNotFoundException("No file selected.");
        }

        try (Scanner scanner = new Scanner(file)) {
            list.zrus();
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                try {
                    String[] parts = line.split(", ");
                    int cisloKraje = Integer.parseInt(parts[0].split("=")[1]);
                    int psc = Integer.parseInt(parts[1].split("=")[1]);
                    String nazevObce = parts[2].split("=")[1].trim();
                    int pocetMuzu = Integer.parseInt(parts[3].split("=")[1]);
                    int pocetZen = Integer.parseInt(parts[4].split("=")[1]);
                    int celkem = Integer.parseInt(parts[5].split("=")[1]);
                    Obec obec = new Obec(cisloKraje, psc, nazevObce, pocetMuzu, pocetZen, celkem);
                    list.Vloz(obec);
                } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                    System.err.println("Error processing line: " + line);
                } catch (Exception e) {
                    System.err.println(e);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
            throw e;
        }

        return list;
    }

}



package pl.jablonskanycz.bakery;

import pl.jablonskanycz.bakery.clients.Address;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ImportData {
    public static void main(String[] args) {
        String line = "";
        String splitBy = " , ";
        List<Address> addresses = new ArrayList<>();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("/Users/asianycz/JAVA/PaniOdProgramowania/csv/ADDRESS.csv"));
            while ((line = bufferedReader.readLine()) != null) {
                String[] address = line.split(splitBy);
                Address readAddress = new Address(Integer.parseInt(address[0]), Double.parseDouble(address[1]), Double.parseDouble(address[2]));
                addresses.add(readAddress);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

package HW4.SaveLoad.Load;

import HW4.Customer.Customer;
import HW4.Market.Market;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.HashSet;

public class LoadFromFile {
    private static File file = new File("market.bin");

    public static Object load() {
        if (!file.exists()) {
            try {
                Files.createFile(file.toPath());
                return null;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        try (FileInputStream fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis)){
            return ois.readObject();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}

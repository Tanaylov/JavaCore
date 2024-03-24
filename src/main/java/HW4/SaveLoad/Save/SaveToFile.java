package HW4.SaveLoad.Save;

import java.io.*;

public class SaveToFile {
    private static File file = new File("market.bin");

    public static void save(Object o) {
        try(FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(o);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

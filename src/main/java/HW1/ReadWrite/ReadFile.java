package HW1.ReadWrite;

import HW1.Note.Note;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.List;

public class ReadFile {
    public static void read(String path) {
        try (FileInputStream fis = new FileInputStream(path);
             ObjectInputStream ois = new ObjectInputStream(fis))
        {
            Note.fillNotes((List<Note>) ois.readObject());
        }catch (IOException e){
            System.out.println("Your note list is empty");;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}

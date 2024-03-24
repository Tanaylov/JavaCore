package HW1.ReadWrite;

import HW1.Note.Note;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;

public class WriteToFile {
    public static void write(List<Note> notes, String path) {
        try (
            FileOutputStream fos = new FileOutputStream(path);
            ObjectOutputStream oos = new ObjectOutputStream(fos)
                ){
            oos.writeObject(notes);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

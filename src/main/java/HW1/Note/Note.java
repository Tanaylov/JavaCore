package HW1.Note;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Note implements Serializable {
    private static int id;
    private int noteCounter;
    private String body;
    private LocalDate date;
    private static List<Note> notes = new ArrayList<>();
    {
        noteCounter = ++id;
    }
    public Note(String body) {
        if (body.isEmpty()) this.body = "note" + noteCounter;
        else this.body = body;
        date = LocalDate.now();
        notes.add(Note.this);
    }
    public static void printNotes() {
        notes.forEach(el -> System.out.println(el));
    }
    public static List<Note> getNotes() {
        return notes;
    }
    public static void fillNotes(List<Note> _notes) {
        notes = _notes;
    }
    public static void addNote(Note note) {
        notes.add(note);
    }
    public static boolean deleteNote(String noteBody) {
        int noteSize = notes.size();
        notes.removeIf(el -> el.body.equals(noteBody));
        if (noteSize != notes.size()) return true;
        return false;
    }
    @Override
    public String toString() {
        return String.format("%s -> %s", date.toString(), body);
    }
}

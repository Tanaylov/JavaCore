package HW1.App;

import HW1.Note.Note;
import HW1.ReadWrite.ReadFile;
import HW1.ReadWrite.WriteToFile;

import java.util.List;
import java.util.Scanner;

public class Start {
    private static Scanner sc = new Scanner(System.in);
    public static void start(String path) {
        ReadFile.read(path);
        Byte option = -1;
        do {
            System.out.println("Choose option:\n" +
                "1 - Add note\n" +
                "2 - Delete note\n" +
                "3 - Show notes\n" +
                "0 - exit");
            while (true) {
                try {
                    option = sc.nextByte();
                    sc.nextLine();
                    if (option > -1 && option < 4)
                        break;
                    else System.out.println("You enter wrong number, try again");
                } catch (NumberFormatException e) {
                    System.out.println("You enter not a number, try again.");
                }
            }
            switch (option) {
                case 1:
                    System.out.print("Enter your note: ");
                    String noteBody = sc.nextLine();
                    new Note(noteBody);
                    break;
                case 2:
                    System.out.print("Enter note  your want to delete: ");
                    String noteBodyToDel = sc.nextLine();
                    if (Note.deleteNote(noteBodyToDel)) System.out.println("Note was deleted");
                    else System.out.println("You don't have a note like this");
                    break;
                case 3:
                    Note.printNotes();
                    break;
                case 0:
                    WriteToFile.write(Note.getNotes(), path);
            }
        }
        while (option != 0);
    }
}

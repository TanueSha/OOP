package Homeitog2;

import java.time.LocalDate;
import java.util.List;


public interface NoteService {
    void addNote(Note note);
    List<Note> getNotesByDate(LocalDate date);
    List<Note> getNotesByWeek(LocalDate startOfWeek);
    void saveToFile(String fileName);
    void loadFromFile(String fileName);
}


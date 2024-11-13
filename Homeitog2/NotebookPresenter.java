package Homeitog2;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class NotebookPresenter {
    private final NoteService noteService;
    private NotebookView view;

    public NotebookPresenter(NoteService noteService) {
        this.noteService = noteService;
    }

    public void setView(NotebookView view) {
        this.view = view;
    }

    public void addNoteToNotebook(LocalDateTime dateTime, String description) {
        Note note = new Note(dateTime, description);
        noteService.addNote(note);
        view.displayMessage("Запись добавлена.");
    }

    public void showNotesByDate(LocalDate date) {
        List<Note> notes = noteService.getNotesByDate(date);
        view.displayNotes(notes);
    }

    public void showNotesByWeek(LocalDate startOfWeek) {
        List<Note> notes = noteService.getNotesByWeek(startOfWeek);
        view.displayNotes(notes);
    }

    public void saveNotes(String fileName) {
        noteService.saveToFile(fileName);
        view.displayMessage("Записи сохранены в файл.");
    }

    public void loadNotes(String fileName) {
        noteService.loadFromFile(fileName);
        view.displayMessage("Записи загружены из файла.");
    }
}

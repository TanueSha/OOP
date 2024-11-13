package Homeitog2;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class NoteServiceImpl implements NoteService {
    private List<Note> notes = new ArrayList<>();

    @Override
    public void addNote(Note note) {
        notes.add(note);
    }

    @Override
    public List<Note> getNotesByDate(LocalDate date) {
        return notes.stream()
                .filter(note -> note.getDateTime().toLocalDate().equals(date))
                .collect(Collectors.toList());
    }

    @Override
    public List<Note> getNotesByWeek(LocalDate startOfWeek) {
        return notes.stream()
                .filter(note -> {
                    LocalDate noteDate = note.getDateTime().toLocalDate();
                    return noteDate.isAfter(startOfWeek.minusDays(1)) && noteDate.isBefore(startOfWeek.plusDays(7));
                })
                .collect(Collectors.toList());
    }

    @Override
    public void saveToFile(String fileName) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName))) {
            out.writeObject(notes);
        } catch (IOException e) {
            System.out.println("Ошибка при сохранении в файл: " + e.getMessage());
        }
    }

    @Override
    public void loadFromFile(String fileName) {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName))) {
            notes = (List<Note>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Ошибка при загрузке из файла: " + e.getMessage());
        }
    }
}



package Homeitog2;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class ConsoleNotebookView implements NotebookView {
    private final NotebookPresenter presenter;
    private final Scanner scanner = new Scanner(System.in);
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public ConsoleNotebookView(NotebookPresenter presenter) {
        this.presenter = presenter;
        this.presenter.setView(this); // Устанавливаем view для presenter
    }

    @Override
    public void displayNotes(List<Note> notes) {
        if (notes.isEmpty()) {
            System.out.println("Нет записей.");
        } else {
            notes.forEach(System.out::println);
        }
    }

    @Override
    public void displayMessage(String message) {
        System.out.println(message);
    }

    public void start() {
        while (true) {
            System.out.println("\nВыберите действие:");
            System.out.println("1 - Добавить запись");
            System.out.println("2 - Показать записи за день");
            System.out.println("3 - Показать записи за неделю");
            System.out.println("4 - Сохранить записи в файл");
            System.out.println("5 - Загрузить записи из файла");
            System.out.println("0 - Выход");

            int choice = scanner.nextInt();
            scanner.nextLine();  // Очистка буфера

            switch (choice) {
                case 1 -> addNote();
                case 2 -> showNotesByDate();
                case 3 -> showNotesByWeek();
                case 4 -> saveNotes();
                case 5 -> loadNotes();
                case 0 -> {
                    System.out.println("Выход...");
                    return;
                }
                default -> System.out.println("Некорректный выбор. Попробуйте снова.");
            }
        }
    }

    private void addNote() {
        System.out.print("Введите дату и время (гггг-мм-дд чч:мм): ");
        String dateTimeString = scanner.nextLine();

        try {
            LocalDateTime dateTime = LocalDateTime.parse(dateTimeString, formatter);
            System.out.print("Введите описание: ");
            String description = scanner.nextLine();
            presenter.addNoteToNotebook(dateTime, description);
        } catch (Exception e) {
            System.out.println("Ошибка: неверный формат даты и времени. Пожалуйста, используйте формат гггг-мм-дд чч:мм.");
        }
    }

    private void showNotesByDate() {
        System.out.print("Введите дату (гггг-мм-дд): ");
        String dateString = scanner.nextLine();
        try {
            LocalDate date = LocalDate.parse(dateString);
            presenter.showNotesByDate(date);
        } catch (Exception e) {
            System.out.println("Ошибка: неверный формат даты. Пожалуйста, используйте формат гггг-мм-дд.");
        }
    }

    private void showNotesByWeek() {
        System.out.print("Введите начальную дату недели (гггг-мм-дд): ");
        String dateString = scanner.nextLine();
        try {
            LocalDate startOfWeek = LocalDate.parse(dateString);
            presenter.showNotesByWeek(startOfWeek);
        } catch (Exception e) {
            System.out.println("Ошибка: неверный формат даты. Пожалуйста, используйте формат гггг-мм-дд.");
        }
    }

    private void saveNotes() {
        System.out.print("Введите имя файла для сохранения: ");
        String fileName = scanner.nextLine();
        presenter.saveNotes(fileName);
    }

    private void loadNotes() {
        System.out.print("Введите имя файла для загрузки: ");
        String fileName = scanner.nextLine();
        presenter.loadNotes(fileName);
    }
}

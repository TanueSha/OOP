package Homeitog2;

public class Main {
    public static void main(String[] args) {
        NoteService noteService = new NoteServiceImpl();
        NotebookPresenter presenter = new NotebookPresenter(noteService);
        ConsoleNotebookView view = new ConsoleNotebookView(presenter);

        view.start(); // Запуск консольного интерфейса
    }
}

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        WordReader reader = new WordReader();
        String filename = "C:\\Users\\Ильнур\\Documents\\Виселица\\Viselica\\Project_n1\\words.txt";

        if (!reader.loadWordsFromFile(filename)) {
            System.out.println("Ошибка при загрузке файла. Игра не может начаться.");
            return;
        }

        if (reader.isEmpty()) {
            System.out.println("Файл пуст или не содержит допустимых слов.");
            return;
        }

        List<String> words = reader.getWords();
        try (Scanner scanner = new Scanner(System.in)) {
            HangmanGame game = new HangmanGame(words);

            while (true) {
                System.out.print("Начать новую игру? (Да/Нет): ");
                String choice = scanner.nextLine().trim();

                if (choice.equalsIgnoreCase("Да")) {
                    game.startNewRound();
                    game.play(scanner);
                } else if (choice.equalsIgnoreCase("Нет")) {
                    System.out.println("Спасибо за игру!");
                    break;
                } else {
                    System.out.println("Пожалуйста, введите 'Да' или 'Нет'.");
                }
            }
        }
    }
}
import java.util.Random;
import java.util.Scanner;
import java.util.List;

public class Main {
    private static Random random = new Random();

    public static void main(String[] args) {
        rf wordReader = new rf();
        if (!wordReader.openFile("C:\\Users\\Ильнур\\Documents\\Виселица\\Viselica\\Project_n1\\words.txt")) {
            System.out.println("Ошибка при открытии файла 'words.txt'. Игра не может начаться.");
            return;
        }
        wordReader.readFile();
        wordReader.closeFile();

        List<String> words = wordReader.getWords();
        if (words.isEmpty()) {
            System.out.println("Файл 'words.txt' пуст или не содержит допустимых слов. Игра не может начаться.");
            return;
        }

        try (Scanner scanner = new Scanner(System.in)) {
            boolean isRunning = true;
            while (isRunning) {
                System.out.println("Начать новую игру? (Да/Нет)");
                String choice = scanner.nextLine();
                if (choice.equalsIgnoreCase("Да")) {
                    startGame(scanner, words);
                } else if (choice.equalsIgnoreCase("Нет")) {
                    System.out.println("Игра закрыта");
                    isRunning = false;
                } else {
                    System.out.println("Неверный выбор");
                }
            }
        }
    }


    public static void drawHangman(int mistakes) {
        String[] stages = {
                "  +---+\n  |   |\n      |\n      |\n      |\n      |\n=========",
                "  +---+\n  |   |\n  O   |\n      |\n      |\n      |\n=========",
                "  +---+\n  |   |\n  O   |\n  |   |\n      |\n      |\n=========",
                "  +---+\n  |   |\n  O   |\n /|   |\n      |\n      |\n=========",
                "  +---+\n  |   |\n  O   |\n /|\\  |\n      |\n      |\n=========",
                "  +---+\n  |   |\n  O   |\n /|\\  |\n /    |\n      |\n=========",
                "  +---+\n  |   |\n  O   |\n /|\\  |\n / \\  |\n      |\n========="
        };
        System.out.println(stages[mistakes]);
    }


    public static void startGame(Scanner scanner, List<String> words) {
        System.out.println("Игра началась");


        String word = words.get(random.nextInt(words.size())).toLowerCase();

        char[] guessedWord = new char[word.length()];
        for (int i = 0; i < guessedWord.length; i++) {
            guessedWord[i] = '_';
        }

        int mistakes = 0;
        final int MAX_MISTAKES = 6;

        while (true) {
            drawHangman(mistakes);
            System.out.println("Слово: " + new String(guessedWord));
            System.out.println("Введите букву");

            String input = scanner.nextLine().trim();

            if (input.length() != 1) {
                System.out.println("Введите ровно одну букву");
                continue;
            }
            char guess = input.toLowerCase().charAt(0);
            if (!Character.isLetter(guess)) {
                System.out.println("Это не буква");
                continue;
            }


            boolean found = false;
            for (int i = 0; i < word.length(); i++) {
                if (word.charAt(i) == guess) {
                    guessedWord[i] = guess;
                    found = true;
                }
            }
            if (!found) {
                mistakes++;
                System.out.println("Нет такой буквы в загаданном слове. Ошибок: " + mistakes + "/" + MAX_MISTAKES);
            }

            boolean isComplete = true;
            for (char c : guessedWord) {
                if (c == '_') {
                    isComplete = false;
                    break;
                }
            }
            if (isComplete) {
                System.out.println("Победа! Слово: " + word);
                break;
            }
            if (mistakes >= MAX_MISTAKES) {
                drawHangman(mistakes);
                System.out.println("Поражение! Загаданное слово было: " + word);
                break;
            }
        }
    }
}
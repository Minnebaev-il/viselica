import java.util.Random;
import java.util.Scanner;
import java.util.List;

public class HangmanGame {
    private static final String[] HANGMAN_STAGES = {
            "  +---+\n  |   |\n      |\n      |\n      |\n      |\n=========",
            "  +---+\n  |   |\n  O   |\n      |\n      |\n      |\n=========",
            "  +---+\n  |   |\n  O   |\n  |   |\n      |\n      |\n=========",
            "  +---+\n  |   |\n  O   |\n /|   |\n      |\n      |\n=========",
            "  +---+\n  |   |\n  O   |\n /|\\  |\n      |\n      |\n=========",
            "  +---+\n  |   |\n  O   |\n /|\\  |\n /    |\n      |\n=========",
            "  +---+\n  |   |\n  O   |\n /|\\  |\n / \\  |\n      |\n========="
    };

    private static final int MAX_MISTAKES = 6;

    private final Random random = new Random();
    private final List<String> wordList;
    private String secretWord;
    private char[] guessedLetters;
    private int mistakes;

    public HangmanGame(List<String> wordList) {
        this.wordList = wordList;
        this.mistakes = 0;
        startNewRound();
    }

    public void startNewRound() {
        this.secretWord = wordList.get(random.nextInt(wordList.size()));
        this.guessedLetters = new char[secretWord.length()];
        for (int i = 0; i < guessedLetters.length; i++) {
            guessedLetters[i] = '_';
        }
        this.mistakes = 0;
    }

    public void play(Scanner scanner) {
        System.out.println("Игра началась!");

        while (true) {
            drawHangman();
            System.out.println("Слово: " + new String(guessedLetters));
            System.out.print("Введите букву: ");

            String input = scanner.nextLine().trim();

            if (input.length() != 1) {
                System.out.println("Введите ровно одну букву.");
                continue;
            }

            char guess = Character.toLowerCase(input.charAt(0));
            if (!Character.isLetter(guess)) {
                System.out.println("Это не буква.");
                continue;
            }

            // Проверка, была ли буква уже отгадана (опционально)
            if (isAlreadyGuessed(guess)) {
                System.out.println("Вы уже пробовали эту букву.");
                continue;
            }

            boolean found = false;
            for (int i = 0; i < secretWord.length(); i++) {
                if (secretWord.charAt(i) == guess) {
                    guessedLetters[i] = guess;
                    found = true;
                }
            }

            if (!found) {
                mistakes++;
                System.out.println("Нет такой буквы. Ошибок: " + mistakes + "/" + MAX_MISTAKES);
            }

            if (isWordGuessed()) {
                System.out.println("Победа! Слово: " + secretWord);
                break;
            }

            if (mistakes >= MAX_MISTAKES) {
                drawHangman();
                System.out.println("Поражение! Загаданное слово было: " + secretWord);
                break;
            }
        }
    }

    private boolean isAlreadyGuessed(char letter) {
        for (char c : guessedLetters) {
            if (c == letter) return true;
        }
        return false;
    }

    private boolean isWordGuessed() {
        for (char c : guessedLetters) {
            if (c == '_') return false;
        }
        return true;
    }

    private void drawHangman() {
        System.out.println(HANGMAN_STAGES[mistakes]);
    }
}
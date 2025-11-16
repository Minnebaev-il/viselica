import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class WordReader {
    private List<String> words = new ArrayList<>();

    public boolean loadWordsFromFile(String filename) {
        try (Scanner scanner = new Scanner(new File(filename))) {
            words.clear();
            while (scanner.hasNext()) {
                String word = scanner.next().trim();
                if (!word.isEmpty()) {
                    words.add(word.toLowerCase());
                }
            }
            return true;
        } catch (FileNotFoundException e) {
            System.err.println("Файл не найден: " + filename);
            return false;
        }
    }

    public List<String> getWords() {
        return new ArrayList<>(words);
    }

    public boolean isEmpty() {
        return words.isEmpty();
    }
}
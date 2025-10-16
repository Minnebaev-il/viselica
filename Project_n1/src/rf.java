import java.io.*;
import java.util.*;

public class rf {
    private Scanner file;
    private List<String> words = new ArrayList<>();


    public boolean openFile(String filename) {
        try {
            this.file = new Scanner(new File("C:\\Users\\Ильнур\\Documents\\Виселица\\Viselica\\Project_n1\\words.txt"));
            return true;
        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден: " + filename);
            return false;
        }
    }


    public void readFile() {
        if (file == null) {
            System.out.println("Файл не открыт. Сначала вызовите openFile().");
            return;
        }
        words.clear();
        while (file.hasNext()) {
            String word = file.next().trim();
            if (!word.isEmpty()) {
                words.add(word);
            }
        }
    }


    public List<String> getWords() {
        return new ArrayList<>(words);
    }

    public void closeFile() {
        if (file != null) {
            file.close();
            file = null;
        }
    }
}
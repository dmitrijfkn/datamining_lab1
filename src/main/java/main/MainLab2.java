package main;

import com.opencsv.exceptions.CsvValidationException;
import entities.CSV;
import entities.WordWithType;
import logic.CSVConverter;
import logic.WordCounter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class MainLab2 {
    public static void main(String[] args) {
        String path;
        Scanner in = new Scanner(System.in);
        CSVConverter csvConverter = new CSVConverter();

        System.out.println("Please choose between:");

        while (true) {
            System.out.println("Enter path:");
            path = in.nextLine();
            try {
                csvConverter.readCSVFiles(path);
            } catch (IOException | CsvValidationException e) {
                System.out.println("File not found, try again!");
                continue;
            }
            break;
        }

        List<CSV> list = csvConverter.getList();

        WordCounter wordCounter = new WordCounter("stopwords.txt");
        String[] strings;
        for (CSV c : list) {
            strings = c.getText();
            for (String line : strings) {
                line = line.replaceAll("[^a-zA-Z]", "");
                if (!line.equals(""))
                    wordCounter.addWord(new WordWithType(c.getType(), line));
            }
        }

        String word;
        while (true) {
            System.out.println("Enter word or \"stop\" to stop");
            word = in.nextLine();
            if (word.equals("stop")) {
                break;
            } else {
                System.out.println((double) wordCounter.countWord(word, "ham") / wordCounter.countAllWords("ham") * 100 + " percents in ham");
                System.out.println((double) wordCounter.countWord(word, "spam") / wordCounter.countAllWords("spam") * 100 + " percents in spam");
            }
        }
    }
}

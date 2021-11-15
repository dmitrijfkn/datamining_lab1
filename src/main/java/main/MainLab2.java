package main;

import com.opencsv.exceptions.CsvValidationException;
import entities.CSV;
import entities.WordWithType;
import logic.CSVConverter;
import logic.WordCounter;
import logic.WorkWithText;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class MainLab2 {
    public static void main(String[] args) {
        String path;
        Scanner in = new Scanner(System.in);
        CSVConverter csvConverter = new CSVConverter();


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

        String sentence = "";
        while (!sentence.equals("stop")) {
            System.out.println("Enter you`r sentence");
            sentence = in.nextLine();

            double spamProbability = findProbabilityOfType("spam", sentence, list);
            double hamProbability = findProbabilityOfType("ham", sentence, list);
            double sumProbability = spamProbability + hamProbability;


            //System.out.println("spam - " + spamProbability /*+ "%"/**/);
            //System.out.println("ham - " + hamProbability /*+ "%"*/);

            if (spamProbability > hamProbability) {
                System.out.println("*** SPAM ***");
            } else {
                System.out.println("*** HAM ***");
            }
        }
    }

    private static double findProbabilityOfType(String type, String sentence, List<CSV> list) {
        WordCounter wordCounter = new WordCounter("stopwords.txt");
        String[] strings;
        ArrayList<String> words = new ArrayList<>(Arrays.asList(WorkWithText.textToWords(sentence)));

        for (CSV c : list) {
            strings = c.getText();
            for (String line : strings) {
                line = line.replaceAll("[^a-zA-Z]", "");
                if (line.length() != 0) {
                    wordCounter.addWord(new WordWithType(c.getType(), line));
                }
            }
        }

        int numberOfMessages = 0;
        int numberOfTypeMessages = 0;


        for (CSV csv : list) {
            if (csv.getType().equals(type))
                numberOfTypeMessages++;
            numberOfMessages++;
        }
        double typeProbability = (double) numberOfTypeMessages / numberOfMessages;

        //p_type


        double p_type = (double) wordCounter.countAllWords(type) / (wordCounter.countAllWords("ham") + wordCounter.countAllWords("spam"));


        //считаем сколько слов не встречалось

        int unknowns_type = 0;

        for (int i = 0; i < words.size(); i++) {
            if (wordCounter.stopWords.contains(words.get(i))) {
                words.remove(words.get(i));
                i--;
            } else if (wordCounter.countWord(words.get(i), type) == 0) {
                unknowns_type++;
            }
        }


        double probability = 1.0;


        for (String word : words) {
            probability *= (double) (wordCounter.countWord(word, type) + 1) / (wordCounter.countAllWords(type));
        }

        return probability * p_type;
    }
}

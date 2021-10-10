package main;

import com.opencsv.exceptions.CsvValidationException;
import entities.CSV;
import entities.WordWithType;
import logic.CSVConverter;
import logic.WordCounter;

import java.io.IOException;
import java.util.List;

public class MainLab1 {
    public static void main(String[] args) {

        CSVConverter csvConverter = new CSVConverter();

        try {
            csvConverter.readCSVFiles("sms-spam-corpus.csv");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CsvValidationException e) {
            e.printStackTrace();
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

        wordCounter.printAChart("ham");
        wordCounter.printAChart("spam");
        wordCounter.printBChart("ham",csvConverter.getListMessageLength());
        wordCounter.printBChart("spam",csvConverter.getListMessageLength());
    }
}

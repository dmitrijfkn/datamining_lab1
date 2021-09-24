package logic;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import entities.CSV;
import entities.MessageLenghts;

import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import static logic.WorkWithText.textToWords;

public class CSVConverter {

    List<CSV> list = new LinkedList<>();
    List<MessageLenghts> listMessageLength = new LinkedList<>();

    public void readCSVFiles(String file) throws IOException, CsvValidationException {

        CSVReader reader = new CSVReader(new FileReader(file));

        String type;
        String message;
        String[] nextLine;

        while ((nextLine = reader.readNext()) != null) {
            type = nextLine[0];
            message = nextLine[1];
            list.add(new CSV(
                    type, textToWords(message)));
            listMessageLength.add(new MessageLenghts(type, message.length()));
        }
    }

    public List<CSV> getList() {
        return list;
    }

    public List<MessageLenghts> getListMessageLength() {
        return listMessageLength;
    }
}

package logic;

import entities.MessageLenghts;
import entities.WordWithType;
import org.jfree.ui.RefineryUtilities;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

public class WordCounter {

    public WordCounter(String stopWordsFile) {
        FileReader fr = null;
        try {
            fr = new FileReader(stopWordsFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Scanner scan = new Scanner(fr);
        while (scan.hasNextLine()) {
            this.stopWords.add(scan.nextLine());
        }
    }

    public List<String> stopWords = new LinkedList<>();

    private TreeMap<WordWithType, Integer> treeMap = new TreeMap<>();


    public void addWord(WordWithType word) {
        if (stopWords.contains(word.getWord())) {
            return;
        } else if (treeMap.computeIfPresent(word, (k, v) -> v + 1) == null) {
            treeMap.put(word, 1);
        }
    }

    public TreeMap<WordWithType, Integer> getTreeMap() {
        return treeMap;
    }

    public double wordLengthMiddle(String type) {
        ArrayList<WordWithType> keyList = new ArrayList<WordWithType>(treeMap.keySet());
        ArrayList<Integer> valueList = new ArrayList<Integer>(treeMap.values());

        int sum = 0;
        int counter = 0;
        for (int i = 0; i < keyList.size(); i++) {
            if (keyList.get(i).getType().equals(type)) {
                sum += keyList.get(i).getWord().length() * valueList.get(i);
                counter += valueList.get(i);
            }
        }
        if (counter > 0) {
            return (double) sum / counter;
        }
        return 0;
    }

    public void printAChart(String type) {

        ArrayList<WordWithType> keyList = new ArrayList<WordWithType>(treeMap.keySet());
        ArrayList<Integer> valueList = new ArrayList<Integer>(treeMap.values());

        TreeMap<Integer, Integer> treeMap1 = new TreeMap<>();

        for (int i = 0; i < keyList.size(); i++) {
            int finalI = i;
            if (treeMap1.computeIfPresent(keyList.get(i).getWord().length(), (k, v) -> v + valueList.get(finalI).intValue()) == null) {
                treeMap1.put(keyList.get(i).getWord().length(), 1);
            }
        }

        List<Integer> listX = new ArrayList<Integer>(treeMap1.keySet());
        List<Integer> listY = new ArrayList<Integer>(treeMap1.values());


        final A a = new A("Middle word length for " + type + " = " + wordLengthMiddle(type), listX, listY, "Word length", "Count");
        a.pack();
        RefineryUtilities.centerFrameOnScreen(a);
        a.setVisible(true);

    }

    public void printBChart(String type, List<MessageLenghts> listMessageLength) {
        int sum = 0;
        int count = 0;


        for (int i = 0; i < listMessageLength.size(); i++) {
            if (listMessageLength.get(i).getType().equals(type)) {
                sum += listMessageLength.get(i).getLength();
                count++;
            }
        }

        double middle = 0;
        if (count != 0) {
            middle = (double) sum / count;
        }

        List<Integer> listX = new ArrayList<>();
        List<Integer> listY = new ArrayList<>();


        int max = 0;


        for (MessageLenghts ml : listMessageLength) {
            if (ml.getLength() > max) {
                max = ml.getLength();
            }
        }

        for (int i = 0; i <= max+1; i++) {
            listX.add(i);
            listY.add(0);
        }


        for (int i = 0; i < listMessageLength.size(); i++) {
            if (listMessageLength.get(i).getType().equals(type)) {
                listY.set(listMessageLength.get(i).getLength(), listY.get(listMessageLength.get(i).getLength()) + 1);
            }
        }

        final A a = new A("Middle length of massage for " + type + " = " + middle, listY, listX, "length of massage", "Count");
        a.pack();
        RefineryUtilities.centerFrameOnScreen(a);
        a.setVisible(true);

    }


    public int countIndividualWords(String type){
        int result = 0;
        for(Map.Entry<WordWithType,Integer> entry : treeMap.entrySet()) {
            WordWithType key = entry.getKey();
            Integer value = entry.getValue();

            if(key.getType().equals(type)){
                result++;
            }
        }
        return result;
    }


    public int countAllWords(String type){
        int result = 0;
        for(Map.Entry<WordWithType,Integer> entry : treeMap.entrySet()) {
            WordWithType key = entry.getKey();
            Integer value = entry.getValue();

            if(key.getType().equals(type)){
                result+=value;
            }
        }
        return result;
    }

    public int countWord(String word, String type){
        int result = 0;
        for(Map.Entry<WordWithType,Integer> entry : treeMap.entrySet()) {
            WordWithType key = entry.getKey();
            Integer value = entry.getValue();

            if(key.getType().equals(type) && key.getWord().equals(word)){
                result+=value;
            }
        }
        return result;
    }

}

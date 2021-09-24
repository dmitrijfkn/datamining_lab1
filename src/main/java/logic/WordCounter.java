package logic;

import entities.WordWithType;
import graphics.ChartSample;
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

    private List<String> stopWords = new LinkedList<>();

    private TreeMap<WordWithType, Integer> treeMap = new TreeMap<>();


    public void addWord(WordWithType word) {
        if (stopWords.contains(word)) {
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
        if (counter>0){
            return sum/counter;
        }
        return 0;
    }

    public void printAChart(String type) {
        final A a = new A("Средняя длинна слова = " + String.valueOf(wordLengthMiddle(type)), type, treeMap);
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);

    }
}

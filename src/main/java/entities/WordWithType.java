package entities;

import java.util.Objects;

public class WordWithType implements Comparable<WordWithType> {
    String type;
    String word;

    public WordWithType(String type, String word) {
        this.type = type;
        this.word = word;
    }

    public String getType() {
        return type;
    }

    public String getWord() {
        return word;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WordWithType that = (WordWithType) o;
        return type.equals(that.type) &&
                word.equals(that.word);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, word);
    }


    @Override
    public int compareTo(WordWithType o) {
        return word.compareTo(o.getWord());
    }

    @Override
    public String toString() {
        return "{type='" + type + '\'' +
                ", word='" + word + '\'' +
                '}';
    }
}

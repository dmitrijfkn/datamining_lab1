package entities;

import java.util.Objects;

public class MessageLenghts {
    private String type;
    private int length;

    public MessageLenghts(String type, int length) {
        this.type = type;
        this.length = length;
    }

    public String getType() {
        return type;
    }

    public int getLength() {
        return length;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MessageLenghts that = (MessageLenghts) o;
        return Objects.equals(type, that.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type);
    }
}

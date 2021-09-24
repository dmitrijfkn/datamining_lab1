package entities;

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
}

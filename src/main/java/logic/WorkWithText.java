package logic;

public class WorkWithText {
    public static String[] textToWords(String text) {
        text = text.toLowerCase();
        String[] returnArr = text.split(" ");
        for (int i = 0; i < returnArr.length; i++) {
            returnArr[i] = returnArr[i].replaceAll("[^a-z`-]", "");
            if (returnArr[i].equals("")){

            }
        }
        return returnArr;
    }
}

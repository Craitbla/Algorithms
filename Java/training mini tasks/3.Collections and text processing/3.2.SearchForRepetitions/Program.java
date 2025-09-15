
import java.util.Scanner;
import java.util.stream.Stream;
import java.util.ArrayList;
import java.util.Arrays;

public class Program {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Введите текст: ");
            String inputStr = scanner.nextLine();
            String[] wordsList = preparingLine(inputStr);
            printRepetitions(wordsList);
        }
    }

    public static String[] preparingLine(String inputStr) {
        inputStr = inputStr.replaceAll("[^\\d\\sa-zA-Zа-яА-ЯёЁ]", "");
        inputStr = inputStr.toLowerCase();
        return inputStr.split("\\s+");
    }

    public static void printRepetitions(String[] wordsList) {
        ArrayList<String> repList = new ArrayList<>();
        Arrays.sort(wordsList);
        String lastStr = "";

        for (int i = 0; i < wordsList.length - 1; i++) {
            if (wordsList[i].equals(wordsList[i + 1]) && !wordsList[i].equals(lastStr)) {
                repList.add(wordsList[i]);
            }
            lastStr = wordsList[i];
        }
        System.out.println(String.join(", ", repList));
    }
};

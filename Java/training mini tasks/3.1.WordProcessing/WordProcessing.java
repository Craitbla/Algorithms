import java.util.regex.*;
import java.util.Scanner;

public class WordProcessing {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Введите текст: ");
            String inputStr = scanner.nextLine();
            inputStr = inputStr.replaceAll("[\\p{Punct}]", "");
            inputStr = inputStr.replace(',', '.');
            Pattern pattern = Pattern.compile("\\s+");
            String[] wordsList = inputStr.split(pattern);
        }
    }
}

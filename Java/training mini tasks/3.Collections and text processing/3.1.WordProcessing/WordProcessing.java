import java.util.HashMap;
import java.util.Map;
import java.util.LinkedHashMap;
import java.util.Scanner;
import java.util.stream.Stream;
import java.util.stream.Collectors;
import java.util.ArrayList;
import java.util.List;

public class WordProcessing {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Введите текст: ");
            String inputStr = scanner.nextLine();
            String[] wordsList = preparingLine(inputStr);
            Map<String, Integer> counterMap = createCounterMap(wordsList);
            printСommonAndRareWords(counterMap);
        }
    }

    public static String[] preparingLine(String inputStr) {
        inputStr = inputStr.replaceAll("[^\\d\\sa-zA-Zа-яА-ЯёЁ]", "");
        inputStr = inputStr.toLowerCase();
        return inputStr.split("\\s+");
    }

    public static Map<String, Integer> createCounterMap(String[] wordsList) {
        Map<String, Integer> counterMap = new LinkedHashMap<>();
        for (String key : wordsList) {
            counterMap.merge(key, 1, Integer::sum);
        }
        return counterMap;
    }

    public static void printСommonAndRareWords(Map<String, Integer> counterMap) {

        System.out.println("\n5 самых часто встречающихся слов:");
        counterMap.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed()).limit(5)
                .forEach(entry -> System.out.format("%s - %d\n", entry.getKey(), entry.getValue()));

        System.out.println("\n5 самых редко встречающихся слов:");
        counterMap.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue()).limit(5)
                .forEach(entry -> System.out.format("%s - %d\n", entry.getKey(), entry.getValue()));

    }

    public static void printList(List<Map.Entry<String, Integer>> counterList, int desiredLimit) {
        int limit = desiredLimit;
        if (counterList.size() < desiredLimit) {
            limit = counterList.size();
        }
        for (int i = 0; i < limit; i++) {
            System.out.format("%s - %d\n", counterList.get(i).getKey(), counterList.get(i).getValue());
        }
    }

}

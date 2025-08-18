import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Stream;
import java.util.stream.Collectors;

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
        inputStr = inputStr.replaceAll("[\\p{Punct}]", "");
        inputStr = inputStr.replace(',', '.');
        inputStr = inputStr.toLowerCase();

        return inputStr.split("\\s+");
    }

    public static Map<String, Integer> createCounterMap(String[] wordsList) {
        Map<String, Integer> counterMap = new HashMap<>();
        Integer tmpValue = 0;
        for (String key : wordsList) {
            if (counterMap.containsKey(key)) {
                tmpValue = counterMap.get(key);
                counterMap.replace(key, ++tmpValue);
            } else {
                counterMap.put(key, 1);
            }
        }
        return counterMap;
    }

    public static void printСommonAndRareWords(Map<String, Integer> counterMap) {
        Stream<Map.Entry<String, Integer>> stream = counterMap.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed()); // в теории можно через regex

        Map<Object, Object> map = stream.collect(Collectors.toMap(s -> s.split("=")[0], s -> s.split("=")[1]));
        // counterMap.entrySet().stream().sorted()
    }

}

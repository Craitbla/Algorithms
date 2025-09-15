import java.util.HashMap;
import java.util.Map;
import java.util.LinkedHashMap;
import java.util.Scanner;
import java.util.stream.Stream;
import java.util.stream.Collectors;
import java.util.ArrayList;
import java.util.List;

// Интерфейс vs Абстрактный класс; это разные вещи 
// Характеристика	                           Интерфейс	                               Абстрактный класс
// Множественное наследование	Да (класс может реализовать много интерфейсов)	Нет (только один родительский класс)
// База
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
        Map<String, Integer> counterMap = new LinkedHashMap<>(); // иначе порядок нахождения потеряется с new
                                                                 // HashMap<>()
        for (String key : wordsList) {
            counterMap.merge(key, 1, Integer::sum);
        }
        // Integer tmpValue = 0; //много инструментов для избавления от лишних циклов
        // for (String key : wordsList) {
        // if (counterMap.containsKey(key)) {
        // tmpValue = counterMap.get(key);
        // counterMap.replace(key, ++tmpValue);
        // } else {
        // counterMap.put(key, 1);
        // }
        // }
        return counterMap;
    }

    // Predicate<Integer> isEven = num -> num % 2 == 0;
    // System.out.println(isEven.test(4)); // true

    // функциональные интерфейсы, прикол
    // В Java есть встроенные интерфейсы (Predicate, Function, Consumer и др.).
    // Можно создавать свои функциональные интерфейсы с аннотацией
    // @FunctionalInterface.

    // Stream<T> limit(long maxSize): оставляет в потоке только maxSize элементов.
    // Промежуточная операция
    // entrySet() — метод, который возвращает множество (Set) всех пар Map.Entry из
    // Map.
    public static void printСommonAndRareWords(Map<String, Integer> counterMap) {
        // List<Map.Entry<String, Integer>> counterList = new
        // ArrayList<>(counterMap.entrySet());

        // List<Map.Entry<String, Integer>> commonSortedList = counterList; //я так и
        // поняла что так нельзя
        // List<Map.Entry<String, Integer>> commonSortedList = new
        // ArrayList<>(counterList);
        // commonSortedList.sort((a, b) -> b.getValue().compareTo(a.getValue()));

        // List<Map.Entry<String, Integer>> rareSortedList = new
        // ArrayList<>(counterList);
        // rareSortedList.sort((a, b) -> a.getValue().compareTo(b.getValue()));

        System.out.println("\n5 самых часто встречающихся слов:");
        counterMap.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed()).limit(5)
                .forEach(entry -> System.out.format("%s - %d\n", entry.getKey(), entry.getValue()));
        // .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (oldVal,
        // newVal) -> oldVal,
        // LinkedHashMap::new)); // круто, круто

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

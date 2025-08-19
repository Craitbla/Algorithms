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

        List<Map.Entry<String, Integer>> counterList = new ArrayList<>(counterMap.entrySet());
        List<Map.Entry<String, Integer>> commonSortedList = counterList;
        commonSortedList.sort((a, b) -> a.getValue().compareTo(b.getValue()));
        ;
        List<Map.Entry<String, Integer>> rareSortedList = counterList;
        rareSortedList.sort((a, b) -> b.getValue().compareTo(a.getValue()));
        // Map<String, Integer> rareSortedMap = counterMap.entrySet().stream()
        // .sorted(Map.Entry.<String, Integer>comparingByValue()).limit(5)
        // .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (oldVal,
        // newVal) -> oldVal,
        // LinkedHashMap::new));

        // Map<String, Integer> commonSortedMap = counterMap.entrySet().stream()
        // .sorted(Map.Entry.<String, Integer>comparingByValue().reversed()).limit(5)
        // .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (oldVal,
        // newVal) -> oldVal,
        // LinkedHashMap::new));
        // System.out.println("\n5 самых часто встречающихся слов:");
        // printMap(commonSortedMap);
        // System.out.println("\n5 самых редко встречающихся слов:");
        // printMap(rareSortedMap);
        System.out.println("\n5 самых часто встречающихся слов:");
        printList(commonSortedMap);
        System.out.println("\n5 самых редко встречающихся слов:");
        printList(rareSortedMap);

    }

    public static void printList(Map<String, Integer> counterMap) {
        for (Map.Entry<String, Integer> pair : counterMap.entrySet()) {
            System.out.format("%s - %d\n", pair.getKey(), pair.getValue());
        }
    }

}

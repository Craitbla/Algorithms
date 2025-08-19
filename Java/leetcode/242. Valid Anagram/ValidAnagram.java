import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Map;

public class ValidAnagram {
    public static void main(String[] args) {
        System.out.println(isAnagram("qeerg", "grqe"));
    }

    public static boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        List<Character> arrA = s.chars().mapToObj(c -> (char) c).collect(Collectors.toList());
        List<Character> arrB = t.chars().mapToObj(c -> (char) c).collect(Collectors.toList());
        arrA.sort((a, b) -> a - b);
        arrB.sort((a, b) -> a - b);
        return arrA.equals(arrB);
    }

    // Операции с массивами быстрее, чем с HashMap
    // по идее лучше int[] charCount = new int[26];
    public static boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        HashMap<Character, Integer> charMap = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            charMap.merge(s.charAt(i), 1, Integer::sum);
        }
        for (int j = 0; j < t.length(); j++) {
            if (!charMap.containsKey(t.charAt(j))) {
                return false;
            }
            charMap.merge(t.charAt(j), -1, Integer::sum);
            if (charMap.get(t.charAt(j)) == 0) {
                charMap.remove(t.charAt(j));
            }
        }
        return charMap.isEmpty();
    }
}

import java.util.ArrayList;
import java.util.List;
// Оба создадут полностью изменяемый ArrayList
// ArrayList<Float> floats1 = new ArrayList<>(Arrays.asList(2F, 432.435F));
// ArrayList<Float> floats2 = new ArrayList<>(List.of(2F, 432.435F));

public class Program {
    public static void main(String[] args) {
        List<Float> floats = new ArrayList<>(List.of(2F, 432.435F));
        System.out.println(countMoreThenX(floats, 134.9F));
    }

    public static <T extends Number & Comparable<T>> int countMoreThenX(List<T> arr, T x) {
        int counter = 0;
        for (T t : arr) {
            if (t.compareTo(x) > 0) {
                counter++;
            }
        }
        return counter;
    }
}
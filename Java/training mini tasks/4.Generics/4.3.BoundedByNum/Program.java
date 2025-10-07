import java.util.ArrayList;
import java.util.List;

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
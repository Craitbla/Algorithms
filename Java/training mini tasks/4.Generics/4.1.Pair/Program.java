public class Program {
    public static void main(String[] args) {
        NumPair<Integer, Double> myPair = new NumPair<>(2, 142.23);
        System.out.println(myPair.average());

        NumPair<Integer, Double> myPair2 = new NumPair<>(2, 1.0);
        System.out.println(myPair2.average());

        NumPair<Integer, Integer> myPair3 = new NumPair<>(2, 1);
        System.out.println(myPair3.average());

        NumPair<Long, Double> myPair4 = new NumPair<>(2L, 1.0);
        System.out.println(myPair4.average());

        NumPair<Long, Float> myPair5 = new NumPair<>(2L, 1.0F);
        System.out.println(myPair5.average());
    }
}


class NumPair<K extends Number, V extends Number> {
    private K key;
    private V value;

    public NumPair(K newKey, V newValue) {
        key = newKey;
        value = newValue;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }

    public double average() {
        return (key.doubleValue() + value.doubleValue()) / 2.0;
    }

}

public class Program {
    public static void main(String[] args) {
        Pair<Integer, String> myPair = new Pair<>(2, "wef");
        System.out.println(myPair.getKey());
        System.out.println(myPair.getValue());
    }
}

class Pair<K, V> {
    private K key;
    private V value;

    public Pair(K newKey, V newValue) {
        key = newKey;
        value = newValue;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }

}

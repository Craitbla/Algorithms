import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

//@SuppressWarnings("unchecked")
//Используется массив Object[] вместо E[] из-за ограничений Java с созданием generic-массивов
//hasNext() на самом деле существует ли тот на котором итератор
public class Program {
    public static void main(String[] args) {
        // 1 вариант
        // MyArrayList<Integer> myArrayList = new MyArrayList<>();
        // System.out.println(myArrayList.capacity);
        // for (int i = 0; i < 20; i++) {
        // myArrayList.add(i + 1);
        // }
        // System.out.println(myArrayList.capacity);
        // myArrayList = new MyArrayList<>(5);
        // System.out.println(myArrayList.capacity);
        // for (int i = 0; i < 50; i++) {
        // myArrayList.add(i + 1);
        // }
        // System.out.println(myArrayList.capacity);
        // System.out.println(myArrayList.add(1)); // 50

        // 2 вариант
        MyArrayList<String> myArrayList = new MyArrayList<>(0);
        System.out.println(myArrayList.add("one")); // 0
        System.out.println(myArrayList.add("two")); // 1
        for (String element : myArrayList) {
            System.out.println(element);
        }
        System.out.println(myArrayList.size()); // 2
        System.out.println(myArrayList.contains("one")); // true
        System.out.println(myArrayList.remove("zero")); // false
        System.out.println(myArrayList.remove("one")); // true
        System.out.println(myArrayList.contains("false")); // false
        for (String element : myArrayList) {
            System.out.println(element);
        }

    }
}

class MyArrayList<E> implements Iterable<E> {
    private Object[] array;
    private int size;
    private int capacity; // можно сделать public

    public MyArrayList() {
        this(10);
    }

    public MyArrayList(int initCapacity) {
        array = new Object[initCapacity];
        capacity = initCapacity;
        size = 0;
    }

    @Override
    public Iterator<E> iterator() {
        return new MyIterator();
    }

    public int add(E element) {
        if (size == capacity) {
            capacity = (capacity * 3) / 2 + 1; // +1 важно
            array = Arrays.copyOf(array, capacity);
        }
        array[size] = element;
        size++;
        return size - 1;
    }

    public boolean remove(E element) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(array[i], element)) {
                for (int j = i; j < size - 1; j++) {
                    array[j] = array[j + 1];
                }
                size--;
                array[size] = 0;
                return true;
            }
        }
        return false;
    }

    public boolean contains(E element) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(array[i], element)) { // более безопасно
                return true;
            }
        }
        return false;
    }

    public int size() {
        return size;
    }

    private class MyIterator implements Iterator<E> {
        private int curIndex = 0;

        @Override
        public boolean hasNext() {
            return curIndex < size;
        }

        @Override
        @SuppressWarnings("unchecked") // класс, супер, прикол
        public E next() { // E
            if (hasNext()) {
                return (E) array[curIndex++];
            } else {
                throw new NoSuchElementException();
            }
        }

    }

}

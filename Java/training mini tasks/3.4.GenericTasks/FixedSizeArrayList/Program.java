//<><><><><><><><><><><><><><><><><><>

// import java.util.Inde
public class Program {
    public static void main(String[] args) {
        FixedSizeArrayList<Integer> myArray = new FixedSizeArrayList<Integer>(3);
        for (int i = 0; i < 3; i++) {
            myArray.set(i, i);
        }
        System.out.println(myArray.get(3));
    }
}

class FixedSizeArrayList<E> {
    int capacity = 0;
    final Object[] array;

    FixedSizeArrayList(int initCapacity) {
        capacity = initCapacity;
        array = new Object[initCapacity];
    }

    void set(int index, E element) {
        if (index >= capacity || index < 0) {
            throw new IndexOutOfBoundsException(
                    "нельзя использовать индекс " + index + ", максимальный индекс " + (capacity - 1));
        } // забыла что каждое исключение это класс и нужно создавать объект
        array[index] = element;
    }

    @SuppressWarnings("unchecked")
    E get(int index) {
        if (index >= capacity || index < 0) {
            throw new IndexOutOfBoundsException(
                    "нельзя использовать индекс " + index + ", максимальный индекс " + (capacity - 1));
        }
        return (E) array[index];
    }
}
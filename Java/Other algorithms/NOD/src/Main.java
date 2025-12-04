
////Найти наибольший общий делитель всех чисел в заданном диапазоне [L, R].
//для чисел 12 и 18 равен 6
//термин: взаимопростые числа
public class Main {
    public static void main(String[] args) {
        System.out.println(getNOD(3,23));
    }
    public  static  int getNOD(int start, int end){
        if (start > end) {
            throw new IllegalArgumentException("первое число должно быть меньше или равно второму числу");
        }

        if (start == end) {
            return Math.abs(start);
        }

        return 1;
    }

}


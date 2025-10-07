import java.util.Scanner;
import java.lang.Double;

public class DoubleAndFloat {
    private static final double EPS = 1e-7;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String inputString = "";

        while (!inputString.equalsIgnoreCase("Выход")) {
            System.out.println("""
                    Операции над double/float
                    1. Сравнить
                    2. Округлить
                    3. Отбросить дробную часть
                    Чтобы выйти напишите \"выход\"""");
            inputString = scanner.nextLine();
            switch (inputString) {
                case "1" -> compare(scanner);
                case "2" -> round(scanner);
                case "3" -> discardFractionalPart(scanner);
            }

        }
        System.out.println("Конец программы");
        scanner.close();
    }

    public static void compare(Scanner scanner) {
        System.out.print("Введите первое число (double): ");
        String inputString = scanner.nextLine();
        double numD = inputDouble(inputString);
        System.out.print("Введите второе число (float): ");
        inputString = scanner.nextLine();
        float numF = inputFloat(inputString);
        double numFloatToDouble = (double) numF;
        if (Double.isNaN(numD) || Double.isNaN(numFloatToDouble)) {
            return;
        }
        double diff = numD - numFloatToDouble;
        if (Math.abs(diff) < EPS) {
            System.out.println("Числа равны");
        } else if (diff > 0) {
            System.out.println("Первое число больше");
        } else {
            System.out.println("Второе число больше");
        }
    }

    public static void round(Scanner scanner) {
        System.out.print("Введите число: ");
        String inputString = scanner.nextLine();
        double numD = inputDouble(inputString);
        System.out.println(Math.round(numD));
    }

    public static void discardFractionalPart(Scanner scanner) {
        System.out.print("Введите число: ");
        String inputString = scanner.nextLine();
        double numD = inputDouble(inputString);
        System.out.println((long) numD);
    }

    public static double inputDouble(String input) {
        try {
            Double num = Double.parseDouble(input.replace(',', '.'));
            if (Double.isInfinite(num)) {
                System.err.println("Ошибка: число слишком большое для double");
                return Double.NaN;
            }
            return num;
        } catch (NumberFormatException e) {
            System.err.println("Ошибка: введено не число");
            return Double.NaN;
        }
    }

    public static float inputFloat(String input) {
        try {
            Float num = Float.parseFloat(input.replace(',', '.'));
            if (Float.isInfinite(num)) {
                System.err.println("Ошибка: число слишком большое для float");
                return Float.NaN;
            }
            return num;
        } catch (NumberFormatException e) {
            System.err.println("Ошибка: введено не число");
            return Float.NaN;
        }
    }
}

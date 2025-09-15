// String — если строка не изменяется или изменяется редко.
// StringBuilder — если строка часто меняется (особенно в циклах).
// StringBuffer — только если нужна потокобезопасность (редко).
// StringJoiner — для объединения строк с разделителем.
// String.format() — для форматированного вывода.

//String имеет Иммутабельность(неизменяемость)

//Нет, в Java нельзя выбросить непроверяемое(extends RuntimeException) исключение без создания класса. 

//Пробрасывание исключений
//Не злоупотребляйте пробросом — иногда лучше обработать ошибку сразу.

//позже скорее всего через AOP из Spring Boot

//return String.format("%s %s %s.", parts[1], parts[2], parts[0].charAt(0));

//Когда в блоке try происходит ошибка (исключение), выполнение кода немедленно прерывается, 
//и управление передаётся в блок catch или finally. Дальнейший код внутри try после строки с ошибкой не выполняется. 
//Возврат альтернативного результата в catch

//по сути единственное почему стоило именно в данном случае пробрасывать потому что метод должен делать только одну вещь, а принт ошибки в валидации не должен быть
import java.util.Scanner;

class WrongNameException extends Exception {
    public WrongNameException(String message) {
        super(message);
    }
}

class WrongEmailException extends RuntimeException {
    public WrongEmailException(String message) {
        super(message);
    }
}

public class Disguise {

    public static void main(String[] args) {

        System.out.println("Вывод: Введите “1” для обработки ФИО и “2” для обработки email");
        try (Scanner scanner = new Scanner(System.in)) {
            int inputInt = Integer.parseInt(scanner.nextLine());
            String inputLine;

            switch (inputInt) {
                case 1:
                    inputLine = scanner.nextLine();
                    if (isValidFullName(inputLine)) {
                        System.out.println(disguiseFullName(inputLine));
                    }

                    break;
                case 2:
                    inputLine = scanner.nextLine();
                    if (isValidEmail(inputLine)) {
                        System.out.println(disguiseEmail(inputLine));
                    }

                    break;
                default:
                    System.out.println("Только 1 или 2");
            }

        } catch (Exception e) {
            System.out.println("Ошибка: Только 1 или 2");
        }

    }

    // решение через обработку на месте, потому что с пробросом главная функция
    // становится менее читаема
    public static boolean isValidFullName(String inputLine) {
        try {
            if (inputLine.matches(".*[^a-zA-Zа-яА-ЯёЁ\\s].*")) {
                throw new WrongNameException("Имя может содержать только буквы и пробелы: " + inputLine);
            }
            return true;
        } catch (Exception e) {
            System.out.println("Ошибка: " + e.getMessage());
            return false;
        }

    }

    public static boolean isValidEmail(String inputLine) {
        try {
            if (!inputLine.contains("@")) {
                throw new WrongEmailException("Почта должна содержать \'@\': " + inputLine);
            }
            return true;
        } catch (Exception e) {
            System.out.println("Ошибка:" + e.getMessage());
            return false;
        }

    }

    public static String disguiseFullName(String inputLine) {
        String[] strList = inputLine.split(" ");
        if (strList.length == 3) {
            return String.format("%s %s %c.", strList[1], strList[2], strList[0].charAt(0));
        } else {
            System.out.println("Введите корректно имя");
        }
        return "";
    }

    public static String disguiseEmail(String inputLine) {
        String[] strList = inputLine.split("@");
        if (strList.length == 2) {
            return String.format("%c***@%s", strList[0].charAt(0), strList[1]);
        } else {
            System.out.println("Введите корректно почту");
        }
        return "";
    }

}

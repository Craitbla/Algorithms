package org.example;

//Проброс исключения//

//Задача 2. Маскирование ФИО и email
//Приложение просит ввести ФИО или email и маскирует их по следующим
//правилам:
//- Для имени: Имя + Отчество + первая буква фамилии с точкой. Если
//попадаются небуквенные символы (кроме пробела), то выбрасываем
//проверяемое исключение WrongNameException
//- Для email: первая буква email + 3 звездочки (***) +“@” + окончание email.
//Если в почте не указан символ “@” выбрасываем непроверяемое
//исключение WrongEmailException.
//Пример 1:
//Вывод: Введите “1” для обработки ФИО и “2” для обработки email
//input: 1
//Вывод: Введите ФИО
//input: Тарасенко Екатерина Александровна
//Вывод: Екатерина Александровна Т.
//Пример 2:
//Вывод: Введите “1” для обработки ФИО и “2” для обработки email
//input: 2
//Вывод: Введите email
//input: tarasenko@gmail.com
//Вывод: t***@gmail.com


//Если WrongEmailException является непроверяемым исключением (наследуется от RuntimeException),
// то объявлять его в throws не нужно и даже не рекомендуется.
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

class Сamoufleur{
    public Disguise(){

    }
}
public class Main {

    public static void main(String[] args) {

        System.out.println("Вывод: Введите “1” для обработки ФИО и “2” для обработки email");
        Scanner scanner = new Scanner(System.in);
        try {
            int inputInt = Integer.parseInt(scanner.nextLine());
            String inputLine;
            switch (inputInt) {
                case 1:
                    inputLine = scanner.nextLine();
                    isValidFullName(inputLine);//throws WrongNameException Проверяемое
                    System.out.println(disguiseFullName(inputLine));// throws WrongNameException Проверяемое

                    break;
                case 2:
                    inputLine = scanner.nextLine();
                    isValidEmail(inputLine); //throws WrongEmailException  Непроверяемое
                    System.out.println(disguiseEmail(inputLine)); //throws WrongEmailException Непроверяемое

                    break;
                default:
                    System.out.println("Ошибка: Только 1 или 2");
            }

        } catch (NumberFormatException e) {
            System.out.println("Ошибка: Только цифры");
        } catch (WrongNameException | WrongEmailException e) {
            System.out.println("Ошибка: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Непредвиденная ошибка: " + e.getMessage());
        }
        scanner.close();

    }

    public static void isValidFullName(String inputLine) throws WrongNameException {
        if (inputLine == null || inputLine.isEmpty()) {
            throw new WrongNameException("Имя не может быть пустым");
        }
        if (!inputLine.matches("[a-zA-Zа-яА-ЯёЁ\\s]+")) {
            throw new WrongNameException("Имя может содержать только буквы и пробелы: " + inputLine);
        }

    }

    public static void isValidEmail(String inputLine) {
        if (inputLine == null || inputLine.isEmpty()) {
            throw new WrongEmailException("Почта не может быть пустой");
        }
        if (!inputLine.contains("@")) {
            throw new WrongEmailException("Почта должна содержать \'@\': " + inputLine);
        }
    }

    public static String disguiseFullName(String inputLine) throws WrongNameException {
        String[] strList = inputLine.split("\\s+");
        if (strList.length != 3) {
            throw new WrongNameException("Ожидается 3 слова: " + inputLine);
        }
        return String.format("%s %s %c.", strList[1], strList[2], strList[0].charAt(0));
    }

    public static String disguiseEmail(String inputLine) {
        String[] strList = inputLine.split("@");
        if (strList.length != 2) {
            throw new WrongEmailException("Ожидается почта с @ в строчке: " + inputLine);
        }
        return String.format("%c***@%s", strList[0].charAt(0), strList[1]);
    }

}

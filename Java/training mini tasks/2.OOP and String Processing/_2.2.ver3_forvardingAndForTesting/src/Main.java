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

class Camoufleur {
    public String Disguise(Integer inputInt, String inputLine) throws WrongNameException {

        if (inputInt == 1) {
            isValidFullName(inputLine);
            return disguiseFullName(inputLine);
        } else {
            isValidEmail(inputLine);
            return disguiseEmail(inputLine);
        }


    }


    public void isValidFullName(String inputLine) throws WrongNameException {
        if (inputLine == null || inputLine.isEmpty()) {
            throw new WrongNameException("Имя не может быть пустым");
        }
        if (!inputLine.matches("[a-zA-Zа-яА-ЯёЁ\\s]+")) {
            throw new WrongNameException("Имя может содержать только буквы и пробелы: " + inputLine);
        }

    }

    public void isValidEmail(String inputLine) {
        if (inputLine == null || inputLine.isEmpty()) {
            throw new WrongEmailException("Почта не может быть пустой");
        }
        if (!inputLine.contains("@")) {
            throw new WrongEmailException("Почта должна содержать '@': " + inputLine);
        }
    }

    public String disguiseFullName(String inputLine) throws WrongNameException {
        String[] strList = inputLine.split("\\s+");
        if (strList.length != 3) {
            throw new WrongNameException("Ожидается 3 слова: " + inputLine);
        }
        return String.format("%s %s %c.", strList[1], strList[2], strList[0].charAt(0));
    }

    public String disguiseEmail(String inputLine) {
        String[] strList = inputLine.split("@");
        if (strList.length != 2) {
            throw new WrongEmailException("Почта должна содержать имя до '@' и вид почты после: " + inputLine);
        }
        return String.format("%c***@%s", strList[0].charAt(0), strList[1]);
    }

}

public class Main {

    public static void main(String[] args) {


        Scanner scanner = new Scanner(System.in);
        Camoufleur camoufleur = new Camoufleur();
        boolean flagContinue = true;
        int inputInt;
        String inputLine;

        while (flagContinue) {
            try {
                System.out.println("Введите “1” для обработки ФИО и “2” для обработки email или “3” для выхода");
                inputInt = Integer.parseInt(scanner.nextLine());
                if (inputInt == 1 || inputInt == 2) {
                    System.out.println("Введите данные для обработки:");
                    inputLine = scanner.nextLine();
                    System.out.println(camoufleur.Disguise(inputInt, inputLine));
                } else if (inputInt == 3) {
                    flagContinue = false;
                    System.out.println("Программа завершена");
                } else {
                    throw new Exception("Ошибка: Только 1, 2 или 3");
                }

            } catch (NumberFormatException e) {
                System.out.println("Ошибка: Только цифры");
            } catch (WrongNameException e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }


        scanner.close();

    }
}

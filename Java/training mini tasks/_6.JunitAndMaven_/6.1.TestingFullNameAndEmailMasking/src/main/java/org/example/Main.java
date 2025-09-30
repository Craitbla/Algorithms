package org.example;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

enum Choice {
    FULLNAME(1),
    EMAIL(2),
    EXIT(3);
    private final int value;

    Choice(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}

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

//не парься что это займет время, это большая тренировка и трудное задание, переделывать это нормально
//главный вопрос жизни, почему это классы, а не просто функциональные интерфейсы
interface Validator { // все поля в интерфейсе final //Parser parser = null;

    void isValid(String inputLine) throws WrongNameException;
}

interface Disguiser {
    String disguise(String[] strList);
}

interface Parser {
    String[] parse(String inputLine) throws WrongNameException;
}

class FullNameParser implements Parser {
    @Override
    public String[] parse(String inputLine) throws WrongNameException {
        String[] strList = inputLine.split("\\s+");
        if (strList.length != 3) {
            throw new WrongNameException("Ожидается 3 слова");
        }
        return strList;
    }
}

class EmailParser implements Parser {
    @Override
    public String[] parse(String inputLine) {
        String[] strList = inputLine.split("@");
        if (strList.length != 2) {
            throw new WrongEmailException("Почта должна содержать имя до '@' и вид почты после");
        }
        return strList;
    }
}

class FullNameValidator implements Validator {

    @Override
    public void isValid(String inputLine) throws WrongNameException {
        if (inputLine == null || inputLine.isEmpty()) {
            throw new WrongNameException("Имя не может быть пустым");
        }
        if (!inputLine.matches("[a-zA-Zа-яА-ЯёЁ\\s]+")) {
            throw new WrongNameException("Имя может содержать только буквы и пробелы");
        }
    }
}

class EmailValidator implements Validator {
    @Override
    public void isValid(String inputLine) {
        if (inputLine == null || inputLine.isEmpty()) {
            throw new WrongEmailException("Почта не может быть пустой");
        }
        if (!inputLine.contains("@")) {
            throw new WrongEmailException("Почта должна содержать '@'");
        }
    }
}

class FullNameDisguiser implements Disguiser {
    @Override
    public String disguise(String[] strList) {
        return String.format("%s %s %c.", strList[1], strList[2], strList[0].charAt(0));
    }
}

class EmailDisguiser implements Disguiser {
    @Override
    public String disguise(String[] strList) {
        return String.format("%c***@%s", strList[0].charAt(0), strList[1]);
    }
}

class Camoufleur {
    public Map<Choice, Processor> processors = new HashMap<>();

    Camoufleur() {
        processors.put(Choice.FULLNAME, new Processor(new FullNameValidator(), new FullNameParser(), new FullNameDisguiser()));
        processors.put(Choice.EMAIL, new Processor(new EmailValidator(), new EmailParser(), new EmailDisguiser()));
    }

    public String camoufleure(Integer inputInt, String inputLine) throws WrongNameException {
        return processors.get(inputInt).process(inputLine);
    }

    public class Processor {
        Validator validator;
        Parser parser;
        Disguiser disguiser;


        Processor(Validator newValidator, Parser newParser, Disguiser newDisguiser) {
            this.validator = newValidator; //выдает исключения
            this.parser = newParser; //выдает исключения тоже, но свои
            this.disguiser = newDisguiser;
        }

        String process(String inputLine) throws WrongNameException {
            validator.isValid(inputLine);
            String[] strList = parser.parse(inputLine);
            return disguiser.disguise(strList);
        }
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
                if (inputInt == Choice.FULLNAME.getValue() || inputInt == Choice.EMAIL.getValue()) {
                    System.out.println("Введите данные для обработки:");
                    inputLine = scanner.nextLine();
                    System.out.println(camoufleur.camoufleure(inputInt, inputLine));
                } else if (inputInt == Choice.EXIT.getValue()) {
                    flagContinue = false;
                    System.out.println("Программа завершена");
                } else {
                    throw new Exception("Ошибка: Только 1, 2 или 3");
                }

            } catch (NumberFormatException e) {
                System.out.println("Ошибка: Только цифры");
            } catch (WrongNameException e) {
                //насколько я понимаю эта часть не имеет смысла,
                // так как никакой новой информации здесь нет
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }


        scanner.close();

    }
}

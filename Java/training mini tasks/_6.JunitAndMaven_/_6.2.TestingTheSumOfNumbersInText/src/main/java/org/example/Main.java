package org.example;

import java.util.regex.*;
import java.util.Scanner;

class Summator {
    final private Pattern digitPattern = Pattern.compile("\\d+\\.?\\d*");
    String SummDigitInString(String inputStr) {

        if(inputStr == null || inputStr.equals("")|| !inputStr.matches(".*\\d.*")){
            return "0";
        }
        inputStr = inputStr.replace(',', '.');

        Matcher matcher = digitPattern.matcher(inputStr);

        StringBuilder resSB = new StringBuilder();
        double summ = 0;
        matcher.find();
        resSB.append(matcher.group());
        summ += Double.parseDouble(matcher.group());
        while (matcher.find()) {
            resSB.append(" + ").append(matcher.group());
            summ += Double.parseDouble(matcher.group());
        }
        String resStr;
        if (summ != 0) {
            resSB.append(" = ").append(summ);
            resStr = resSB.toString();
            resStr = resStr.replace('.', ',');

        } else {
            resStr = "0";
        }

        return resStr;
    }
}

public class Main {


    public static void main(String[] args) {


        Summator summator = new Summator();
        boolean flagContinue = true;
        String inputLine;

        while (flagContinue) {
            try (Scanner scanner = new Scanner(System.in)){
                System.out.println("Введите текст для сумматора или ESC для выхода");
                inputLine = scanner.nextLine();
                if (!inputLine.equals("\n\0")) {
                    System.out.println("Введите данные для обработки:");
                    inputLine = scanner.nextLine();
                    System.out.println(summator.SummDigitInString(inputLine));
                } else {
                    flagContinue = false;
                    System.out.println("Программа завершена");
                }

            }  catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }


    }
}
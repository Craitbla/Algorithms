package org.example;
import java.util.regex.*;
import java.util.Scanner;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.


class SummInStr {
}
public class Main {


        public static void main(String[] args) {
            try (Scanner scanner = new Scanner(System.in)) {
                String inputStr = scanner.nextLine();
                inputStr = inputStr.replace(',', '.');
                Pattern digitPattern = Pattern.compile("\\d+\\.?\\d*");
                Matcher matcher = digitPattern.matcher(inputStr);

                StringBuilder resSB = new StringBuilder();
                double summ = 0;
                matcher.find();
                resSB = resSB.append(matcher.group());
                summ += Double.parseDouble(matcher.group());
                while (matcher.find()) {
                    resSB = resSB.append(" + ").append(matcher.group());
                    summ += Double.parseDouble(matcher.group());
                }
                resSB = resSB.append(" = ").append(summ);
                String resStr = resSB.toString();
                resStr = resStr.replace('.', ',');
                System.out.println(resStr);
            }
        }
}
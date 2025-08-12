import java.util.Scanner;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class Events {
  public enum Operation {
    INCOME,
    EXPENSE
  }

  private static final BigDecimal FIRST_TAX_FACTOR = new BigDecimal("0.05");
  private static final BigDecimal SECOND_TAX_FACTOR = new BigDecimal("0.18");

  private static BigDecimal income = BigDecimal.ZERO;
  private static BigDecimal expense = BigDecimal.ZERO;

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    String inputString = "";

    while (!inputString.equalsIgnoreCase("Выход")) {
      System.out.println("Выберите операцию и введите ее номер:");
      System.out.println("1. Добавить новый доход");
      System.out.println("2. Добавить новый расход");
      System.out.println("3. Выбрать систему налогообложения\n");

      inputString = scanner.nextLine();
      switch (inputString) {
        case "1":
          System.out.println("Введите сумму дохода:");
          inputString = scanner.nextLine();
          addInput(Operation.INCOME, inputString);
          break;

        case "2":
          System.out.println("Введите сумму расхода:");
          inputString = scanner.nextLine();
          addInput(Operation.EXPENSE, inputString);
          break;

        case "3":
          BigDecimal firstTypeTax = calculationOfFirstTypeOfTaxes();
          BigDecimal secondTypeTax = calculationOfSecondTypeOfTaxes();
          if (firstTypeTax.compareTo(secondTypeTax) < 0) {
            System.out.println("Советую вам УСН доходы");
            printBenefits(firstTypeTax, secondTypeTax);
          } else if (firstTypeTax.compareTo(secondTypeTax) > 0) {
            System.out.println("Советую вам УСН доходы минус расходы");
            printBenefits(secondTypeTax, firstTypeTax);
          } else {
            System.out.println("Налоги в расчете по системам равны");
            System.out.println("Ваш налог составит: " + firstTypeTax.setScale(2, RoundingMode.HALF_EVEN) + " рублей");
          }
          break;

        default:
          break;
      }
    }
    System.out.println("Конец программы");
    scanner.close();
  }

  public static void addInput(Operation operation, String inputStr) {
    BigDecimal inputBigDecimalNum = inputBigDecimal(inputStr);

    if (inputBigDecimalNum == null) {
      System.out.println("Ошибка: введено не число\n");
    } else if (inputBigDecimalNum.compareTo(BigDecimal.ZERO) < 0) {
      System.out.println("Ошибка: введено отрицательное число\n");
    } else {
      if (operation == Operation.INCOME) {
        income = income.add(inputBigDecimalNum);
      } else {
        expense = expense.add(inputBigDecimalNum);
      }

    }
  }

  public static void printBenefits(BigDecimal profitable, BigDecimal unprofitable) {
    BigDecimal savings = unprofitable.subtract(profitable).setScale(2, RoundingMode.HALF_EVEN);
    profitable = profitable.setScale(2, RoundingMode.HALF_EVEN);
    unprofitable = unprofitable.setScale(2, RoundingMode.HALF_EVEN);

    System.out.println("Ваш налог составит: " + profitable + " рублей");
    System.out.println("Налог по другой системе: " + unprofitable + " рублей");
    System.out.println("Экономия: " + savings + " рублей");
  }

  public static BigDecimal calculationOfFirstTypeOfTaxes() {
    if (income.compareTo(BigDecimal.ZERO) <= 0)
      return BigDecimal.ZERO;
    return income.multiply(FIRST_TAX_FACTOR);
  }

  public static BigDecimal calculationOfSecondTypeOfTaxes() {
    if (expense.compareTo(income) >= 0)
      return BigDecimal.ZERO;
    return income.subtract(expense).multiply(SECOND_TAX_FACTOR);
  }

  public static BigDecimal inputBigDecimal(String input) {
    input = input.replace(',', '.');
    try {
      return new BigDecimal(input);
    } catch (NumberFormatException e) {
      return null;
    }
  }
}

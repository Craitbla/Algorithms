package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
//"Метод_Условие_ОжидаемыйРезультат".
//MethodName_StateUnderTest_ExpectedBehavio
//maskFullName_ValidInput_CorrectlyMasked

// не Public!!!  и должен ЗАКАНЧИВАТЬСЯ на Test - *Test.java или *Tests.java

//Моки
//Надежность: Тест не сломается, если изменится логика валидации
//Этот подход следует принципу "тестируй поведение, а не реализацию"
// - мы проверяем, что при валидном email возвращается правильная маскировка,
// а валидация действительно вызывается.


//План:
//сделать общие интерфейсы, почему интерфейсы, а не абстрактные классы?
//не нужна конкретная реализация, по сути без разницы

//сделать отдельные классы для валидации

class PositiveMaskingTest {
    final private Camoufleur camoufleur = new Camoufleur();

    //    если бы были внут
    @Test
    void maskFullName_ValidInput_CorrectlyMasked() throws WrongNameException {
        String inputLine = "Иванова Карина Олеговна";
        String expected = "Карина Олеговна И.";
        String result = camoufleur.Disguise(1, inputLine);
        assertEquals(expected, result, "для ФИО '" + inputLine + "' ожидался результат '" + expected + "'");
    }

    @Test
    void maskEmail_ValidInput_CorrectlyMasked() throws WrongNameException {
        String result = camoufleur.Disguise(2, "ki225431@gmail.com");
        assertEquals("k***@gmail.com", result);
    }
}

class CheckedExceptionsTest {
    final private Camoufleur camoufleur = new Camoufleur();
    final int choise = 1;

    @Test
    void maskFullName_EmptyInput_WrongNameException() {
        WrongNameException exeption = assertThrows(
                WrongNameException.class,
                () -> camoufleur.Disguise(choise, null)
        );
        Assertions.assertTrue(exeption.getMessage().contains("Имя не может быть пустым"));
    }

    @Test
    void maskFullName_DigitInput_WrongNameException() {
        String inputLine = "Ивано124 Карина Олеговна";
        WrongNameException exeption = assertThrows(
                WrongNameException.class,
                () -> camoufleur.Disguise(choise, inputLine)
        );
        Assertions.assertTrue(exeption.getMessage().contains("Имя может содержать только буквы и пробелы: " + inputLine));
    }

    @Test
    void maskFullName_InvalidInput_WrongNameException() {
        String inputLine = "Иванова Карина";
        WrongNameException exeption = assertThrows(
                WrongNameException.class,
                () -> camoufleur.Disguise(choise, inputLine)
        );
        Assertions.assertTrue(exeption.getMessage().contains("Ожидается 3 слова: " + inputLine));
    }


}

// Для непроверяемого исключения - ТОЧНО ТАК ЖЕ!
class UncheckedExceptionsTest {
    final private Camoufleur camoufleur = new Camoufleur();
    final int choise = 2;

    @Test
    void maskEmail_EmptyInput_WrongEmailException() {
        WrongEmailException exeption = assertThrows(
                WrongEmailException.class,
                () -> camoufleur.Disguise(choise, null)
        );
        Assertions.assertTrue(exeption.getMessage().contains("Почта не может быть пустой"));
    }

    @Test
    void maskEmail_WithoutDog_WrongEmailException() {
        String inputLine = "ki225431gmail.com";
        WrongEmailException exeption = assertThrows(
                WrongEmailException.class,
                () -> camoufleur.Disguise(choise, inputLine)
        );
        Assertions.assertTrue(exeption.getMessage().contains("Почта должна содержать '@': " + inputLine));
    }

    @Test
    void maskEmail_WithoutPartAfterDog_WrongEmailException() {
        String inputLine = "ki225431@";
        WrongEmailException exeption = assertThrows(
                WrongEmailException.class,
                () -> camoufleur.Disguise(choise, inputLine)
        );
        Assertions.assertTrue(exeption.getMessage().contains("Почта должна содержать имя до '@' и вид почты после: " + inputLine));
    }
}

package org.example;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
//"Метод_Условие_ОжидаемыйРезультат".
//MethodName_StateUnderTest_ExpectedBehavio

//maskFullName_ValidInput_CorrectlyMasked
//"maskFullName" - это метод, который мы тестируем.
//"ValidInput" - условие (валидный входной данные).
//"CorrectlyMasked" - ожидаемый результат (корректно замаскировано).

//Забыла про моки
//пока первые тесты сделаю без них, чтобы лучше понять

// не Public!!!
class TestPositiveMasking {
//    @BeforeEach
//    для создания объекта - бред, потому что все равно нужно
//    будет возвращать, а это одна строчка
    @Test
    void maskFullName_ValidInput_CorrectlyMasked() throws WrongNameException{
        Camoufleur camoufleur = new Camoufleur();
        String result =camoufleur.Disguise(1,"Иванова Карина Олеговна");
        assertEquals("Карина Олеговна И.", result);
    }

//    @Test
//    void maskEmail_ValidInput_CorrectlyMasked() {
//Camoufleur camoufleur = new Camoufleur();
//    String result =camoufleur.Disguise(1,"Иванова Карина Олеговна");
//    assertEquals("Карина Олеговна И.", result);
//        // Тестируем корректное маскирование email
//    }
}
//
//class CheckedExeptionsTest {
//    @Test
//    void maskFullName_InvalidInput_WrongNameException() {
//        WrongNameExeption exeption = assertThrows(
//                WrongNameExeption
//        )
//    }
//
//}
//
//class UncheckedExeptionsTest {
//    @Test
//    void maskFullName_ValidInput_CorrectlyMasked() {
//        // Тестируем корректное маскирование ФИО
//    }
//
//    @Test
//    void maskEmail_ValidInput_CorrectlyMasked() {
//        // Тестируем корректное маскирование email
//    }
//}
//public class MyTest {
//
//    @BeforeEach
//    public void setUp() {
//        // Метод, выполняющийся перед каждым тестовым случаем
//    }
//
//    @AfterEach
//    public void tearDown() {
//        // Метод, выполняющийся после каждого тестового случая
//    }
//
//    @Tag("Positive scenario")
//    @Test
//    public void testSomething() {
//        // Тестовый случай
//    }
//
//    @Tag("Positive scenario")
//    @Test
//    public void testAnotherThing() {
//        // Другой тестовый случай
//    }
//
//    @Tag("Negative scenario")
//    @Test
//    public void testAnotherThing() {
//        // Другой тестовый случай
//    }
//    import org.junit.jupiter.api.Test;
//import static org.junit.jupiter.api.Assertions.*;
//
//    // Для непроверяемых исключений (например, RuntimeException)
//    @Test
//    void testUncheckedException() {
//        IllegalArgumentException exception = assertThrows(
//                IllegalArgumentException.class,
//                () -> methodThatThrowsException()
//        );
//        assertEquals("Invalid argument", exception.getMessage());
//    }
//
//    // Для проверяемых исключений (например, IOException)
//    @Test
//    void testCheckedException() {
//        IOException exception = assertThrows(
//                IOException.class,
//                () -> methodThatThrowsCheckedException()
//        );
//        assertTrue(exception.getMessage().contains("File not found"));
//    }
//
//}
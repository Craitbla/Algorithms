package org.example;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

//"Метод_Условие_ОжидаемыйРезультат".
//MethodName_StateUnderTest_ExpectedBehavior
//maskFullName_ValidInput_CorrectlyMasked

// не Public!!!  и должен ЗАКАНЧИВАТЬСЯ на Test - *Test.java или *Tests.java

//Моки
//Надежность: Тест не сломается, если изменится логика валидации
//Этот подход следует принципу "тестируй поведение, а не реализацию"
// - мы проверяем, что при валидном email возвращается правильная маскировка,
// а валидация действительно вызывается.


//План:
//@Nested
//@DisplayName
//1)Параметризация
//2)моки

class CamoufleurTest {
    private final Camoufleur camoufleur = new Camoufleur();

    @Nested
    @DisplayName("Маскировка ФИО")
    class FullNameDisguise {
        private final Camoufleur.Processor processor = camoufleur.processors.get(Choice.FULLNAME);

        @Nested
        @DisplayName("Успешные сценарии")
        class Positive {
            private static Stream<Arguments> provideValidFullNames() {
                return Stream.of(
                        Arguments.of("Иванова Карина Олеговна", "Карина Олеговна И.")
                );
            }

            //            @CsvSource({ //не подходит здесь потому что его надо повторять,
//            а @MethodSource просто дает ссылку одной строчкой
//                    "'Иванова Карина Олеговна', 'Карина Олеговна И.'"
//            })
            @ParameterizedTest //честно, чуть-чуть оверкод, но ладно
            @MethodSource("provideValidFullNames")
            void isValid_validFullName_returnsWithoutException(String inputLine) throws WrongNameException {
                processor.validator.isValid(inputLine); ////////////////
            }

            @ParameterizedTest
            @MethodSource("provideValidFullNames")
            void disguise_validFullName_returnsCorrect(String inputLine, String expected) throws WrongNameException {  ////////////////
                String result = processor.disguiser.disguise(inputLine);
                assertEquals(expected, result, "для ФИО '" + inputLine + "' ожидался результат '" + expected + "'");
            }
        }

        @Nested
        @DisplayName("Ошибочные сценарии")  ////////////////
        class Negative {
            private static Stream<Arguments> provideInvalidFullNames() {
                return Stream.of(
                        Arguments.of(
                                "", "Имя не может быть пустым"),
                        Arguments.of(
                                "Ивано124 Карина Олеговна", "Имя может содержать только буквы и пробелы: {0}"),
                        Arguments.of("Иванова Карина", "Ожидается 3 слова: {0}")
                );
            }

            @ParameterizedTest
            @MethodSource("provideInvalidFullNames")
            void isValid_emptyFullName_throwsException(String inputLine, String expected) {
                WrongNameException exception = assertThrows(
                        WrongNameException.class,
                        () -> processor.validator.isValid(inputLine)
                );
                Assertions.assertTrue(exception.getMessage().contains(expected));
            }
//            @Test void fullNameWithDigits_throwsException() {...} //моки
        }
    }

//    @Nested
//    @DisplayName("Маскировка Email")
//    class EmailDisguise {
//        private final Camoufleur.Processor processor = camoufleur.processors.get(Choice.EMAIL);
//
//        @Nested
//        @DisplayName("Успешные сценарии")
//        class Positive {
//            @Test void isValid_validEmail_returnsCorrectlyMasked() {...}
//            @Test void disguise_validFullName_returnsCorrect(){}
//        }
//
//        @Nested
//        @DisplayName("Ошибочные сценарии")
//        class Negative {
//            @Test void emailWithoutAt_throwsException() {...}
//            @Test void disguise_validFullName_returnsCorrect(){}
//        }
//    }
}
//@DisplayName("Тесты валидации")
//class ValidationTests {
//
//    @DisplayName("Тесты валидации FullName")
//    @Nested
//    class FullNameValidationTests {
//        private FullNameValidator validator = new FullNameValidator();
//
////        @BeforeEach
////        void setUp() {
////            validator = new FullNameValidator();
////        }
//
//        //        @ParameterizedTest //потом
////        @CsvSource({
////
////        })
//        @Test
//        void maskFullName_ValidInput_CorrectlyMasked() throws WrongNameException {
//            assertDoesNotThrow(() -> validator.isValid());
////            String inputLine = "Иванова Карина Олеговна";
////            String expected = "Карина Олеговна И.";
////            String result = camoufleur.Disguise(1, inputLine);
////            assertEquals(expected, result, "для ФИО '" + inputLine + "' ожидался результат '" + expected + "'");
//        }
//    }
//
//    @DisplayName("Тесты валидации Email")
//    @Nested
//    class EmailValidationTests {
//
//    }
//
//}
//
////потом проверка большого класса через моки
//class PositiveMaskingTest {
//    final private Camoufleur camoufleur = new Camoufleur();
//
//    //    если бы были внут
//    @Test ////////////////
//    void maskFullName_ValidInput_CorrectlyMasked() throws WrongNameException {
//        String inputLine = "Иванова Карина Олеговна";
//        String expected = "Карина Олеговна И.";
//        String result = camoufleur.Disguise(1, inputLine);
//        assertEquals(expected, result, "для ФИО '" + inputLine + "' ожидался результат '" + expected + "'");
//    }
//
//    @Test
//    void maskEmail_ValidInput_CorrectlyMasked() throws WrongNameException {
//        String result = camoufleur.Disguise(2, "ki225431@gmail.com");
//        assertEquals("k***@gmail.com", result);
//    }
//}
//
//class CheckedExceptionsTest {
//    final private Camoufleur camoufleur = new Camoufleur();
//    final int choise = 1;
//
//    @Test
//    void maskFullName_EmptyInput_WrongNameException() {
//        WrongNameException exeption = assertThrows(
//                WrongNameException.class,
//                () -> camoufleur.Disguise(choise, null)
//        );
//        Assertions.assertTrue(exeption.getMessage().contains("Имя не может быть пустым"));
//    }
//
//    @Test
//    void maskFullName_DigitInput_WrongNameException() {
//        String inputLine = "Ивано124 Карина Олеговна";
//        WrongNameException exeption = assertThrows(
//                WrongNameException.class,
//                () -> camoufleur.Disguise(choise, inputLine)
//        );
//        Assertions.assertTrue(exeption.getMessage().contains("Имя может содержать только буквы и пробелы: " + inputLine));
//    }
//
//    @Test
//    void maskFullName_InvalidInput_WrongNameException() {
//        String inputLine = "Иванова Карина";
//        WrongNameException exeption = assertThrows(
//                WrongNameException.class,
//                () -> camoufleur.Disguise(choise, inputLine)
//        );
//        Assertions.assertTrue(exeption.getMessage().contains("Ожидается 3 слова: " + inputLine));
//    }
//
//
//}
//
//// Для непроверяемого исключения - ТОЧНО ТАК ЖЕ!
//class UncheckedExceptionsTest {
//    final private Camoufleur camoufleur = new Camoufleur();
//    final int choise = 2;
//
//    @Test
//    void maskEmail_EmptyInput_WrongEmailException() {
//        WrongEmailException exeption = assertThrows(
//                WrongEmailException.class,
//                () -> camoufleur.Disguise(choise, null)
//        );
//        Assertions.assertTrue(exeption.getMessage().contains("Почта не может быть пустой"));
//    }
//
//    @Test
//    void maskEmail_WithoutDog_WrongEmailException() {
//        String inputLine = "ki225431gmail.com";
//        WrongEmailException exeption = assertThrows(
//                WrongEmailException.class,
//                () -> camoufleur.Disguise(choise, inputLine)
//        );
//        Assertions.assertTrue(exeption.getMessage().contains("Почта должна содержать '@': " + inputLine));
//    }
//
//    @Test
//    void maskEmail_WithoutPartAfterDog_WrongEmailException() {
//        String inputLine = "ki225431@";
//        WrongEmailException exeption = assertThrows(
//                WrongEmailException.class,
//                () -> camoufleur.Disguise(choise, inputLine)
//        );
//        Assertions.assertTrue(exeption.getMessage().contains("Почта должна содержать имя до '@' и вид почты после: " + inputLine));
//    }
//}

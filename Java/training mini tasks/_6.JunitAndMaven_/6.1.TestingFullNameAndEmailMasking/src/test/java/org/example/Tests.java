package org.example;

import org.junit.jupiter.api.*;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Map;
import java.util.stream.Stream;

//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.ArgumentMatchers.anyString;
//import static org.mockito.Mockito.*;

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

//// Тестируем только координацию, а не логику валидации в моках
//@Nested не статик, но
//public static Stream<Arguments> provideValidFullName()
//@MethodSource("org.example.Tests#provideValidFullName") !!!

//@Mock
//private Processor mockProcessor;
//
//@BeforeEach
//    void setUp() {
//            reset(mockProcessor); // Нужно сбрасывать между тестами
//            } поэтому используют в каждом тесте Processor mock = mock(Processor.class); который не надо стирать потом
@ExtendWith(MockitoExtension.class)
class Tests {
    public static Stream<Arguments> provideValidFullName() {
        return Stream.of(
                Arguments.of("Иванова Карина Олеговна", new String[]{"Иванова", "Карина", "Олеговна"}, "Карина Олеговна И."));
    }

    public static Stream<Arguments> provideValidEmail() {
        return Stream.of(Arguments.of("ki225431@gmail.com", new String[]{"ki225431", "gmail.com"}, "k***@gmail.com")
        );
    }

    public static Stream<Arguments> provideValid() {
        return Stream.concat(
                provideValidFullName(),
                provideValidEmail()
        );
    }

    public static Stream<Arguments> provideInvalidFullNameForValidatorAndDisguiser() {
        return Stream.of(
                Arguments.of("", "Имя не может быть пустым"),
                Arguments.of("Ивано124 Карина Олеговна", "Имя может содержать только буквы и пробелы")
        );
    }

    public static Stream<Arguments> provideInvalidEmailForValidatorAndDisguiser() {
        return Stream.of(
                Arguments.of("", "Почта не может быть пустой"),
                Arguments.of("ki225431gmail.com", "Почта должна содержать '@'")
        );
    }

    public static Stream<Arguments> provideInvalidFullNameForParser() {
        return Stream.of(
                Arguments.of("Иванова Карина", "Ожидается 3 слова"),
                Arguments.of("Иванова Карина Ольга Олеговна ", "Ожидается 3 слова")
        );
    }

    public static Stream<Arguments> provideInvalidEmailForParser() {
        return Stream.of(
                Arguments.of("ki225431gmail.com", "Почта должна содержать имя до '@' и вид почты после"),
                Arguments.of("ki2@25431@gmail.com", "Почта должна содержать имя до '@' и вид почты после")
        );
    }
//
//    @Nested
//    @DisplayName("Тестирование компонентов")
//    class ComponentsTests {
//
//
//        @Nested
//        class ValidatorsTests {
//            FullNameValidator fullNameValidator = new FullNameValidator();
//
//            @ParameterizedTest
//            @MethodSource("org.example.Tests#provideValidFullName")
//            void isValid_validFullName_returnsWithoutException(String inputLine, String[] parsed, String expected) throws WrongNameException {
//                fullNameValidator.isValid(inputLine);
//            }
//
//            @ParameterizedTest
//            @MethodSource("org.example.Tests#provideInvalidFullNameForValidatorAndDisguiser")
//            void isValid_InvalidFullName_throwsException(String inputLine, String expected) {
//                WrongNameException exception = assertThrows(
//                        WrongNameException.class,
//                        () -> fullNameValidator.isValid(inputLine)
//                );
//                Assertions.assertTrue(exception.getMessage().contains(expected));
//            }
//
//            EmailValidator emailValidator = new EmailValidator();
//
//            @ParameterizedTest
//            @MethodSource("org.example.Tests#provideValidEmail")
//            void isValid_validEmail_returnsWithoutException(String inputLine) {
//                emailValidator.isValid(inputLine);
//            }
//
//            @ParameterizedTest
//            @MethodSource("org.example.Tests#provideInvalidEmailForValidatorAndDisguiser")
//            void isValid_InvalidEmail_throwsException(String inputLine, String expected) {
//                WrongEmailException exception = assertThrows(
//                        WrongEmailException.class,
//                        () -> emailValidator.isValid(inputLine)
//                );
//                Assertions.assertTrue(exception.getMessage().contains(expected));
//            }
//        }
//
//        @Nested
//        class ParsersTests {
//            FullNameParser fullNameParser = new FullNameParser();
//
//            @ParameterizedTest
//            @MethodSource("org.example.Tests#provideValidFullName")
//            void parse_validFullName_returnsWithoutException(String inputLine, String[] parsed) throws WrongNameException {
//                String[] result = fullNameParser.parse(inputLine);
//                assertArrayEquals(parsed, result);
//            }
//
//            @ParameterizedTest
//            @MethodSource("org.example.Tests#provideInvalidFullNameForParser")
//            void parse_invalidFullName_throwsException(String inputLine, String expected) throws WrongNameException {
//                WrongNameException exception = assertThrows(
//                        WrongNameException.class,
//                        () -> fullNameParser.parse(inputLine)
//                );
//                Assertions.assertTrue(exception.getMessage().contains(expected));
//            }
//
//            EmailParser emailParser = new EmailParser();
//
//            @ParameterizedTest
//            @MethodSource("org.example.Tests#provideValidEmail")
//            void parse_validEmail_returnsWithoutException(String inputLine, String[] parsed) throws WrongNameException {
//                String[] result = emailParser.parse(inputLine);
//                assertArrayEquals(parsed, result);
//            }
//
//            @ParameterizedTest
//            @MethodSource("org.example.Tests#provideInvalidEmailForParser")
//            void parse_invalidEmail_throwsException(String inputLine, String expected) throws WrongNameException {
//                WrongEmailException exception = assertThrows(
//                        WrongEmailException.class,
//                        () -> emailParser.parse(inputLine)
//                );
//                Assertions.assertTrue(exception.getMessage().contains(expected));
//            }
//        }
//
//        @Nested
//        class DisguisersTests { //только хорошие случаи потому что исключений он ен выдает
//            FullNameDisguiser fullNameDisguiser = new FullNameDisguiser(); //чтоб наверняка
//
//            @ParameterizedTest
//            @MethodSource("org.example.Tests#provideValidFullName")
//            void disguise_validFullName_returnsWithoutException(String inputLine, String[] parsed, String expected) {
//                String result = fullNameDisguiser.disguise(parsed);
//                Assertions.assertEquals(result, expected);
//            }
//
//
//            EmailDisguiser emailDisguiser = new EmailDisguiser();
//
//            @ParameterizedTest
//            @MethodSource("org.example.Tests#provideValidEmail")
//            void disguise_validEmail_returnsWithoutException(String inputLine, String[] parsed, String expected) {
//                String result = emailDisguiser.disguise(parsed);
//                Assertions.assertEquals(result, expected);
//            }
//        }
//    }

    @Nested
    @DisplayName("Unit тесты больших классов с моками")
    class UnitTests {
        @Mock
        private Validator mockedValidator;
        @Mock
        private Parser mockedParser;
        @Mock
        private Disguiser mockedDisguiser;


        // @InjectMocks работало бы если бы это бал бы не внутренний класс
//        @Nested
//        class ProcessorsTests {
//            private Camoufleur.Processor processor;
//
//            @BeforeEach
//            void setUp() {
//                processor = new Camoufleur().new Processor(mockedValidator, mockedParser, mockedDisguiser);
//            }
//
//            @ParameterizedTest
////гпт не прав, по крайней мере у меня,
//// process_ValidInput_CallsAllComponentsInOrder точно видит и первый сурс и второй
//            @MethodSource("org.example.Tests#provideValidFullName")
//            @MethodSource("org.example.Tests#provideValidEmail")
//            void process_ValidInput_CallsAllComponentsInOrder(
//                    String input, String[] parsed, String expected) throws WrongNameException {
//                doNothing().when(mockedValidator).isValid(input);
//                when(mockedParser.parse(input)).thenReturn(parsed);
//                when(mockedDisguiser.disguise(parsed)).thenReturn(expected);
//
//                String result = processor.process(input);
//
//                Assertions.assertEquals(result, expected);
//
//                verify(mockedValidator).isValid(input);
//                verify(mockedParser).parse(input);
//                verify(mockedDisguiser).disguise(parsed);
//            }
//
//            @Test
//            void process_ValidatorThrowsNameException_ProcessStops() throws WrongNameException {
//                doThrow(new WrongNameException("test")).when(mockedValidator).isValid(any());
//
//                assertThrows(WrongNameException.class, () -> processor.process("any input"));
//                verify(mockedParser, never()).parse(any());
//                verify(mockedDisguiser, never()).disguise(any());
//            }
//
//            @Test
//            void process_ParserThrowsNameException_ProcessStops() throws WrongNameException {
//                doThrow(new WrongNameException("test")).when(mockedParser).parse(any());
//
//                assertThrows(WrongNameException.class, () -> processor.process("any input"));
//                verify(mockedDisguiser, never()).disguise(any());
//            }
//
//            @Test
//            void process_ValidatorThrowsEmailException_ProcessStops() throws WrongNameException {
//                doThrow(new WrongEmailException("test")).when(mockedValidator).isValid(any());
//
//                assertThrows(WrongEmailException.class, () -> processor.process("any input"));
//                verify(mockedParser, never()).parse(any());
//                verify(mockedDisguiser, never()).disguise(any());
//            }
//
//            @Test
//            void process_ParserThrowsEmailException_ProcessStops() throws WrongNameException {
//                doThrow(new WrongEmailException("test")).when(mockedParser).parse(any());
//
//                assertThrows(WrongEmailException.class, () -> processor.process("any input"));
//                verify(mockedDisguiser, never()).disguise(any());
//            }
//
//
//        } //6
        @ExtendWith(MockitoExtension.class)
        @Nested
        class CamoufleurTests {

            //проблема была в том что функция принимающая енам
            // не могла переварить интеджер и ломала работу метода

            // Убираем @InjectMocks и инициализируем карту вручную
//            private Map<Choice, Camoufleur.Processor> mockedProcessors;
            private Camoufleur camoufleur = new Camoufleur(true);

            @ParameterizedTest
            @MethodSource("org.example.Tests#provideValidFullName")
            void camoufleure_FullNameChoice_CallsFullNameProcessor(String input, String[] ignored, String expected) throws WrongNameException {
                // Arrange
                Camoufleur.Processor mockedProcessor = mock(Camoufleur.Processor.class);
                camoufleur.processors.put(Choice.FULLNAME, mockedProcessor);

                when(mockedProcessor.process(input)).thenReturn(expected);

//                 Act
                String result = camoufleur.camoufleure(Choice.FULLNAME, input);

                // Assert
                Assertions.assertEquals(expected, result);
                verify(mockedProcessor).process(input);

            }

//
//                @BeforeEach
//                void setUp() {
//                    reset(processor);
//                    mockedProcessors.put(Choice.FULLNAME, processor);
//
//                    camoufleur = new Camoufleur(mockedProcessors);
//                }
//
//                @ParameterizedTest
//                @MethodSource("org.example.Tests#provideValidFullName")
//                void camoufleure_FullNameChoice_CallsFullNameProcessor(String input, String[] ignored, String expected) throws WrongNameException {
//                    // Arrange
//                    when(processor.process(input)).thenReturn(expected);
//                    //любишь делать моки, лючи и поведение их прописывать, а иначе они null возвращают
//                    // Act
//                    String result = camoufleur.camoufleure(Choice.FULLNAME.getValue(), input);
//
//                    // Assert
//                    Assertions.assertEquals(result, expected);
//                    verify(processor).process(input);
//                }

//                @ParameterizedTest
//                @MethodSource("org.example.Tests#provideValidEmail")
//
//                void camoufleuring_FullNameChoice_CallsFullNameProcessor() throws WrongNameException {
//                    // Arrange
//                    camoufleur.processors.put(Choice.FULLNAME, mockedProcessor);
//                    // Act
//                    String result = camoufleur.camoufleure()
//
//                    // Assert
//                }

        }


//        public String Camoufleuring(Integer inputInt, String inputLine) throws WrongNameException {
//            return processors.get(inputInt).process(inputLine);
//        }


    }
}
//
//    @Nested
//    @DisplayName("Интеграционные тесты. Тесты полных сценариев без моков. ")
//    class IntegrationTests {
//    }


//
//    private final Camoufleur camoufleur = new Camoufleur();
//
//    @Nested
//    @DisplayName("Маскировка ФИО")
//    class FullNameDisguise {
//        private final Camoufleur.Processor processor = camoufleur.processors.get(Choice.FULLNAME);
//
//        @Nested
//        @DisplayName("Успешные сценарии")
//        class Positive {
//            //            @CsvSource({ //не подходит здесь потому что его надо повторять,
////            а @MethodSource просто дает ссылку одной строчкой
////                    "'Иванова Карина Олеговна', 'Карина Олеговна И.'"
////            })
//s
//            private static Stream<Arguments> provideValidFullNamesForParser() {
//                return Stream.of(
//                        Arguments.of("Иванова Карина Олеговна", new String[]{"Иванова", "Карина", "Олеговна"}, "Карина Олеговна И.")
//                );
//            }
//
//            @ParameterizedTest
//            @MethodSource("provideValidFullNamesForParser")
//            void parse_validFullName_returnsWithoutException(String inputLine, String[] parsed) throws WrongNameException {
//                String[] result = processor.mockedParser.parse(inputLine);
//                assertArrayEquals(parsed, result);
//            }
//
//            @ParameterizedTest //честно, чуть-чуть оверкод, но ладно
//            @MethodSource("provideValidFullNamesForValidatorAndDisguiser")
//            void isValid_validFullName_returnsWithoutException(String inputLine) throws WrongNameException {
//                processor.mockedValidator.isValid(inputLine);
//            }
//
//            @ParameterizedTest //мок
//            @MethodSource("provideValidFullNamesForValidatorAndDisguiser")
//            void disguise_validFullName_returnsCorrect(String inputLine, String[] parsed, String expected) throws WrongNameException {  ////////////////
//                String result = processor.mockedDisguiser.disguise(parsed);
//                assertEquals(expected, result, "для ФИО '" + inputLine + "' ожидался результат '" + expected + "'");
//            }
//        }
//
//        @Nested
//        @DisplayName("Ошибочные сценарии")  ////////////////
//        class Negative {
//            private static Stream<Arguments> provideInvalidFullNamesForValidator() {
//                return Stream.of(
//                        Arguments.of(
//                                "", "Имя не может быть пустым"),
//                        Arguments.of(
//                                "Ивано124 Карина Олеговна", "Имя может содержать только буквы и пробелы")
//                );
//            }
//
//            @ParameterizedTest
//            @MethodSource("provideInvalidFullNamesForValidator")
//            void isValid_InvalidFullNames_throwsException(String inputLine, String expected) {
//                WrongNameException exception = assertThrows(
//                        WrongNameException.class,
//                        () -> processor.validator.isValid(inputLine)
//                );
//                Assertions.assertTrue(exception.getMessage().contains(expected));
//            }
//
//            private static Stream<Arguments> provideInvalidFullNamesForParser() {
//                return Stream.of(
//                        Arguments.of("Иванова Карина", "Ожидается 3 слова")
//                );
//            }
//
//            @ParameterizedTest
//            @MethodSource("provideInvalidFullNamesForParser")
//            void parser_emptyFullName_throwsException(String inputLine, String expected) {
//                WrongNameException exception = assertThrows(
//                        WrongNameException.class,
//                        () -> processor.parser.parse(inputLine)
//                );
//                Assertions.assertTrue(exception.getMessage().contains(expected));
//            }
//        }
//    }


///
///
///
///
///
///
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
//}
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

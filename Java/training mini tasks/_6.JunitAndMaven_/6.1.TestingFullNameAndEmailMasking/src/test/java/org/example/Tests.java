package org.example;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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

    public static Stream<Arguments> provideValidForIntegrationTests() {
        return Stream.of(
                Arguments.of(Choice.FULLNAME, "Иванова Карина Олеговна", "Карина Олеговна И."),
                Arguments.of(Choice.EMAIL, "ki225431@gmail.com", "k***@gmail.com"));
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
                Arguments.of("ki2@25431@gmail.com", "Почта должна содержать только один символ '@'")
        );
    }

    @Nested
    class ComponentsTests {
        @Nested
        class ValidatorsTests {
            FullNameValidator fullNameValidator = new FullNameValidator();

            @ParameterizedTest
            @MethodSource("org.example.Tests#provideValidFullName")
            void isValid_validFullName_returnsWithoutException(String inputLine, String[] parsed, String expected) throws WrongNameException {
                fullNameValidator.isValid(inputLine);
            }

            @ParameterizedTest
            @MethodSource("org.example.Tests#provideInvalidFullNameForValidatorAndDisguiser")
            void isValid_InvalidFullName_throwsException(String inputLine, String expected) {
                WrongNameException exception = assertThrows(
                        WrongNameException.class,
                        () -> fullNameValidator.isValid(inputLine)
                );
                Assertions.assertTrue(exception.getMessage().contains(expected));
            }

            EmailValidator emailValidator = new EmailValidator();

            @ParameterizedTest
            @MethodSource("org.example.Tests#provideValidEmail")
            void isValid_validEmail_returnsWithoutException(String inputLine) {
                emailValidator.isValid(inputLine);
            }

            @ParameterizedTest
            @MethodSource("org.example.Tests#provideInvalidEmailForValidatorAndDisguiser")
            void isValid_InvalidEmail_throwsException(String inputLine, String expected) {
                WrongEmailException exception = assertThrows(
                        WrongEmailException.class,
                        () -> emailValidator.isValid(inputLine)
                );
                Assertions.assertTrue(exception.getMessage().contains(expected));
            }
        }

        @Nested
        class ParsersTests {
            FullNameParser fullNameParser = new FullNameParser();

            @ParameterizedTest
            @MethodSource("org.example.Tests#provideValidFullName")
            void parse_validFullName_returnsWithoutException(String inputLine, String[] parsed) throws WrongNameException {
                String[] result = fullNameParser.parse(inputLine);
                assertArrayEquals(parsed, result);
            }

            @ParameterizedTest
            @MethodSource("org.example.Tests#provideInvalidFullNameForParser")
            void parse_invalidFullName_throwsException(String inputLine, String expected) throws WrongNameException {
                WrongNameException exception = assertThrows(
                        WrongNameException.class,
                        () -> fullNameParser.parse(inputLine)
                );
                Assertions.assertTrue(exception.getMessage().contains(expected));
            }

            EmailParser emailParser = new EmailParser();

            @ParameterizedTest
            @MethodSource("org.example.Tests#provideValidEmail")
            void parse_validEmail_returnsWithoutException(String inputLine, String[] parsed) throws WrongNameException {
                String[] result = emailParser.parse(inputLine);
                assertArrayEquals(parsed, result);
            }

            @ParameterizedTest
            @MethodSource("org.example.Tests#provideInvalidEmailForParser")
            void parse_invalidEmail_throwsException(String inputLine, String expected) throws WrongNameException {
                WrongEmailException exception = assertThrows(
                        WrongEmailException.class,
                        () -> emailParser.parse(inputLine)
                );
                Assertions.assertTrue(exception.getMessage().contains(expected));
            }
        }

        @Nested
        class DisguisersTests { //только хорошие случаи потому что исключений он ен выдает
            FullNameDisguiser fullNameDisguiser = new FullNameDisguiser(); //чтоб наверняка

            @ParameterizedTest
            @MethodSource("org.example.Tests#provideValidFullName")
            void disguise_validFullName_returnsWithoutException(String inputLine, String[] parsed, String expected) {
                String result = fullNameDisguiser.disguise(parsed);
                Assertions.assertEquals(result, expected);
            }


            EmailDisguiser emailDisguiser = new EmailDisguiser();

            @ParameterizedTest
            @MethodSource("org.example.Tests#provideValidEmail")
            void disguise_validEmail_returnsWithoutException(String inputLine, String[] parsed, String expected) {
                String result = emailDisguiser.disguise(parsed);
                Assertions.assertEquals(result, expected);
            }
        }
    }

    @Nested //6+4
    class UnitTests {
        @Mock
        private Validator mockedValidator;
        @Mock
        private Parser mockedParser;
        @Mock
        private Disguiser mockedDisguiser;


        //         @InjectMocks работало бы если бы это бал бы не внутренний класс
        @Nested
        class ProcessorsTests { //6
            private Camoufleur.Processor processor;

            @BeforeEach
            void setUp() {
                processor = new Camoufleur().new Processor(mockedValidator, mockedParser, mockedDisguiser);
            }

            @ParameterizedTest
//гпт не прав, по крайней мере у меня,
// process_ValidInput_CallsAllComponentsInOrder точно видит и первый сурс и второй
            @MethodSource("org.example.Tests#provideValidFullName")
            @MethodSource("org.example.Tests#provideValidEmail")
            void process_ValidInput_CallsAllComponentsInOrder(
                    String input, String[] parsed, String expected) throws WrongNameException {
                doNothing().when(mockedValidator).isValid(input);
                when(mockedParser.parse(input)).thenReturn(parsed);
                when(mockedDisguiser.disguise(parsed)).thenReturn(expected);

                String result = processor.process(input);

                Assertions.assertEquals(result, expected);

                verify(mockedValidator).isValid(input);
                verify(mockedParser).parse(input);
                verify(mockedDisguiser).disguise(parsed);
            }

            @Test
            void process_ValidatorThrowsNameException_ProcessStops() throws WrongNameException {
                doThrow(new WrongNameException("test")).when(mockedValidator).isValid(any());

                assertThrows(WrongNameException.class, () -> processor.process("any input"));
                verify(mockedParser, never()).parse(any());
                verify(mockedDisguiser, never()).disguise(any());
            }

            @Test
            void process_ParserThrowsNameException_ProcessStops() throws WrongNameException {
                doThrow(new WrongNameException("test")).when(mockedParser).parse(any());

                assertThrows(WrongNameException.class, () -> processor.process("any input"));
                verify(mockedDisguiser, never()).disguise(any());
            }

            @Test
            void process_ValidatorThrowsEmailException_ProcessStops() throws WrongNameException {
                doThrow(new WrongEmailException("test")).when(mockedValidator).isValid(any());

                assertThrows(WrongEmailException.class, () -> processor.process("any input"));
                verify(mockedParser, never()).parse(any());
                verify(mockedDisguiser, never()).disguise(any());
            }

            @Test
            void process_ParserThrowsEmailException_ProcessStops() throws WrongNameException {
                doThrow(new WrongEmailException("test")).when(mockedParser).parse(any());

                assertThrows(WrongEmailException.class, () -> processor.process("any input"));
                verify(mockedDisguiser, never()).disguise(any());
            }


        }

        //        @ExtendWith(MockitoExtension.class)//4 //уже есть
        @Nested
        class CamoufleurUnitTests {

            //проблема была в том что функция принимающая енам
            // не могла переварить интеджер и ломала работу метода
            private Camoufleur camoufleur = new Camoufleur(true);

            //валидные

            @ParameterizedTest
            @MethodSource("org.example.Tests#provideValidFullName")
            void camoufleure_FullNameChoice_CallsFullNameProcessor(String input, String[] ignored, String expected) throws WrongNameException {
                // Arrange
                Camoufleur.Processor mockedProcessor = mock(Camoufleur.Processor.class);
                camoufleur.processors.put(Choice.FULLNAME, mockedProcessor);
                when(mockedProcessor.process(input)).thenReturn(expected);
                // Act
                String result = camoufleur.camoufleure(Choice.FULLNAME, input);
                // Assert
                Assertions.assertEquals(expected, result);
                verify(mockedProcessor).process(input);
            }

            @ParameterizedTest
            @MethodSource("org.example.Tests#provideValidEmail")
            void camoufleure_EmailChoice_CallsEmailProcessor(String input, String[] ignored, String expected) throws WrongNameException {
                // Arrange
                Camoufleur.Processor mockedProcessor = mock(Camoufleur.Processor.class);
                camoufleur.processors.put(Choice.EMAIL, mockedProcessor);
                when(mockedProcessor.process(input)).thenReturn(expected);
                // Act
                String result = camoufleur.camoufleure(Choice.EMAIL, input);
                // Assert
                Assertions.assertEquals(expected, result);
                verify(mockedProcessor).process(input);
            }

            //не валидные

            //по сути что в разных исключительных ситуациях
            // сам комуфлир потом пробрасывает исключение которое берет от от выбранного процессора

            //я не проверяю правильность описания исключения,
            // а сам факт, поэтому даже быть параметризированным не надо

            //любишь делать моки, лючи и поведение их прописывать, а иначе они null возвращают
            @Test
            void camoufleure_FullNameChoice_FullNameCamoufleurThrowsException() throws WrongNameException {
                // Arrange
                Camoufleur.Processor mockedProcessor = mock(Camoufleur.Processor.class);
                camoufleur.processors.put(Choice.FULLNAME, mockedProcessor);
                when(mockedProcessor.process("any input")).thenThrow(new WrongNameException("test"));
                // Act#
                // Assert#
                assertThrows(WrongNameException.class, () -> camoufleur.camoufleure(Choice.FULLNAME, "any input"));
            }

            @Test
            void camoufleure_EmailChoice_EmailCamoufleurThrowsException() throws WrongNameException {
                Camoufleur.Processor mockedProcessor = mock(Camoufleur.Processor.class);
                camoufleur.processors.put(Choice.EMAIL, mockedProcessor);
                when(mockedProcessor.process("any input")).thenThrow(new WrongEmailException("test"));
                assertThrows(WrongEmailException.class, () -> camoufleur.camoufleure(Choice.EMAIL, "any input"));
            }
        }
//        @Nested
//        class CamoufleurRoutingTests { //по идее не нужно, потому что самого рутинга уже и нет
//            private Camoufleur camoufleur;
//            @BeforeEach
//            void setUp(){
//                camoufleur = new Camoufleur();
//            }
//
//
//        }

    }

    @Nested //10
//    @DisplayName("Интеграционные тесты. Тесты полных сценариев без моков. ")
    class IntegrationTests {
        //без моков, гпт написал что процессор как контроллер,
        // поэтому его тестить не надо, дублирование будет
        private Camoufleur camoufleur = new Camoufleur();

        @ParameterizedTest
        @MethodSource("org.example.Tests#provideValidForIntegrationTests")
        void Integration_ValidInput_CorrectOutput(Choice choice, String input, String expected) throws WrongNameException {
            String result = camoufleur.camoufleure(choice, input);
            Assertions.assertEquals(result, expected);
        }

        @ParameterizedTest
        @MethodSource("org.example.Tests#provideInvalidFullNameForValidatorAndDisguiser")
        @MethodSource("org.example.Tests#provideInvalidFullNameForParser")
        void Integration_InValidInput_ThrowsExceptionWrongName(String input, String expected) throws WrongNameException {
            WrongNameException exception = assertThrows(
                    WrongNameException.class,
                    () -> camoufleur.camoufleure(Choice.FULLNAME, input)
            );
            Assertions.assertTrue(exception.getMessage().contains(expected));
        }

        @ParameterizedTest
        @MethodSource("org.example.Tests#provideInvalidEmailForValidatorAndDisguiser")
        @MethodSource("org.example.Tests#provideInvalidEmailForParser")
            //странно
        void Integration_InValidInput_ThrowsExceptionWrongEmail(String input, String expected) throws WrongNameException {
            WrongEmailException exception = assertThrows(
                    WrongEmailException.class,
                    () -> camoufleur.camoufleure(Choice.EMAIL, input)
            );
            Assertions.assertTrue(exception.getMessage().contains(expected));
        }
    }
}


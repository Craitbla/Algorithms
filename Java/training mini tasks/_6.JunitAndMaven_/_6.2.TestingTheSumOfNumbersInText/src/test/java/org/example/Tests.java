package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


//"Метод_Условие_ОжидаемыйРезультат".
//MethodName_StateUnderTest_ExpectedBehavior
class Tests{
    Summator summator = new Summator();
    @Test
    void SummDigitInString_NumbersAtBeginning_CorrectSumm(){
        String input ="50,5 kilometers and 30.7 meters covered in 2 hours";
        String output = summator.SummDigitInString(input);
        String expected = "50,5 + 30,7 + 2 = 83,2";
//    assertEquals(expected, output, "для текста '" + input + "' ожидался результат '" + expected + "' но пулучилось '" + output + "'");
        //вобщем не поняла для чего там пояснение потому что и так все есть
        assertEquals(expected, output);
    }

    @Test
    void SummDigitInString_NumbersInMiddle_CorrectSum() {
        String input = "Wind speed was 15.3 m/s, temperature 23,5 degrees with 80% humidity";
        String output = summator.SummDigitInString(input);
        String expected = "15,3 + 23,5 + 80 = 118,8";
        assertEquals(expected, output);
    }

    @Test
    void SummDigitInString_NumbersAtEnd_CorrectSum() {
        String input = "We found 3 stones and 2,5 kilograms of sand. Total: 5.5";
        String output = summator.SummDigitInString(input);
        String expected = "3 + 2,5 + 5,5 = 11,0";
        assertEquals(expected, output);
    }

    @Test
    void SummDigitInString_TextWithoutNumbers_Zero() {
        String input = "Hello, how are you? What's new?";
        String output = summator.SummDigitInString(input);
        String expected = "0";
        assertEquals(expected, output);
    }

    @Test
    void SummDigitInString_OnlyNumbersWithDots_CorrectSum() {
        String input = "Prices: 10.5, 20.3 and 30.2 dollars";
        String output = summator.SummDigitInString(input);
        String expected = "10,5 + 20,3 + 30,2 = 61,0";
        assertEquals(expected, output);
    }

    @Test
    void SummDigitInString_OnlyNumbersWithCommas_CorrectSum() {
        String input = "Weight: 5,5 kg, volume: 10,5 liters, length: 3,2 meters";
        String output = summator.SummDigitInString(input);
        String expected = "5,5 + 10,5 + 3,2 = 19,2";
        assertEquals(expected, output);
    }

    @Test
    void SummDigitInString_MixedNumberFormats_CorrectSum() {
        String input = "Morning temperature 15.5°C, afternoon 23,5°C, evening 18.2°C";
        String output = summator.SummDigitInString(input);
        String expected = "15,5 + 23,5 + 18,2 = 57,2";
        assertEquals(expected, output);
    }
    @Test
    void SummDigitInString_SingleNumber_CorrectSum() {
        String input = "Today temperature is 25.5 degrees";
        String output = summator.SummDigitInString(input);
        String expected = "25,5 = 25,5";
        assertEquals(expected, output);
    }

    @Test
    void SummDigitInString_EmptyString_Zero() {
        String input = "";
        String output = summator.SummDigitInString(input);
        String expected = "0";
        assertEquals(expected, output);
    }

    @Test
    void SummDigitInString_MathematicalExpressions_CorrectSum() {
        String input = "Calculation result: 2.5 + 3.7 = 6.2, and 10/2 = 5";
        String output = summator.SummDigitInString(input);
        String expected = "2,5 + 3,7 + 6,2 + 10 + 2 + 5 = 29,4";
        assertEquals(expected, output);
    }

    @Test
    void SummDigitInString_LargeNumbers_CorrectSum() {
        String input = "Population: 1,500,000 in 2023 with growth rate 2.5%";
        String output = summator.SummDigitInString(input);
        String expected = "1,500 + 000 + 2023 + 2,5 = 2027,0";
        assertEquals(expected, output);
    }

    @Test
    void SummDigitInString_NegativeNumbers_CorrectSum() {
        String input = "Temperature dropped from 15.5 to -3.2 degrees";
        String output = summator.SummDigitInString(input);
        String expected = "15,5 + 3,2 = 18,7";
        assertEquals(expected, output);
    }

}
import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Comparator;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        TextProcessor delSpaces = t-> t.replace(" ", "");
        System.out.println(delSpaces.processText(" Hello worl d!  "));
        System.out.println(delSpaces.reverse(" Hello worl d!  "));
        System.out.println(TextProcessor.createDefault().processText(" Hello worl d!  "));

        TextValidator numVal = t-> t.chars().anyMatch(Character::isDigit);
        System.out.println(numVal.isValid("sdfasd")); //false
        System.out.println(numVal.isValid("sdfas1"));//true
        System.out.println(TextValidator.lengthValidator(3).isValid("s1"));//false
        System.out.println(TextValidator.lengthValidator(3).isValid("sdfas1")); //true

        ArrayList<String> arr = new ArrayList<>(Arrays.asList("Java8", "123", "hello world", "functional")) ;

        System.out.println("\nреверс");
        arr.stream().map(delSpaces::reverse).forEach(t->System.out.println(t));
        System.out.println("\nс цифрами и минимум из 5 символов");
        arr.stream().filter(numVal.and(TextValidator.lengthValidator(5))::isValid).forEach(t->System.out.println(t));


        System.out.println(delSpaces.description());
        System.out.println(numVal.description());

        TextService service = new TextService(delSpaces, numVal);
        System.out.println(service.toString());
    }
}

@FunctionalInterface
interface TextProcessor {
    String processText(String text);

    default String reverse(String text) {
        StringBuilder str = new StringBuilder(text);
        return str.reverse().toString();
    }

    static TextProcessor createDefault() {
        return String::toUpperCase;
    }
    default String description(){
        return "Processor";
    }
}

@FunctionalInterface
interface TextValidator {
    boolean isValid(String text);
    default TextValidator and(TextValidator other) {
        return t -> (this.isValid(t) && other.isValid(t));

    }
    static TextValidator lengthValidator(int minLength) {
        return t->(t.length() >= minLength ? true : false);
    }
    default String description(){
        return "Validator";
    }

}

class  TextService{
    private TextProcessor processor;
    private TextValidator validator;

    public TextService(TextProcessor proc,TextValidator val){
        this.processor = proc;
        this.validator = val;
    }

    public List<String> processValid(List<String> input){
        return input.stream().filter(validator::isValid).map(processor::processText).collect(Collectors.toList());
    }

    @Override
    public String toString(){
        return "TextService:" + getClass().getSimpleName()+", "  + processor.description()+", " + validator.description();
    }

}


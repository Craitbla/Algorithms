// import java.util.function;

public class Program {
    public static void main(String[] args) {
        System.out.println(TextProcessor.createDefault().processText("waaa"));
        // TextValidator tv1 = t->

        // };

        System.out.println(TextProcessor.createDefault().processText("waaa"));
    }
}

@FunctionalInterface
interface TextProcessor { // интерфейс-функция
    String processText(String text);

    default String reverse(String text) {
        StringBuilder str = new StringBuilder(text);
        return str.reverse().toString();
    }

    static TextProcessor createDefault() {
        // class UpperTextProcessor implements TextProcessor {
        // @Override
        // public String processText(String text) {
        // return text.toUpperCase();
        // }

        // }
        // TextProcessor upper = new UpperTextProcessor();
        // TextProcessor upper = t -> t.toUpperCase();
        return String::toUpperCase;
    }

    String toString();

    boolean equals(Object obj);
}

@FunctionalInterface
interface TextValidator {
    boolean isValid(String text);

    default TextValidator and(TextValidator other) {
        // class TextValidatorWithAnd implements TextValidator {
        // @Override
        // public boolean isValid(String text) {
        // return this.isValid(text) && other.isValid(text);
        // }
        // }
        // TextValidator tVal = new TextValidatorWithAnd();
        // tVal.and(other);
        // return tVal;
        return t -> (this.isValid(t) && other.isValid(t));

    }

    static TextValidator lengthValidator(int minLength) {
        class TextValidatorLen implements TextValidator {
            @Override
            public boolean isValid(String text) {
                return (text.length() <= minLength ? true : false);
            }
        }
        return new TextValidatorLen();
    }
}
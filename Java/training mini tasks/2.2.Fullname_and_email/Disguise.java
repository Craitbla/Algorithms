public class Disguise {

    public static void main(String[] args) {

        System.out.println("Вывод: Введите “1” для обработки ФИО и “2” для обработки email");
        try (Scanner scanner = new Scanner(System.in)) {
            int inputInt = 0;
            inputInt = scanner.getInt();
            StringBuilder sb;
            sb = scanner.getLine();
            switch (inputInt) {
                case 1:
                    disguiseFullName(sb);
                    break;
                case 2:
                    disguiseEmail(sb);
                    break;
                default:
                    System.out.println("Только 1 или 2");
            }

        } catch (Exception e) {
            System.out.println("Попробуйте еще раз");
        }

    }

    public static void disguiseFullName(StringBuilder sb) {

    }

    public static void disguiseEmail(StringBuilder sb) {

    }

}

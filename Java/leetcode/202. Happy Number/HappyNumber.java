//Поиск зацикленности? -> 2 указателя!!!

//разделение на цифры математически

//При любом n >= 1 последовательность либо достигнет 1, либо войдёт в цикл 4 → 16 → 37 → 58 → 89 → 145 → 42 → 20 → 4...
//Это доказано для всех чисел в ограничениях задачи

public class HappyNumber {
    public static void main(String[] args) {
        System.out.println(isHappy(19));
    }

    public static boolean isHappy(int n) {
        int curSummSlow = n;
        int curSummFast = n;

        while (curSummSlow != 1 && curSummFast != 1) {
            curSummSlow = SummNums(curSummSlow);
            curSummFast = SummNums(SummNums(curSummFast));
            if (curSummFast == curSummSlow) {
                return false;
            }
        }
        return true;

    }

    public static int SummNums(int num) {
        int summ = 0;
        int tmpDigit = 0;
        while (num > 0) {
            tmpDigit = num % 10;
            summ += Math.pow(tmpDigit, 2);
            num /= 10;
        }
        // String tmpStr = Integer.toString(num); /////////не эффективно
        // num = 0;
        // for (int i = 0; i < (tmpStr.length()); i++) {
        // num += Math.pow(Integer.parseInt(String.valueOf(tmpStr.charAt(i))), 2);
        // }
        return summ;
    }
}

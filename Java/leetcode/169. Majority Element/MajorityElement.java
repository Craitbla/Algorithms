//алгоритм Бойера — Мура
//обновление cur приоритетнее изменений счетчика
public class MajorityElement {
    public static void main(String[] args) {
        // int[] nums = { 2, 5, 4, 5, 8, 3, 5, 5, 5 };
        int[] nums = { 3, 2, 3 };
        int counter = 0;
        int cur = nums[0];
        for (int i : nums) {
            if (counter == 0) {
                cur = i;
            }
            if (i == cur) {
                counter++;
            } else {
                counter--;
            }

        }
    }
}

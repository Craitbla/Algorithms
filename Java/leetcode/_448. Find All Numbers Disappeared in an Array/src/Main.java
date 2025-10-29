import java.util.ArrayList;
import java.util.List;

//лишняя мар'а лучше чем поиск значения в одной мар'е
//забыть про обрачение по индексу так [i]
public class Main {
    public static void main(String[] args) {
        Solution sol = new Solution();
        int nums[] = {};
        List<Integer> res = sol.findDisappearedNumbers(nums);
        for (Integer el:
             res) {
            System.out.println(el);
        }
    }
} //

//Пример 1:
//
//        Ввод: числа = [4,3,2,7,8,2,3,1]
//        Вывод: [5,6]
//        Пример 2:
//
//        Ввод: числа = [1,1]
//        Вывод: [2]
class Solution {
    public List<Integer> findDisappearedNumbers(int[] nums) {
        List<Boolean> checklist = new ArrayList<>(nums.length);
        //нужен, нро другой
        for (int i = 0; i < nums.length; i++) {
            checklist.add(false);
        }
        for (int i = 0; i < nums.length; i++) {
            checklist.set(nums[i]-1, true);
        }
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if(checklist.get(i)==false){
                res.add(i+1);
            }
        }

    return res;
    }

}

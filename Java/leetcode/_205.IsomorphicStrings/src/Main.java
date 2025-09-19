import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

//лишняя мар'а лучше чем поиск значения в одной мар'е
//забыть про обрачение по индексу так [i]
public class Main {
    public static void main(String[] args) {
        Solution sol = new Solution();

        System.out.println(sol.isIsomorphic("aada", "ddaf"));
    }
}

//рассмотрение массива(ArrayList) как map, где или ключ или значение это int
//Ошибка в вашем коде заключается в использовании метода add() вместо set()
//Однако для лучшей производительности рекомендуется использовать обычные массивы вместо ArrayList
class Solution {
    public boolean isIsomorphic(String s, String t) {
        ArrayList<Integer> sMap = new ArrayList<Integer>(Collections.nCopies(128, -1));
        ArrayList<Integer> tMap = new ArrayList<Integer>(Collections.nCopies(128, -1));
        int sChar = 0, tChar = 0;
        for (int i = 0; i < s.length(); i++) {
            sChar = s.charAt(i);
            tChar = t.charAt(i);
            if (sMap.get(sChar) == -1 && tMap.get(tChar) == -1) {
                sMap.set(sChar, tChar);
                tMap.set(tChar, sChar);
            } else if(sMap.get(sChar)!=tChar || tMap.get(tChar)!=sChar){
                return false;
            }
        }
        return true;
    }

}
